package SemanticAnalyzer;

import SemanticAnalyzer.Models.ParentClassModel;
import SemanticAnalyzer.Models.PositionModel;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.List;

public class Helper {

    static public Boolean isPrimitiveType(String type) {
        return switch (type) {
            case "int", "bool", "float", "str", "void" -> true;
            default -> false;
        };
    }

    static public List<ParentClassModel> extractParentClasses(List<TerminalNode> terminalNodeList) {
        return terminalNodeList.parallelStream()
                .skip(1)
                .map(s -> new ParentClassModel(
                        s.getText(),
                        new PositionModel(
                                s.getSymbol().getLine(),
                                s.getSymbol().getCharPositionInLine()
                        )
                        )
                )
                .toList();
    }
}
