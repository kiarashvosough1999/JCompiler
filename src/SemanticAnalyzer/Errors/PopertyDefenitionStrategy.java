package SemanticAnalyzer.Errors;

import SemanticAnalyzer.JScope.Scope;
import java.util.List;

public class PopertyDefenitionStrategy implements ErrorStrategy {

    private final List<Scope> scopes;

    public PopertyDefenitionStrategy(List<Scope> scopes) {
        this.scopes = scopes;
    }

    @Override
    public ValidationResultModel checkValidity(ErrorProneEntity errorProneEntity) throws Exception {
        return null;
    }
}
