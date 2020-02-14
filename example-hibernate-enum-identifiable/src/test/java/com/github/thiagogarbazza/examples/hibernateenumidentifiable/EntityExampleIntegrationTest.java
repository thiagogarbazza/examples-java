package com.github.thiagogarbazza.examples.hibernateenumidentifiable;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class EntityExampleIntegrationTest {

  @Test
  void verifyFind() {
    final UUID uuid = EntityExampleDAO.getInstance().save(EntityExample.builder()
      .description("Example to find value")
      .enumIdentifiableString(EnumIdentifiableString.TYPE_ZC)
      .enumIdentifiableString2(EnumIdentifiableString.TYPE_ZB)
      .enumIdentifiableInteger(EnumIdentifiableInteger.TYPE_30)
      .enumIdentifiableInteger2(EnumIdentifiableInteger.TYPE_20)
      .build()).getId();

    final EntityExample entityExample = EntityExampleDAO.getInstance().findById(uuid);

    assertEquals(uuid, entityExample.getId());
    assertEquals("Example to find value", entityExample.getDescription());
    assertEquals(EnumIdentifiableString.TYPE_ZC, entityExample.getEnumIdentifiableString());
    assertEquals(EnumIdentifiableString.TYPE_ZB, entityExample.getEnumIdentifiableString2());
    assertEquals(EnumIdentifiableInteger.TYPE_30, entityExample.getEnumIdentifiableInteger());
    assertEquals(EnumIdentifiableInteger.TYPE_20, entityExample.getEnumIdentifiableInteger2());

    assertEquals("ZC", EntityExampleDAO.getInstance().findColumn(uuid, "enum_identifiable_string"));
    assertEquals("ZB", EntityExampleDAO.getInstance().findColumn(uuid, "enum_identifiable_string2"));
    assertEquals("30", EntityExampleDAO.getInstance().findColumn(uuid, "enum_identifiable_integer"));
    assertEquals("20", EntityExampleDAO.getInstance().findColumn(uuid, "enum_identifiable_integer2"));
  }

  @Test
  void verifySave() {
    final EntityExample entityExample = EntityExampleDAO.getInstance().save(EntityExample.builder()
      .description("Any text...")
      .enumIdentifiableString(EnumIdentifiableString.TYPE_ZC)
      .enumIdentifiableString2(EnumIdentifiableString.TYPE_ZB)
      .enumIdentifiableInteger(EnumIdentifiableInteger.TYPE_30)
      .enumIdentifiableInteger2(EnumIdentifiableInteger.TYPE_20)
      .build());

    assertNotNull(entityExample.getId());
    assertEquals("Any text...", entityExample.getDescription());
    assertEquals(EnumIdentifiableString.TYPE_ZC, entityExample.getEnumIdentifiableString());
    assertEquals(EnumIdentifiableString.TYPE_ZB, entityExample.getEnumIdentifiableString2());
    assertEquals(EnumIdentifiableInteger.TYPE_30, entityExample.getEnumIdentifiableInteger());
    assertEquals(EnumIdentifiableInteger.TYPE_20, entityExample.getEnumIdentifiableInteger2());

    assertEquals("ZC", EntityExampleDAO.getInstance().findColumn(entityExample.getId(), "enum_identifiable_string"));
    assertEquals("ZB", EntityExampleDAO.getInstance().findColumn(entityExample.getId(), "enum_identifiable_string2"));
    assertEquals("30", EntityExampleDAO.getInstance().findColumn(entityExample.getId(), "enum_identifiable_integer"));
    assertEquals("20", EntityExampleDAO.getInstance().findColumn(entityExample.getId(), "enum_identifiable_integer2"));
  }
}
