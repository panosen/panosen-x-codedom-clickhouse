package com.panosen.codedom.clickhouse;

import com.google.common.collect.Lists;
import com.panosen.codedom.clickhouse.builder.ConditionBuilders;

import java.util.List;

public abstract class Conditions<TConditions extends Conditions<?>> extends Condition {

    private List<Condition> conditionList;

    public List<Condition> getConditionList() {
        return conditionList;
    }

    public void setConditionList(List<Condition> conditionList) {
        this.conditionList = conditionList;
    }

    public void addCondition(Condition condition) {
        if (this.conditionList == null) {
            this.conditionList = Lists.newArrayList();
        }
        this.conditionList.add(condition);
    }

    public MustConditions must() {
        MustConditions mustConditions = new MustConditions();
        addCondition(mustConditions);
        return mustConditions;
    }

    public ShouldConditions should() {
        ShouldConditions shouldConditions = new ShouldConditions();
        addCondition(shouldConditions);
        return shouldConditions;
    }

    @SuppressWarnings("unchecked")
    public TConditions equal(String fieldName, Object value) {
        EqualCondition equalCondition = ConditionBuilders.equalCondition(fieldName, value);
        addCondition(equalCondition);
        return (TConditions) this;
    }

    @SuppressWarnings("unchecked")
    public TConditions notEqual(String fieldName, Object value) {
        NotEqualCondition equalCondition = ConditionBuilders.notEqualCondition(fieldName, value);
        addCondition(equalCondition);
        return (TConditions) this;
    }

    @SuppressWarnings("unchecked")
    public TConditions gt(String fieldName, Object value) {
        GtCondition gtCondition = ConditionBuilders.gtCondition(fieldName, value);
        addCondition(gtCondition);
        return (TConditions) this;
    }

    @SuppressWarnings("unchecked")
    public TConditions gte(String fieldName, Object value) {
        GteCondition gteCondition = ConditionBuilders.gteCondition(fieldName, value);
        addCondition(gteCondition);
        return (TConditions) this;
    }

    @SuppressWarnings("unchecked")
    public TConditions lt(String fieldName, Object value) {
        LtCondition ltCondition = ConditionBuilders.ltCondition(fieldName, value);
        addCondition(ltCondition);
        return (TConditions) this;
    }

    @SuppressWarnings("unchecked")
    public TConditions lte(String fieldName, Object value) {
        LteCondition lteCondition = ConditionBuilders.lteCondition(fieldName, value);
        addCondition(lteCondition);
        return (TConditions) this;
    }

    @SuppressWarnings("unchecked")
    public <E> TConditions in(String fieldName, Iterable<? extends Object> values) {
        InCondition inCondition = ConditionBuilders.inCondition(fieldName, values);
        addCondition(inCondition);
        return (TConditions) this;
    }

    @SuppressWarnings("unchecked")
    public TConditions in(String fieldName, Object... values) {
        InCondition inCondition = ConditionBuilders.inCondition(fieldName, values);
        addCondition(inCondition);
        return (TConditions) this;
    }
}
