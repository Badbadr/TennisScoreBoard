package org.example.util;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class QueryParamsCollector {

    public static Map<String, String> collect(String queryParams) {
        return Arrays.stream(queryParams.split("&")).map(param -> param.split("="))
                .collect(Collectors.toMap(param -> param[0], param -> param[1]));
    }
}
