package com.example.restdemo.util;

import com.example.restdemo.entity.Gender;
import jakarta.persistence.AttributeConverter;

public class GenderAttributeConverter implements AttributeConverter<Gender, String> {

    @Override
    public String convertToDatabaseColumn(Gender attribute) {
        if (attribute == null)
            return null;
        return switch (attribute) {
            case MALE -> "M";
            case FEMALE -> "F";
            default -> throw new IllegalArgumentException(attribute + " is not a valid gender");
        };

    }

    @Override
    public Gender convertToEntityAttribute(String dbData) {
        if (dbData == null)
            return null;
        return switch (dbData) {
            case "M" -> Gender.MALE;
            case "F" -> Gender.FEMALE;
            default -> throw new IllegalArgumentException(dbData + " is not a valid gender");
        };
    }

}