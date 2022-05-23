package Cliente;

public class EntradaDemasiadaLarga extends Exception
{
	public EntradaDemasiadaLarga()
	{
		System.out.println("la entrada no debe pasar de los 50 caracteres");
	}
}
