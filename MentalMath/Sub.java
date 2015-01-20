package MentalMath;

import java.util.*;

class Sub {
  private Random rn;
  
  public Sub() {
    rn = new Random();
  }
  
	public static void main(String[] args) {
    Sub s = new Sub();
    
		int n = Integer.parseInt(args[0]);
		int before = Integer.parseInt(args[1]);
    int after = Integer.parseInt(args[2]);
		Node res = s.makeSub(n, before, after);
    s.print(res);
	}
  
	public int dispatch() {
		return 1;
	}

  private void print(Node n) {
    if (n.left.doubleValue() == n.left.intValue())
      System.out.println(n.left.intValue());
    else
      System.out.println(n.left);
    
    System.out.println(n.op);
    
    if (n.right.doubleValue() == n.right.intValue())
      System.out.println(n.right.intValue());
    else
      System.out.println(n.right);
  }

	/*public Number makeSingle(int opt, int before, int after) {
		;
	}*/

	public Node makeSub(int n, int before, int after) {
		Node res = new Node();
		if (n == 1) {
			res.left = makeInt(before) + makeDouble(after);
      res.op = randOp();
      res.right = makeInt(before) + makeInt(after);
		} else if (n == 2) {
			res.left = makeDouble(after);
      res.op = randOp();
      res.right = makeDouble(after);
		} else if (n == 3) {
			res.left = makeInt(before);
      res.op = randOp();
      res.right = makeInt(after);
		} else if (n == 4) {
		  res.left = makeInt(before) + makeDouble(after);
      res.op = randOp();
      res.right = makeDouble(after);
		} else if (n == 5) {
      res.left = makeInt(before) + makeDouble(after);
		  res.op = Operator.MUL;
      res.right = makeInt(before);
		} else if (n == 6) {
		  res.left = makeInt(before);
      res.op = Operator.MUL;
      res.right = makeInt(before);
		} else if (n == 7) {
      res.left = makeInt(before) + makeDouble(after);
		  res.op = Operator.MUL;
      res.right = makeInt(before) + makeDouble(after);
		} else if (n == 8) {
      res.left = makeDouble(after);
		  res.op = Operator.MUL;
      res.right = makeInt(before);
		} else if (n == 9) {
      res = getRational(before, after);
		} /*else if (n == 10) {
      res.left = ;
		  res.op = Operator.DIV;
      res.right = ;
		} else if (n == 11) {
      res.left = ;
		  res.op = Operator.DIV;
      res.right = ;
		} else if (n == 12) {
      res.left = ;
		  res.op = Operator.DIV;
      res.right = ;
		} else {
      ;
		}*/
    
    res.left = randSign(res.left);
    res.right = randSign(res.right);
		return res;
	}
  
  private Operator randOp() {
    if (rn.nextInt(2) == 1)
      return Operator.PLU;
    else
      return Operator.SUB;
  }
  
  private Number randSign(Number n) {
    if (rn.nextInt(10) < 7)
      return n;
    else
      return -1 * n.doubleValue();
  }

	private int makeInt(int order) {
		Random rn = new Random();
		return rn.nextInt(pow(10, order)) + 1;
	}

	private double makeDouble(int order) {
		Random rn = new Random();
		return (double)makeInt(order) / pow(10, order);
	}

	private int pow(int base, int exp) {
    int result = 1;
    while (exp != 0) {
      if ((exp & 1) != 0)
        result *= base;
      exp >>= 1;
      base *= base;
    }

    return result;
	}
  
  private Node getRational(int before, int after) {
    Node res = new Node();
    int a, b, c, d;
    b = 1;
    // so if before were 3, then it would be
    // nextInt(999) + 1, which would be [1,999]

    // change back to 1?
    c = inclusiveRange(0, after);
    d = inclusiveRange(0, after);

    // 1000 < ab < 9999
    // a is dependent on c and d
    int limit2 = 100 * pow(2, c) * pow(5, d);
    int limit1 = limit2/10;
    limit2 -= 1;
    //a = inclusiveRange((int)Math.ceil(Math.sqrt(limit1)), (int)Math.floor(Math.sqrt(limit2)));
    a = inclusiveRange((int)Math.ceil(Math.sqrt(limit1)), limit2/2);
    if (limit1/a == 0 || limit2/a == 0)
      b = 1;
    else
      b = inclusiveRange(limit1/a, limit2/a);

    int num = a * b;
    int denom = b * pow(2, c) * pow(5, d);
    res.left = num;
    res.op = Operator.DIV;
    res.right = denom;
    return res;
  }
  
  private int inclusiveRange(int min, int max) {
    Random rn = new Random();
    return rn.nextInt((max - min) + 1) + min;
  }
}
