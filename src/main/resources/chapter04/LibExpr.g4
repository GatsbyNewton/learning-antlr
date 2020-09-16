grammar LibExpr;
import CommonLexerRules;    // 导入CommonLexerRules.g4中的全部规则

prog: stat+;

stat: expr NEWLINE
    | ID '=' expr NEWLINE
    | NEWLINE
    ;

expr: expr ('*' | '/') expr
    | expr ('+' | '-') expr
    | INT
    | ID
    | '(' expr ')'
    ;