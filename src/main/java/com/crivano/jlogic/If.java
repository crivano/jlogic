package com.crivano.jlogic;

public class If extends CompositeExpressionSupport {
	Expression expressionTest;
	Expression expressionTrue;
	Expression expressionFalse;

	public If(Expression expressionTest, Expression expressionTrue, Expression expressionFalse) {
		this.expressionTest = expressionTest;
		this.expressionTrue = expressionTrue;
		this.expressionFalse = expressionFalse;
	}

	public static If of(Expression expressionTest, Expression expressionTrue, Expression expressionFalse) {
		return new If(expressionTest, expressionTrue, expressionFalse);
	}

	@Override
	protected Expression create() {
		return Or.of(And.of(expressionTest, expressionTrue), And.of(Not.of(expressionTest), expressionFalse));
	}

}
