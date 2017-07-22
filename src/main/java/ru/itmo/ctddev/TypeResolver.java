package ru.itmo.ctddev;

import ru.itmo.ctddev.entities.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TypeResolver {
    private Lambda mainLambda;
    private Map<Variable, String> variableTypeName = new HashMap<>();
    private Map<String, Type> typeByName = new HashMap<>();
    private Set<TypeEquation> equations = new HashSet<>();

    public TypeResolver(Lambda lambda) {
        this.mainLambda= lambda;
    }

    public Type resolve() {
        Type lambdaType = inferType(mainLambda);

        return null;
    }

    private Type inferType(Lambda lambda) {
        if (lambda instanceof Variable) {
            return getTypeForVariable((Variable) lambda);
        }
        if (lambda instanceof Abstraction) {
            Type varType = getTypeForVariable(new Variable(((Abstraction) lambda).getVar()));
            Type lambdaType = inferType(((Abstraction) lambda).getLambda());
            return new TypeFunction(varType, lambdaType);
        }
        if (lambda instanceof Application) {
            Type funcType = inferType(((Application) lambda).getFunc());
            Type argType = inferType(((Application) lambda).getArg());
            Type newTypeVar = createNewTypeVariable();
            equations.add(new TypeEquation(funcType, new TypeFunction(argType, newTypeVar)));
            return newTypeVar;
        }
        throw new IllegalStateException("Error in inferType(): unknown type of lambda");
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
