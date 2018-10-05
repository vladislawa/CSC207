package ca.utoronto.utm.floatingpoint;

public class q3 {
	// See https://docs.oracle.com/javase/8/docs/api/java/lang/Float.html
	// as well as the lecture notes.txt for Week11.
	
	/**
	 * Complete the code below, so that when executed, the output should exactly match 
	 * IEEE754SingleOut.txt included in this project. Do not modify main in any way. 
	 * Implement the methods below so that they perform as expected. You can add additional
	 * static constants as well as static helper methods if it helps.
	 */
	public static void main(String[] args) {
		System.out.println("0 to 10");
		for (float i = 0.0f; i <= 10.0f; i++) {
			System.out.println(binRep(i));
		}
		System.out.println("misc");
		System.out.println(binRep(-6.8f));
		System.out.println(binRep(23.1f));
		System.out.println(binRep(14.625f));
		System.out.println(binRep(.1f));
		System.out.println(binRep(5.75f));
		System.out.println(binRep(1.0f / 3.0f));

		System.out.println("Machine Epsilon");
		float me = machineEpsilon();
		System.out.println("Machine Epsilon = " + binRep(me));
		System.out.println("1+machine epsilon = " + binRep(1.0f + me));
		System.out.println("Underflow");
		System.out.println("Underflow = " + binRep(underflow()));

		// System.out.println("rounds by");
		System.out.println("Overflow");
		System.out.println("Overflow = " + binRep(overflow()));
		System.out.println("MAX_VALUE = " + binRep(Float.MAX_VALUE));
	}
	/**
	 * Search for machine epsilon (eps), that is, the smallest
	 * float such that 1+eps>1. 
	 * Print out progress along the way.
	 * 
	 * @return machine epsilon
	 */
	public static float machineEpsilon() {
		float one = 1.0f, me = 1.0f, meNew = 1.0f;
		while(one + meNew > 1) {
			if(meNew > 0) {
				me = meNew;
			}
			System.out.println(binRep(one + meNew));
			meNew /= 2.0f;
		}
		System.out.println(binRep(one + meNew));
		return (me);
	}

	/**
	 * Search for underflow, that is the smallest float
	 * number that is greater than 0. 
	 * Print out progress along the way.
	 * @return underflow
	 */
	public static float underflow() {
		float ufl = 1.0f, uflNew = 1.0f;
		while(uflNew > 0) {
			if(uflNew > 0) {
				ufl = uflNew;
			}
			System.out.println(binRep(uflNew));
			uflNew /= 2.0f;
		}
		System.out.println(binRep(uflNew));
		return ufl;
	}

	/**
	 * Search for overflow, the largest float, 
	 * by first finding the largest exponent, and
	 * then finding the largest mantissa. 
	 * Print out progress along the way.
	 * @return overflow
	 */
	
	public static float overflow() {
		/*
		 * Algorithm: First find the maximum exponent and then the mantissa.
		 */
		System.out.println("Maximum Exponent");
		float ofl = 1.0f, oflNew = 1.0f;
		while(ofl < Float.MAX_VALUE/2.0f) {
			System.out.println(binRep(ofl));
			ofl *= 2.0f;
		}
		float max_expo = ofl;
		System.out.println(binRep(ofl));
		
		/*
		 * Add more (lower order) bits to the mantissa. We rely on round to even here to
		 * stop us.
		 */
		System.out.println("Maximum Mantissa");
		System.out.println(binRep(ofl));
		float add = oflNew;
		while(ofl*add < Float.MAX_VALUE) {
			oflNew = oflNew/2.0f;
			add += oflNew;
			ofl *= add;
			System.out.println(binRep(ofl));
			ofl = max_expo;
		}
		return ofl*add;
	}

	/**
	 * Take apart a floating point number and return a string representation of it.
	 * @param d the floating point number to investigate
	 * @return By example, this method returns strings like...
	 * 
	 * binRep(0.0f) returns "0[00000000]00000000000000000000000=+0.00000000000000000000000x2^(0)=0.0"
	 * binRep(1.0f) returns "0[01111111]00000000000000000000000=+1.00000000000000000000000x2^(0)=1.0"
	 * binRep(2.0f) returns "0[10000000]00000000000000000000000=+1.00000000000000000000000x2^(1)=2.0"
	 * binRep(14.625f) returns "0[10000010]11010100000000000000000=+1.11010100000000000000000x2^(3)=14.625"
	 * binRep(0.1f) returns "0[01111011]10011001100110011001101=+1.10011001100110011001101x2^(-4)=0.1"
	 */
	// Return information about the representation of floating point number d
	public static String binRep(float d) {
		/*
		 * See Float.floatToRawIntBits
		 */

		int l = Float.floatToRawIntBits(d); // Use this to pull bits of d
		int sign = 0;
		int exponent = 0; 
		int mantissa = 0;
		String sSign = "";
		String sExponent = "";
		String sMantissa = "";
		String extraBit = "1.";
		boolean isDenormalized;
		
		String newString = Integer.toBinaryString(l);
		newString = String.format("%32s", newString).replace(' ', '0');
		
		sSign = newString.substring(0,1);
		sExponent = newString.substring(1, 9);
		sMantissa = newString.substring(9, 32);
		
		String s = sSign + "[" + sExponent + "]" + sMantissa;
		
		if(l < 0) {
			sign = 1;
		}
		else {
			sign = 0;
		}
		
		String t = (sign == 0) ? "+" : "-";
		
		exponent = Integer.parseInt(sExponent, 2);
		int trueExponent = exponent - 127;
		
		if(d == 0.0f) {
			extraBit = "0.";
			trueExponent = 0;
		}
		
		if(sExponent.contains("1")) {
			isDenormalized = false;
		}
		else {
			isDenormalized = true;
		}
		
		if(d != 0.0f && isDenormalized == true) {
			extraBit = "0.";
			trueExponent = -126;
		}
		
		t = t + extraBit + sMantissa + "x2^(" + trueExponent + ")";
		return (s + "=" + t + "=" + d);
	}
}