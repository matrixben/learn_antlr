grammar LibExpr;  //功能和Expr.g4一样,只是将语法和词法规则分成两个文件
import CommonLexerRules;

prog : stat+ ;

stat : expr NEWLINE
     | ID '=' expr NEWLINE
     | NEWLINE
     ;

expr : expr ('*'|'/') expr
     | expr ('+'|'-') expr
     | INT
     | ID
     | '(' expr ')'
     ;
