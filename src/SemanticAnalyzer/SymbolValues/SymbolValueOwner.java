package SemanticAnalyzer.SymbolValues;

import SemanticAnalyzer.JScope.ScopeType;

public enum SymbolValueOwner {

    program("Program"),

    classs("Class"),

    method("Method"),

    constructor("Constructor"),

    block("Block");

    private final String name;

    SymbolValueOwner(String name) {
        this.name = name;
    }

    public static SymbolValueOwner from(ScopeType scopeType) {
        switch (scopeType) {
            case program -> {
                return SymbolValueOwner.program;
            }
            case classs -> {
                return SymbolValueOwner.classs;
            }
            case method -> {
                return SymbolValueOwner.method;
            }
            case constructor -> {
                return SymbolValueOwner.constructor;
            }
            case block -> {
                return SymbolValueOwner.block;
            }
        }
        return null;
    }

    public boolean equalsName(String otherName) {
        return name.equals(otherName);
    }

    public String toString() {
        return this.name;
    }
}
