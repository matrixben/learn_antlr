lexer grammar CommonLexerRules;

/**抽取出单独的文件保存词法规则,可用于不同的语法分析器 */
ID : [a-zA-Z]+ ;
INT : [0-9]+ ;
NEWLINE : '\r'? '\n' ;
WS : [ \t]+ -> skip ;
