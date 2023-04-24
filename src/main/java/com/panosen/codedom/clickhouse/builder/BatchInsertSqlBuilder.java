package com.panosen.codedom.clickhouse.builder;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.panosen.codedom.clickhouse.BatchInsertSql;

public class BatchInsertSqlBuilder {

    private final BatchInsertSql batchInsertSql = new BatchInsertSql();

    public BatchInsertSql getBatchInsertSql() {
        return batchInsertSql;
    }

    public BatchInsertSqlBuilder intoTable(String tableName) {
        batchInsertSql.setTableName(tableName);
        return this;
    }

    public BatchBuilder addBatch() {

        if (batchInsertSql.getBatchList() == null) {
            batchInsertSql.setBatchList(Lists.newArrayList());
        }

        BatchBuilder batchBuilder = new BatchBuilder();
        batchInsertSql.getBatchList().add(batchBuilder.getBatch());

        return batchBuilder;
    }

    public BatchInsertSqlBuilder addColumn(String name, int dbType) {
        if (batchInsertSql.getColumnMap() == null) {
            batchInsertSql.setColumnMap(Maps.newHashMap());
        }
        batchInsertSql.getColumnMap().putIfAbsent(name, dbType);
        return this;
    }
}
