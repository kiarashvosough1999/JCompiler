package SemanticAnalyzer;

public class ParameterModel {

    final private String name;

    final private String type;

    public ParameterModel(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }
}
