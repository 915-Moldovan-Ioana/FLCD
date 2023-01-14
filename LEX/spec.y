%{
    #include <stdio.h>
    #include <stdlib.h>
%}

%token plus
%token minus
%token mul
%token div
%token mod
%token assign
%token equal
%token notEqual
%token less
%token greater
%token lessOrEqual
%token greaterOrEqual

%token openCurlyBracket
%token closeCurlyBracket
%token openRoundBracket
%token closeRoundBracket
%token openSquareBracket
%token closeSquareBracket
%token colon
%token semicolon

%token array
%token while
%token const
%token do
%token else
%token if
%token int
%token string
%token char
%token bool
%token true
%token false
%token is

%token read
%token write

%token identifier
%token number
%token stringCh
%token character
%token boolean

%start program

%%

program : stmtlist
declaration : identifier is type
type : character | number | stringCh | boolean
stmtlist : stmt | stmt semicolon stmtlist
stmt : simplestmt | structstmt
simplestmt : asiignstmt | iostmt
assignstmt : identifier assign expression
expression : expression plus term | expression minus term | term
term : term mul factor | term div factor | term mod factor | factor
factor : openRoundBracket expression closeRoundBracket | identifier | int
iostmt : read identifier | write identifier | write expression | write char | see bool
arrayDeclaration : declaration openSquareBracket int closeSquareBracket
structstmt : ifstmt | whilestmt
logicalop : and | or 
ifstmt :  if openRoundBracket condlist closeRoundBracket openCurlyBracket stmtlist closeCurlyBracket | if condlist openCurlyBracket stmtlist closeCurlyBracket else openCurlyBracket stmtlist closeCurlyBracket
whilestmt : while openRoundBracket condlist closeRoundBracket openCurlyBracket stmtlist closeCurlyBracket
condlist : condition | condition logicalop condlist
condition : expression relation expression | bool
relation : less | lessOrEqual | equal | notEqual | greaterOrEqual | greater

%%

yyerror(char *s) {
    printf("%s\n", s);
}

extern FILE *yyin;

main(int argc, char ** argv) {
    if (argc > 1)
        yyin = fopen(argv[1], "r");
    if ((argc > 2) && (!strcmp(argv[2], "-d")))
        yydebug = 1;
    if (!yyparse())
        fprintf(stderr, "error\n");
}