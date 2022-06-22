package SemanticAnalyzer;

import Constants.Constants;
import SemanticAnalyzer.SymbolValues.SymbolValue;
import java.util.HashMap;
import java.util.Map;

public class SymbolTable {

    private final Map<String, SymbolValue> table;

    public SymbolTable() {
        this.table = new HashMap<>();
    }

    public void insert(SymbolValue value) {
        this.table.put(value.getName(), value);
    }

    public SymbolValue getSymbolValueFor(String key) {
        return table.get(key);
    }
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<String,SymbolValue> entry : this.table.entrySet()){
            builder.append(Constants.Key);
            builder.append(Constants.Colon);
            builder.append(entry.getKey());
            builder.append(" ");
            builder.append(entry.getValue().toString());
            builder.append("\n");
        }
        return builder.toString();
    }
}
