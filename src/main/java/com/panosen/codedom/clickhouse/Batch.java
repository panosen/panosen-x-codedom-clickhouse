package com.panosen.codedom.clickhouse;

import java.util.Map;

public class Batch {

    private Map<String,Object> values;

    public Map<String, Object> getValues() {
        return values;
    }

    public void setValues(Map<String, Object> values) {
        this.values = values;
    }
}
