package SemanticAnalyzer.Validators;

public interface ValidationStrategy {

    ValidationResultModel checkValidity(ErrorProneEntity errorProneEntity) throws Exception;
}
