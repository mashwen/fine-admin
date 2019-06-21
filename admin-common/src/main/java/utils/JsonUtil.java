package utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class JsonUtil {

    private static final Logger log = LoggerFactory.getLogger(JsonUtil.class);

    public static String getValue(String content, String key) {
        JsonNode node = getNode(content, key);

        if (node == null) {
            return null;
        }

        return node.asText();
    }

    public static JsonNode getNode(String content, String key) {
        JsonNode node = null;

        try {
            ObjectMapper mapper = new ObjectMapper();
            node = mapper.readTree(content);
            return node.get(key);
        } catch (Exception e) {
            log.error(String.format("Fails to get key from json. (key, json) = (%s, %s)", key, content));
        }

        return node;
    }



    public static String toJson(Object obj) {
        String json = null;

        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);
            json = mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            log.error("Failed to parse object to json");
        }

        return json;
    }
    public static <T > T toModel(String json, Class<T> modelClass) {
        T model = null;

        try {
            ObjectMapper mapper = new ObjectMapper();
            model = mapper.readValue(json, modelClass);
        } catch (Exception e) {
            log.error(String.format("Fails to parse json to model.\njson = %s\nmodel = %s", json, modelClass));
        }

        return model;
    }

    public static <T> List<T> toModels(String json, Class<T> modelClass) {
        ObjectMapper mapper = new ObjectMapper();
        TypeFactory typeFactory = mapper.getTypeFactory();
        List<T> list = null;

        try {
            list = mapper.readValue(json, typeFactory.constructCollectionType(List.class, modelClass));
        } catch (Exception e) {
            log.error("Failed to parse json to list of models: " + json, e);
        }

        return list;
    }

    public static Map<String, String> toMap(String json) {
        Map<String, String> map = new HashMap<>();

        try {
            ObjectMapper mapper = new ObjectMapper();
            map = mapper.readValue(json, new TypeReference<Map<String, String>>() {
            });
        } catch (Exception e) {
            log.error("Failed to parse json to map: " + json);
        }

        return map;
    }

    public static List<Map<String, String>> toMaps(String json) {
        ObjectMapper mapper = new ObjectMapper();
        TypeFactory typeFactory = mapper.getTypeFactory();
        List<Map<String, String>> list = null;

        try {
            list = mapper.readValue(json, typeFactory.constructCollectionType(List.class, Map.class));
        } catch (Exception e) {
            log.error("Failed to parse json to list of maps: " + json);
        }

        return list;
    }

}
