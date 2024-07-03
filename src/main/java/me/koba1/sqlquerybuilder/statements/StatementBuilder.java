package me.koba1.sqlquerybuilder.statements;

import me.koba1.sqlquerybuilder.statements.sets.SetStatement;
import me.koba1.sqlquerybuilder.statements.wheres.WhereStatement;

public class StatementBuilder {
    public static WhereStatement where(String key, Object value) {
        return new WhereStatement(key, value);
    }

    public static SetStatement set(String key, Object value) {
        return new SetStatement(key, value);
    }
}
