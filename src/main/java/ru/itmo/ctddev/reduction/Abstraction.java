package ru.itmo.ctddev.reduction;

public class Abstraction implements Lambda {
    private String var;
    private Lambda lambda;

    public Abstraction(String var, Lambda lambda) {
        this.var = removeLeadingSpaces(var);
        this.lambda = lambda;
    }

    @Override
    public Lambda substitute(String substVar, Lambda substLambda) {
        if (var.equals(substVar)) {
            return this;
        }
        if (substLambda instanceof Variable) {
            if (((Variable) substLambda).getVar().equals(var)) {
                String oldVarName = var;
                do {
                    var += "\'";
                } while (Variable.isVariableNameExists(var));
                lambda = lambda.substitute(oldVarName, new Variable(var));
            }
        }
        return new Abstraction(var, lambda.substitute(substVar, substLambda));
    }

    @Override
    public Lambda reduce() {
        Lambda reducedLambda = lambda.reduce();
        if (reducedLambda != null) {
            return new Abstraction(var, reducedLambda);
        }
        return null;
    }

    public String getVar() {
        return var;
    }

    public Lambda getLambda() {
        return lambda;
    }

    @Override
    public String toString() {
        return "\\" + var + '.' + lambda;
    }
}
