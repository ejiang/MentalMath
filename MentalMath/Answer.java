package MentalMath;

import java.util.*;

public class Answer {
	/*
	public static void main(String[] args) {
		Question q = new Question(Integer.parseInt(args[0]));
		Deque<Pair> deq = q.getQueue();
		for (Pair p : deq) {
			System.out.println(p);
		}
		Answer an = new Answer(deq);
		System.out.println(an.getAnswer());
		System.out.println(an.getOriginal());
	}*/

	private Deque<Pair> answer, original;
	private StringBuilder sb;

	public Answer(Question q) {
		Deque<Pair> dq = q.getQueue();
		original = dq;
		answer = dq;
		evaluate();
		evaluate2();
	}

	public String getAnswer() {
		Number val = answer.peekFirst().val;
		if (val.intValue() == val.doubleValue())
			return Integer.toString(val.intValue());
		return Double.toString(val.doubleValue());
	}

	private void evaluate() {
		// Get divs and muls
		// Works!
		Pair front = answer.pollFirst();
		while (front.op != Operator.NUL) {
			if ((front.op == Operator.MUL) || (front.op == Operator.DIV)) {
				Pair next = answer.pollFirst();
				front.val = calculate(front.val, next.val, front.op);
				front.op = next.op;
			} else {
				answer.offerLast(front);
				front = answer.pollFirst();
			}
		}
		answer.offerLast(front);
	}

	private void evaluate2() {
		Pair front = answer.pollFirst();
		while (front.op != Operator.NUL) {
			Pair next = answer.pollFirst();
			front.val = calculate(front.val, next.val, front.op);
			front.op = next.op;
		}
		answer.offerLast(front);
	}

	private Number calculate(Number a, Number b, Operator op) {
		double res = a.doubleValue();
		double bv = b.doubleValue();
		switch (op) {
			case PLU:
				res += bv;
				break;
			case SUB:
				res -= bv;
				break;
			case MUL:
				res *= bv;
				break;
			case DIV:
				res /= bv;
				break;
			default:
				break;
		}
		return new Double(res);
	}
}
