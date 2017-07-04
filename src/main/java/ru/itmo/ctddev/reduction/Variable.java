package ru.itmo.ctddev.reduction;

import java.util.HashSet;
import java.util.Set;

public class Variable implements Lambda {
    private static Set<String> allExpressionVariablesNames = new HashSet<>();
    private String var;

    public Variable(String var) {
        var = removeLeadingSpaces(var);
        this.var = var;
        allExpressionVariablesNames.add(var);
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

    public static boolean isVariableNameExists(String name) {
        return allExpressionVariablesNames.contains(name);
    }

    @Override
    public String toString() {
        return var;
    }
}
