package com.krab.integration.messaging;

import com.krab.avro.InputMessageEvent;
import tech.allegro.schema.json2avro.converter.JsonAvroConverter;

import java.nio.charset.StandardCharsets;

public class JsonToAvro {

    public InputMessageEvent getBase() {
        String schema = "{\"type\":\"record\",\"name\":\"InputMessageEvent\",\"namespace\":\"com.krab.avro\",\"fields\":[{\"name\":\"messageId\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"},\"doc\":\"Message ID number\"},{\"name\":\"someValue\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"someValue2\",\"type\":[\"null\",\"double\"],\"default\":null},{\"name\":\"someFlag\",\"type\":[\"null\",\"boolean\"],\"default\":null}]}";
        String baseJson = "{\n" +
                "  \"messageId\": \"1234567\",\n" +
                "  \"someValue\": 123,\n" +
                "  \"someValue2\": 1234.11,\n" +
                "  \"someFlag\": true\n" +
                "}";
        JsonAvroConverter converter = new JsonAvroConverter();
        return converter.convertToSpecificRecord(baseJson.getBytes(StandardCharsets.UTF_8), InputMessageEvent.class, schema);
    }
}
