package SemanticAnalyzer.Models;

import java.util.Stack;

public record DFSSearchResultModel<T>(Boolean isFounded, Stack<T> graphStack) {}
