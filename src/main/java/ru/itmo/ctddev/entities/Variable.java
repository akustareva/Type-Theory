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

    public static String getNewVarName(String oldName) {
        StringBuilder newName = new StringBuilder(oldName);
        do {
            newName.append("\'");
        } while (Variable.isVariableNameExist(newName.toString()));
        return newName.toString();
    }

    @Override
    public String toString() {
        return var;
    }

    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }

    @Override
    public boolean equals(Object o) {
        return !(o == null || !(o instanceof Variable)) && this.hashCode() == o.hashCode();
    }
}
