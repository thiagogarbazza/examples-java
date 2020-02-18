package com.github.thiagogarbazza.examples.hibernateenversauditing.config;

import org.hibernate.CacheMode;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.util.Collection;
import java.util.UUID;

public class CommonEntityDAO {

  public <T> Collection<T> find(final String queryString, final Class<T> clazz) {
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      session.setCacheMode(CacheMode.IGNORE);
      final Query sqlQuery = session.createQuery(queryString);

      return sqlQuery.getResultList();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public <T> T findById(final UUID id, final Class<T> clazz) {
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      session.setCacheMode(CacheMode.IGNORE);
      final T entityExample = session.load(clazz, id);

      Hibernate.initialize(entityExample);
      return entityExample;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public <T> T save(final T entityExample) {
    Transaction transaction = null;
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      // start a transaction
      transaction = session.beginTransaction();
      // save the student objects
      session.save(entityExample);
      // commit transaction
      transaction.commit();
    } catch (Exception e) {
      if (transaction != null) {
        transaction.rollback();
      }
      throw new RuntimeException(e);
    }

    return entityExample;
  }

  public <T> T update(final T entityExample) {
    Transaction transaction = null;
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      // start a transaction
      transaction = session.beginTransaction();
      // save the student objects
      session.update(entityExample);
      // commit transaction
      transaction.commit();
    } catch (Exception e) {
      if (transaction != null) {
        transaction.rollback();
      }
      throw new RuntimeException(e);
    }

    return entityExample;
  }

  public <T> void delete(final T entityExample) {
    Transaction transaction = null;
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      // start a transaction
      transaction = session.beginTransaction();
      // save the student objects
      session.delete(entityExample);
      // commit transaction
      transaction.commit();
    } catch (Exception e) {
      if (transaction != null) {
        transaction.rollback();
      }
      throw new RuntimeException(e);
    }
  }

  public static CommonEntityDAO getInstance() {
    return new CommonEntityDAO();
  }
}
