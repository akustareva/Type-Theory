package ru.itmo.ctddev.entities;

import java.util.*;

public class Variable implements Lambda {
    private static Set<String> wholeExpressionVariablesNames = new HashSet<>();
    private String var;

    public Variable(String var) {
        var = removeLeadingSpaces(var);
        this.var = var;
        wholeExpressionVariablesNames.add(var);
    }

    @Override
    public Set<String> getIncludedVarsNames() {
        return Collections.singleton(var);
    }

    @Override
    public Lambda substitute(String substVar, Lambda substLambda) {
        if (var.equals(substVar)) {
            return substLambda;
        }
        return this;
    }

    public String getVar() {
        return var;
    }

    public static boolean isVariableNameExist(String name) {
        return wholeExpressionVariablesNames.contains(name);
    }

    @Override
    public String toString() {
        return var;
    }
}
