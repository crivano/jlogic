package com.crivano.jlogic;

public class CanFoo implements Expression {
	boolean b;

	public CanFoo(boolean b) {
		this.b = b;
	}

	public boolean eval() {
		return this.b;
	}

	public String explain(boolean result) {
		return JLogic.explain("foo", result);
	}
}
