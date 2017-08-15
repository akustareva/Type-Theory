package ru.itmo.ctddev.entities;

public interface Type {
    boolean contains(String typeName);
    Type substitute(String substName, Type substType);
    @Override
    String toString();
    @Override
    int hashCode();
    @Override
    boolean equals(Object o);
}
