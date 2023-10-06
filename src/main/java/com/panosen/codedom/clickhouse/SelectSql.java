package com.panosen.codedom.clickhouse;

import java.util.List;

/**
 * select * from table;
 */
public class SelectSql extends Sql {

    /**
     * select * from ${tableSchema}.tableName;
     */
    private String tableSchema;

    /**
     * select * from ${tableName};
     */
    private String tableName;

    /**
     * select ${columnList} from table
     */
    private List<Column> columnList;

    /**
     * Only useful when limitSize is set.
     * select * from ${tableName} limit ${limitFrom}, 10;
     */
    private Integer limitFrom;

    /**
     * select * from table limit ${limitSize};
     */
    private Integer limitSize;

    /**
     * where
     */
    private ConditionStatement where;

    /**
     * order by
     */
    private List<OrderBy> orderByList;

    /**
     * group by
     */
    private GroupBy groupBy;

    public String getTableSchema() {
        return tableSchema;
    }

    public void setTableSchema(String tableSchema) {
        this.tableSchema = tableSchema;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<Column> getColumnList() {
        return columnList;
    }

    public void setColumnList(List<Column> columnList) {
        this.columnList = columnList;
    }

    public Integer getLimitFrom() {
        return limitFrom;
    }

    public void setLimitFrom(Integer limitFrom) {
        this.limitFrom = limitFrom;
    }

    public Integer getLimitSize() {
        return limitSize;
    }

    public void setLimitSize(Integer limitSize) {
        this.limitSize = limitSize;
    }

    public ConditionStatement getWhere() {
        return where;
    }

    public void setWhere(ConditionStatement where) {
        this.where = where;
    }

    public List<OrderBy> getOrderByList() {
        return orderByList;
    }

    public void setOrderByList(List<OrderBy> orderByList) {
        this.orderByList = orderByList;
    }

    public GroupBy getGroupBy() {
        return groupBy;
    }

    public void setGroupBy(GroupBy groupBy) {
        this.groupBy = groupBy;
    }
}
