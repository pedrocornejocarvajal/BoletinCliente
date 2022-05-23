package Cliente;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;



/*Cada registro de un fichero de texto (CLIENTES.TXT) contiene la siguiente
informaci�n sobre un cliente:
Nombre (hasta 50 caracteres).
Apellidos (Hasta 50 caracteres)
C.I.F. (hasta 10 caracteres).
Categor�a (entero sin signo).
Direcci�n (hasta 50 caracteres).
salto de l�nea.
Los datos est�n ordenados ascendentemente por CIF.
Escribe un programa que permita mantener la informaci�n del fichero. Deber� incluir, al menos,
altas, bajas, modificaciones, consultas y b�squeda de un elemento.
Las consultas y b�squedas deben poder hacerse por apellidos y por CIF.//**/


/**PROPIEDADES
 * nombre: cadena consultable y modificable. maximo 50 caracteres
 * apellidos: cadena consultabley modificable. maximo 50 caracteres
 * cif: cadena consultable y modificable maximo 10 caracteres
 * categoria: entero consultable y modificable
 * direccion: cadena consultable y modificable maximo 50 caracteres
 * 
 * METODOS:
 * compareTo: metodo que compara dos clientes en funcion de su cif
 * 
 * INTERFAZ
 * String getNombre()
 * void setNombre(String nombre)
 * String getApellidos()
 * void setApellidos(String apellidos)
 * String getCif()
 * void setCif(String cif)
 * int getCategoria()
 * void setCategoria(int categoria)
 * String getDireccion()
 * void setDireccion(String direccion)
 * 
 */
public class Cliente implements Comparable<Cliente>
{

	public static Integer id;

	private String nombre;
	private String apellidos;
	private String cif;
	private int categoria;
	private String direccion;
	
	public Cliente() throws IOException
	{
		id=cargarValor();
		
		this.nombre="";
		this.apellidos="";
		this.cif="";
		this.categoria=0;
		this.direccion="";
		
		
	}
	
	public Cliente(String nombre, String apellidos, String cif, int categoria, String direccion) throws IOException
	{
		id=cargarValor();
		try
		{
		this.nombre=nombre;
		if(this.nombre.length()>50)
			throw new EntradaDemasiadaLarga();
		}
		catch (EntradaDemasiadaLarga no)
		{
			no.getMessage();
			System.out.println("el nombre esta indefinido");
			this.nombre="indefinido";
		}
		try
		{
		this.apellidos=apellidos;
		if(this.apellidos.length()>50)
			throw new EntradaDemasiadaLarga();
		}
		catch (EntradaDemasiadaLarga no) 
		{
			no.getMessage();
			System.out.println("el apellido esta indefinido");
			this.apellidos="indefinido";
		}
		
		try
		{
		this.cif=cif;
		if(this.cif.length()>10)
			throw new CifDemasiadoLargo();
		}
		catch (CifDemasiadoLargo e)
		{
		e.getMessage();
		System.out.println("el cif esta indefinido");
		this.cif="indefinido";
		}
		this.categoria=categoria;
		try
		{
		this.direccion=direccion;
		if(this.direccion.length()>50)
			throw new EntradaDemasiadaLarga();
		}
		catch (EntradaDemasiadaLarga e)
		{
			e.getMessage();
			System.out.println("la direccion esta vacia");
			this.direccion="indefinido";
		}
	}

	public String getNombre() 
	{
		return nombre;
	}

	public void setNombre(String nombre) 
	{
		try
		{
		this.nombre=nombre;
		if(this.nombre.length()>50)
			throw new EntradaDemasiadaLarga();
		}
		catch (EntradaDemasiadaLarga no) 
		{
			no.getMessage();
			System.out.println("el nombre esta indefinido");
			this.nombre="indefinido";
		}
	}

	public String getApellidos() 
	{
		return apellidos;
	}

	public void setApellidos(String apellidos) 
	{
		try
		{
		this.apellidos=apellidos;
		if(this.apellidos.length()>50)
			throw new EntradaDemasiadaLarga();
		}
		catch (EntradaDemasiadaLarga no) 
		{
			no.getMessage();
			System.out.println("el apellido esta indefinido");
			this.apellidos="indefinido";
		}
	}

	public String getCif() 
	{
		return cif;
	}

	public void setCif(String cif) 
	{
		try
		{
		this.cif=cif;
		if(this.cif.length()>10)
			throw new CifDemasiadoLargo();
		}
		catch (CifDemasiadoLargo e)
		{
		e.getMessage();
		System.out.println("el cif esta indefinido");
		this.cif="indefinido";
		}
	}

	public int getCategoria() 
	{
		return categoria;
	}

	public void setCategoria(int categoria) 
	{
		this.categoria = categoria;
	}

	public String getDireccion() 
	{
		return direccion;
	}

	public void setDireccion(String direccion) 
	{
		try
		{
		this.direccion=direccion;
		if(this.direccion.length()>50)
			throw new EntradaDemasiadaLarga();
		}
		catch (EntradaDemasiadaLarga e)
		{
			e.getMessage();
			System.out.println("la direccion esta vacia");
			this.direccion="indefinido";
			
		}
	}

	/**funcion que muestra al cliente en el formato exacto en el que se va a guardar en un fichero
	 * entrada nada
	 * salida una cadena
	 * precondiciones nada
	 * postcondiciones la cadena devuelta representa el formato en el que se va a registrar el objeto cliente en un fichero de texto
	 */
	public String toString()
	{
		return this.getNombre()+","+this.getApellidos()+","+this.getCif()+","+this.getCategoria()+","+this.getDireccion()+","+this.id;
	}

	/**funcion que compara dos clientes en funcion de su nif
	 * entrada un cliente
	 * precondiciones el cliente es valido
	 * salida un entero
	 * postcondicion el entero vale 1 si el cliente que invoca es mayor -1 si menor, 0 si igual
	 */
	public int compareTo(Cliente o) 
	{
		int numero=0;
		
		if(this.cif.charAt(0)>o.getCif().charAt(0))
			numero=1;
		if(this.cif.charAt(0)<o.getCif().charAt(0))
			numero=-1;
		
		return numero;
	}
	
	   public Integer cargarValor() throws IOException
	   {
	    	int valor=0;
	    	String cadena;
	    	String valorID="";
	    	String[] campos;
	    	
			if(new File("cliente.txt").exists())
			{
			BufferedReader lectura= new BufferedReader(new FileReader("cliente.txt"));
			cadena=lectura.readLine();
			while(cadena!=null)
			{
				campos=cadena.split(",");
				valorID=campos[5];
				cadena=lectura.readLine();	
			}
			valor=Integer.parseInt(valorID);
			}
			return valor;
	   }

	
	   
	   
}
