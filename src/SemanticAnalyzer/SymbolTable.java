package SemanticAnalyzer;

import SemanticAnalyzer.SymbolValues.SymbolValue;

import java.util.HashMap;
import java.util.Map;

public class SymbolTable {

    private final Map<String, SymbolValue> table;

    public SymbolTable() {
        this.table = new HashMap<>();
    }

    public void insert(SymbolValue value) {
        this.table.put(value.getKind().toString() + "_" + value.getName(), value);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<String,SymbolValue> entry : this.table.entrySet()){
            builder.append(entry.getKey());
            builder.append(" ");
            builder.append(entry.getValue().toString());
            builder.append("\n");
        }
        return builder.toString();
    }
}
