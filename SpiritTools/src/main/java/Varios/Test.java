package Varios;

public class Test {

	public static void main(String[] args) {
		int num;
		num = 10;
		calc(num);
		System.out.println("num = " + num);
	}
	
	static void calc(int num){
		num += 100;
	}
}
