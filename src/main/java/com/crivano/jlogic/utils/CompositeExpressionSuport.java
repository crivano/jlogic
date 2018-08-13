package com.crivano.jlogic.utils;

import com.crivano.jlogic.Expression;

public abstract class CompositeExpressionSuport implements Expression {
	private Expression exp = null;

	public boolean eval() {
		if (exp == null)
			exp = create();
		return exp.eval();
	};

	protected abstract Expression create();

	@Override
	public String explain(boolean result) {
		return exp.explain(result);
	}
}
