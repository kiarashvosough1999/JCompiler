package SemanticAnalyzer.JScope;

import SemanticAnalyzer.Models.ParameterModel;
import SemanticAnalyzer.Models.PositionModel;

import java.util.ArrayList;

public interface ParameteredScope extends Scope {

    public void addParameters(String name, String type, PositionModel namePosition, PositionModel typePosition);

    public ArrayList<ParameterModel> getParameters();

    public String getAsValue();
}
