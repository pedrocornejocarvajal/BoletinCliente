package Cliente;

public class CifDemasiadoLargo extends Exception
{
	public CifDemasiadoLargo()
	{
		System.out.println("el cif no debe sobrepasar los 10 caracteres");
	}
}
