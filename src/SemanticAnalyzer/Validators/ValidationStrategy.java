package SemanticAnalyzer.Validators;

import SemanticAnalyzer.Models.ValidationResultModel;

public interface ValidationStrategy {

    ValidationResultModel checkValidity(ErrorProneEntity errorProneEntity) throws Exception;
}
