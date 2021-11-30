package com.crivano.jlogic;

public class NAnd extends CompositeExpressionSupport {
	Expression[] expressions;

	public NAnd(Expression... expressions) {
		this.expressions = expressions;
	}

	public static NAnd of(Expression... expressions) {
		return new NAnd(expressions);
	}

	public static NAnd of(Expression expressionTest, Expression expressionTrue, Expression expressionFalse) {
		return new NAnd(expressionTest, expressionTrue, expressionFalse);
	}

	@Override
	protected Expression create() {
		return Not.of(And.of(expressions));
	}

}
