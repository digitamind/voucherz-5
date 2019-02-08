package com.interswitch.voucherz.authservice.util;

import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class CsvReadUtil {
    public static <T> List<T> read(Class<T> model, InputStream stream) throws IOException {
        CsvMapper mapper = new CsvMapper();
        CsvSchema schema = mapper.schemaFor(model).withHeader().withColumnReordering(true);
        ObjectReader reader = mapper.readerFor(model).with(schema);
        return reader.<T>readValues(stream).readAll();
    }
}
