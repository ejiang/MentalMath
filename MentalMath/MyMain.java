package MentalMath;

public class MyMain {
	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		Question q = new Question(n);
		Answer a = new Answer(q);
		System.out.println(q);
		System.out.println(a.getAnswer());
	}
}
