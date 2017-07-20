package ru.itmo.ctddev;

import ru.itmo.ctddev.entities.*;

import java.util.HashMap;
import java.util.Map;

public class TypeResolver {
    private Lambda lambda;
    private Map<Variable, String> variableTypeName = new HashMap<>();
    private Map<String, Type> typeByName = new HashMap<>();

    public TypeResolver(Lambda lambda) {
        this.lambda = lambda;
    }

    public Type resolve() {
        Type lambdaType = inferType();
        return null;
    }

    private Type inferType() {
        if (lambda instanceof Variable) {
            return getTypeForVariable((Variable) lambda);
        }
        if (lambda instanceof Abstraction) {

        }
        return null;
    }

    private Type getTypeForVariable(Variable variable) {
        if (!variableTypeName.containsKey(variable)) {
            variableTypeName.put(variable, createNewTypeVariable().getTypeName());
        }
        Type type = typeByName.get(variableTypeName.get(variable));
        if (type == null) {
            throw new IllegalStateException("There is no type for specified variable " + variable +
                    " with type name " + variableTypeName.get(variable));
        }
        return type;
    }

    private TypeVariable createNewTypeVariable() {
        String typeVariableName = TypeVariableNameGenerator.getNewName();
        TypeVariable type = new TypeVariable(typeVariableName);
        typeByName.put(typeVariableName, type);
        return type;
    }

    private static class TypeVariableNameGenerator {
        private static int id = 0;

        static String getNewName() {
            return "t" + id++;
        }
    }
}
