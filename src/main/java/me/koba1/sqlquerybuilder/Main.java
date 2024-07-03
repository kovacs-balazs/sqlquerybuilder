package me.koba1.sqlquerybuilder;

import me.koba1.sqlquerybuilder.statements.wheres.Where;
import me.koba1.sqlquerybuilder.statements.wheres.WhereStatement;

/**
 * Hello world!
 *
 */
public class Main {
    public static void main( String[] args ) {
        WhereStatement st = Where.of("guildId", "?").or(Where.of(Where.of(Where.of(new WhereStatement("rankID", "10", WhereStatement.WhereStatementType.GREATER)).and("lastNumber", "?"))).and("ID", "?"));
        System.out.println(st.toString());
    }
}// WHERE guildId = 637690596378804234 OR (rankID = 1088886233100472471 AND lastNumber = 0 AND ID = 1);
