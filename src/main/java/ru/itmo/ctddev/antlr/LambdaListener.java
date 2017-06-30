// Generated from C:/Users/Anna/IdeaProjects/Type-Theory/src/main/java/ru/itmo/ctddev/antlr\Lambda.g4 by ANTLR 4.7
package ru.itmo.ctddev.antlr;

    import ru.itmo.ctddev.reduction.Lambda;
    import ru.itmo.ctddev.reduction.Abstraction;
    import ru.itmo.ctddev.reduction.Application;
    import ru.itmo.ctddev.reduction.Variable;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link LambdaParser}.
 */
public interface LambdaListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link LambdaParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(LambdaParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link LambdaParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(LambdaParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link LambdaParser#application}.
	 * @param ctx the parse tree
	 */
	void enterApplication(LambdaParser.ApplicationContext ctx);
	/**
	 * Exit a parse tree produced by {@link LambdaParser#application}.
	 * @param ctx the parse tree
	 */
	void exitApplication(LambdaParser.ApplicationContext ctx);
	/**
	 * Enter a parse tree produced by {@link LambdaParser#abstraction}.
	 * @param ctx the parse tree
	 */
	void enterAbstraction(LambdaParser.AbstractionContext ctx);
	/**
	 * Exit a parse tree produced by {@link LambdaParser#abstraction}.
	 * @param ctx the parse tree
	 */
	void exitAbstraction(LambdaParser.AbstractionContext ctx);
	/**
	 * Enter a parse tree produced by {@link LambdaParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterAtom(LambdaParser.AtomContext ctx);
	/**
	 * Exit a parse tree produced by {@link LambdaParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitAtom(LambdaParser.AtomContext ctx);
}