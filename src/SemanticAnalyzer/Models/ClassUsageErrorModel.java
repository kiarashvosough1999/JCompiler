package SemanticAnalyzer.Models;

import Constants.Constants;

public record ClassUsageErrorModel(String name, PositionModel namePosition) {

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Error102 : in line ");
        stringBuilder.append(Constants.LeftBracket);
        stringBuilder.append(namePosition.line());
        stringBuilder.append(Constants.Colon);
        stringBuilder.append(namePosition.column());
        stringBuilder.append(Constants.RightBracket);
        stringBuilder.append(" , can not find class ");
        stringBuilder.append(Constants.LeftBracket);
        stringBuilder.append(name);
        stringBuilder.append(Constants.RightBracket);
        return stringBuilder.toString();
    }
}
