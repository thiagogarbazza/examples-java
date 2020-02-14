package com.github.thiagogarbazza.examples.hibernateenumidentifiable;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.util.UUID;

class EntityExampleDAO {

  EntityExample findById(final UUID id) {
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      final EntityExample entityExample = session.load(EntityExample.class, id);

      Hibernate.initialize(entityExample);
      return entityExample;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  String findColumn(final UUID uuid, final String column) {
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      final String id = uuid.toString().replaceAll("-", "");
      final NativeQuery sqlQuery = session.createSQLQuery("select " + column + " FROM tbl_entity_example WHERE id = '" + id + "';");

      return String.valueOf(sqlQuery.uniqueResult());
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  EntityExample save(final EntityExample entityExample) {
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

  public static EntityExampleDAO getInstance() {
    return new EntityExampleDAO();
  }
}
