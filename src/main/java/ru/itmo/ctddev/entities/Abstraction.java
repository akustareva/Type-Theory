package ru.itmo.ctddev.entities;

import java.util.*;

public class Abstraction implements Lambda {
    private String var;
    private Lambda lambda;
    private Set<String> includedVars;

    public Abstraction(String var, Lambda lambda) {
        this.var = removeLeadingSpaces(var);
        this.lambda = lambda;
        this.includedVars = new HashSet<>();
        includedVars.add(var);
        includedVars.addAll(lambda.getIncludedVarsNames());
    }

    @Override
    public Set<String> getIncludedVarsNames() {
        return Collections.unmodifiableSet(includedVars);
    }

    @Override
    public Lambda substitute(String substVar, Lambda substLambda) {
        if (var.equals(substVar)) {
            return this;
        }
        if (substLambda.getIncludedVarsNames().contains(var)) {
            String oldVarName = var;
            var = getNewVarName(var);
            lambda = lambda.substitute(oldVarName, new Variable(var));
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

    private String getNewVarName(String oldName) {
        StringBuilder newName = new StringBuilder(oldName);
        do {
            newName.append("\'");
        } while (Variable.isVariableNameExist(newName.toString()));
        return newName.toString();
    }

    @Override
    public String toString() {
        return "\\" + var + '.' + lambda;
    }
}
