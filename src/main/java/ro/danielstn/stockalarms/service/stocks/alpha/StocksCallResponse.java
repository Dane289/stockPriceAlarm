package ro.danielstn.stockalarms.service.stocks.alpha;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class StocksCallResponse {
    Map<String, Object> details = new LinkedHashMap<>();

    public Map<String, Object> getDetails() {
        return details;
    }

    @JsonAnySetter
    void setDetail(String key, Object value) {
        details.put(key, value);
    }
}
