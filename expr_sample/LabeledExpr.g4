grammar LabeledExpr;  //功能和Expr.g4一样,只是将语法和词法规则分成两个文件
import CommonLexerRules;

prog : stat+ ;

stat : expr NEWLINE              # printExpr
     | ID '=' expr NEWLINE       # assign
     | NEWLINE                   # blank
     ;

expr : expr op=('*'|'/') expr    # MulDiv
     | expr op=('+'|'-') expr    # AddSub
     | INT                       # int
     | ID                        # id
     | '(' expr ')'              # parens
     ;

MUL  : '*' ; //为上述语法中的*命名
DIV  : '/' ;
ADD  : '+' ;
SUB  : '-' ;