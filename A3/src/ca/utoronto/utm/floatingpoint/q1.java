package ca.utoronto.utm.floatingpoint;

public class q1 {
	public static void main(String[] args) {
		q1 p = new q1();
		System.out.println(p.solve711());
	}
	
	/**
	 * Most fractions cannot represented exactly as a binary fractions 
	 * (e.g. 1/3 or 1/10 or 1/100 are indefinitely repeating fractions in base 2). 
	 * This means that if we stop at any finite number of bits for those decimal
	 * fractions we will get an approximate representation.
	 * So for the 7.11 problem it means that by having some floating points a,b,c,d and 7.11, 
	 * we get their approximations which makes a + b + c + d never exactly equal
	 * to 7.11 and the product of a,b,c,d never exactly equal to 7.11. So the 
	 * following code will never find a,b,c,d.
	 * @return
	 */
	public String solve711() {
		float a, b, c, d;
		for (a = 0.00f; a < 7.11f; a = a + .01f) {
			for (b = 0.00f; b < 7.11f; b = b + .01f) {
				for (c = 0.00f; c < 7.11f; c = c + .01f) {
					for (d = 0.00f; d < 7.11f; d = d + .01f) {
						if (a * b * c * d == 7.11f && a + b + c + d == 7.11f) {
							return (a + " " + b + " " + c + " " + d);
						}
					}
				}
			}
		}
		return "";
	}
}
