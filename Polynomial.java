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
	int n = coefficients.length;
	for( int i = 0; i<n; i++)
	{
	other.coefficients[i] = other.coefficients[i] + coefficients[i];
	}
	return other;
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
