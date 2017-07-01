package ru.itmo.ctddev.reduction;

public class Application implements Lambda {
    private Lambda func;
    private Lambda arg;

    public Application(Lambda func, Lambda arg) {
        this.func = func;
        this.arg = arg;
    }

    @Override
    public Lambda substitute(String substVar, Lambda substLambda) {
        return new Application(func.substitute(substVar, substLambda), arg.substitute(substVar, substLambda));
    }

    @Override
    public Lambda reduce() {
        if (func instanceof Abstraction) {
            return ((Abstraction) func).getLambda().substitute(((Abstraction) func).getVar(), arg);
        }
        Lambda reducedFunc = func.reduce();
        if (reducedFunc != null) {
            return new Application(reducedFunc, arg);
        }
        Lambda reducedArg = arg.reduce();
        if (reducedArg != null) {
            return new Application(func, reducedArg);
        }
        return null;
    }

    public Lambda getFunc() {
        return func;
    }

    public Lambda getArg() {
        return arg;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        if (func instanceof Abstraction) {
            builder.append("(").append(func).append(")");
        } else {
            builder.append(func.toString());
        }
        builder.append(" ");
        if (arg instanceof Variable) {
            builder.append(arg);
        } else {
            builder.append("(").append(arg).append(")");
        }
        return builder.toString();
    }
}
