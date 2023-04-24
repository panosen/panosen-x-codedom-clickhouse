package com.panosen.codedom.clickhouse.builder;

import com.google.common.collect.Lists;
import com.panosen.codedom.clickhouse.GroupBy;

public class GroupByBuilder {

    private final GroupBy groupBy = new GroupBy();

    public GroupBy getGroupBy() {
        return groupBy;
    }

    public GroupByBuilder column(String column) {
        if (groupBy.getColumnNames() == null) {
            groupBy.setColumnNames(Lists.newArrayList());
        }
        groupBy.getColumnNames().add(column);
        return this;
    }

    public GroupByBuilder columns(String... columns) {
        if (columns == null || columns.length == 0) {
            return this;
        }
        if (groupBy.getColumnNames() == null) {
            groupBy.setColumnNames(Lists.newArrayList());
        }
        groupBy.getColumnNames().addAll(Lists.newArrayList(columns));
        return this;
    }

    public ConditionsBuilder having() {
        ConditionsBuilder conditionsBuilder = new ConditionsBuilder();
        groupBy.setHaving(conditionsBuilder.getConditionStatement());
        return conditionsBuilder;
    }
}
