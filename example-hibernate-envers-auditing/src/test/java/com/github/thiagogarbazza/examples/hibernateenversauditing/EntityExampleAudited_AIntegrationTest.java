package com.github.thiagogarbazza.examples.hibernateenversauditing;

import com.github.thiagogarbazza.examples.hibernateenversauditing.config.CommonEntityDAO;
import com.github.thiagogarbazza.examples.hibernateenversauditing.config.ResetDatabaseService;
import com.github.thiagogarbazza.examples.hibernateenversauditing.config.RevisionInformation;
import com.github.thiagogarbazza.examples.hibernateenversauditing.config.RevisionInformationService;
import org.apache.commons.collections4.IterableUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EntityExampleAudited_AIntegrationTest {

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

    final EntityExampleAudited_A created_a_2 = this.commonEntityDAO.save(EntityExampleAudited_A.builder()
      .code("any-code_a_2")
      .description("any-code_a_2 description 02")
      .build());

    assertEquals(1L, this.revisionInformationService.count(created_a_1.getClass(), created_a_1.getId()));
    final Collection<RevisionInformation> revisions_a_1 = revisionInformationService.revisions(created_a_1.getClass(), created_a_1.getId());
    assertEquals(1, revisions_a_1.size());
    final EntityExampleAudited_A created_a_1_rev_1 = this.revisionInformationService.revision(created_a_1.getClass(), created_a_1.getId(),
      revisions_a_1.iterator().next().getId());
    Assertions.assertEquals(created_a_1.getId(), created_a_1_rev_1.getId());
    Assertions.assertEquals(created_a_1.getCode(), created_a_1_rev_1.getCode());
    Assertions.assertEquals(created_a_1.getDescription(), created_a_1_rev_1.getDescription());

    assertEquals(1L, this.revisionInformationService.count(created_a_2.getClass(), created_a_2.getId()));
    final Collection<RevisionInformation> revisions_a_2 = revisionInformationService.revisions(created_a_2.getClass(), created_a_2.getId());
    assertEquals(1, revisions_a_2.size());
    final EntityExampleAudited_A created_a_2_rev_1 = this.revisionInformationService.revision(created_a_2.getClass(), created_a_2.getId(),
      revisions_a_2.iterator().next().getId());
    Assertions.assertEquals(created_a_2.getId(), created_a_2_rev_1.getId());
    Assertions.assertEquals(created_a_2.getCode(), created_a_2_rev_1.getCode());
    Assertions.assertEquals(created_a_2.getDescription(), created_a_2_rev_1.getDescription());
  }

  @Test
  void vefiryAuditedOnDelete() {
    final EntityExampleAudited_A created_a_1 = this.commonEntityDAO.save(EntityExampleAudited_A.builder()
      .code("any-code_a_1")
      .description("any-code_a_1 description 01")
      .build());

    final EntityExampleAudited_A created_a_2_0 = this.commonEntityDAO.save(EntityExampleAudited_A.builder()
      .code("any-code_a_2_0")
      .description("any-code_a_2_0 description 02")
      .build());

    final EntityExampleAudited_A created_a_2_1 = this.commonEntityDAO.findById(created_a_2_0.getId(), EntityExampleAudited_A.class);
    created_a_2_1.setCode("any-code_a_2_1");
    this.commonEntityDAO.update(created_a_2_1);

    this.commonEntityDAO.delete(created_a_2_1);

    assertEquals(1L, this.revisionInformationService.count(created_a_1.getClass(), created_a_1.getId()));
    final Collection<RevisionInformation> revisions_a_1 = revisionInformationService.revisions(created_a_1.getClass(), created_a_1.getId());
    assertEquals(1, revisions_a_1.size());
    final EntityExampleAudited_A created_a_1_rev_1 = this.revisionInformationService.revision(created_a_1.getClass(), created_a_1.getId(),
      revisions_a_1.iterator().next().getId());
    Assertions.assertEquals(created_a_1.getId(), created_a_1_rev_1.getId());
    Assertions.assertEquals(created_a_1.getCode(), created_a_1_rev_1.getCode());
    Assertions.assertEquals(created_a_1.getDescription(), created_a_1_rev_1.getDescription());

    assertEquals(3L, this.revisionInformationService.count(created_a_2_0.getClass(), created_a_2_0.getId()));
    final Collection<RevisionInformation> revisions_a_2 = revisionInformationService.revisions(created_a_2_0.getClass(), created_a_2_0.getId());
    assertEquals(3, revisions_a_2.size());

    final EntityExampleAudited_A created_a_2_rev_1 = this.revisionInformationService.revision(created_a_2_0.getClass(), created_a_2_0.getId(),
      IterableUtils.get(revisions_a_2, 0).getId());
    Assertions.assertEquals(created_a_2_0.getId(), created_a_2_rev_1.getId());
    Assertions.assertEquals(created_a_2_0.getCode(), created_a_2_rev_1.getCode());
    Assertions.assertEquals(created_a_2_0.getDescription(), created_a_2_rev_1.getDescription());

    final EntityExampleAudited_A created_a_2_rev_2 = this.revisionInformationService.revision(created_a_2_0.getClass(), created_a_2_0.getId(),
      IterableUtils.get(revisions_a_2, 1).getId());
    Assertions.assertEquals(created_a_2_1.getId(), created_a_2_rev_2.getId());
    Assertions.assertEquals(created_a_2_1.getCode(), created_a_2_rev_2.getCode());
    Assertions.assertEquals(created_a_2_1.getDescription(), created_a_2_rev_2.getDescription());

    final EntityExampleAudited_A created_a_2_rev_3 = this.revisionInformationService.revision(created_a_2_0.getClass(), created_a_2_0.getId(),
      IterableUtils.get(revisions_a_2, 2).getId());
    Assertions.assertEquals(created_a_2_1.getId(), created_a_2_rev_3.getId());
    Assertions.assertNull(created_a_2_rev_3.getCode());
    Assertions.assertNull(created_a_2_rev_3.getDescription());
  }

  @Test
  void vefiryAuditedOnUpdate() {
    final EntityExampleAudited_A created_a_1 = this.commonEntityDAO.save(EntityExampleAudited_A.builder()
      .code("any-code_a_1")
      .description("any-code_a_1 description 01")
      .build());

    final EntityExampleAudited_A created_a_2_0 = this.commonEntityDAO.save(EntityExampleAudited_A.builder()
      .code("any-code_a_2_0")
      .description("any-code_a_2_0 description 02")
      .build());

    final EntityExampleAudited_A created_a_2_1 = this.commonEntityDAO.findById(created_a_2_0.getId(), EntityExampleAudited_A.class);
    created_a_2_1.setCode("any-code_a_2_1");
    this.commonEntityDAO.update(created_a_2_1);

    assertEquals(1L, this.revisionInformationService.count(created_a_1.getClass(), created_a_1.getId()));
    final Collection<RevisionInformation> revisions_a_1 = revisionInformationService.revisions(created_a_1.getClass(), created_a_1.getId());
    assertEquals(1, revisions_a_1.size());
    final EntityExampleAudited_A created_a_1_rev_1 = this.revisionInformationService.revision(created_a_1.getClass(), created_a_1.getId(),
      revisions_a_1.iterator().next().getId());
    Assertions.assertEquals(created_a_1.getId(), created_a_1_rev_1.getId());
    Assertions.assertEquals(created_a_1.getCode(), created_a_1_rev_1.getCode());
    Assertions.assertEquals(created_a_1.getDescription(), created_a_1_rev_1.getDescription());

    assertEquals(2L, this.revisionInformationService.count(created_a_2_0.getClass(), created_a_2_0.getId()));
    final Collection<RevisionInformation> revisions_a_2 = revisionInformationService.revisions(created_a_2_0.getClass(), created_a_2_0.getId());
    assertEquals(2, revisions_a_2.size());

    final EntityExampleAudited_A created_a_2_rev_1 = this.revisionInformationService.revision(created_a_2_0.getClass(), created_a_2_0.getId(),
      IterableUtils.get(revisions_a_2, 0).getId());
    Assertions.assertEquals(created_a_2_0.getId(), created_a_2_rev_1.getId());
    Assertions.assertEquals(created_a_2_0.getCode(), created_a_2_rev_1.getCode());
    Assertions.assertEquals(created_a_2_0.getDescription(), created_a_2_rev_1.getDescription());

    final EntityExampleAudited_A created_a_2_rev_2 = this.revisionInformationService.revision(created_a_2_0.getClass(), created_a_2_0.getId(),
      IterableUtils.get(revisions_a_2, 1).getId());
    Assertions.assertEquals(created_a_2_1.getId(), created_a_2_rev_2.getId());
    Assertions.assertEquals(created_a_2_1.getCode(), created_a_2_rev_2.getCode());
    Assertions.assertEquals(created_a_2_1.getDescription(), created_a_2_rev_2.getDescription());
  }
}
