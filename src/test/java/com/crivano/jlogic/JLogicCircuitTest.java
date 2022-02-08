package com.crivano.jlogic;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class JLogicCircuitTest {

	@Test
	public void test1Succeed() {
		Expression e = new CanFoo(true);
		String s = "foo";
		assertEquals(s, JLogicCircuit.draw(e));
	}

	@Test
	public void test2AndSucceed() {
		Expression e = And.of(new CanFoo(true), new CanBar(true));
		String s = "foo━bar";
		assertEquals(s, JLogicCircuit.draw(e));
	}

	@Test
	public void test3AndSucceed() {
		Expression e = And.of(new CanFoo(true), new CanBar(true), new CanBar2(true));
		String s = "foo━bar━bar2";
		assertEquals(s, JLogicCircuit.draw(e));
	}

	@Test
	public void test2OrSucceed() {
		Expression e = Or.of(new CanFoo(true), new CanBar(true));
		String s = "";
		s += "┳foo┳\n";
		s += "┗bar┛";
		assertEquals(s, JLogicCircuit.draw(e));
	}

	@Test
	public void test3OrSucceed() {
		Expression e = Or.of(new CanFoo(true), new CanBar(true), new CanBar2(true));
		String s = "";
		s += "┳foo━┳\n";
		s += "┣bar━┫\n";
		s += "┗bar2┛";
		assertEquals(s, JLogicCircuit.draw(e));
	}

	@Test
	public void test2And2OrSucceed() {
		Expression e = And.of(Or.of(new CanFoo(true), new CanBar(true)), new CanBar2(true));
		String s = "";
		s += "┳foo┳━bar2\n";
		s += "┗bar┛     ";
		assertEquals(s, JLogicCircuit.draw(e));
	}

	@Test
	public void test2Or2AndSucceed() {
		Expression e = Or.of(And.of(new CanFoo(true), new CanBar(true)), new CanBar2(true));
		String s = "";
		s += "┳foo━bar┳\n";
		s += "┗bar2━━━┛";
		assertEquals(s, JLogicCircuit.draw(e));
	}

	@Test
	public void test2Or2OrSucceed() {
		Expression e = Or.of(And.of(Or.of(new CanFoo(true), new CanBar(true)), new CanBar2(true)), new CanFoo2(true));
		String s = "";
		s += "┳┳foo┳━bar2┳\n";
		s += "┃┗bar┛     ┃\n";
		s += "┗foo2━━━━━━┛";

		assertEquals(s, JLogicCircuit.draw(e));
	}
}
