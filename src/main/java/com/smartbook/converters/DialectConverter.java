package com.smartbook.converters;

import com.smartbook.entity.enums.Dialect;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class DialectConverter implements AttributeConverter<Dialect, String> {
    @Override
    public String convertToDatabaseColumn(Dialect dialect) {
        if (dialect == null){
            return null;
        }
        return dialect.getName();
    }

    @Override
    public Dialect convertToEntityAttribute(String dbData) {
        if (dbData == null){
            return null;
        }
        return Stream.of(Dialect.values())
                .filter(c -> c.getName().equals(dbData))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
