package com.crivano.jlogic;

public class Or implements Expression {
	Expression[] expressions;

	public Or(Expression... expressions) {
		this.expressions = expressions;
	}

	public static Or of(Expression... expressions) {
		return new Or(expressions);
	}

	@Override
	public boolean eval() {
		for (Expression expression : expressions) {
			if (expression.eval())
				return true;
		}
		return false;
	}

	@Override
	public String explain(boolean result) {
		if (result) {
			for (Expression expression : expressions)
				if (expression.eval())
					return expression.explain(true);
			return null;
		} else {
			// this would be better if made with false explanations.
			StringBuilder sb = new StringBuilder();
			for (Expression expression : expressions) {
				if (!expression.eval())
					if (sb.length() != 0)
						sb.append(JLogic.AND);
				sb.append(expression.explain(false));
			}
			if (sb.length() == 0)
				return null;
			return sb.toString();
		}
	}

}
