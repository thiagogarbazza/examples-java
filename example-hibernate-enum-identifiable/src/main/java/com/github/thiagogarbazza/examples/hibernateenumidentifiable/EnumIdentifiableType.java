package com.github.thiagogarbazza.examples.hibernateenumidentifiable;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.internal.util.ReflectHelper;
import org.hibernate.usertype.DynamicParameterizedType;
import org.hibernate.usertype.UserType;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Properties;

import static java.text.MessageFormat.format;

public class EnumIdentifiableType implements DynamicParameterizedType, UserType {

  private static final String METHOD_NAME = "getId";

  private Class<? extends Enum> typeEnum;
  private Class<?> typeId;
  private int typeSql;

  @Override
  public int[] sqlTypes() {
    return new int[] {typeSql};
  }

  @Override
  public Class<? extends Enum> returnedClass() {
    return typeEnum;
  }

  @Override
  public boolean equals(Object x, Object y) {
    return x == y;
  }

  @Override
  public int hashCode(Object x) {
    return x == null ? 0 : x.hashCode();
  }

  @Override
  public Object nullSafeGet(ResultSet rs, String[] names, SharedSessionContractImplementor session, Object owner) throws SQLException {
    if (rs.wasNull()) {
      return null;
    }

    final Object id = rs.getObject(names[0], this.typeId);

    for (Enum value : returnedClass().getEnumConstants()) {
      if (value instanceof EnumIdentifiable) {
        EnumIdentifiable enumComCodigo = (EnumIdentifiable) value;
        if (enumComCodigo.getId().equals(id)) {
          return value;
        }
      }
    }

    throw new IllegalStateException(format("Não foi possível achar o item de id {0} em {1}.", id, returnedClass()));
  }

  @Override
  public void nullSafeSet(PreparedStatement st, Object value, int index, SharedSessionContractImplementor session) throws SQLException {
    if (value == null) {
      st.setNull(index, this.typeSql);
    } else {
      final Object id = ((EnumIdentifiable) value).getId();
      st.setObject(index, id);
    }
  }

  @Override
  public Object deepCopy(Object value) {
    return value;
  }

  @Override
  public boolean isMutable() {
    return false;
  }

  @Override
  public Serializable disassemble(Object value) {
    return (Serializable) value;
  }

  @Override
  public Object assemble(Serializable cached, Object owner) {
    return cached;
  }

  @Override
  public Object replace(Object original, Object target, Object owner) {
    return original;
  }

  @Override
  public void setParameterValues(Properties parameters) {
    ParameterType parameterType = (ParameterType) parameters.get(PARAMETER_TYPE);

    if (parameterType == null) {
      String enumClassName = (String) parameters.get(RETURNED_CLASS);
      try {
        this.typeEnum = ReflectHelper.classForName(enumClassName, this.getClass()).asSubclass(EnumIdentifiable.class);
      } catch (ClassNotFoundException exception) {
        throw new HibernateException("Enum class not found", exception);
      }
    } else {
      this.typeEnum = parameterType.getReturnedClass().asSubclass(EnumIdentifiable.class);
    }

    try {
      Method getCodigoMethod = this.typeEnum.getMethod(METHOD_NAME);
      this.typeId = getCodigoMethod.getReturnType();

      determineTypeSql(this.typeId);
    } catch (NoSuchMethodException exception) {
      throw new HibernateException("Enum method not found", exception);
    }
  }

  private void determineTypeSql(Class typeId) {
    switch (typeId.getSimpleName()) {
      case "Integer":
      case "Long":
        this.typeSql = Types.SMALLINT;
        break;
      case "String":
      default:
        this.typeSql = Types.VARCHAR;
        break;
    }
  }
}
