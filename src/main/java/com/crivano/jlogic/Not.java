package com.crivano.jlogic;

public class Not implements Expression {
	Expression expression;

	public Not(Expression expression) {
		this.expression = expression;
	}

	public static Not of(Expression expression) {
		return new Not(expression);
	}

	@Override
	public boolean eval() {
		return !expression.eval();
	}

	@Override
	public String explain(boolean result) {
		return expression.explain(!result);
	}
}
