package com.panosen.codedom.clickhouse.engine;

import com.panosen.codedom.CodeWriter;
import com.panosen.codedom.Marks;
import com.panosen.codedom.clickhouse.*;

import java.util.Iterator;
import java.util.List;

public abstract class SqlEngine {

    protected void generateWhere(ConditionStatement where, CodeWriter codeWriter, Parameters parameters) {
        if (where == null || where.getCondition() == null) {
            return;
        }

        codeWriter.write(Marks.WHITESPACE).write(Keywords.WHERE).write(Marks.WHITESPACE);

        generateCondition(where.getCondition(), codeWriter, parameters, false);
    }

    protected void generateCondition(Condition condition, CodeWriter codeWriter, Parameters parameters, boolean parenthesis) {
        if (condition == null) {
            return;
        }

        if (condition instanceof EqualCondition) {
            generateEqualCondition((EqualCondition) condition, codeWriter, parameters);
        }

        if (condition instanceof NotEqualCondition) {
            generateNotEqualCondition((NotEqualCondition) condition, codeWriter, parameters);
        }

        if (condition instanceof GtCondition) {
            generateGtCondition((GtCondition) condition, codeWriter, parameters);
        }

        if (condition instanceof GteCondition) {
            generateGteCondition((GteCondition) condition, codeWriter, parameters);
        }

        if (condition instanceof LtCondition) {
            generateLtCondition((LtCondition) condition, codeWriter, parameters);
        }

        if (condition instanceof LteCondition) {
            generateLteCondition((LteCondition) condition, codeWriter, parameters);
        }

        if (condition instanceof InCondition) {
            generateInCondition((InCondition) condition, codeWriter, parameters);
        }

        if (condition instanceof MustConditions) {
            generateMustConditions((MustConditions) condition, codeWriter, parameters, parenthesis);
        }

        if (condition instanceof ShouldConditions) {
            generateShoudConditions((ShouldConditions) condition, codeWriter, parameters, parenthesis);
        }
    }

    private void generateMustConditions(MustConditions mustConditions, CodeWriter codeWriter, Parameters parameters, boolean parenthesis) {
        generateConditions(mustConditions.getConditionList(), codeWriter, parameters, Keywords.AND, parenthesis);
    }

    private void generateShoudConditions(ShouldConditions shouldConditions, CodeWriter codeWriter, Parameters parameters, boolean parenthesis) {
        generateConditions(shouldConditions.getConditionList(), codeWriter, parameters, Keywords.OR, parenthesis);
    }

    private void generateConditions(List<Condition> conditionList, CodeWriter codeWriter, Parameters parameters, String logicalOperator, boolean parenthesis) {
        if (conditionList == null || conditionList.isEmpty()) {
            return;
        }

        if (parenthesis && conditionList.size() > 1) {
            codeWriter.write(Marks.LEFT_BRACKET);
        }

        Iterator<Condition> iterator = conditionList.iterator();
        boolean hasNext = iterator.hasNext();
        while (hasNext) {
            generateCondition(iterator.next(), codeWriter, parameters, true);

            hasNext = iterator.hasNext();
            if (hasNext) {
                codeWriter.write(Marks.WHITESPACE).write(logicalOperator).write(Marks.WHITESPACE);
            }
        }

        if (parenthesis && conditionList.size() > 1) {
            codeWriter.write(Marks.RIGHT_BRACKET);
        }
    }

    private void generateEqualCondition(EqualCondition equalCondition, CodeWriter codeWriter, Parameters parameters) {
        generateSimpleCondition(equalCondition, codeWriter, parameters, Marks.EQUAL);
    }

    private void generateNotEqualCondition(NotEqualCondition notEqualCondition, CodeWriter codeWriter, Parameters parameters) {
        generateSimpleCondition(notEqualCondition, codeWriter, parameters, Marks.NOT_EQUAL);
    }

    private void generateGtCondition(GtCondition gtCondition, CodeWriter codeWriter, Parameters parameters) {
        generateSimpleCondition(gtCondition, codeWriter, parameters, Marks.GREATER_THAN);
    }

    private void generateGteCondition(GteCondition gteCondition, CodeWriter codeWriter, Parameters parameters) {
        generateSimpleCondition(gteCondition, codeWriter, parameters, Marks.GREATER_EQUAL_THAN);
    }

    private void generateLtCondition(LtCondition ltCondition, CodeWriter codeWriter, Parameters parameters) {
        generateSimpleCondition(ltCondition, codeWriter, parameters, Marks.LESS_THAN);
    }

    private void generateLteCondition(LteCondition lteCondition, CodeWriter codeWriter, Parameters parameters) {
        generateSimpleCondition(lteCondition, codeWriter, parameters, Marks.LESS_EQUAL_THAN);
    }

    private void generateSimpleCondition(SimpleCondition simpleCondition, CodeWriter codeWriter, Parameters parameters, String mathOperator) {
        codeWriter.write(Marks.BACKQUOTE).write(simpleCondition.getFieldName()).write(Marks.BACKQUOTE)
                .write(Marks.WHITESPACE)
                .write(mathOperator)
                .write(Marks.WHITESPACE)
                .write(":p" + parameters.size());
        parameters.add(simpleCondition.getValue());
    }

    private void generateInCondition(InCondition inCondition, CodeWriter codeWriter, Parameters parameters) {
        codeWriter.write(Marks.BACKQUOTE).write(inCondition.getFieldName()).write(Marks.BACKQUOTE)
                .write(Marks.WHITESPACE)
                .write(Keywords.IN)
                .write(Marks.WHITESPACE);

        // (
        codeWriter.write(Marks.LEFT_BRACKET);

        for (int i = 0, length = inCondition.getValues().size(); i < length; i++) {
            codeWriter.write(":p" + parameters.size());
            parameters.add(inCondition.getValues().get(i));

            if (i < length - 1) {
                codeWriter.write(Marks.COMMA).write(Marks.WHITESPACE);
            }
        }

        // )
        codeWriter.write(Marks.RIGHT_BRACKET);
    }
}
