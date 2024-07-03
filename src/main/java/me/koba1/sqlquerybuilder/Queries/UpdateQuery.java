package me.koba1.sqlquerybuilder.Queries;

import me.koba1.sqlquerybuilder.statements.IStatement;
import me.koba1.sqlquerybuilder.statements.sets.SetStatement;
import me.koba1.sqlquerybuilder.statements.wheres.WhereStatement;
import me.koba1.sqlquerybuilder.statements.wheres.WhereStatementHolder;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class UpdateQuery {

    private final String table;
    private final List<IStatement> statements;

    public UpdateQuery(String table, List<IStatement> statements) {
        this.table = table;
        this.statements = statements;
    }

    // @Override
    public PreparedStatement getStatement() throws SQLException {
        List<WhereStatement> wheres = statements.stream().filter(el -> el instanceof WhereStatement).map(WhereStatement.class::cast).toList();
        List<SetStatement> sets = statements.stream().filter(el -> el instanceof SetStatement).map(SetStatement.class::cast).toList();
        List<String> setsKeys = sets.stream().map(el -> el.getKey() + " = ?").toList();

        // build query string
        String query = "UPDATE " + table + " ";
        String set = "SET " + String.join(", ", setsKeys);
        StringBuilder where = new StringBuilder("WHERE ");
        for (WhereStatement st : wheres) {
            where.append(st.toString());
        }

        PreparedStatement statement = null;
        return null;
    }
}
