package ru.itmo.ctddev.entities;

public class TypeEquation {
    private Type left;
    private Type right;

    public TypeEquation(Type left, Type right) {
        this.left = left;
        this.right = right;
    }

    public Type getLeft() {
        return left;
    }

    public Type getRight() {
        return right;
    }

    @Override
    public String toString() {
        return left + " = " + right;
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
