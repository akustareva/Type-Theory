package ru.itmo.ctddev;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import ru.itmo.ctddev.antlr.LambdaLexer;
import ru.itmo.ctddev.antlr.LambdaParser;
import ru.itmo.ctddev.entities.Lambda;
import ru.itmo.ctddev.entities.Type;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {
    private static String INPUT_FILE_EXTENSION = ".in";
    private static String OUTPUT_FILE_EXTENSION = ".out";
    private static String NORMALIZE = "normalize";
    private static String TYPE = "infer_type";

    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            System.err.println(getHelpMessage());
            return;
        }
        String fileName;
        String inputFileName;
        if (args[1].contains(".")) {
            fileName = args[1].substring(0, args[1].indexOf("."));
            inputFileName = args[1];
        } else {
            fileName = args[1];
            inputFileName = fileName + INPUT_FILE_EXTENSION;
        }
        String outputFileName = fileName + OUTPUT_FILE_EXTENSION;
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
        PrintWriter writer = new PrintWriter(outputFileName, "UTF-8");
        if (NORMALIZE.equalsIgnoreCase(args[0])) {
            Lambda reduced = lambda.reduce();
            while (reduced != null) {
                lambda = reduced;
                reduced = lambda.reduce();
            }
            writer.print(lambda);
        } else if (TYPE.equalsIgnoreCase(args[0])) {
            TypeResolver resolver = new TypeResolver(lambda);
            Type type = resolver.resolve();
            if (type != null) {
                // TODO: println type & context
            } else {
                writer.println("Лямбда-выражение не имеет типа.");
            }
        } else {
            System.err.println(getHelpMessage("Unexpected operation."));
        }
        writer.close();
    }

    private static String getHelpMessage(String prefix) {
        if (!prefix.equals("") && !prefix.endsWith("\n")) {
            prefix += "\n";
        }
        return prefix +
            "Usage: java -jar Type-Theory-1.0-SNAPSHOT.jar <operation> <filename>\n" +
            "Please, specify the operation and input filename.\n" +
            "Available operations:\n\t" +
                "* " + NORMALIZE + "    for normalization of lambda (if it has normal form)\n\t" +
                "* " + TYPE +       "   for type inference (in simply typed lambda calculus)\n" +
            "You can specify filename without extension then we will use \'.in\' by default. For example, " +
            "if you specify \'task1\' then we will read input from file \'task1.in\'.";
    }

    private static String getHelpMessage() {
        return getHelpMessage("");
    }
}
