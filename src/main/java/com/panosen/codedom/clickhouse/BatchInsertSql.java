package com.panosen.codedom.clickhouse;

import java.util.List;
import java.util.Map;

public class BatchInsertSql extends Sql {

    /**
     * table name
     */
    private String tableName;

    private Map<String, Integer> columnMap;

    /**
     * batch list
     */
    private List<Batch> batchList;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }


    public Map<String, Integer> getColumnMap() {
        return columnMap;
    }

    public void setColumnMap(Map<String, Integer> columnMap) {
        this.columnMap = columnMap;
    }
    public List<Batch> getBatchList() {
        return batchList;
    }

    public void setBatchList(List<Batch> batchList) {
        this.batchList = batchList;
    }
}
