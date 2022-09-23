public class Polynomial {
	double coefficients[];

	public Polynomial()
	{
		coefficients = new double[1];
		coefficients[0] = 0;
	}

	public Polynomial(double listOfCoefficients[])
	{
		int n = listOfCoefficients.length;
		coefficients = new double[n];
		for(int i = 0; i < n; i++)
		{
			coefficients[i] = listOfCoefficients[i];
		}
	}

	public Polynomial add(Polynomial other)
	{
		if (coefficients.length>other.coefficients.length)
		{
			//make a new array of length coefficients.length
			Polynomial sum = new Polynomial(coefficients);
			for( int i = 0; i<other.coefficients.length; i++)
	    		{
	    			sum.coefficients[i] = other.coefficients[i] + coefficients[i];
	    		}
	    		return sum;
		}
		else
		{
			Polynomial sum = new Polynomial(other.coefficients);
			for (int i = 0; i<coefficients.length; i++)
			{
				sum.coefficients[i] = other.coefficients[i] + coefficients[i];
			}
			return sum;
		}
	}

	public double evaluate(double x)
	{
		double result = 0;
	        int n = coefficients.length;
	        for(int i = 0; i < n; i++)
	        {
	            result = result + Math.pow(x, i) * coefficients[i];
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

}
