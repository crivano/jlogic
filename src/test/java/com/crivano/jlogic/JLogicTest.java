package com.crivano.jlogic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class JLogicTest {

	@Test
	public void test1Succeed() {
		Expression e = new CanFoo(true);
		assertTrue(JLogic.eval(e));
		assertEquals("foo", JLogic.explain(e, true));
	}

	@Test
	public void test0Fail() {
		Expression e = new CanFoo(false);
		assertFalse(JLogic.eval(e));
		assertEquals("_not_ foo", JLogic.explain(e, false));
	}

	@Test
	public void test1And1Succeed() {
		Expression e = And.of(new CanFoo(true), new CanBar(true));
		assertTrue(JLogic.eval(e));
		assertEquals("foo _and_ bar", JLogic.explain(e, true));
	}

	@Test
	public void test1Or1Succeed() {
		Expression e = Or.of(new CanFoo(true), new CanBar(true));
		assertTrue(JLogic.eval(e));
		assertEquals("foo", JLogic.explain(e, true));
	}

	@Test
	public void test1AndNot0eSucceed() {
		Expression e = And.of(new CanFoo(true), Not.of(new CanBar(false)));
		assertTrue(JLogic.eval(e));
		assertEquals("foo _and_ _not_ bar", JLogic.explain(e, true));
	}

	@Test
	public void test0Or1SucceedBecauseOfSecond() {
		Expression e = Or.of(new CanFoo(false), new CanBar(true));
		assertTrue(JLogic.eval(e));
		assertEquals("bar", JLogic.explain(e, true));
	}

	@Test
	public void test1And0FailAtSecond() {
		Expression e = And.of(new CanFoo(true), new CanBar(false));
		assertFalse(JLogic.eval(e));
		assertEquals("_not_ bar", JLogic.explain(e, false));
	}

	@Test
	public void test1AndNot1FailAtSecond() {
		Expression e = And.of(new CanFoo(true), Not.of(new CanBar(true)));
		assertFalse(JLogic.eval(e));
		assertEquals("bar", JLogic.explain(e, false));
	}

	@Test
	public void test0Or0Fail() {
		Expression e = Or.of(new CanFoo(false), new CanBar(false));
		assertFalse(JLogic.eval(e));
		assertEquals("_not_ foo _and_ _not_ bar", JLogic.explain(e, false));
	}

	@Test
	public void test1And1Or1FailAtThird() {
		Expression e = And.of(new CanFoo(true),
				Or.of(new CanFoo2(false), new CanBar2(true)));
		assertTrue(JLogic.eval(e));
		assertEquals("foo _and_ bar2", JLogic.explain(e, true));
	}

	@Test
	public void test1And0Or0FailAtThird() {
		Expression e = And.of(new CanFoo(true),
				Or.of(new CanFoo2(false), new CanBar2(false)));
		assertFalse(JLogic.eval(e));
		assertEquals("_not_ foo2 _and_ _not_ bar2", JLogic.explain(e, false));
	}

	@Test
	public void test0Or1And0Or0FailAtThird() {
		Expression e = And.of(Or.of(new CanFoo(false), new CanBar(true)),
				Or.of(new CanFoo2(false), new CanBar2(false)));
		assertFalse(JLogic.eval(e));
		assertEquals("_not_ foo2 _and_ _not_ bar2", JLogic.explain(e, false));
	}

	@Test
	public void test0Or0And0FailAtSecond() {
		Expression e = And.of(Or.of(new CanFoo(false), new CanBar(false)),
				new CanFoo2(false));
		assertFalse(JLogic.eval(e));
		assertEquals("_not_ foo _and_ _not_ bar", JLogic.explain(e, false));
	}

	@Test
	public void test1OrInner0Or1Succeed() {
		Expression e = And.of(new CanFoo(true), inner0Or1());
		assertTrue(JLogic.eval(e));
		assertEquals("foo _and_ bar2", JLogic.explain(e, true));
	}

	@Test
	public void test1OrInner0FailAtSecond() {
		Expression e = And.of(new CanFoo(true), inner0Or0());
		assertFalse(JLogic.eval(e));
		assertEquals("_not_ foo2 _and_ _not_ bar2", JLogic.explain(e, false));
	}

	@Test
	public void test1OrNotInner0Or1FailAtSecond() {
		Expression e = And.of(new CanFoo(true), Not.of(inner0Or1()));
		assertFalse(JLogic.eval(e));
		assertEquals("bar2", JLogic.explain(e, false));
	}

	private Expression inner0Or0() {
		return Or.of(new CanFoo2(false), new CanBar2(false));
	}

	private Expression inner0Or1() {
		return Or.of(new CanFoo2(false), new CanBar2(true));
	}

}
