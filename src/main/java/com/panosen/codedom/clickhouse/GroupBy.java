package com.panosen.codedom.clickhouse;

import java.util.List;

public class GroupBy {

    /**
     * select * from table group by ${columnNames}
     */
    private List<String> columnNames;

    /**
     * having
     */
    private ConditionStatement having;

    public List<String> getColumnNames() {
        return columnNames;
    }

    public void setColumnNames(List<String> columnNames) {
        this.columnNames = columnNames;
    }

    public ConditionStatement getHaving() {
        return having;
    }

    public void setHaving(ConditionStatement having) {
        this.having = having;
    }
}
