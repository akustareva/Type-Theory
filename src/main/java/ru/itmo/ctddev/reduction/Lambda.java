package ru.itmo.ctddev.reduction;

public interface Lambda {
    Lambda substitute(String substVar, Lambda substLambda);
    default Lambda reduce() {
        return null;
    }
}
