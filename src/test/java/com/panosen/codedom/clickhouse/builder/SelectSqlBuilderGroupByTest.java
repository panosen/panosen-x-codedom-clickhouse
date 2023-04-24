package com.panosen.codedom.clickhouse.builder;

import com.panosen.codedom.clickhouse.Parameters;
import com.panosen.codedom.clickhouse.engine.GenerationResponse;
import com.panosen.codedom.clickhouse.engine.SelectSqlEngine;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Types;

public class SelectSqlBuilderGroupByTest {

    @Test
    public void simple() {

        SelectSqlBuilder selectSqlBuilder = new SelectSqlBuilder()
                .from("student");

        selectSqlBuilder.groupBy().columns("name", "age");

        GenerationResponse generationResponse = new SelectSqlEngine().generate(selectSqlBuilder);
        String actual = generationResponse.getSql();
        String expected = "select * from `student` group by `name`, `age`;";

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void having() {

        SelectSqlBuilder selectSqlBuilder = new SelectSqlBuilder()
                .from("student");

        selectSqlBuilder
                .groupBy().columns("name", "age")
                .having().equal("a", 14);

        GenerationResponse response = new SelectSqlEngine().generate(selectSqlBuilder);

        String actual = response.getSql();
        String expected = "select * from `student` group by `name`, `age` having `a` = :p0;";
        Assert.assertEquals(expected, actual);

        Parameters parameters = response.getParameters();

        Assert.assertEquals(1, parameters.size());
        Assert.assertEquals(14, parameters.get(0).getValue());
    }

}
