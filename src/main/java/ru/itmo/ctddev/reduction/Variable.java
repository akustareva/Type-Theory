package ru.itmo.ctddev.reduction;

public class Variable implements Lambda {
    private String var;

    public Variable(String var) {
        this.var = var;
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

    @Override
    public String toString() {
        return var;
    }
}
