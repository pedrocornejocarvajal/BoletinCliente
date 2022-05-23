package Controlador;


import Cliente.Cliente;

import java.io.IOException;


public class TestCliente 
{
	public static void main(String[]args) throws IOException
	{
		Cliente cli1= new Cliente("carlobbbbbbpppp","doraphhhhhhppo","540bbbbbbbbbbbbbbb",'c',"felipe II");
		Cliente cli2= new Cliente("carlos","dorado","240",2,"felipeII");
		
		System.out.println(cli1);
		System.out.println(cli2);
		
		System.out.println(cli1.compareTo(cli2));

		System.out.println(cli2.compareTo(cli1));
		
		
	
	
	}
}
