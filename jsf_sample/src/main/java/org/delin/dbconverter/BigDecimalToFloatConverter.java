package org.delin.dbconverter;

import org.delin.util.db.template.convertor.IConverter;

import java.math.BigDecimal;

public class BigDecimalToFloatConverter implements IConverter {
    @Override
    public Object convertDataFromObject(Object obj) {
        return obj;
    }

    @Override
    public Object convertDataFromDatabase(Object obj) {
        BigDecimal value = (BigDecimal) obj;
        return value.floatValue();
    }
}
