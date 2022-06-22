package SemanticAnalyzer.Errors;

public interface ErrorStrategy {

    ValidationResultModel checkValidity(ErrorProneEntity errorProneEntity) throws Exception;
}
