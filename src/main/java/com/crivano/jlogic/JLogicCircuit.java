package com.crivano.jlogic;

import java.util.ArrayList;
import java.util.List;

public class JLogicCircuit {

	public static String draw(Expression e) {
		char r[][] = drawExpression(e);
		StringBuilder sb = new StringBuilder();
		for (int y = 0; y < r[0].length; y++) {
			for (int x = 0; x < r.length; x++) {
				sb.append(r[x][y]);
			}
			if (y < r[0].length - 1)
				sb.append('\n');
		}
		return sb.toString();
	}

	public static char[][] drawExpression(Expression e) {
		if (e instanceof And)
			return drawAnd((And) e);
		if (e instanceof Or)
			return drawOr((Or) e);
		if (e instanceof Not)
			return drawNot((Not) e);
		else {
			String s = JLogic.explain(e, true);
			char r[][] = createCharArray(s.length(), 1);
			for (int i = 0; i < s.length(); i++)
				r[i][0] = s.charAt(i);
			return r;
		}
	}

	public static char[][] drawNot(Not e) {
		String s = "!" + JLogic.explain(e.getExpression(), true);
		char r[][] = createCharArray(s.length(), 1);
		for (int i = 0; i < s.length(); i++)
			r[i][0] = s.charAt(i);
		return r;
	}

	public static char[][] drawAnd(And expression) {
		int cx = 0, cy = 0;
		List<char[][]> subs = new ArrayList<>();
		for (Expression e : expression.getExpressions()) {
			// get child circuit
			char[][] sub = JLogicCircuit.drawExpression(e);
			subs.add(sub);
			// calc max height
			if (sub[0].length > cy)
				cy = sub[0].length;
			// calc width
			cx += sub.length;
		}
		cx += subs.size() - 1;

		// create my resulting array of char
		char r[][] = createCharArray(cx, cy);
//		r[0][0] = '━';
//		r[cx - 1][0] = '━';

		// draw subempressions at their positions
		int posx = 0;
		for (char[][] sub : subs) {
			for (int y = 0; y < sub[0].length; y++)
				for (int x = 0; x < sub.length; x++)
					r[posx + x][y] = sub[x][y];
			posx += sub.length + 1;
			if (posx < cx)
				r[posx - 1][0] = '━';
		}
		return r;
	}

	public static char[][] drawOr(Or expression) {
		int cx = 0, cy = 0;
		List<char[][]> subs = new ArrayList<>();
		for (Expression e : expression.getExpressions()) {
			// get child circuit
			char[][] sub = JLogicCircuit.drawExpression(e);
			subs.add(sub);
			// calc height
			cy += sub[0].length;
			// calc width
			if (sub.length + 2 > cx)
				cx = sub.length + 2;
		}
		// create my resulting array of char
		char r[][] = createCharArray(cx, cy);

		int posy = 0;
		for (char[][] sub : subs) {
			// draw subexpressions at their positions
			for (int y = 0; y < sub[0].length; y++) {
				for (int x = 0; x < sub.length; x++)
					r[x + 1][posy + y] = sub[x][y];
			}
			// fill each item to cx
			for (int x = sub.length + 1; x < cx - 1; x++)
				r[x][posy] = '━';
			// draw borders
			for (int y = posy; y < posy + sub[0].length; y++) {
				if (y == 0) {
					r[0][0] = '┳';
					r[cx - 1][0] = '┳';
				} else if (y == posy && y < cy - 1) {
					r[0][y] = '┣';
					r[cx - 1][y] = '┫';
				} else if (y > posy && y < cy - 1) {
					r[0][y] = '┃';
					r[cx - 1][y] = '┃';
				} else if (y == cy - 1) {
					r[0][cy - 1] = '┗';
					r[cx - 1][cy - 1] = '┛';
				}

			}

			posy += sub[0].length;
		}
		return r;
	}

	private static char[][] createCharArray(int cx, int cy) {
		char r[][] = new char[cx][cy];
		// and fill with spaces
		for (int y = 0; y < cy; y++)
			for (int x = 0; x < cx; x++)
				r[x][y] = ' ';
		return r;
	}
}
