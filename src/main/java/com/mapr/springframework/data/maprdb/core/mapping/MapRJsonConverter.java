package com.mapr.springframework.data.maprdb.core.mapping;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class MapRJsonConverter {

    private ObjectMapper objectMapper;

    public MapRJsonConverter() {
        this(new ObjectMapper());

    }

    public MapRJsonConverter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        objectMapper.setAnnotationIntrospector(new MapRAnnotationIntrospector());
    }

    public <T> Map toJson(T objectToConvert) {
        return objectMapper.convertValue(objectToConvert, Map.class);
    }

    public <T> T toObject(Map json, Class<T> entityClass) {
        return objectMapper.convertValue(json, entityClass);
    }
}
