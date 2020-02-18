package com.github.thiagogarbazza.examples.hibernateenversauditing.config;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "tbl_revision_information")
@RevisionEntity(RevisionInformationListener.class)
public class RevisionInformation {

  @Id
  @RevisionNumber
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @RevisionTimestamp
  @Column(name = "timestamp")
  private long timestamp;

  @Column(name = "user_name")
  private String userName;
}
