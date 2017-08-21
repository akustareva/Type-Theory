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
        boolean isSolvable = solveEquationsSystem();
        if (!isSolvable) {
            return null;
        } else {
            for (Map.Entry<String, Type> entry : typeByName.entrySet()) {
                lambdaType = lambdaType.substitute(entry.getKey(), entry.getValue());
            }
            return lambdaType;
        }
    }

    public Map<Variable, Type> getResolvedTypes() {
        Map<Variable, Type> resolvedTypes = new HashMap<>();
        for (Map.Entry<Variable, String> entry : variableTypeName.entrySet()) {
            resolvedTypes.put(entry.getKey(), typeByName.get(entry.getValue()));
        }
        return resolvedTypes;
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

    private boolean solveEquationsSystem() {
        boolean wasModified = true;
        while (wasModified) {
            wasModified = false;
            for (TypeEquation equation : equations) {
                if (!(equation.getLeft() instanceof TypeVariable) && equation.getRight() instanceof TypeVariable) {
                    equations.remove(equation);
                    equations.add(new TypeEquation(equation.getRight(), equation.getLeft()));
                    wasModified = true;
                    break;
                }
            }
            for (TypeEquation equation : equations) {
                if (equation.getLeft().equals(equation.getRight())) {
                    equations.remove(equation);
                    wasModified = true;
                    break;
                }
            }
            for (TypeEquation equation : equations) {
                if (equation.getLeft() instanceof TypeFunction && equation.getRight() instanceof TypeFunction) {
                    equations.remove(equation);
                    equations.add(new TypeEquation(((TypeFunction) equation.getLeft()).getLeft(),
                            ((TypeFunction) equation.getRight()).getLeft()));
                    equations.add(new TypeEquation(((TypeFunction) equation.getLeft()).getRight(),
                            ((TypeFunction) equation.getRight()).getRight()));
                    wasModified = true;
                    break;
                }
            }
            for (TypeEquation equation : equations) {
                if (equation.getLeft() instanceof TypeVariable) {
                    if (equation.getRight().contains(((TypeVariable) equation.getLeft()).getTypeName())) {
                        return false;
                    }
                    String typeNameForChecking = ((TypeVariable) equation.getLeft()).getTypeName();
                    Set<TypeEquation> equationsForRemoving = new HashSet<>();
                    Set<TypeEquation> equationsForAdding = new HashSet<>();
                    for (TypeEquation equationForChecking : equations) {
                        if (equation.equals(equationForChecking)) {
                            continue;
                        }
                        if (equationForChecking.getLeft().contains(typeNameForChecking) ||
                                equationForChecking.getRight().contains(typeNameForChecking)) {
                            equationsForRemoving.add(equationForChecking);
                            equationsForAdding.add(new TypeEquation(equationForChecking.getLeft().substitute(typeNameForChecking, equation.getRight()),
                                    equationForChecking.getRight().substitute(typeNameForChecking, equation.getRight())));
                            wasModified = true;
                        }
                    }
                    equations.removeAll(equationsForRemoving);
                    equations.addAll(equationsForAdding);
                    if (wasModified) {
                        break;
                    }
                }
            }
        }
        for (TypeEquation equation : equations) {
            if (equation.getLeft() instanceof TypeVariable) {
                typeByName.put(((TypeVariable) equation.getLeft()).getTypeName(), equation.getRight());
            } else {
                throw new IllegalStateException("Unexpected result after completion of solveEquationsSystem() method.");
            }
        }
        return true;
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

    public void useEquivalence() {
        mainLambda = useEquivalence(mainLambda, new HashSet<>());
        System.out.println("Expression was changed on alpha-equivalent: " + mainLambda);
    }

    private Lambda useEquivalence(Lambda lambda, Set<String> boundVars) {
        if (lambda instanceof Abstraction) {
            String boundVar = ((Abstraction) lambda).getVar();
            if (boundVars.contains(boundVar)) {
                String oldVarName = boundVar;
                String newVarName = Variable.getNewVarName(oldVarName);
                lambda = new Abstraction(newVarName, ((Abstraction) lambda).getLambda().substitute(oldVarName, new Variable(newVarName)));
                boundVar = newVarName;
            }
            boundVars.add(boundVar);
            return new Abstraction(((Abstraction) lambda).getVar(), useEquivalence(((Abstraction) lambda).getLambda(), boundVars));
        } else if (lambda instanceof Application) {
            Lambda func = useEquivalence(((Application) lambda).getFunc(), boundVars);
            Lambda arg = useEquivalence(((Application) lambda).getArg(), boundVars);
            return new Application(func, arg);
        }
        return lambda;
    }
}
