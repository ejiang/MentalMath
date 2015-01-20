package MentalMath;

import java.util.*;
import java.lang.*;

public class Question {
	private Random rn;
	private Deque<Pair> an;
	private Node res;
	private StringBuilder sb;
  private Sub sub;

	public Question(int n) {
		rn = new Random();
    sub = new Sub();
		// ArrayDeque?
		an = new LinkedList<Pair>();
		res = create(n);
		printNode(res);

		sb = new StringBuilder();
		makeString();
	}

	protected Deque<Pair> getQueue() {
		return an;
	}

	public String toString() {
		return sb.toString();
	}

	private Node create(int n) {
		// For creation, we can think of it
		// as linking together types of subproblems

		// n >= 2
		Node res = new Node();

		// So there are 3 types of Nodes
		// 1) Int - Op - Int
		// 2) Int - Op - Node
		// 3) Node - Op - Node

    // TODO
    // Decimal handle properly
    // Options pane
		if (n == 2) {
      res = sub.makeSub(1, 2, 2);
		} else if (n == 3) {
			res.left = new Integer(randint());
			res.op = getOp();
			res.rnode = create(n - n / 2);
		} else {
			res.lnode = create(n / 2);
			res.op = getOp();
			res.rnode = create(n - n / 2);
		}
		return res;
	}

	private int randint() {
		return rn.nextInt(10) + 1;
	}

	private Operator getOp() {
		int op = rn.nextInt(4);
		Operator o;
		switch (op) {
			case 0:
				o = Operator.PLU;
				break;
			case 1:
				o = Operator.SUB;
				break;
			case 2:
				o = Operator.MUL;
				break;
			case 3:
				o = Operator.DIV;
				break;
			default:
				o = Operator.PLU;
				break;
		}
		return o;
	}

	private void printNode(Node n) {
		// Referring to the 3 types of Nodes
		// Handling 1, 2, 3 in order

		if (n.rnode == null)
			printNode(n.left, n.op, n.right);
		else if (n.lnode == null)
			printNode(n.left, n.op, n.rnode);
		else
			printNode(n.lnode, n.op, n.rnode);
	}

	private void printNode(Number a, Operator op, Number b) {
		an.offerLast(new Pair(a, op));
		an.offerLast(new Pair(b, Operator.NUL));
	}

	private void printNode(Number a, Operator op, Node b) {
		an.offerLast(new Pair(a, op));
		printNode(b);
	}

	private void printNode(Node a, Operator op, Node b) {
		Pair p = an.pollLast();
		// TODO: remove null test
		if (p != null) {
			p.op = op;
			an.offerLast(p);
		}
		printNode(a);
		p = an.pollLast();
		p.op = op;
		an.offerLast(p);
		printNode(b);
	}

	private void makeString() {
		for (Pair p : an) {
			sb.append(p.val);
			printOp(p.op);
		}
	}

	private void printOp(Operator op) {
		switch (op) {
			case PLU:
				sb.append(" + ");
				break;
			case SUB:
				sb.append(" - ");
				break;
			case MUL:
				sb.append(" * ");
				break;
			case DIV:
				sb.append(" / ");
				break;
			case NUL:
				break;
			default:
				break;
		}
	}
}
