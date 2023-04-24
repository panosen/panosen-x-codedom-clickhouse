package com.panosen.codedom.clickhouse.builder;

import com.panosen.codedom.clickhouse.engine.GenerationResponse;
import com.panosen.codedom.clickhouse.engine.SelectSqlEngine;
import org.junit.Assert;
import org.junit.Test;

public class SelectSqlBuilderLimitTest {

    @Test
    public void build2() {

        SelectSqlBuilder selectSqlBuilder = new SelectSqlBuilder()
                .from("student")
                .limit(10);

        GenerationResponse generationResponse = new SelectSqlEngine().generate(selectSqlBuilder);
        String actual = generationResponse.getSql();
        String expected = "select * from `student` limit 10;";

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void build3() {

        SelectSqlBuilder selectSqlBuilder = new SelectSqlBuilder()
                .from("student")
                .limit(10, 15);

        GenerationResponse generationResponse = new SelectSqlEngine().generate(selectSqlBuilder);
        String actual = generationResponse.getSql();
        String expected = "select * from `student` limit 10, 15;";

        Assert.assertEquals(expected, actual);
    }
}
