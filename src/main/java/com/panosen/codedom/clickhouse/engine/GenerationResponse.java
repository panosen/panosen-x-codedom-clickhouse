package com.panosen.codedom.clickhouse.engine;

import com.panosen.codedom.clickhouse.Parameters;

public class GenerationResponse {

    private String sql;

    private Parameters parameters;

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public Parameters getParameters() {
        return parameters;
    }

    public void setParameters(Parameters parameters) {
        this.parameters = parameters;
    }
}
