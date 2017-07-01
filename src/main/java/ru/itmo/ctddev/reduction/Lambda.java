package ru.itmo.ctddev.reduction;

public interface Lambda {
    Lambda substitute(String substVar, Lambda substLambda);
    default Lambda reduce() {
        return null;
    }
    default String removeLeadingSpaces(String str) {
        return str.substring(str.lastIndexOf(' ') + 1);
    }
}
