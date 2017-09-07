grammar Expr;
/**起始规则,语法分析的起点 */
prog:	stat+ ;

stat:   expr NEWLINE
    |   ID '=' expr NEWLINE
    |   NEWLINE
    ;

expr:	expr ('*'|'/') expr
    |	expr ('+'|'-') expr
    |	INT
    |   ID
    |	'(' expr ')'
    ;

/**词法规则 */
ID      : [a-zA-Z]+ ;
NEWLINE : [\r\n]+ ;
INT     : [0-9]+ ;
WS      : [ \t]+ -> skip ;
