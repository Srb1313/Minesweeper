package Extensions;
public class StringExtensions
{
	public static String reverse(String value)
	{
		return new StringBuilder(value).reverse().toString();
	}
}
