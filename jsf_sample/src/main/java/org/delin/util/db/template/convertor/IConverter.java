package org.delin.util.db.template.convertor;

public interface IConverter {
    Object convertDataFromObject(Object obj);

    Object convertDataFromDatabase(Object obj);
}
