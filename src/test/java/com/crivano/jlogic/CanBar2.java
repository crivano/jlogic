package com.crivano.jlogic;

public class CanBar2 implements Expression {
	boolean b;

	public CanBar2(boolean b) {
		this.b = b;
	}

	public boolean eval() {
		return this.b;
	}

	public String explain(boolean result) {
		return JLogic.explain("bar2", result);
	}
}
