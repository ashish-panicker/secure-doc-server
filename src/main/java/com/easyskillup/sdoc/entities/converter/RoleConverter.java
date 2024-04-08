package com.easyskillup.sdoc.entities.converter;

import com.easyskillup.sdoc.entities.Authority;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class RoleConverter implements AttributeConverter<Authority, String> {
    @Override
    public String convertToDatabaseColumn(Authority authority) {
        if (authority == null) {
            return null;
        }
        return authority.getValue();
    }

    @Override
    public Authority convertToEntityAttribute(String s) {
        System.err.println(s);
        if (s == null) {
            return null;
        }
        System.err.println("convertToEntityAttribute");
        return Stream
                .of(Authority.values())
                .filter(authority -> authority.getValue().equals(s))
                .findFirst()
                .orElseThrow(IllegalAccessError::new);
    }
}
