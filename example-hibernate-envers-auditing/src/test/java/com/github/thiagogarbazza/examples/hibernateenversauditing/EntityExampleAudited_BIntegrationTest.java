package com.github.thiagogarbazza.examples.hibernateenversauditing;

import com.github.thiagogarbazza.examples.hibernateenversauditing.config.CommonEntityDAO;
import com.github.thiagogarbazza.examples.hibernateenversauditing.config.ResetDatabaseService;
import com.github.thiagogarbazza.examples.hibernateenversauditing.config.RevisionInformation;
import com.github.thiagogarbazza.examples.hibernateenversauditing.config.RevisionInformationService;
import org.apache.commons.collections4.IterableUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EntityExampleAudited_BIntegrationTest {

  private CommonEntityDAO commonEntityDAO;
  private RevisionInformationService revisionInformationService;

  @BeforeEach
  void beforeEach() {
    this.commonEntityDAO = CommonEntityDAO.getInstance();
    this.revisionInformationService = RevisionInformationService.getInstance();
    ResetDatabaseService.getInstance().reset();
  }

  @Test
  void vefiryAuditedOnCreate() {
    final EntityExampleAudited_A created_a_1 = this.commonEntityDAO.save(EntityExampleAudited_A.builder()
      .code("any-code_a_1")
      .description("any-code_a_1 description 01")
      .build());

    final EntityExampleAudited_B created_b_1 = this.commonEntityDAO.save(EntityExampleAudited_B.builder()
      .code("any-code_b_1")
      .description("any-code_b_1 description 01")
      .simpleEntityAuditedA(created_a_1)
      .extensions(Arrays.asList("json", "txt", "xml"))
      .build());

    assertEquals(1L, this.revisionInformationService.count(created_b_1.getClass(), created_b_1.getId()));
    final Collection<RevisionInformation> revisions_b_1 = revisionInformationService.revisions(created_b_1.getClass(), created_b_1.getId());
    assertEquals(1, revisions_b_1.size());
    final EntityExampleAudited_B created_b_1_rev_1 = this.revisionInformationService.revision(created_b_1.getClass(), created_b_1.getId(),
      revisions_b_1.iterator().next().getId());
    Assertions.assertEquals(created_b_1.getId(), created_b_1_rev_1.getId());
    Assertions.assertEquals(created_b_1.getCode(), created_b_1_rev_1.getCode());
    Assertions.assertEquals(created_b_1.getDescription(), created_b_1_rev_1.getDescription());
    Assertions.assertEquals(created_b_1.getSimpleEntityAuditedA().getId(), created_b_1_rev_1.getSimpleEntityAuditedA().getId());
    Assertions.assertEquals(created_b_1.getExtensions().size(), created_b_1_rev_1.getExtensions().size());
    Assertions.assertIterableEquals(new TreeSet<>(created_b_1.getExtensions()), new TreeSet<>(created_b_1_rev_1.getExtensions()));
  }

  @Test
  @Disabled("Aguardando busca da revisão trazer corretamente a coleção de extenções")
  void vefiryAuditedOnUpdate() {
    final EntityExampleAudited_A created_a_1 = this.commonEntityDAO.save(EntityExampleAudited_A.builder()
      .code("any-code_a_1")
      .description("any-code_a_1 description 01")
      .build());

    final EntityExampleAudited_A created_a_2 = this.commonEntityDAO.save(EntityExampleAudited_A.builder()
      .code("any-code_a_2_0")
      .description("any-code_a_2_0 description 02")
      .build());

    final EntityExampleAudited_B created_b_1 = this.commonEntityDAO.save(EntityExampleAudited_B.builder()
      .code("any-code_b_1_0")
      .description("any-code_b_1_0 description 01")
      .simpleEntityAuditedA(created_a_1)
      .extensions(Arrays.asList("json", "txt", "xml"))
      .build());

    final EntityExampleAudited_B created_b_2 = this.commonEntityDAO.findById(created_b_1.getId(), EntityExampleAudited_B.class);
    created_b_2.setCode("any-code_b_1_1");
    created_b_2.setSimpleEntityAuditedA(created_a_2);
    created_b_2.setExtensions(Arrays.asList("txt", "xml"));
    this.commonEntityDAO.update(created_b_2);

    assertEquals(2L, this.revisionInformationService.count(created_b_1.getClass(), created_b_1.getId()));
    final Collection<RevisionInformation> revisions_b_1 = revisionInformationService.revisions(created_b_1.getClass(), created_b_1.getId());
    assertEquals(2, revisions_b_1.size());

    final EntityExampleAudited_B created_b_1_rev_1 = this.revisionInformationService.revision(created_b_1.getClass(), created_b_1.getId(),
      IterableUtils.get(revisions_b_1, 0).getId());
    Assertions.assertEquals(created_b_1.getId(), created_b_1_rev_1.getId());
    Assertions.assertEquals(created_b_1.getCode(), created_b_1_rev_1.getCode());
    Assertions.assertEquals(created_b_1.getDescription(), created_b_1_rev_1.getDescription());
    Assertions.assertEquals(created_b_1.getSimpleEntityAuditedA().getId(), created_b_1_rev_1.getSimpleEntityAuditedA().getId());
    Assertions.assertEquals(created_b_1.getExtensions().size(), created_b_1_rev_1.getExtensions().size());
    Assertions.assertIterableEquals(new TreeSet<>(created_b_1.getExtensions()), new TreeSet<>(created_b_1_rev_1.getExtensions()));

    final EntityExampleAudited_B created_b_1_rev_2 = this.revisionInformationService.revision(created_b_2.getClass(), created_b_2.getId(),
      IterableUtils.get(revisions_b_1, 1).getId());
    Assertions.assertEquals(created_b_2.getId(), created_b_1_rev_2.getId());
    Assertions.assertEquals(created_b_2.getCode(), created_b_1_rev_2.getCode());
    Assertions.assertEquals(created_b_2.getDescription(), created_b_1_rev_2.getDescription());
    Assertions.assertEquals(created_b_2.getSimpleEntityAuditedA().getId(), created_b_1_rev_2.getSimpleEntityAuditedA().getId());
    Assertions.assertEquals(created_b_2.getExtensions().size(), created_b_1_rev_2.getExtensions().size());
    Assertions.assertIterableEquals(new TreeSet<>(created_b_2.getExtensions()), new TreeSet<>(created_b_1_rev_2.getExtensions()));
  }

  @Test
  @Disabled("Aguardando busca da revisão trazer corretamente a coleção de extenções")
  void vefiryAuditedOnDelete() {
    final EntityExampleAudited_A created_a_1 = this.commonEntityDAO.save(EntityExampleAudited_A.builder()
      .code("any-code_a_1")
      .description("any-code_a_1 description 01")
      .build());

    final EntityExampleAudited_A created_a_2 = this.commonEntityDAO.save(EntityExampleAudited_A.builder()
      .code("any-code_a_2_0")
      .description("any-code_a_2_0 description 02")
      .build());

    final EntityExampleAudited_B created_b_1 = this.commonEntityDAO.save(EntityExampleAudited_B.builder()
      .code("any-code_b_1_0")
      .description("any-code_b_1_0 description 01")
      .simpleEntityAuditedA(created_a_1)
      .extensions(Arrays.asList("json", "txt", "xml"))
      .build());

    final EntityExampleAudited_B created_b_2 = this.commonEntityDAO.findById(created_b_1.getId(), EntityExampleAudited_B.class);
    created_b_2.setCode("any-code_b_1_1");
    created_b_2.setSimpleEntityAuditedA(created_a_2);
    created_b_2.setExtensions(Arrays.asList("txt", "xml"));
    this.commonEntityDAO.update(created_b_2);

    this.commonEntityDAO.delete(created_b_2);

    assertEquals(3L, this.revisionInformationService.count(created_b_1.getClass(), created_b_1.getId()));
    final Collection<RevisionInformation> revisions_b_1 = revisionInformationService.revisions(created_b_1.getClass(), created_b_1.getId());
    assertEquals(3, revisions_b_1.size());

    final EntityExampleAudited_B created_b_1_rev_1 = this.revisionInformationService.revision(created_b_1.getClass(), created_b_1.getId(),
      IterableUtils.get(revisions_b_1, 0).getId());
    Assertions.assertEquals(created_b_1.getId(), created_b_1_rev_1.getId());
    Assertions.assertEquals(created_b_1.getCode(), created_b_1_rev_1.getCode());
    Assertions.assertEquals(created_b_1.getDescription(), created_b_1_rev_1.getDescription());
    Assertions.assertEquals(created_b_1.getSimpleEntityAuditedA().getId(), created_b_1_rev_1.getSimpleEntityAuditedA().getId());
    Assertions.assertEquals(created_b_1.getExtensions().size(), created_b_1_rev_1.getExtensions().size());
    Assertions.assertIterableEquals(new TreeSet<>(created_b_1.getExtensions()), new TreeSet<>(created_b_1_rev_1.getExtensions()));

    final EntityExampleAudited_B created_b_1_rev_2 = this.revisionInformationService.revision(created_b_2.getClass(), created_b_2.getId(),
      IterableUtils.get(revisions_b_1, 1).getId());
    Assertions.assertEquals(created_b_2.getId(), created_b_1_rev_2.getId());
    Assertions.assertEquals(created_b_2.getCode(), created_b_1_rev_2.getCode());
    Assertions.assertEquals(created_b_2.getDescription(), created_b_1_rev_2.getDescription());
    Assertions.assertEquals(created_b_2.getSimpleEntityAuditedA().getId(), created_b_1_rev_2.getSimpleEntityAuditedA().getId());
    Assertions.assertEquals(created_b_2.getExtensions().size(), created_b_1_rev_2.getExtensions().size());
    Assertions.assertIterableEquals(new TreeSet<>(created_b_2.getExtensions()), new TreeSet<>(created_b_1_rev_2.getExtensions()));

    final EntityExampleAudited_B created_b_1_rev_3 = this.revisionInformationService.revision(created_b_2.getClass(), created_b_2.getId(),
      IterableUtils.get(revisions_b_1, 2).getId());
    Assertions.assertEquals(created_b_2.getId(), created_b_1_rev_3.getId());
    Assertions.assertNull(created_b_1_rev_2.getCode());
    Assertions.assertNull(created_b_1_rev_2.getDescription());
    Assertions.assertNull(created_b_1_rev_2.getSimpleEntityAuditedA());
    Assertions.assertEquals(created_b_2.getExtensions().size(), created_b_1_rev_2.getExtensions().size());
    Assertions.assertIterableEquals(new TreeSet<>(), new TreeSet<>(created_b_1_rev_2.getExtensions()));
  }
}
