package ru.itmo.ctddev.entities;

public class TypeFunction implements Type {
    private Type left;
    private Type right;

    public TypeFunction(Type left, Type right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public boolean contains(String typeName) {
        return left.contains(typeName) || right.contains(typeName);
    }

    @Override
    public Type substitute(String substName, Type substType) {
        return new TypeFunction(left.substitute(substName, substType), right.substitute(substName, substType));
    }

    public Type getLeft() {
        return left;
    }

    public Type getRight() {
        return right;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        if (left instanceof TypeVariable) {
            builder.append(left);
        } else {
            builder.append("(").append(left).append(")");
        }
        builder.append(" -> ");
        if (right instanceof TypeVariable) {
            builder.append(right);
        } else {
            builder.append("(").append(right).append(")");
        }
        return builder.toString();
    }

    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }

    @Override
    public boolean equals(Object o) {
        return !(o == null || !(o instanceof TypeFunction)) && this.hashCode() == o.hashCode();
    }
}
