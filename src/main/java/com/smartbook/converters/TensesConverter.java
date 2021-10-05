package com.smartbook.converters;

import com.smartbook.entity.enums.Tenses;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class TensesConverter implements AttributeConverter<Tenses, String> {
    @Override
    public String convertToDatabaseColumn(Tenses tenses) {
        if (tenses == null) {
            return null;
        }
        return tenses.getName();
    }

    @Override
    public Tenses convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        return Stream.of(Tenses.values())
                .filter(c -> c.getName().equals(dbData))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
