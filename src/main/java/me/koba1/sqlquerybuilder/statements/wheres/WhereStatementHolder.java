package me.koba1.sqlquerybuilder.statements.wheres;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class WhereStatementHolder {

    @Getter
    @AllArgsConstructor
    public static enum Operators {
        AND(" AND "),
        OR(" OR ");

        private final String keyword;
    }

    private final WhereStatement first;
    private final WhereStatement second;
    private final Operators operator;

    public WhereStatementHolder(WhereStatement first, WhereStatement second, Operators operator) {
        this.first = first;
        this.second = second;
        this.operator = operator;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("(")
                .append(this.first.getKey())
                .append(" = ?")
                .append(this.operator.keyword)
                .append(this.second.getKey())
                .append(" = ?")
                .append(")")
                .toString();
    }
}
