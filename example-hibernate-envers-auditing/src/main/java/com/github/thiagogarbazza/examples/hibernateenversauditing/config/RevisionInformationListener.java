package com.github.thiagogarbazza.examples.hibernateenversauditing.config;

import org.hibernate.envers.RevisionListener;

class RevisionInformationListener implements RevisionListener {

  @Override
  public void newRevision(final Object revisionEntity) {
    if (revisionEntity instanceof RevisionInformation) {
      final RevisionInformation revisionInformation = (RevisionInformation) revisionEntity;

      revisionInformation.setUserName("thiagogarbazza");
    }
  }
}
