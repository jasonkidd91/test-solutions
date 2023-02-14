package hackerrank;

import java.util.Random;

public class NewPassword {
	
	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		
		String a = getSaltString();
		String b = getSaltString();
		System.out.println("a: " + a);
		System.out.println("b: " + b);
		String res = newPassword(a, b);
		System.out.println(res);
		
		long duration = (System.currentTimeMillis() - startTime) / 1000;
		System.out.println("elapse time: " + duration);
	}
	
	public static String newPassword(String a, String b) {
		String res = "";
		
		// take shortest word length count
		int len = a.length() <= b.length() ? a.length() : b.length();
		
		int i = 0;
		while(i<=len) {
			if(i >= a.length()) {
				res += b.substring(i); // take the remaining
			} else if(i >= b.length()) {
				res += a.substring(i); // take the remaining
			} else {
				// concat character a + b
				res += Character.toString(a.charAt(i)) + Character.toString(b.charAt(i));
			}
			i++;
		}
		
		return res;
	}
	
	static protected String getSaltString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 18) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }

}
