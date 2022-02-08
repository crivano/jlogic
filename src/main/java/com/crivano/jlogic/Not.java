package com.crivano.jlogic;

public class Not implements Expression {
	private Expression expression;

	public Not(Expression expression) {
		this.expression = expression;
	}

	public static Not of(Expression expression) {
		return new Not(expression);
	}

	public boolean eval() {
		return !getExpression().eval();
	}

	public String explain(boolean result) {
		return getExpression().explain(!result);
	}

	public Expression getExpression() {
		return expression;
	}
}
