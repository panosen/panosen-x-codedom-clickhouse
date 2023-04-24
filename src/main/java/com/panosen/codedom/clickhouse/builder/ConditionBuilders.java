package com.panosen.codedom.clickhouse.builder;

import com.google.common.collect.Lists;
import com.panosen.codedom.clickhouse.*;

public class ConditionBuilders {

    public static EqualCondition equalCondition(String fieldName, Object value) {
        EqualCondition equalCondition = new EqualCondition();
        equalCondition.setFieldName(fieldName);
        equalCondition.setValue(value);
        return equalCondition;
    }

    public static NotEqualCondition notEqualCondition(String fieldName, Object value) {
        NotEqualCondition notEqualCondition = new NotEqualCondition();
        notEqualCondition.setFieldName(fieldName);
        notEqualCondition.setValue(value);
        return notEqualCondition;
    }

    public static GtCondition gtCondition(String fieldName, Object value) {
        GtCondition gtCondition = new GtCondition();
        gtCondition.setFieldName(fieldName);
        gtCondition.setValue(value);
        return gtCondition;
    }

    public static GteCondition gteCondition(String fieldName, Object value) {
        GteCondition gteCondition = new GteCondition();
        gteCondition.setFieldName(fieldName);
        gteCondition.setValue(value);
        return gteCondition;
    }

    public static LtCondition ltCondition(String fieldName, Object value) {
        LtCondition ltCondition = new LtCondition();
        ltCondition.setFieldName(fieldName);
        ltCondition.setValue(value);
        return ltCondition;
    }

    public static LteCondition lteCondition(String fieldName, Object value) {
        LteCondition lteCondition = new LteCondition();
        lteCondition.setFieldName(fieldName);
        lteCondition.setValue(value);
        return lteCondition;
    }

    public static InCondition inCondition(String fieldName, Iterable<? extends Object>  values) {
        InCondition inCondition = new InCondition();
        inCondition.setFieldName(fieldName);
        inCondition.setValues(Lists.newArrayList(values));
        return inCondition;
    }

    public static InCondition inCondition(String fieldName, Object... values) {
        InCondition inCondition = new InCondition();
        inCondition.setFieldName(fieldName);
        inCondition.setValues(Lists.newArrayList(values));
        return inCondition;
    }
}
