package org.delin.dbconverter;

import org.delin.util.db.template.convertor.IConverter;

import java.util.Arrays;
import java.util.List;

public class BookAuthorsConverter implements IConverter {
    @Override
    public Object convertDataFromObject(Object obj) {
        System.out.println("call convertDataFromObject" + obj);
        return String.join(",", (List<String>) obj);
    }

    @Override
    public Object convertDataFromDatabase(Object obj) {
        String s = (String) obj;
        return Arrays.asList(s.split("\\s*,\\s*"));
    }
}
