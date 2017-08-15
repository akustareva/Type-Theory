package ru.itmo.ctddev.entities;

public class TypeVariable implements Type {
    private String typeName;

    public TypeVariable(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public boolean contains(String typeName) {
        return this.typeName.equals(typeName);
    }

    @Override
    public Type substitute(String substName, Type substType) {
        if (typeName.equals(substName)) {
            return substType;
        }
        return this;
    }

    public String getTypeName() {
        return typeName;
    }

    @Override
    public String toString() {
        return "\'" + typeName;
    }

    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }

    @Override
    public boolean equals(Object o) {
        return !(o == null || !(o instanceof TypeVariable)) && this.hashCode() == o.hashCode();
    }
}
