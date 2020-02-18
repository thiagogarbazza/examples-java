package com.github.thiagogarbazza.examples.hibernateenversauditing.config;

import lombok.extern.apachecommons.CommonsLog;
import org.hibernate.CacheMode;
import org.hibernate.Session;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.query.AuditEntity;

import java.util.Collection;
import javax.persistence.EntityManager;

@CommonsLog
public class RevisionInformationService {

  public <ID> Long count(final Class clazz, final ID id) {
    return (Long) getAuditReader().createQuery()
      .forRevisionsOfEntity(clazz, false, true)
      .addProjection(AuditEntity.id().count())
      .add(AuditEntity.id().eq(id))
      .getSingleResult();
  }

  public <ID, T> T revision(final Class<T> clazz, final ID id, final long revisionId) {
    final Object[] singleResult = (Object[]) getAuditReader().createQuery()
      .forRevisionsOfEntity(clazz, false, true)
      .add(AuditEntity.id().eq(id))
      .add(AuditEntity.revisionNumber().eq(revisionId))
      .getSingleResult();

    //noinspection unchecked
    return (T) singleResult[0];
  }

  public <ID> Collection<RevisionInformation> revisions(final Class clazz, final ID id) {
    //noinspection unchecked
    final Collection<Long> revisions = (Collection<Long>) getAuditReader().createQuery()
      .forRevisionsOfEntity(clazz, false, true)
      .addProjection(AuditEntity.revisionNumber())
      .add(AuditEntity.id().eq(id))
      .addOrder(AuditEntity.revisionNumber().asc())
      .getResultList();

    final String queryString = "SELECT rev FROM RevisionInformation rev WHERE rev.id IN" + revisions.toString().replace("[", "(").replace("]", ")");
    return CommonEntityDAO.getInstance().find(queryString, RevisionInformation.class);
  }

  public static RevisionInformationService getInstance() {
    return new RevisionInformationService();
  }

  private AuditReader getAuditReader() {
    final Session session = HibernateUtil.getSessionFactory().openSession();
    session.setCacheMode(CacheMode.IGNORE);
    return AuditReaderFactory.get(session);
  }
}
