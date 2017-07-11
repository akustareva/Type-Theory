package ru.itmo.ctddev.reduction;

import java.util.List;
import java.util.Set;

public interface Lambda {
    Set<String> getIncludedVarsNames();
    Lambda substitute(String substVar, Lambda substLambda);
    default Lambda reduce() {
        return null;
    }
    default String removeLeadingSpaces(String str) {
        int pos = 0;
        while(str.charAt(pos) == ' ' || str.charAt(pos) ==  '\t' || str.charAt(pos) == '\r' || str.charAt(pos) == '\n') {
            pos++;
        }
        return str.substring(pos);
    }
}
