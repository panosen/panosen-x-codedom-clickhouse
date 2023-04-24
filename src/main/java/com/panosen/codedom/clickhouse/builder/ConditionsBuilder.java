package com.panosen.codedom.clickhouse.builder;

import com.panosen.codedom.clickhouse.*;

public class ConditionsBuilder {

    private final ConditionStatement conditionStatement = new ConditionStatement();

    public ConditionStatement getConditionStatement() {
        return conditionStatement;
    }

    public MustConditions must() {
        MustConditions mustConditions = new MustConditions();
        conditionStatement.setCondition(mustConditions);
        return mustConditions;
    }

    public ShouldConditions should() {
        ShouldConditions shouldConditions = new ShouldConditions();
        conditionStatement.setCondition(shouldConditions);
        return shouldConditions;
    }

    public void equal(String fieldName, Object value) {
        EqualCondition equalCondition = ConditionBuilders.equalCondition(fieldName, value);
        conditionStatement.setCondition(equalCondition);
    }

    public void gt(String fieldName, Object value) {
        conditionStatement.setCondition(ConditionBuilders.gtCondition(fieldName, value));
    }

    public void gte(String fieldName, Object value) {
        conditionStatement.setCondition(ConditionBuilders.gteCondition(fieldName, value));
    }

    public void lt(String fieldName, Object value) {
        conditionStatement.setCondition(ConditionBuilders.ltCondition(fieldName, value));
    }

    public void lte(String fieldName, Object value) {
        conditionStatement.setCondition(ConditionBuilders.lteCondition(fieldName, value));
    }

    public <E> void in(String fieldName, Iterable<? extends E> values) {
        InCondition inCondition = ConditionBuilders.inCondition(fieldName, values);
        conditionStatement.setCondition(inCondition);
    }

    public void in(String fieldName, Object... values) {
        InCondition inCondition = ConditionBuilders.inCondition(fieldName, values);
        conditionStatement.setCondition(inCondition);
    }
}
