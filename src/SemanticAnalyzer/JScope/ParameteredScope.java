package SemanticAnalyzer.JScope;

import SemanticAnalyzer.ParameterModel;

import java.util.ArrayList;

public interface ParameteredScope extends Scope {

    public void addParameters(String name, String type);

    public ArrayList<ParameterModel> getParameters();

    public String getAsValue();
}
