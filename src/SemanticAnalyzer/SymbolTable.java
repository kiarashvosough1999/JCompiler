package SemanticAnalyzer;

import java.util.HashMap;
import java.util.Map;

public class SymbolTable {

    private final Map<String,SymbolValue> table;

    public SymbolTable() {
        this.table = new HashMap<>();
    }

    public void insert(String symbol, SymbolValue value) {
        this.table.put(symbol, value);
    }
}
