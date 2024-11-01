package com.nutriroute.adapters;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.time.LocalDate;

public class LocalDateAdapter extends TypeAdapter<LocalDate> {
    @Override
    public void write(final JsonWriter jsonWriter, final LocalDate localDate) throws IOException {
        if (localDate != null) {
            jsonWriter.value(localDate.toString());
        } else {
            jsonWriter.nullValue(); // Handle null LocalDate
        }
    }

    @Override
    public LocalDate read(final JsonReader jsonReader) throws IOException {
        String dateStr = jsonReader.nextString();
        return LocalDate.parse(dateStr); // Parse the single string value
    }
}