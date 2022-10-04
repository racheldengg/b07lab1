package lab1;
import java.io.*;
import java.util.*;

public class Polynomial {
	double coefficients[];
	int exponents[];

	public Polynomial()
	{
		coefficients = new double[1];
		coefficients[0] = 0;

		exponents = new int[1];
		exponents[0] = 0;
	}

	public Polynomial(double listOfCoefficients[])
	{
		//takes in an array of double finds lengths to set nonZero coefficients to;
		int n = listOfCoefficients.length;
		int nonzeroCoeff = 0;

		for(int i = 0; i < n; i++)
		{
			if(listOfCoefficients[i] != 0)
			{
					nonzeroCoeff++;
			}
		}

		//make an array of the length of nonzero coefficients
		coefficients = new double[nonzeroCoeff];
		exponents = new int[nonzeroCoeff];

		//go through the array
		int nonZeroIndex = 0;
		for(int i = 0; i < n; i++)
		{
			if (listOfCoefficients[i] != 0)
			{
				coefficients[nonZeroIndex] = listOfCoefficients[i];
				exponents[nonZeroIndex] = i;
				nonZeroIndex++;
			}
		}
	}

	public Polynomial add(Polynomial other)
	{
		Polynomial addition = new Polynomial();
		Polynomial temporary = new Polynomial();
		int largestExponent = -1;

		//find largest exponent within both arrays
		for (int i = 0; i < other.exponents.length; i++)
		{
			if (other.exponents[i] > largestExponent)
			{
				largestExponent = other.exponents[i];
			}
		}

		for (int i = 0; i < exponents.length; i++)
		{
			if (exponents[i] > largestExponent)
			{
				largestExponent = exponents[i];
			}
		}
		//edge case, arrays are empty and largestExponent = -1;
		if (largestExponent == -1)
		{
			return addition;
		}
		else
		{
			temporary.coefficients = new double [largestExponent + 1];
		}

		//iterate through array
		for (int i = 0; i < temporary.coefficients.length; i++)
		{
			for (int j = 0; j < other.exponents.length; j++)
			{
				if (other.exponents[j] == i)
				{
					temporary.coefficients[i] = temporary.coefficients[i] + other.coefficients[j];
				}
			}

			for (int j = 0; j < coefficients.length; j++)
			{
				if (exponents[j] == i)
				{
					temporary.coefficients[i] = temporary.coefficients[i] + coefficients[j];
				}
			}
		}

		//
		int lengthOfArray = 0;
		for (int i = 0; i < temporary.coefficients.length; i++)
		{
			if (temporary.coefficients[i] != 0)
			{
				lengthOfArray++;
			}
		}

		if (lengthOfArray == 0)
		{
			return addition;
		}
		else
		{
			addition.coefficients = new double [lengthOfArray];
			addition.exponents = new int [lengthOfArray];
		}
		int index = 0;
		for (int i = 0; i < temporary.coefficients.length; i++)
		{
			if (temporary.coefficients[i] != 0)
			{
				addition.coefficients[index] = temporary.coefficients[i];
				addition.exponents[index] = i;
				index++;
			}
		}
		return addition;
	}

	public double evaluate(double x)
	{
		double result = 0;

		for (int i = 0; i < coefficients.length; i++)
		{
			result = result + (coefficients[i] * Math.pow(x, exponents[i]));
		}

		return result;
	}


	public boolean hasRoot(double x)
	{
		double result = evaluate(x);
		if (result == 0)
		{
			return true;
	    }
		else
		{
			return false;
		}
	 }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////


	public Polynomial multiply (Polynomial other)
	{





		Polynomial temp = new Polynomial();
		Polynomial multiply = new Polynomial();
		int largestExponent = 0;

		for (int i = 0; i < exponents.length; i++)
		{
			for (int j = 0; j < other.exponents.length; j++)
			{
				int addExponent = exponents[i] + other.exponents[j];
				if (addExponent > largestExponent)
				{
					largestExponent = addExponent;
				}
		}
		}

		temp.coefficients = new double[largestExponent + 1]; ///FJEDKSLA;JFDLS;A

		for (int i = 0; i < exponents.length; i++)
		{
			for (int j = 0; j < exponents.length; j++)
			{
				int addExponent = exponents[i] + other.exponents[j];
				temp.coefficients[addExponent] = temp.coefficients[addExponent] + (coefficients[i] * other.coefficients[j]);
			}
		}

		int lengthOfArray=0;
		for (int i = 0; i < temp.coefficients.length; i++)
		{
			if (temp.coefficients[i] != 0)
			{
				lengthOfArray++;
			}
		}

		multiply.exponents = new int[lengthOfArray];
		multiply.coefficients = new double[lengthOfArray];

		int index = 0;
		for (int i = 0; i < temp.coefficients.length; i++)
		{
			if (temp.coefficients[i] != 0)
			{
				multiply.coefficients[index] = temp.coefficients[i];
				multiply.exponents[index] = i;
				index++;
			}
		}
		return multiply;
	}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public Polynomial(File file)
	{
		Scanner input = null;

		try
		{
			input = new Scanner(file);
		}
		catch (Exception e)
		{
			System.out.println("error with file");
			return;
		}

		String line = input.nextLine();
		String[] terms = line.split("((?=\\+)|(?=-))");
		coefficients = new double[terms.length];
		exponents = new int[terms.length];

		for (int i = 0; i<terms.length; i++)
		{
			// does not handle case of +3x or -4
			String[] coefficientExponent = terms[i].split("x");
			System.out.println(terms[i]);
			//normal case
			if (coefficientExponent.length == 2)
			{
				if(coefficientExponent[0].charAt(0) == '-' && coefficientExponent[0].length() == 1 )
				{
					coefficients[i] = -1;
					exponents[i] = Integer.parseInt(coefficientExponent[1]);
				}
				else if(coefficientExponent[0].charAt(0) == '+' && coefficientExponent[0].length() == 1 )
				{
					coefficients[i] = 1;
					exponents[i] = Integer.parseInt(coefficientExponent[1]);
				}
				else
				{
					coefficients[i] = Double.parseDouble(coefficientExponent[0]);
					exponents[i] = Integer.parseInt(coefficientExponent[1]);
				}
			}
			//
			else if (terms[i].charAt(0) == 'x')
			{
				coefficients[i] = 1;
				exponents[i] = Integer.parseInt(coefficientExponent[0]);
			}
			else if (terms[i].charAt(1) == 'x')
			{
				if (terms[i].charAt(0) == '-')
				{
					coefficients[i] = -1;
					exponents[i] = 1;
				}
				else if (terms[i].charAt(0) == '+')
				{
					coefficients[i] = 1;
					exponents[i] = 1;
				}
				else
				{
					coefficients[i] = Double.parseDouble(coefficientExponent[0]);
					exponents[i] = 1;
				}
			}
			else if (coefficientExponent.length == 1)
			{
				//System.out.print("potato1");
				if (terms[i].charAt(0) == 'x')
				{
					coefficients[i] = 1;
					exponents[i] = 1;
				}
				else if (terms[i].charAt(0) != 'x' && terms[i].charAt(terms[i].length() - 1) != 'x')
				{
					//for constant terms
					coefficients[i] = Double.parseDouble(terms[i]);
					exponents[i] = 0;
					//System.out.println("POTATOTOTO");
				}
				else if (terms[i].charAt(terms[i].length() - 1) == 'x')
				{
					coefficients[i] = Double.parseDouble(coefficientExponent[0]);
					exponents[i] = 1;
				}
			}
		}

	}

	public void saveToFile(String fileName)
	{
		File file = new File(fileName);
		PrintWriter writer;


		try {
			file.createNewFile();
			writer = new PrintWriter(file);
		} catch (IOException e) {
			return;
		}
		for (int i = 0; i < coefficients.length; i++)
		{
			if (coefficients[i]>0 && i == 0)
			{
				if (coefficients[i] != 1)
				{
					writer.print(coefficients[i]);
				}
				if (coefficients[i] == 1 && exponents[i] == 0)
				{
					writer.print(coefficients[i]);
				}
				if (exponents[i] != 0)
				{
					writer.print("x");
					if (exponents[i] != 1)
					{
						writer.print(exponents[i]);
					}
				}
			}
		/////////
			else if (coefficients[i]>0 && i > 0)
			{
				writer.print("+");
				if (coefficients[i] != 1)
				{
					writer.print(coefficients[i]);
				}
				if (coefficients[i] == 1 && exponents[i] == 0)
				{
					writer.print(coefficients[i]);
				}
				if (exponents[i] != 0)
				{
					writer.print("x");
					if (exponents[i] != 1)
					{
						writer.print(exponents[i]);
					}
				}
			}
			else
			{
				if (coefficients[i] != 1)
				{
					writer.print(coefficients[i]);
				}
				if (exponents[i] != 0)
				{
					writer.print("x");
					if (exponents[i] != 1)
					{
						writer.print(exponents[i]);
					}
				}
			}
		}
		writer.close();

	}
}
