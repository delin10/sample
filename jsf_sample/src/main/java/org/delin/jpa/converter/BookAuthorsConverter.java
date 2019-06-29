package org.delin.jpa.converter;

import javax.persistence.AttributeConverter;
import java.util.Arrays;
import java.util.List;

public class BookAuthorsConverter implements AttributeConverter<String, List<String>> {
    @Override
    public List<String> convertToDatabaseColumn(String s) {
        return Arrays.asList(s.split(","));
    }

    @Override
    public String convertToEntityAttribute(List<String> strings) {
        return String.join(",", strings);
    }
}
