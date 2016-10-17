/**
 * Autogenerated by Thrift Compiler (0.9.3)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package org.apache.airavata.sharing.registry.models;


import java.util.Map;
import java.util.HashMap;
import org.apache.thrift.TEnum;

/**
 * <p>This list of fields that can be used to search entities</p>
 * <li>NAME : Name of the entity</li>
 * <li>DESCRIPTION : Description of the entity</li>
 * <li>FULL_TEXT : Full text field of the entity</li>
 * <li>PARENT_ENTITY_ID : Parent entity id of the entity</li>
 * <li>CREATED_TIME : Created time of the entity</li>
 * <li>UPDATED_TIME : Updated time of the entity</li>
 * 
 */
public enum EntitySearchField implements org.apache.thrift.TEnum {
  NAME(0),
  DESCRIPTION(1),
  FULL_TEXT(2),
  PARRENT_ENTITY_ID(3),
  PERMISSION_TYPE_ID(4),
  CREATED_TIME(5),
  UPDATED_TIME(6);

  private final int value;

  private EntitySearchField(int value) {
    this.value = value;
  }

  /**
   * Get the integer value of this enum value, as defined in the Thrift IDL.
   */
  public int getValue() {
    return value;
  }

  /**
   * Find a the enum type by its integer value, as defined in the Thrift IDL.
   * @return null if the value is not found.
   */
  public static EntitySearchField findByValue(int value) { 
    switch (value) {
      case 0:
        return NAME;
      case 1:
        return DESCRIPTION;
      case 2:
        return FULL_TEXT;
      case 3:
        return PARRENT_ENTITY_ID;
      case 4:
        return PERMISSION_TYPE_ID;
      case 5:
        return CREATED_TIME;
      case 6:
        return UPDATED_TIME;
      default:
        return null;
    }
  }
}
