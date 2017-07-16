grammar Lambda;

@parser::header {
import ru.itmo.ctddev.entities.Lambda;
import ru.itmo.ctddev.entities.Abstraction;
import ru.itmo.ctddev.entities.Application;
import ru.itmo.ctddev.entities.Variable;
}

expression returns [Lambda lambda]
    : application? abstraction {
        if ($ctx.application() == null) {
            $lambda = $abstraction.lambda;
        } else {
            $lambda = new Application($application.lambda, $abstraction.lambda);
        }
    }
    | application                                       { $lambda = $application.lambda; }
    ;

application returns [Lambda lambda]
    : app=application atom                              { $lambda = new Application($app.lambda, $atom.lambda); }
    | atom                                              { $lambda = $atom.lambda; }
    ;

abstraction returns [Lambda lambda]
    : LAMBDA VAR DOT expression                         { $lambda = new Abstraction($VAR.text, $expression.lambda); }
    ;

atom returns [Lambda lambda]
    : LEFT_PARENTHESIS expression RIGHT_PARENTHESIS     { $lambda = $expression.lambda; }
    | VAR                                               { $lambda = new Variable($VAR.text); }
    ;

VAR
    : [ \t\r\n]?[a-z][a-z0-9']*
    ;

LAMBDA
    : '\\'
    ;

DOT
    : '.'
    ;

LEFT_PARENTHESIS
    : '('
    ;

RIGHT_PARENTHESIS
    : ')'
    ;

WS
   : [ \t\r\n]+ -> skip
   ;