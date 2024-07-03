package me.koba1.sqlquerybuilder.statements.wheres;

import lombok.Getter;
import me.koba1.sqlquerybuilder.statements.IStatement;

import java.util.LinkedHashMap;
import java.util.Map;

@Getter
public class WhereStatement implements IStatement {

    public static enum WhereStatementType {
        EQUAL("="),
        GREATER(">"),
        LOWER("<"),
        IS_NOT_NULL("IS NOT NULL"),
        IS_NULL("IS NULL"),;

        private final String value;
        WhereStatementType(final String value) {
            this.value = value;
        }
    }

    private final String key;
    private final Object value;
    private final WhereStatementType type;
    private final Map<WhereStatement, WhereStatementHolder.Operators> childs;

    public WhereStatement(String key, Object value) {
        this.key = key;
        this.value = value;
        this.type = WhereStatementType.EQUAL;
        this.childs = new LinkedHashMap<>();
    }

    public WhereStatement(String key, Object value, WhereStatementType type) {
        this.key = key;
        this.value = value;
        this.type = type;
        this.childs = new LinkedHashMap<>();
    }

    public WhereStatement(String key, WhereStatementType type) {
        this.key = key;
        this.value = null;
        this.type = type;
        this.childs = new LinkedHashMap<>();
    }

    public String getType() {
        System.out.println(type);
        switch (type) {
            case IS_NOT_NULL, IS_NULL -> {
                return this.key + " " + this.type.value;
            }
            case null, default ->  {
                return this.key + " " + this.type.value + " ?";
            }
        }
    }

    public WhereStatement and(WhereStatement st) {
        this.childs.put(st, WhereStatementHolder.Operators.AND);
        return this;
    }

    public WhereStatement or(WhereStatement st) {
        this.childs.put(st, WhereStatementHolder.Operators.OR);
        return this;
    }

    public WhereStatement and(String key, Object value) {
        this.childs.put(new WhereStatement(key, value), WhereStatementHolder.Operators.AND);
        return this;
    }

    public WhereStatement or(String key, Object value) {
        this.childs.put(new WhereStatement(key, value), WhereStatementHolder.Operators.AND);
        return this;
    }

    @Override
    public String toString() {
        if(this.childs.isEmpty()) {
            return getType();
        }

        StringBuilder builder = new StringBuilder("(");
        builder//.append(this.key)
                .append(this.getType());
        for (Map.Entry<WhereStatement, WhereStatementHolder.Operators> child : this.childs.entrySet()) {
            builder.append(child.getValue().getKeyword()).append(child.getKey().toString());
        }
        builder.append(")");

        return builder.toString();
    } // (UUID = ? AND (NAME = ? AND LEVEL = ?) OR ADMIN = ?)
}
