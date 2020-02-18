package com.github.thiagogarbazza.examples.hibernateenversauditing;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.envers.Audited;

import java.util.Collection;
import java.util.UUID;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import static lombok.AccessLevel.PACKAGE;
import static lombok.AccessLevel.PRIVATE;

@Entity
@Getter
@Setter
@Audited
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor(access = PACKAGE)
@AllArgsConstructor(access = PRIVATE)
@Table(name = "tbl_entity_example_audited_b", indexes = {
  @Index(name = "idx_tbl_entity_example_audited_b_code", columnList = "code", unique = true)
})
public class EntityExampleAudited_B {

  @Column(name = "code", nullable = false, length = 50)
  private String code;
  @Column(name = "description", nullable = false, length = 50)
  private String description;
  @Id
  @GeneratedValue
  @Column(name = "id", nullable = false, unique = true, updatable = false, columnDefinition = "CHAR(32)")
  private UUID id;

  @ElementCollection(fetch = FetchType.EAGER)
  @CollectionTable(name="tbl_entity_example_audited_b_extensions", joinColumns=@JoinColumn(name="tbl_entity_example_audited_b_id"))
  @Column(name="extension")
  public Collection<String> extensions;

  @ManyToOne
  @JoinColumn(name = "tbl_entity_example_audited_a_id", nullable = false)
  private EntityExampleAudited_A simpleEntityAuditedA;
}
