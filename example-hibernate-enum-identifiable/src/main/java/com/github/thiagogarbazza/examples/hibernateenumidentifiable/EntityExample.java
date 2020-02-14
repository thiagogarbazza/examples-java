package com.github.thiagogarbazza.examples.hibernateenumidentifiable;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Entity
@Builder
@Table(name = "tbl_entity_example")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class EntityExample {

  @Column(name = "description")
  private String description;
  @Type(type = EnumIdentifiable.TYPE_NAME)
  @Column(name = "enum_identifiable_integer", nullable = false, columnDefinition = "TINYINT")
  private EnumIdentifiableInteger enumIdentifiableInteger;
  @Type(type = EnumIdentifiable.TYPE_NAME)
  @Column(name = "enum_identifiable_integer2")
  private EnumIdentifiableInteger enumIdentifiableInteger2;
  @Type(type = EnumIdentifiable.TYPE_NAME)
  @Column(name = "enum_identifiable_string", nullable = false, columnDefinition = "CHAR(2)")
  private EnumIdentifiableString enumIdentifiableString;
  @Type(type = EnumIdentifiable.TYPE_NAME)
  @Column(name = "enum_identifiable_string2")
  private EnumIdentifiableString enumIdentifiableString2;
  @Id
  @GeneratedValue
  @Column(name = "id", nullable = false, unique = true, updatable = false, columnDefinition = "CHAR(32)")
  private UUID id;
}
