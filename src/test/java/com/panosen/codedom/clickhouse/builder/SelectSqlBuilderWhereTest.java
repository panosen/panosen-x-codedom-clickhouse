package com.panosen.codedom.clickhouse.builder;

import com.google.common.collect.Lists;
import com.panosen.codedom.clickhouse.MustConditions;
import com.panosen.codedom.clickhouse.Parameters;
import com.panosen.codedom.clickhouse.ShouldConditions;
import com.panosen.codedom.clickhouse.engine.GenerationResponse;
import com.panosen.codedom.clickhouse.engine.SelectSqlEngine;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Types;
import java.util.List;

public class SelectSqlBuilderWhereTest {

    @Test
    public void build4() {

        SelectSqlBuilder selectSqlBuilder = new SelectSqlBuilder()
                .from("student")
                .limit(10, 15);

        selectSqlBuilder.where()
                .equal("age", 12);

        GenerationResponse generationResponse = new SelectSqlEngine().generate(selectSqlBuilder);
        String actual = generationResponse.getSql();
        Parameters parameters = generationResponse.getParameters();

        String expected = "select * from `student` where `age` = :p0 limit 10, 15;";

        Assert.assertEquals(expected, actual);
        Assert.assertEquals(1, parameters.size());
    }

    @Test
    public void build5() {

        SelectSqlBuilder selectSqlBuilder = new SelectSqlBuilder()
                .from("student")
                .limit(10, 15);

        selectSqlBuilder.where().must()
                .equal("x", 12)
                .equal("y", 13);

        GenerationResponse generationResponse = new SelectSqlEngine().generate(selectSqlBuilder);
        String actual = generationResponse.getSql();
        Parameters parameters = generationResponse.getParameters();

        String expected = "select * from `student` where `x` = :p0 and `y` = :p1 limit 10, 15;";

        Assert.assertEquals(expected, actual);
        Assert.assertEquals(2, parameters.size());
    }

    @Test
    public void build6() {

        SelectSqlBuilder selectSqlBuilder = new SelectSqlBuilder()
                .from("student")
                .limit(10, 15);

        MustConditions must = selectSqlBuilder.where().must();
        must.should()
                .equal("x", 12)
                .equal("y", 13);
        must.should()
                .equal("a", 14)
                .equal("b", 15);

        GenerationResponse generationResponse = new SelectSqlEngine().generate(selectSqlBuilder);
        String actual = generationResponse.getSql();
        Parameters parameters = generationResponse.getParameters();

        String expected = "select * from `student` where (`x` = :p0 or `y` = :p1) and (`a` = :p2 or `b` = :p3) limit 10, 15;";

        Assert.assertEquals(expected, actual);
        Assert.assertEquals(4, parameters.size());
        Assert.assertEquals(12, parameters.get(0).getValue());
        Assert.assertEquals(13, parameters.get(1).getValue());
        Assert.assertEquals(14, parameters.get(2).getValue());
        Assert.assertEquals(15, parameters.get(3).getValue());
    }

    @Test
    public void build7() {

        SelectSqlBuilder selectSqlBuilder = new SelectSqlBuilder()
                .from("student")
                .limit(10, 15);

        ShouldConditions should = selectSqlBuilder.where().should();
        should.must()
                .equal("x", 12)
                .equal("y", 13);
        should.must()
                .equal("a", 14)
                .equal("b", 15);

        GenerationResponse generationResponse = new SelectSqlEngine().generate(selectSqlBuilder);
        String actual = generationResponse.getSql();
        Parameters parameters = generationResponse.getParameters();

        String expected = "select * from `student` where (`x` = :p0 and `y` = :p1) or (`a` = :p2 and `b` = :p3) limit 10, 15;";

        Assert.assertEquals(expected, actual);
        Assert.assertEquals(4, parameters.size());
        Assert.assertEquals(12, parameters.get(0).getValue());
        Assert.assertEquals(13, parameters.get(1).getValue());
        Assert.assertEquals(14, parameters.get(2).getValue());
        Assert.assertEquals(15, parameters.get(3).getValue());
    }

    @Test
    public void build8() {

        SelectSqlBuilder selectSqlBuilder = new SelectSqlBuilder()
                .from("student")
                .limit(10, 15);

        selectSqlBuilder.where()
                .in("age", 12, 13);

        GenerationResponse generationResponse = new SelectSqlEngine().generate(selectSqlBuilder);
        String actual = generationResponse.getSql();
        Parameters parameters = generationResponse.getParameters();

        String expected = "select * from `student` where `age` in (:p0, :p1) limit 10, 15;";

        Assert.assertEquals(expected, actual);
        Assert.assertEquals(2, parameters.size());
        Assert.assertEquals(12, parameters.get(0).getValue());
        Assert.assertEquals(13, parameters.get(1).getValue());
    }

    @Test
    public void build9() {

        SelectSqlBuilder selectSqlBuilder = new SelectSqlBuilder()
                .from("student")
                .limit(10, 15);

        selectSqlBuilder.where()
                .in("age", Lists.newArrayList(12, 13));

        GenerationResponse generationResponse = new SelectSqlEngine().generate(selectSqlBuilder);
        String actual = generationResponse.getSql();
        Parameters parameters = generationResponse.getParameters();

        String expected = "select * from `student` where `age` in (:p0, :p1) limit 10, 15;";

        Assert.assertEquals(expected, actual);
        Assert.assertEquals(2, parameters.size());
        Assert.assertEquals(12, parameters.get(0).getValue());
        Assert.assertEquals(13, parameters.get(1).getValue());
    }

    @Test
    public void build10() {

        SelectSqlBuilder selectSqlBuilder = new SelectSqlBuilder()
                .from("student")
                .limit(10, 15);

        selectSqlBuilder.where()
                .in("age", Lists.newArrayList("A", "B"));

        GenerationResponse generationResponse = new SelectSqlEngine().generate(selectSqlBuilder);
        String actual = generationResponse.getSql();
        Parameters parameters = generationResponse.getParameters();

        String expected = "select * from `student` where `age` in (:p0, :p1) limit 10, 15;";

        Assert.assertEquals(expected, actual);
        Assert.assertEquals(2, parameters.size());
        Assert.assertEquals("A", parameters.get(0).getValue());
        Assert.assertEquals("B", parameters.get(1).getValue());
    }

    @Test
    public void build11() {

        List<String> names = Lists.newArrayList("A", "B");

        SelectSqlBuilder selectSqlBuilder = new SelectSqlBuilder()
                .from("student")
                .limit(10, 15);

        selectSqlBuilder.where()
                .in("name", names);

        GenerationResponse generationResponse = new SelectSqlEngine().generate(selectSqlBuilder);
        String actual = generationResponse.getSql();
        Parameters parameters = generationResponse.getParameters();

        String expected = "select * from `student` where `name` in (:p0, :p1) limit 10, 15;";

        Assert.assertEquals(expected, actual);
        Assert.assertEquals(2, parameters.size());
        Assert.assertEquals("A", parameters.get(0).getValue());
        Assert.assertEquals("B", parameters.get(1).getValue());
    }
}
