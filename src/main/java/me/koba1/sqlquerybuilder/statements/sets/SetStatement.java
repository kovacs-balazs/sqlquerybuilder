package me.koba1.sqlquerybuilder.statements.sets;

import lombok.Getter;
import me.koba1.sqlquerybuilder.statements.IStatement;
import me.koba1.sqlquerybuilder.statements.wheres.WhereStatement;

import java.util.ArrayList;
import java.util.List;

@Getter
public class SetStatement implements IStatement {

    private final String key;
    private final Object value;
    private final List<SetStatement> sets;

    public SetStatement(String key, Object value) {
        this.key = key;
        this.value = value;
        this.sets = new ArrayList<>();
    }

    public SetStatement append(SetStatement set) {
        this.sets.add(set);
        return this;
    }
}
