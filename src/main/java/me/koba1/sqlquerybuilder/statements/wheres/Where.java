package me.koba1.sqlquerybuilder.statements.wheres;

public class Where {

    public static WhereStatement of(String key, Object value) {
        return new WhereStatement(key, value);
    }

    public static WhereStatement of(WhereStatement st) {
        WhereStatement asd = st;
        asd.getChilds().putAll(st.getChilds());
        return asd;
    }
}
