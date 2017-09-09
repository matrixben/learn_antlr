grammar JavaGammar;  //部分java语法规则，完整版见github.com/antlr/grammar-v4

//java源码语法分析的起始规则,先不管packge
compilationUnit
    : importDeclaration* typeDeclaration* EOF
    ;
importDeclaration
    :   'import' 'static'? qualifiedName ('.' '*')? ';'
    ;
//先不管枚举，接口，声明
typeDeclaration
    : classOrInterfaceModifier* classDeclaration
    ;
classOrInterfaceModifier
    :   (   'public'     // class or interface
        |   'protected'  // class or interface
        |   'private'    // class or interface
        |   'static'     // class or interface
        |   'abstract'   // class or interface
        |   'final'      // class only -- does not apply to interfaces
        |   'strictfp'   // class or interface
        )
    ;

classDeclaration
    : 'class' Identifier typeParameters?
      ('extends' typeType)?
      ('implements' typeList)?
      classBody
    ;
    
methodDeclaration
    : (typeType|'void') Identifier formalParameters ('[' ']')*
      ( methodBody
      | ';'
      )
    ;

//语法规则
methodBody
    :   block
    ;
block
    :   '{' blockStatement* '}'
    ;
blockStatement
    :   [a-zA-Z,.'";]+
    ;
qualifiedName
    :   Identifier ('.' Identifier)*
    ;
typeParameters
    : '<' typeParameter (',' typeParameter)* '>'
    ;
typeParameter
    : Identifier ('extends' typeBound)?
    ;
typeBound
    : typeType ('&' typeType)*
    ;
typeList
    : typeType (',' typeType)*
    ;
typeType
    : classOrInterfaceType ('[' ']')*
    | primitiveType ('[' ']')*
    ;
classOrInterfaceType
    :   Identifier typeArguments? ('.' Identifier typeArguments? )*
    ;
primitiveType
    :   'boolean'
    |   'char'
    |   'byte'
    |   'short'
    |   'int'
    |   'long'
    |   'float'
    |   'double'
    ;
typeArguments
    :   '<' typeArgument (',' typeArgument)* '>'
    ;
typeArgument
    :   typeType
    |   '?' (('extends' | 'super') typeType)?
    ;
classBody
    : '{' classBodyDeclaration '}'
    ;
classBodyDeclaration
    :   ';'
    ;
memberDeclaration
    :   methodDeclaration
    |   classDeclaration
    ;
formalParameters
    :   '(' formalParameterList? ')'
    ;
formalParameterList
    :   formalParameter (',' formalParameter)* (',' lastFormalParameter)?
    |   lastFormalParameter
    ;
formalParameter
    :   variableModifier* typeType variableDeclaratorId
    ;
lastFormalParameter
    :   variableModifier* typeType '...' variableDeclaratorId
    ;
variableDeclaratorId
    :   Identifier ('[' ']')*
    ;
variableModifier
    :   'final'
    ;
    
//Lexer
//Java Keywords
ABSTRACT      : 'abstract';
ASSERT        : 'assert';
BOOLEAN       : 'boolean';
BREAK         : 'break';
BYTE          : 'byte';
CASE          : 'case';
CATCH         : 'catch';
CHAR          : 'char';
CLASS         : 'class';
CONST         : 'const';
CONTINUE      : 'continue';
DEFAULT       : 'default';
DO            : 'do';
DOUBLE        : 'double';
ELSE          : 'else';
ENUM          : 'enum';
EXTENDS       : 'extends';
FINAL         : 'final';
FINALLY       : 'finally';
FLOAT         : 'float';
FOR           : 'for';
IF            : 'if';
GOTO          : 'goto';
IMPLEMENTS    : 'implements';
IMPORT        : 'import';
INSTANCEOF    : 'instanceof';
INT           : 'int';
INTERFACE     : 'interface';
LONG          : 'long';
NATIVE        : 'native';
NEW           : 'new';
PACKAGE       : 'package';
PRIVATE       : 'private';
PROTECTED     : 'protected';
PUBLIC        : 'public';
RETURN        : 'return';
SHORT         : 'short';
STATIC        : 'static';
STRICTFP      : 'strictfp';
SUPER         : 'super';
SWITCH        : 'switch';
SYNCHRONIZED  : 'synchronized';
THIS          : 'this';
THROW         : 'throw';
THROWS        : 'throws';
TRANSIENT     : 'transient';
TRY           : 'try';
VOID          : 'void';
VOLATILE      : 'volatile';
WHILE         : 'while';

//String Literals
StringLiteral
    :   '"' StringCharacters? '"'
    ;
fragment
StringCharacters
    :   StringCharacter+
    ;
fragment
StringCharacter
    :   ~["\\]
    ;

//Separators
LPAREN  : '(';
RPAREN  : ')';
LBRACE  : '{';
RBRACE  : '}';
LBRACK  : '[';
RBRACK  : ']';
SEMI    : ';';
COMMA   : ',';
DOT     : '.';

//parts of Operators

ASSIGN   : '=';
GT       : '>';
LT       : '<';
BANG     : '!';
TILDE    : '~';
QUESTION : '?';
COLON    : ':';
MUL      : '*';

ELLIPSIS : '...';
    
Identifier
    : JavaLetter JavaLetterOrDigit*
    ;
fragment
JavaLetter
    : [a-zA-Z$_]
    ;
fragment
JavaLetterOrDigit
    : [a-zA-Z0-9$_]
    ;
    
WS  : [ \t\r\n]+ -> skip ;
COMMENT
    :   '/*' .*? '*/' -> channel(HIDDEN)
    ;
LINE_COMMENT
    :   '//' ~[\r\n]* -> channel(HIDDEN)
    ;