package com.crivano.jlogic;

public class CanFoo2 implements Expression {
	boolean b;

	public CanFoo2(boolean b) {
		this.b = b;
	}

	public boolean eval() {
		return this.b;
	}

	public String explain(boolean result) {
		return JLogic.explain("foo2", result);
	}
}
