package lab1;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class Driver {
	public static void main(String [] args)
	{
		Polynomial p = new Polynomial();
		System.out.println(p.evaluate(3));
		double [] c1 = {6,0,0,5};
		Polynomial p1 = new Polynomial(c1);
		double [] c2 = {0,-2,0,0,-9};
		Polynomial p2 = new Polynomial(c2);
		Polynomial s = p1.add(p2);
		Polynomial a = p1.multiply(p2);
		for (int i = 0; i < a.exponents.length; i++)
		{
			System.out.print(a.coefficients[i]);
			System.out.print("x^");
			System.out.print(a.exponents[i]);
			System.out.print(" ");
		}
		System.out.print("Hello");
		System.out.println();
		System.out.println();
		System.out.println("s(0.1) = " + s.evaluate(0.1));
		if(s.hasRoot(1))
			System.out.println("1 is a root of s");
		else
			System.out.println("1 is not a root of s");

		try {
			File temp = new File("potato.txt");
			PrintWriter print = new PrintWriter(temp);
			print.print("5x4+87-9x5+7x4-10x54+5x");
			print.close();
		} catch (IOException e) {
			return;
		}

		Polynomial potato = new Polynomial(new File("potato.txt"));
		for (int i = 0; i < potato.coefficients.length; i++ )
		{
			System.out.print(potato.coefficients[i]);
			System.out.print(" ");
		}
		System.out.println("POTATO");
		for (int i = 0; i < potato.exponents.length; i++ )
		{
			System.out.print(potato.exponents[i]);
			System.out.print(" ");
		}
		Polynomial pot = new Polynomial();
		pot.saveToFile("potatoes.txt");
		pot.coefficients = new double[] {1};
		pot.exponents = new int[] {1};
		pot.saveToFile("abc.txt");

	}
}
