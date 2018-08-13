package com.crivano.jlogic;

public class CanBar implements Expression {
	boolean b;

	public CanBar(boolean b) {
		this.b = b;
	}

	public boolean eval() {
		return this.b;
	}

	public String explain(boolean result) {
		return JLogic.explain("bar", result);
	}
}
