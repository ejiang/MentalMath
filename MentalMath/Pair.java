package MentalMath;

class Pair {
	public Number val;
	public Operator op;
	public Pair(Number val, Operator op) {
		this.val = val;
		this.op = op;
	}
	public String toString() {
		return "" + val + " " + op;
	}
}
