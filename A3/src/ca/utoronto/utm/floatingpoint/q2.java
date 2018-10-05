package ca.utoronto.utm.floatingpoint;

import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.Set;

/**
 * Class contains two methods. Solve711() solves the problem for 7.11 and solve() solves all the 
 * similar problems including 7.11
 * 
 * @author csc207 student
 */
public class q2 {
	public static void main(String[] args) {
		q2 p = new q2();
		System.out.println(p.solve711());
		System.out.println(p.solve(7.1111)); // input any double 									
											//(note: double will be rounded to 2 decimals)
        
        

	}
	/**
	 * Solves only 7.11 problem. 
	 * 
	 * @return String with answer to the problem or "not found" if there is no answer
	 * @author csc207 student
	 */
	public String solve711() {
		int a, b, c, d, product;
		product = (int) (7.11 * 100 * 100 * 100 * 100);
		for (a = 1; a < 711; a++) {
			for (b = 1; b < 711 - a ; b++) {
				for (c = 1; c < 711 - b - c; c++) {
					d = 711 - a - b - c;
					if (a * b * c * d == product) {
						return "answer: " + a /(double) 100 + " " + b /(double) 100 + " " + c /(double) 100 + " " + d /(double) 100;
						}
					}
				}
			}
		return "not found";
		}
	
	/**
	 * Solves problems similar to 7.11 problem 
	 * @param a double with 2 decimals after comma or any double (it will be rounded to two decimals)
	 * @return Sets of sets with all possible solutions
	 * 
	 * @author csc207 student
	 */
	public Set<HashSet> solve(double input) {
		//rounding double to 2 decimals 
		DecimalFormat df = new DecimalFormat("#.##");
		String s = df.format(input);
		Double e = Double.parseDouble((s).replace(",","."));
		
		//finding all solutions
		Set<HashSet> allSolutions = new HashSet<HashSet>();
		int a, b, c, d, product;
		int toInt = (int) (e * 100); //making double an integer
		product = (int) (e * 100 * 100 * 100 * 100); //finding the product
		for (a = 1; a < toInt; a++) {
			for (b = 1; b < toInt - a ; b++) {
				for (c = 1; c < toInt - b - c ; c++) {
					d = toInt - a - b - c;
					if (a * b * c * d == product) {
						Set<Double> answer = new HashSet<Double>();
						answer.add(a /(double) 100);
						answer.add(b /(double) 100);
						answer.add(c /(double) 100);
						answer.add(d /(double) 100);
						allSolutions.add((HashSet) answer);
						}
					}
				}
			}
		return allSolutions;
		}
}
