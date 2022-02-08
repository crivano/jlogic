package com.crivano.jlogic;

public class And implements Expression {
	private Expression[] expressions;

	public And(Expression... expressions) {
		this.expressions = expressions;
	}

	public static And of(Expression... expressions) {
		return new And(expressions);
	}

	public boolean eval() {
		for (Expression expression : getExpressions()) {
			if (!expression.eval())
				return false;
		}
		return true;
	}

	public String explain(boolean result) {
		if (result) {
			StringBuilder sb = new StringBuilder();
			for (Expression expression : getExpressions()) {
				if (expression.eval())
					if (sb.length() != 0)
						sb.append(JLogic.AND);
				sb.append(expression.explain(true));
			}
			if (sb.length() == 0)
				return null;
			return sb.toString();
		} else {
			for (Expression expression : getExpressions()) {
				if (!expression.eval())
					return expression.explain(false);
			}
			return null;
		}
	}

	public Expression[] getExpressions() {
		return expressions;
	}
}
