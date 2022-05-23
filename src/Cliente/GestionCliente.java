package Cliente;



import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


/**PROPIEDADES
 * 
 * ninguna
 * 
 * 
 * METODOS:
 * 
 * altaCliente: procedimiento que registra un cliente en el final de un fichero de texto
 * consultarElementoPorApellidos: procedimiento que muestra el registro al que le corresponda los apellidos pasados por parametro
 * consultarElementoPorCif: procedimiento que muestra el registro al que le corresponda el cif pasado por parametro
 * buscarElementoPorApellidos: funcion que devuelve el registro al que le corresponda los apellidos pasado por parametro
 * buscarElementoPorCif: funcion que devuelve el registro al que le corresponda el cif pasado por parametro
 * insertarModificacionCliente: procedimiento que inserta en el fichero de modificaciones un cliente para ser modificado en un futuro sobre le original de clientes
 * insertarBajaCliente: procedimiento que inserta en el fichero d ebajas un cliente para ser dado de baja en en un futuro sobre el fichero original de clientes
 * realizarAltas: procedimiento que recoge en el fichero clientes todas las altas registradas en el fichero de altas
 * realizarBajas: procedimiento que recoge en el fichero clientes todas las altas registradas en el fichero de bajas
 * relizarModificaciones: procedimiento que recoge en el fichero clientes todas las modificaciones registradas en el fichero de modificaciones
 * transformarAObjeto: funcion que transforma un registro cliente en forma de cadena a un objeto cliente
 * ordenarFichero: metodo que ordena un fichero ascendentemente segun el compareTo implementado por el registro
 * 
 * OTROS METODOS QUE IBA A BORRAR:
 * 
  altaClienteOrdenado: procedimiento que registra un cliente en el lugar que le corresponde en un fichero de texto segun cif
  modificarElemento:procedimiento que modifica un elemento del fichero directamente con ayuda de un fichero auxiliar
  bajaElemento: procedimiento que borra del fichero un elemento directamente con ayuda de un fichero auxiliar
 * 
 * 
 * INTERFAZ
 * void altaCliente(File fichero,Cliente cliente)
 * Cliente transformarAObjeto(String registro)
 * void consultarElementoPorApellidos(File fichero, String apellidos)
 * void consultarElementoPorCif(File fichero, String cif)
 * Cliente buscarElementoPorApellidos (File fichero, String apellidos)
 * Cliente buscarElementoPorCif (File fichero, String apellidos)
 * void insertarModifacionCliente(File fichero, Cliente cli)
 * void insertarBajaCliente(File fichero, Cliente cli)
 * void realizarAltas(File clientes, File altas)
 * void realizarModificaciones(File clientes, File modificaciones)
 * void realizarBajas(File clientes, File bajas)
 * 
 * 
 * 
 * 
 * void altaClienteOrdenado(File fichero, Cliente cliente)
 * void modificarElemento(File fichero, String registroViejo, Cliente registroNuevo)
 * void bajaElemento(File fichero, String registro)
 * 
 */


public class GestionCliente 
{
	/**procedimiento que escribe en un fichero de texto un cliente
	 * entrada un objeto file y un objeto cliente a escribir
	 * precondiciones el cliente debe ser valido. el fichero no tiene por que existir
	 * salida nada
	 * postcondiciones el cliente queda registrado a final de fichero en forma de cadena

	 */
	public static void altaCliente(File fichero, Cliente cliente)
	{
		Cliente.id++;
		FileWriter fw;
		BufferedWriter escritor=null;
		try 
		{
			try
			{
			fw= new FileWriter(fichero,true);
			escritor= new BufferedWriter(fw);
			escritor.write(cliente.toString()); //as� se guarda un registro como string
			escritor.newLine();
			
			}
			catch (FileNotFoundException e) 
			{
				e.printStackTrace();
			}
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}	
		finally
		{
			try
			{
				assert escritor != null;
				escritor.close();
			}
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
	}
	
	/**procedimiento que registra un cliente en un fichero de texto en el lugar que le corresponde segun su cif
	 * entrada un fichero y un cliente
	 * precondiciones el cliente debe ser valido y el fichero debe contener al menos 1 registro insertado
	 * salida nada
	 * postcondiciones el cliente queda registrado en el lugar que le corresponde
	 */
	public static void altaClienteOrdenado(File fichero, Cliente cliente) 
	{
		boolean insertado=false;
		Cliente clienteEnFichero;
		String linea;
		File auxiliar= new File("auxiliar.txt");
		Cliente.id++;
		FileReader fr;
		BufferedReader lector=null;
		FileWriter fw;
		BufferedWriter escritor=null;
		try 
		{
			fr= new FileReader(fichero);
			lector= new BufferedReader(fr);
			try
			{
			fw= new FileWriter(auxiliar);
			escritor= new BufferedWriter(fw);
			linea=lector.readLine();
			while(linea!=null || !insertado)
			{
				clienteEnFichero=transformarAObjeto(linea);
				if(cliente.compareTo(clienteEnFichero)<0 && !insertado)
				{
					escritor.write(cliente.toString());
					escritor.newLine();
					escritor.write(linea);
					escritor.newLine();
					insertado=true;
				}
				else
				{
					escritor.write(linea);
					escritor.newLine();
				}
				
				linea=lector.readLine();
				if(linea==null && !insertado)
				{
					escritor.write(cliente.toString());
					escritor.newLine();
					insertado=true;
				}
			}
			}
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
		catch (FileNotFoundException e) 
		{
		e.printStackTrace();
		}
		finally
		{
			try
			{
			lector.close();
			escritor.close();
			fichero.delete();
			auxiliar.renameTo(new File("clientes.txt"));
			}
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
	}
	
	
	/**procedimiento que realiza una consulta a un fichero de texto y muestra el registro correspondiente en pantalla
	 * entrada un fichero de texto y una cadena que corresponde a los apellidos del registro a consultar
	 * precondiciones ambas entradas son validas
	 * salida nada
	 * postcondicion en caso de existir un registro con dichos apellidos se mostrara en pantalla con todos sus datos
	 * @param fichero
	 * @param apellidos
	 */
	public static void consultarElementoPorApellidos(File fichero,String apellidos)
	{
		String linea="";
		String[] campos;
		boolean encontrado=false;
		FileReader fr=null;
		BufferedReader lector=null;
		try 
		{
			fr = new FileReader(fichero);
			lector= new BufferedReader(fr);
			try 
			{
			linea=lector.readLine();
			while(linea!=null && !encontrado)
			{
				campos=linea.split(",");
				if(campos[1].equals(apellidos))
				{
					System.out.println(linea);
					encontrado=true;
				}
				linea=lector.readLine();
				
			}
			} catch (IOException e) 
			{
				e.printStackTrace();
			}
		
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
			lector.close();
			}
			catch (Exception e) 
			{
			e.printStackTrace();
			}
		}
	}
	/**procedimiento que realiza una consulta a un fichero de texto y muestra el registro correspondiente en pantalla
	 * entrada un fichero de texto y una cadena que corresponde al dni del registro a consultar
	 * precondiciones ambas entradas son validas
	 * salida nada
	 * postcondicion en caso de existir un registro con dicho dni se mostrara en pantalla con todos sus datos
	 * @param fichero
	 * @param cif
	 */
	public static void consultarElementoPorCif(File fichero,String cif)
	{
		String linea="";
		String[] campos;
		boolean encontrado=false;
		FileReader fr=null;
		BufferedReader lector=null;
		try 
		{
			fr = new FileReader(fichero);
			lector= new BufferedReader(fr);
			try 
			{
			linea=lector.readLine();
			while(linea!=null && !encontrado)
			{
				campos=linea.split(",");
				if(campos[2].equals(cif))
				{
					System.out.println(linea);
					encontrado=true;
				}
				linea=lector.readLine();
				
			}
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		} 
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
			lector.close();
			}
			catch (Exception e) 
			{
			e.printStackTrace();	
			}
		}
	}
	/**funcion que busca en un fichero de texto un registro tipo cliente mediante el cif
	 * entrada un fichero y una cadena que corresponde al cif del elemento a buscar
	 * precondiciones ambos deben ser validos
	 * salida una cadena
	 * postcondiciones la cadena es el registro del fichero correspondiente al cif introducido
	 * @param fichero
	 * @param cif
	 * @return
	 */
	public static String buscarElementoPorCif(File fichero,String cif)
	{
		String registro="";
		String linea="";
		String[] campos;
		boolean encontrado=false;
		FileReader fr=null;
		BufferedReader lector=null;
		try 
		{
			 fr = new FileReader(fichero);
			 lector= new BufferedReader(fr);
			try 
			{
			linea=lector.readLine();
			while(linea!=null && !encontrado)
			{
				campos=linea.split(",");
				if(campos[2].equals(cif))
				{
					registro=linea;
					encontrado=true;
				}
				linea=lector.readLine();
				
			}
			lector.close();
			} catch (IOException e) 
			{
				e.printStackTrace();
			}
		
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try 
			{
				lector.close();
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		return registro;
	}
	
	/**funcion que busca en un fichero de texto un registro tipo cliente mediante los apellidos
	 * entrada un fichero y una cadena que corresponde al cif del elemento a buscar
	 * precondiciones ambos deben ser validos
	 * salida una cadena
	 * postcondiciones la cadena es el registro del fichero correspondiente a los apellidos introducidos
	 * @param fichero
	 * @param apellidos
	 * @return
	 */
	public static String buscarElementoPorApellidos(File fichero,String apellidos)
	{
		String registro="";
		String linea="";
		String[] campos;
		boolean encontrado=false;
		FileReader fr=null;
		BufferedReader lector=null;
		try 
		{
			fr = new FileReader(fichero);
			 lector= new BufferedReader(fr);
			try 
			{
			linea=lector.readLine();
			while(linea!=null && !encontrado)
			{
				campos=linea.split(",");
				if(campos[2].equals(apellidos))
				{
					registro=linea;
					encontrado=true;
				}
				linea=lector.readLine();
				
			}
			} catch (IOException e) 
			{
				e.printStackTrace();
			}
		
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try 
			{
				lector.close();
			} catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
		return registro;
	}
	
	/**procedimiento que modifica un registro. Para ello: primero el registro es borrado y luego sobreescrito con el nuevo registro
	 * entrada un objeto file que es un fichero de texto de clientes, un registro que es el cliente a modificar y un objeto cliente que el cliente modificado
	 * salida nada
	 * precondiciones: las entradas son validas
	 * postcondiciones el elemento queda modificado por un nuevo registro
	 * @param fichero
	 * @param registro
	 * @param nuevo
	 */
	public static void modificarElemento(File fichero,String registro,Cliente nuevo)
	{
		String linea;
		FileReader fr=null;
		BufferedReader lector=null;
		FileWriter fw=null;
		BufferedWriter escritor=null;
		File temporal=null;
		try 
		{

			temporal= new File("cliente2.txt");
			fr = new FileReader(fichero);
			lector= new BufferedReader(fr);
			try
			{
			fw= new FileWriter(temporal,true);
			escritor= new BufferedWriter(fw);
			linea=lector.readLine();
			while(linea!=null)
			{
					if(!linea.equals(registro))
					{
					escritor.write(linea);
					escritor.newLine();
					}
					else
					{
					escritor.write(nuevo.toString());
					escritor.newLine();
					}
				linea=lector.readLine();
			}
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		catch (FileNotFoundException e) 
		{
				e.getMessage();
		}
		finally
		{
			try
			{
			lector.close();
			escritor.close();
			fichero.delete();
			temporal.renameTo(new File("clientes.txt"));
			}
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		}	
	}
	/**procedimiento que borra del fichero el registro pasado por parametro
	 * entrada un fichero de texto y una cadena que es el registro a borrar
	 * precondiciones las entradas son validas
	 * salida nada
	 * postoncidionces el elemento queda borrado del fichero de texto
	 * @param fichero
	 * @param registro
	 */
	public static void bajaElemento(File fichero,String registro)
	{
		File temporal= new File("cliente2.txt");
		String linea="";
		FileReader fr=null;
		BufferedReader lector=null;
		FileWriter fw=null;
		BufferedWriter escritor=null;
		try 
		{
			fr = new FileReader(fichero);
			lector= new BufferedReader(fr);
			escritor= new BufferedWriter(fw);
			try
			{
			fw= new FileWriter(temporal,true);
			linea=lector.readLine();
			while(linea!=null)
			{
					if(!linea.equals(registro))
					{
					escritor.write(linea);
					escritor.newLine();
					}
				linea=lector.readLine();
			}
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
				
		}
		finally
		{
			try
			{
			lector.close();
			escritor.close();
			fichero.delete();
			temporal.renameTo(new File("clientes.txt"));
			}
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
	}
	
	/**funcion que transforma un registro de un fichero de texto en un objeto cliente
	 * entrada una cadena
	 * precondiciones la cadena debe valida. Corresponde a un registro tipo cliente de un fichero de texto
	 * salida un cliente
	 * postcondiciones la cadena enviada por parametro sale devuelta en un objeto cliente
	 * @param registro
	 * @return
	 * @throws IOException
	 */
	public static Cliente transformarAObjeto(String registro) throws IOException
	{
		String[] campos= registro.split(",");
		int categoria= Integer.parseInt(campos[3]);
		return new Cliente(campos[0],campos[1],campos[2],categoria ,campos[4]);
	}
	
	/**procedimiento que inserta en el fichero de modificaciones pasado por parametro el cliente pasado por parametro
	 * entrada un fichero de modificaciones para clientes y el cliente a modificar
	 * salida nada
	 * precondiciones ambos son validos
	 * postcondiciones el cliente queda registrado en el fichero de modificacion
	 * @param ficheroModificacion
	 * @param cliente
	 */
	public static void insertarModificacionCliente(File ficheroModificacion, Cliente cliente)
	{
		Cliente.id++;
		FileWriter fw=null;
		BufferedWriter escritor=null;
		try 
		{
			try
			{
			fw= new FileWriter(ficheroModificacion,true);
			escritor= new BufferedWriter(fw);
			escritor.write(cliente.toString());
			escritor.newLine();
			}
			catch (FileNotFoundException e) 
			{
				e.printStackTrace();
			}
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}	
		finally
		{
			try
			{
			escritor.close();
			}
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
	}
	/**procedimiento que inserta en el fichero de bajas pasado por parametro el cliente pasado por parametro
	 * entrada un fichero de bajas para clientes y el cliente a dar de baja
	 * salida nada
	 * precondiciones ambos son validos
	 * postcondiciones el cliente queda registrado en el fichero de bajas
	 * @param ficheroBaja
	 * @param cliente
	 */
	public static void insertarBajaCliente(File ficheroBaja, Cliente cliente)
	{
		Cliente.id++;
		FileWriter fw=null;
		BufferedWriter escritor=null;
		try 
		{
			try
			{
			fw= new FileWriter(ficheroBaja,true);
			escritor= new BufferedWriter(fw);
			escritor.write(cliente.toString());
			escritor.newLine();
			}
			catch (FileNotFoundException e) 
			{
				e.printStackTrace();
			}
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}	
		finally
		{
			try
			{
			escritor.close();
			}
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
	}
	
	/**procedimiento que realiza todas las modificaciones registradas en el fichero de modificaciones
	 * entrada dos ficheros, el original de clientes y el de modificaciones
	 * salida nada
	 * precondiciones ambos son validos
	 * postcondiciones el fichero clientes recoger� todas las modificaciones registradas en el fichero de modificaciones
	 * 
	 * @param clientes
	 * @param modificaciones
	 */
	public static void realizarModificaciones(File clientes, File modificaciones) 
	{
		Cliente clienteOriginal=null;
		Cliente clienteModificacion=null;
		String lineaOriginal="";
		String lineaMod="";
		File ficheroFinal=null;
		FileWriter fw=null;
		BufferedWriter escritor=null;
		FileReader fr=null;
		BufferedReader lectorModificaciones=null;
		FileReader fr2=null;
		BufferedReader lectorOriginal=null;
		ficheroFinal= new File ("ficheroFinal.txt");
			try
			{
			fw= new FileWriter(ficheroFinal);
			escritor= new BufferedWriter(fw);
			fr= new FileReader(clientes);
			lectorOriginal= new BufferedReader(fr);
			fr2= new FileReader(modificaciones);
			lectorModificaciones=new BufferedReader(fr2);
			lineaOriginal=lectorOriginal.readLine();
			lineaMod=lectorModificaciones.readLine();
			while(lineaMod!=null && lineaOriginal!=null)
			{
				clienteOriginal=transformarAObjeto(lineaOriginal);
				clienteModificacion=transformarAObjeto(lineaMod);
				if(clienteOriginal.compareTo(clienteModificacion)==0)
				{
					escritor.write(clienteModificacion.toString());
					escritor.newLine();
					lineaMod=lectorModificaciones.readLine();
					lineaOriginal=lectorOriginal.readLine();
				}
				else
				{
					escritor.write(clienteOriginal.toString());
					escritor.newLine();
					lineaOriginal=lectorOriginal.readLine();
				}
			}
			while(lineaOriginal!=null)
			{
				escritor.write(lineaOriginal);
				escritor.newLine();
				lineaOriginal=lectorOriginal.readLine();
			}
			}
			catch (IOException e) 
			{
				e.printStackTrace();
			}
			finally
			{
				try
				{
				escritor.close();
				lectorModificaciones.close();
				lectorOriginal.close();
				//clientes.delete();
				//modificaciones.delete();
				//ficheroFinal.renameTo(new File("clientes.txt"));
				}
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
	}
		
	/**procedimiento que realiza todas las bajas registradas en el fichero de bajas
	 * entrada dos ficheros, el original de clientes y el de bajas
	 * salida nada
	 * precondiciones ambos son validos
	 * postcondiciones el fichero clientes recoger� todas las bajas registradas en el fichero de modificaciones
	 * 
	 * @param clientes
	 * @param bajas
	 */
		public static void realizarBajas(File clientes, File bajas) 
		{
			Cliente clienteOriginal=null;
			Cliente clienteBaja=null;
			String lineaOriginal="";
			String lineaBaja="";
			File ficheroFinal=null;
			FileWriter fw=null;
			BufferedWriter escritor=null;
			FileReader fr=null;
			BufferedReader lectorBajas=null;
			FileReader fr2=null;
			BufferedReader lectorOriginal=null;
			ficheroFinal= new File ("ficheroFinal.txt");
				try
				{
				fw= new FileWriter(ficheroFinal);
				escritor= new BufferedWriter(fw);
				fr= new FileReader(clientes);
				lectorOriginal= new BufferedReader(fr);
				fr2= new FileReader(bajas);
				lectorBajas=new BufferedReader(fr2);
				lineaOriginal=lectorOriginal.readLine();
				lineaBaja=lectorBajas.readLine();
				while(lineaBaja!=null && lineaOriginal!=null)
				{
					clienteOriginal=transformarAObjeto(lineaOriginal);
					clienteBaja=transformarAObjeto(lineaBaja);
					if(clienteOriginal.compareTo(clienteBaja)==0)
					{
						lineaBaja=lectorBajas.readLine();
						lineaOriginal=lectorOriginal.readLine();
					}
					else
					{
						escritor.write(clienteOriginal.toString());
						escritor.newLine();
						lineaOriginal=lectorOriginal.readLine();
					}
				}
				while(lineaOriginal!=null)
				{
					escritor.write(lineaOriginal);
					escritor.newLine();
					lineaOriginal=lectorOriginal.readLine();
				}
				}
				catch (IOException e) 
				{
					e.printStackTrace();
				}
				finally
				{
					try
					{
					escritor.close();
					lectorBajas.close();
					lectorOriginal.close();
					clientes.delete();
					bajas.delete();
					ficheroFinal.renameTo(new File("clientes.txt"));
					}
					catch (Exception e) {
						e.printStackTrace();
					}
				}
			
				
		
		
	}
	
	
	/**procedimiento que realiza todas las altas registradas en el fichero de altas
	 * entrada dos ficheros, el original de clientes y el de altas
	 * salida nada
	 * precondiciones ambos son validos
	 * postcondiciones el fichero clientes a�adira todas las altas registradas en el fichero de altas
	 * 
	 * @param clientes
	 * @param altas
	 */
	public static void realizarAltas(File clientes, File altas)
	{
			FileReader fr= null;
			BufferedReader lector=null;
			FileWriter fw= null;
			BufferedWriter escritor=null;
			String linea="";
			
			try 
			{
				fr= new FileReader(altas);	
				try
				{
					fw= new FileWriter(clientes,true);
					lector= new BufferedReader(fr);
					escritor= new BufferedWriter(fw);
					linea=lector.readLine();
					
					while(linea!=null)
					{
						escritor.write(linea);
						linea=lector.readLine();
					}
				} catch (IOException e) 
				{
					e.printStackTrace();
				}
				
			} catch (FileNotFoundException e)
			{
				e.printStackTrace();
			}
			finally
			{
				try
				{
				//escritor.close();
				//lector.close();
				//altas.delete();
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
				
			}
	}
			
		/**procedimiento que ordena un fichero de forma ascendente segun el compareTo implementado por el registro del fichero
		 * entrada un fichero a ordenar
		 * precondiciones el fichero debe existir y estar lleno
		 * salida nada
		 * postcondiciones el fichero queda ordenado ascendentemente
		 */
		public static void OrdenarFichero(File fichero) throws IOException
		{
			int longitud, total_registros;
			
			total_registros = OrdenacionExterna.contarRegistros(fichero.getPath());
			
			longitud = 1;
			
			while(longitud <= total_registros){
				OrdenacionExterna.partir(fichero.getPath(), "aux1.txt", "aux2.txt", longitud);
				OrdenacionExterna.mezclar(fichero.getPath(), "aux1.txt", "aux2.txt", longitud);
				
				longitud = longitud*2;
		}
		
			
			
	}
	
	public static void sincronizar(File clientes, File modificacionesOBajas)
	{
		if(modificacionesOBajas.getPath().equals("modificaciones"))
		realizarModificaciones(clientes, modificacionesOBajas);
		else
		realizarBajas(clientes, modificacionesOBajas);
	}
	
	public static void sincronizar(File clientes, File modificaciones, File bajas)
	{
		realizarModificaciones(clientes, modificaciones);
		realizarModificaciones(clientes, bajas);
	}
	
	
	
}
