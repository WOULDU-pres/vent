package me.hwjoo.backend.common.util;

// src/main/java/me/hwjoo/backend/raffle/util/JsonParser.java

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.util.Map;
import me.hwjoo.backend.common.entity.Participation;

public class JsonParser {
    private static final Gson gson = new Gson();

    private JsonParser() { // 유틸리티 클래스 방지
        throw new AssertionError("Cannot instantiate utility class");
    }

    public static String parseStatus(String jsonData) {
        return parseField(jsonData, "resultStatus", String.class);
    }

    public static String extractPrizeName(Participation participation) {
        return parseField(participation.getResultData(), "prizeName", String.class);
    }

    public static Integer extractPrizeAmount(Participation participation) {
        return parseField(participation.getResultData(), "prizeAmount", Integer.class);
    }

    private static <T> T parseField(String json, String fieldName, Class<T> type) {
        if (json == null || json.isBlank()) return null;

        try {
            Map<String, Object> map = gson.fromJson(json,
                    new com.google.gson.reflect.TypeToken<Map<String, Object>>(){}.getType());

            Object value = map.getOrDefault(fieldName, null);
            return type.cast(value);
        } catch (JsonSyntaxException | ClassCastException e) {
            return null; // 파싱 실패 시 기본값
        }
    }
}
