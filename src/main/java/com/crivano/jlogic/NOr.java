package com.crivano.jlogic;

public class NOr extends CompositeExpressionSupport {
	Expression[] expressions;

	public NOr(Expression... expressions) {
		this.expressions = expressions;
	}

	public static NOr of(Expression... expressions) {
		return new NOr(expressions);
	}

	public static NOr of(Expression expressionTest, Expression expressionTrue, Expression expressionFalse) {
		return new NOr(expressionTest, expressionTrue, expressionFalse);
	}

	@Override
	protected Expression create() {
		return Not.of(Or.of(expressions));
	}

}
