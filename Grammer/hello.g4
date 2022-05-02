grammar hello;


start: (importStatement | classDecleration | localDecelration)*;

localDecelration: (variableDecleration | functionDecleration | expression | forStatement | whileStatement | ifStatement | matchStatement | functionCall);
classProperties: (variableDecleration | functionDecleration );



importStatement: ('import' importHiarachy);
importHiarachy: allowedNameingProperties | allowedNameingProperties'.''_' | allowedNameingProperties'.'(importHiarachy | multipleImport);
multipleImport: '{'(allowedNameingProperties',' | allowedNameingProperties | speceficImportFromClass)*'}';
speceficImportFromClass: allowedNameingProperties '=>' allowedNameingProperties;



variableDecleration: ('var' | 'let') allowedNameingProperties ':' dataTypesDecleration '=' variableAssignment+;
variableAssignment: stringInput | numbersInput
                                | 'new' (dataTypesDecleration | 'Array')+
                                (arrayDeclerationOfDataTypes*
                                '(' (array | stringInput | numbersInput)* ')') ;



classDecleration: 'class' dataTypesDecleration ('extend' dataTypesDecleration)*
                 ('implement' dataTypesDecleration (withStatement) )*
                 ('('inputVariable')')*
                  '{' classProperties* '}';
withStatement: ('with' dataTypesDecleration | ','withStatement)*;


functionDecleration: 'def' allowedNameingProperties+ ('('inputVariable*')')
                                   (':' (dataTypesDecleration || '[' dataTypesDecleration ']'))*
                                    '{' localDecelration* ('return' (Letter)*)*'}';
functionCall: allowedNameingProperties'(' inputVariable* | stringInput* ')';


forStatement: 'for''(' (iteratorfor | stepfor) ')' '{' localDecelration* '}';
iteratorfor: allowedNameingProperties+ '<-' allowedNameingProperties+;
stepfor: allowedNameingProperties+ '<-' range ('step' Numbers)* (';' stepfor)*;


whileStatement: 'while' conditionHolder  '{' localDecelration* '}' | 'do''{' localDecelration* '}' 'while' conditionHolder;

ifStatement: ('if' | 'elif') conditionHolder '{' localDecelration* '}' | ifStatement (elseStatement | elifStatement);
elseStatement:'else''{' localDecelration* '}';
elifStatement: 'elif' conditionHolder '{' localDecelration* '}';

matchStatement: allowedNameingProperties 'match' '{' caseStatement '}';
caseStatement: 'case' ( allowedNameingProperties |stringInput | numbersInput | caseTuple | '_') '=>' localDecelration* ('break')* caseStatement* ;
caseTuple: allowedNameingProperties'(' tupleInput+ ')' ;
tupleInput: ( allowedNameingProperties | stringInput | numbersInput | '_' | '_*') | ','tupleInput  ;


expression:  not= '~' expression
                                    | expression power='**' expression
                                    | unary=('-' |'+') expression
                                    | expression unaryOP=('++' | '--')
                                    | expression mult=('*' | '/' | '//' | '%') expression
                                    | expression addOrminize=( '+' | '-' ) expression
                                    | expression stream=( '<<' | '>>' ) expression
                                    | expression bitwiseOP=( '&' | '|' | '^' ) expression
                                    | expression compareOP=( '==' | '!=' | '<>' ) expression
                                    | expression compareOP1=( '>=' | '<=' | '<' | '>' ) expression
                                    | expression compareOP2=( 'not' | 'and' | 'or' | '||' | '&&' ) expression
                                    | allowedNameingProperties assignmentOP=( '=' | '+=' | '/=' | '*=' | '-=' | '**=' ) expression
                                    | '(' expression ')'
                                    | atom
                                    | ' \n' expression*
                                    ;


atom: numbersInput | allowedNameingProperties | stringInput;
allowedNameingProperties: ((Underline)* Letter (Underline | Numbers)*)+;
conditionHolder: '(' condition ')';
condition: expression || BOOL;
stringInput: '"'( Letter | LetterAndNumbers | Numbers | stringInput )*'"';
numbersInput: Numbers | (Numbers'.'Numbers ('-'Numbers)*);
array: '[' arrayInputs* ']' ;
arrayInputs: (stringInput | numbersInput | BOOL | allowedNameingProperties) | arrayInputs',' ;
arrayDeclerationOfDataTypes: '['(DataTypes | Letter)+']';
inputVariable: Letter':'dataTypesDecleration | Letter':'dataTypesDecleration','inputVariable;
range: '('Numbers','Numbers')';
dataTypesDecleration: DataTypes |  allowedNameingProperties ;

//newLine: '\n';
WS:[ \t\r\n]+ -> skip;
COMMENT: '/*' .*? '*/' -> channel(HIDDEN);// match anything between /* and */ ;
DataTypes: 'Int' | 'String' | 'Double' | 'Float' | 'Char' | 'Bool';
BOOL : 'true' | 'false';
fragment UPPERLETTER: [A-Z];
fragment LOWERLETTER: [a-z];
fragment NUMBERS: [0-9];
fragment LETTERAndNUMBERS: [a-zA-Z0-9];
fragment UnderlineLetter:[_];
Letter: (UPPERLETTER | LOWERLETTER)+;
Numbers: NUMBERS+;
OneNumber:NUMBERS;
LetterAndNumbers: LETTERAndNUMBERS+;
Underline:UnderlineLetter+;