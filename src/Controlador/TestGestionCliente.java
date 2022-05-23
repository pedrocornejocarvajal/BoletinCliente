package Controlador;

import Cliente.*;
import java.io.File;
import java.io.IOException;


public class TestGestionCliente
{
	public static void main(String[]args) throws IOException
	{

		File ficheroClientes= new File("clientes.txt");
		File ficheroModificaciones= new File("modificaciones.txt");
		File ficheroBajas= new File("bajas.txt");
		File ficheroAltas= new File("altas.txt");
		
		Cliente cli1= new Cliente("uno","uno","1",1,"felipe I");
		Cliente cli2= new Cliente("dos","dos","2",2,"felipe II");
		Cliente cli3= new Cliente("tres","tres","3",3,"felipe III");
		Cliente cli4= new Cliente("cuatro","cuatro","4",4,"felipe IV");
		Cliente cli5= new Cliente("cinco","cinco","5",5,"felipe V");
		
		
		GestionCliente.altaCliente(ficheroClientes, cli5);
		GestionCliente.altaCliente(ficheroClientes, cli2);
		GestionCliente.altaCliente(ficheroClientes, cli4);
		GestionCliente.altaCliente(ficheroClientes, cli3);
		GestionCliente.altaCliente(ficheroClientes, cli1);
		
		//GestionCliente.OrdenarFichero(ficheroClientes);
		
		
		
		Cliente cli1M= new Cliente("UNO","UNO","1",1,"FELIPE II");
		Cliente cli3M= new Cliente("TRES","TRES","3",3,"FELIPE iii");
		
		//GestionCliente.insertarModificacionCliente(ficheroModificaciones, cli1M);
		//GestionCliente.insertarModificacionCliente(ficheroModificaciones, cli3M);
		
		//GestionCliente.realizarModificaciones(ficheroClientes, ficheroModificaciones);
		
		Cliente cli2B= new Cliente("dos","dos","2",2,"felipe II");
		Cliente cli4B= new Cliente("cuatro","cuatro","4",4,"felipe IV");
		
		//GestionCliente.insertarBajaCliente(ficheroBajas, cli2B);
		//GestionCliente.insertarBajaCliente(ficheroBajas, cli4B);
		
		//GestionCliente.realizarBajas(ficheroClientes, ficheroBajas);
		
		Cliente cli7A= new Cliente("siete","siete","7",7,"Felipe VII");
		
		
		//GestionCliente.altaCliente(ficheroAltas, cli7A);
		
		//GestionCliente.realizarAltas(ficheroClientes, ficheroAltas);
		
		
		
		
		
		
		//GestionCliente.altaClienteOrdenado(fichero, cli1);
		//GestionCliente.altaClienteOrdenado(fichero, cli2);
		//GestionCliente.altaClienteOrdenado(fichero, cli4);
		//GestionCliente.altaClienteOrdenado(fichero, cli5);
		
		
		

		GestionCliente.consultarElementoPorApellidos(ficheroClientes, "cinco");
		
		//String registro="";
		//registro=GestionCliente.buscarElementoPorCif(fichero, "1");
		//System.out.println(registro);
		
		
		//GestionCliente.modificarElemento(fichero, registro, cli10);
		
		//GestionCliente.bajaElemento(fichero, registro);
	}
}
