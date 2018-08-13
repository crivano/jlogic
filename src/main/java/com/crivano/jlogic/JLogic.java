package com.crivano.jlogic;

public class JLogic {
	public static final String NOT = "_not_ ";
	public static final String NOR = " _nor_ ";
	public static final String NEITHER = "_neither_ ";
	public static final String AND = " _and_ ";

	static public boolean eval(Expression e) {
		return e.eval();
	}

	public static String explain(Expression e, boolean result) {
		return e.explain(result);
	}

	public static String explain(String explanation, boolean result) {
		if (result)
			return explanation;
		else
			return NOT + explanation;
	}
}
