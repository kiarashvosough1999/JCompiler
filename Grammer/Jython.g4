// Copyright (c) 2022 Kiarash Vosough

//  Permission is hereby granted, free of charge, to any person obtaining a copy
//  of this software and associated documentation files (the "Software"), to deal
//  in the Software without restriction, including without limitation the rights
//  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
//  copies of the Software, and to permit persons to whom the Software is
//  furnished to do so, subject to the following conditions:

//  The above copyright notice and this permission notice shall be included in all
// copies or substantial portions of the Software.

// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
// IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
// FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
// AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
// LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
// OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
// SOFTWARE.

grammar Jython;

program : importclass* (classDef)* ;

importclass : 'import' importName = CLASSNAME ;

classDef : 'class' className = CLASSNAME ('(' classParentName = CLASSNAME (',' otherClassParentName = CLASSNAME)* ')')? '{' class_body* '}';

class_body : varDec | methodDec | constructor | arrayDec ;

varDec : variableType = (TYPE | CLASSNAME) varibaleName = ID ;

arrayDec : arrayType = (TYPE | CLASSNAME) '['arraySize = INTEGER']' arrayVaribaleName = ID ;

methodDec : 'def' methodReturnType = (TYPE | CLASSNAME |'void') methodName = ID '(' methodParameters = parameter* ')''{' methodStatement = statement* '}';

constructor : 'def' cosntructorType = (TYPE | CLASSNAME) '(' constructorParameters = parameter* ')''{' (statement)* '}' ;

parameter : varDec (',' varDec)* ;

statement : varDec | assignment | print_statment | method_call | return_statment |if_statment | while_statment | if_else_statment | for_statment;

return_statment : 'return' exp ;

condition_list : condition (('or'|'and') condition)* ;

condition : BOOL | prefixexp | (exp) relational_operators (exp);

if_statment : 'if' '(' condition_list ')' '{' statement* '}';

while_statment : 'while' '(' condition_list ')' '{' statement* '}' ;

if_else_statment :'if' '(' condition_list ')' '{' statement* '}' ('elif' '(' condition_list ')' '{' statement* '}')* 'else' '{' statement* '}' ;

print_statment : 'print' '(' (prefixexp | (TYPE | CLASSNAME) args | INTEGER | STRING | BOOL) ')' ;

for_statment : 'for' ID 'in' ID '{' statement* '}' | 'for' ID 'in' 'range''('INTEGER (',' INTEGER)? (',' INTEGER)? ')' '{' statement* '}' ;

method_call : ID ('.')? args ;

assignment : prefixexp assignment_operators exp | varDec assignment_operators exp | arrayDec '=' (TYPE | CLASSNAME) args ('['INTEGER']') ;

exp :'none' | BOOL | INTEGER | STRING | FLOAT | prefixexp | exp arithmetic_operator exp | (TYPE | CLASSNAME) args | '('exp')' | ID args ;

prefixexp : ID | prefixexp '[' INTEGER ']' | prefixexp '.' ID | prefixexp '.' ID args ;

args : '(' (explist)? ')' ;

explist : exp (',' exp)*;

arithmetic_operator: '+' | '-' | '*' | '/' | '%' ;

relational_operators : '<' | '>' | '<=' | '>=' | '==' | '!=' ;

assignment_operators : '=' | '+=' | '-=' | '*=' | '/=' ;


TYPE: INT | FLOATING_POINT | STRING | BOOL;
INT: 'int';
FLOATING_POINT: 'float';
STRING: 'string';
BOOL: 'bool';
CLASSNAME: [A-Z] (LETTER|DIGIT)*;
ID: [a-z]([A-Za-z_])*;
LETTER: [A-Za-z];
INTEGER: DIGIT+;
FLOAT: (DIGIT)+ '.' (DIGIT)+;
DIGIT: [0-9];



OPEN_COMMENT : '#*' ;
CLOSE_COMMENT : '*#' ;
COMMENT : OPEN_COMMENT (COMMENT | .)*? CLOSE_COMMENT -> skip ;
ONE_LINE_COMMENT : '#' (~ '\n')* '\n'? -> skip ;

WHITESPACE : [ \t\r\n\f]+ -> skip ;