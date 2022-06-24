package SemanticAnalyzer.Validators.Redundancy;

import SemanticAnalyzer.JScope.Scope;
import SemanticAnalyzer.Validators.ErrorProneEntity;
import SemanticAnalyzer.Validators.ValidationResultModel;
import SemanticAnalyzer.Validators.ValidationStrategy;

import java.util.List;

public class PopertyDefenitionStrategy implements ValidationStrategy {

    private final List<Scope> scopes;

    public PopertyDefenitionStrategy(List<Scope> scopes) {
        this.scopes = scopes;
    }

    @Override
    public ValidationResultModel checkValidity(ErrorProneEntity errorProneEntity) throws Exception {
        return null;
    }
}
