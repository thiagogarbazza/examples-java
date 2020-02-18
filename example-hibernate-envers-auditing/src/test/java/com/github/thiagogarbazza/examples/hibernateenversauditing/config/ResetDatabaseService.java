package com.github.thiagogarbazza.examples.hibernateenversauditing.config;

import lombok.extern.apachecommons.CommonsLog;
import org.hibernate.Metamodel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.tool.hbm2ddl.SchemaExport;

import java.util.Arrays;
import java.util.Collection;

@CommonsLog
public class ResetDatabaseService {
  public void reset() {
    Collection<String> tableNames = Arrays.asList(
      "tbl_revision_information",
      "tbl_entity_example_audited_a",
      "aud_tbl_entity_example_audited_a_aud",
      "tbl_entity_example_audited_b",
      "tbl_entity_example_audited_b_extensions",
      "aud_tbl_entity_example_audited_b_aud",
      "aud_tbl_entity_example_audited_b_extensions_aud"
                                                 );
    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    Metamodel hibernateMetadata = sessionFactory.getMetamodel();
    SchemaExport schemaExport = new SchemaExport();

    //for (ClassMetadata classMetadata : hibernateMetadata.values()) {
    // AbstractEntityPersister aep = (AbstractEntityPersister) classMetadata;
    //tableNames.add(aep.getTableName());
    // }

    Transaction transaction = null;
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      // start a transaction
      transaction = session.beginTransaction();
      // save the student objects


      session.createNativeQuery("SET referential_integrity false").executeUpdate();
      tableNames.forEach(tableName -> session.createNativeQuery("TRUNCATE TABLE " + tableName).executeUpdate());
      session.createNativeQuery("SET referential_integrity true").executeUpdate();



      // commit transaction
      transaction.commit();
    } catch (Exception e) {
      if (transaction != null) {
        transaction.rollback();
      }
      throw new RuntimeException(e);
    }
  }


  public static ResetDatabaseService getInstance() {
    return new ResetDatabaseService();
  }
}
