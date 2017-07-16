// Generated from C:/Users/Anna/IdeaProjects/Type-Theory/src/main/java/ru/itmo/ctddev/antlr\Lambda.g4 by ANTLR 4.7
package ru.itmo.ctddev.antlr;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.ParserATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.TerminalNode;
import ru.itmo.ctddev.entities.Abstraction;
import ru.itmo.ctddev.entities.Application;
import ru.itmo.ctddev.entities.Lambda;
import ru.itmo.ctddev.entities.Variable;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class LambdaParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		VAR=1, LAMBDA=2, DOT=3, LEFT_PARENTHESIS=4, RIGHT_PARENTHESIS=5, WS=6;
	public static final int
		RULE_expression = 0, RULE_application = 1, RULE_abstraction = 2, RULE_atom = 3;
	public static final String[] ruleNames = {
		"expression", "application", "abstraction", "atom"
	};

	private static final String[] _LITERAL_NAMES = {
		null, null, "'\\'", "'.'", "'('", "')'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "VAR", "LAMBDA", "DOT", "LEFT_PARENTHESIS", "RIGHT_PARENTHESIS", 
		"WS"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Lambda.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public LambdaParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ExpressionContext extends ParserRuleContext {
		public Lambda lambda;
		public ApplicationContext application;
		public AbstractionContext abstraction;
		public AbstractionContext abstraction() {
			return getRuleContext(AbstractionContext.class,0);
		}
		public ApplicationContext application() {
			return getRuleContext(ApplicationContext.class,0);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LambdaListener ) ((LambdaListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LambdaListener ) ((LambdaListener)listener).exitExpression(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_expression);
		int _la;
		try {
			setState(17);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(9);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==VAR || _la==LEFT_PARENTHESIS) {
					{
					setState(8);
					((ExpressionContext)_localctx).application = application(0);
					}
				}

				setState(11);
				((ExpressionContext)_localctx).abstraction = abstraction();

				        if (_localctx.application() == null) {
				            ((ExpressionContext)_localctx).lambda =  ((ExpressionContext)_localctx).abstraction.lambda;
				        } else {
				            ((ExpressionContext)_localctx).lambda =  new Application(((ExpressionContext)_localctx).application.lambda, ((ExpressionContext)_localctx).abstraction.lambda);
				        }
				    
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(14);
				((ExpressionContext)_localctx).application = application(0);
				 ((ExpressionContext)_localctx).lambda =  ((ExpressionContext)_localctx).application.lambda; 
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ApplicationContext extends ParserRuleContext {
		public Lambda lambda;
		public ApplicationContext app;
		public AtomContext atom;
		public AtomContext atom() {
			return getRuleContext(AtomContext.class,0);
		}
		public ApplicationContext application() {
			return getRuleContext(ApplicationContext.class,0);
		}
		public ApplicationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_application; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LambdaListener ) ((LambdaListener)listener).enterApplication(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LambdaListener ) ((LambdaListener)listener).exitApplication(this);
		}
	}

	public final ApplicationContext application() throws RecognitionException {
		return application(0);
	}

	private ApplicationContext application(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ApplicationContext _localctx = new ApplicationContext(_ctx, _parentState);
		ApplicationContext _prevctx = _localctx;
		int _startState = 2;
		enterRecursionRule(_localctx, 2, RULE_application, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(20);
			((ApplicationContext)_localctx).atom = atom();
			 ((ApplicationContext)_localctx).lambda =  ((ApplicationContext)_localctx).atom.lambda; 
			}
			_ctx.stop = _input.LT(-1);
			setState(29);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new ApplicationContext(_parentctx, _parentState);
					_localctx.app = _prevctx;
					_localctx.app = _prevctx;
					pushNewRecursionContext(_localctx, _startState, RULE_application);
					setState(23);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(24);
					((ApplicationContext)_localctx).atom = atom();
					 ((ApplicationContext)_localctx).lambda =  new Application(((ApplicationContext)_localctx).app.lambda, ((ApplicationContext)_localctx).atom.lambda); 
					}
					} 
				}
				setState(31);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class AbstractionContext extends ParserRuleContext {
		public Lambda lambda;
		public Token VAR;
		public ExpressionContext expression;
		public TerminalNode LAMBDA() { return getToken(LambdaParser.LAMBDA, 0); }
		public TerminalNode VAR() { return getToken(LambdaParser.VAR, 0); }
		public TerminalNode DOT() { return getToken(LambdaParser.DOT, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public AbstractionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_abstraction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LambdaListener ) ((LambdaListener)listener).enterAbstraction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LambdaListener ) ((LambdaListener)listener).exitAbstraction(this);
		}
	}

	public final AbstractionContext abstraction() throws RecognitionException {
		AbstractionContext _localctx = new AbstractionContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_abstraction);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(32);
			match(LAMBDA);
			setState(33);
			((AbstractionContext)_localctx).VAR = match(VAR);
			setState(34);
			match(DOT);
			setState(35);
			((AbstractionContext)_localctx).expression = expression();
			 ((AbstractionContext)_localctx).lambda =  new Abstraction((((AbstractionContext)_localctx).VAR!=null?((AbstractionContext)_localctx).VAR.getText():null), ((AbstractionContext)_localctx).expression.lambda); 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AtomContext extends ParserRuleContext {
		public Lambda lambda;
		public ExpressionContext expression;
		public Token VAR;
		public TerminalNode LEFT_PARENTHESIS() { return getToken(LambdaParser.LEFT_PARENTHESIS, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RIGHT_PARENTHESIS() { return getToken(LambdaParser.RIGHT_PARENTHESIS, 0); }
		public TerminalNode VAR() { return getToken(LambdaParser.VAR, 0); }
		public AtomContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atom; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LambdaListener ) ((LambdaListener)listener).enterAtom(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LambdaListener ) ((LambdaListener)listener).exitAtom(this);
		}
	}

	public final AtomContext atom() throws RecognitionException {
		AtomContext _localctx = new AtomContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_atom);
		try {
			setState(45);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LEFT_PARENTHESIS:
				enterOuterAlt(_localctx, 1);
				{
				setState(38);
				match(LEFT_PARENTHESIS);
				setState(39);
				((AtomContext)_localctx).expression = expression();
				setState(40);
				match(RIGHT_PARENTHESIS);
				 ((AtomContext)_localctx).lambda =  ((AtomContext)_localctx).expression.lambda; 
				}
				break;
			case VAR:
				enterOuterAlt(_localctx, 2);
				{
				setState(43);
				((AtomContext)_localctx).VAR = match(VAR);
				 ((AtomContext)_localctx).lambda =  new Variable((((AtomContext)_localctx).VAR!=null?((AtomContext)_localctx).VAR.getText():null)); 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 1:
			return application_sempred((ApplicationContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean application_sempred(ApplicationContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 2);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\b\62\4\2\t\2\4\3"+
		"\t\3\4\4\t\4\4\5\t\5\3\2\5\2\f\n\2\3\2\3\2\3\2\3\2\3\2\3\2\5\2\24\n\2"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\7\3\36\n\3\f\3\16\3!\13\3\3\4\3\4\3\4"+
		"\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\5\5\60\n\5\3\5\2\3\4\6\2\4\6"+
		"\b\2\2\2\61\2\23\3\2\2\2\4\25\3\2\2\2\6\"\3\2\2\2\b/\3\2\2\2\n\f\5\4\3"+
		"\2\13\n\3\2\2\2\13\f\3\2\2\2\f\r\3\2\2\2\r\16\5\6\4\2\16\17\b\2\1\2\17"+
		"\24\3\2\2\2\20\21\5\4\3\2\21\22\b\2\1\2\22\24\3\2\2\2\23\13\3\2\2\2\23"+
		"\20\3\2\2\2\24\3\3\2\2\2\25\26\b\3\1\2\26\27\5\b\5\2\27\30\b\3\1\2\30"+
		"\37\3\2\2\2\31\32\f\4\2\2\32\33\5\b\5\2\33\34\b\3\1\2\34\36\3\2\2\2\35"+
		"\31\3\2\2\2\36!\3\2\2\2\37\35\3\2\2\2\37 \3\2\2\2 \5\3\2\2\2!\37\3\2\2"+
		"\2\"#\7\4\2\2#$\7\3\2\2$%\7\5\2\2%&\5\2\2\2&\'\b\4\1\2\'\7\3\2\2\2()\7"+
		"\6\2\2)*\5\2\2\2*+\7\7\2\2+,\b\5\1\2,\60\3\2\2\2-.\7\3\2\2.\60\b\5\1\2"+
		"/(\3\2\2\2/-\3\2\2\2\60\t\3\2\2\2\6\13\23\37/";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}