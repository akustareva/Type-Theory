package ru.itmo.ctddev;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import ru.itmo.ctddev.antlr.LambdaLexer;
import ru.itmo.ctddev.antlr.LambdaParser;
import ru.itmo.ctddev.reduction.Lambda;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    private static String INPUT_FILE_EXTENSION = ".in";

    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.err.println("Please, specify the filename without extension. For example, \'task1\'.");
            return;
        }
        String fileName = args[0] + INPUT_FILE_EXTENSION;
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        StringBuilder expression = new StringBuilder();
        String line = reader.readLine();
        while (line != null) {
            expression.append(line).append(" ");
            line = reader.readLine();
        }
        CharStream input = CharStreams.fromString(expression.toString());
        LambdaLexer lexer = new LambdaLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        LambdaParser parser = new LambdaParser(tokens);
        LambdaParser.ExpressionContext expressionContext = parser.expression();
        Lambda lambda = expressionContext.lambda;
        Lambda reduced = lambda.reduce();
        while (reduced != null) {
            lambda = reduced;
            reduced = lambda.reduce();
        }
        System.out.println(lambda);
    }
}
