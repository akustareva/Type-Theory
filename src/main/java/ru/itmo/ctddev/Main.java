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
import java.io.PrintWriter;

public class Main {
    private static String INPUT_FILE_EXTENSION = ".in";
    private static String OUTPUT_FILE_EXTENSION = ".out";

    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.err.println("Please, specify the filename without extension. For example, \'task1\'.");
            return;
        }
        String inputFileName = args[0] + INPUT_FILE_EXTENSION;
        String outputFileName = args[0] + OUTPUT_FILE_EXTENSION;
        BufferedReader reader = new BufferedReader(new FileReader(inputFileName));
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
        PrintWriter writer = new PrintWriter(outputFileName, "UTF-8");
        writer.print(lambda);
        writer.close();
    }
}
