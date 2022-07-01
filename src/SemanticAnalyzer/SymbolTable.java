package SemanticAnalyzer;

import Constants.Constants;
import SemanticAnalyzer.Models.PositionModel;
import SemanticAnalyzer.SymbolValues.SymbolValue;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class SymbolTable {

    private final Map<String, SymbolValue> table;

    public Set<Map.Entry<String, SymbolValue>> getEntrySet() {
        return table.entrySet();
    }

    public SymbolTable() {
        this.table = new HashMap<>();
    }

    public void insert(SymbolValue value) {
        this.table.put(value.getName(), value);
    }

    public void insertRedundant(SymbolValue value, PositionModel positionModel) {
        String name = value.getName() + "_" + positionModel.line() + "_" + positionModel.column();
        this.table.put(name, value);
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
