package Cliente;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class OrdenacionExterna
{
	
	//para contar los registros que tiene un archivo.
	public static int contarRegistros(String fichero) throws IOException{
		int registros = 0;
		String reg = null;
		
		FileReader fr_fichero = new FileReader(fichero);
		BufferedReader br_fichero = new BufferedReader(fr_fichero);
		
		reg = br_fichero.readLine();
		
		while(reg != null){
			
			registros++;
			
			reg = br_fichero.readLine();
		}
		
		br_fichero.close();
		
		return registros;
	}
	/**
	 * Comentario: m�todo para partir un fichero
	 * void partir (String fichero, String aux1, String aux2, int longitud)
	 * Entrada: 3 cadenas y 1 entero
	 * Salida: no tiene.
	 * Precondici�n: las direcciones de las cadenas deben ser correctas, es decir, que los archivos existan, que no est�n vac�os y
	 * la longitud no debe ser negativa.
	 * Postcondici�n: obtenemos el contenido de fichero dividido en aux1 y aux2
	 * */
	
	/*
	 * PSEUDOC�DIGO DE PARTIR
	 * 
	 * abrir fichero para leer
	 * abrir aux1 para escribir
	 * abrir aux2 para escribir
	 * 
	 *  leer fichero
	 *  
	 *  mientras que no sea FF
	 *  
	 *  	para i = 0, mientra que i sea menor que la longitud y no sea FF, incrementar i de uno en uno
	 *  
	 *  		escribir en aux1 el reg de fichero
	 *  		leer fichero
	 *  
	 *  	fin para
	 *  
	 *  	para i = 0, mientra que i sea menor que la longitud y no sea FF, incrementar i de uno en uno
	 *  
	 * 			escribir en aux2 el reg de fichero
	 *  		leer fichero
	 *  
	 *  	fin para
	 *  
	 *  fin mientras
	 *  
	 *  cerrar todos los archivos abiertos
	 * 
	 * */
	public static void partir(String fichero, String aux1, String aux2, int longitud) throws IOException{
		FileReader fr_fichero = new FileReader(fichero);
		BufferedReader br_fichero = new BufferedReader(fr_fichero);
		FileWriter fw_aux1 = new FileWriter(aux1);
		BufferedWriter bw_aux1 = new BufferedWriter(fw_aux1);
		FileWriter fw_aux2 = new FileWriter(aux2);
		BufferedWriter bw_aux2 = new BufferedWriter(fw_aux2);
		String registro = null;
		
		registro = br_fichero.readLine();
		
		while(registro != null){
			
			for (int i = 0; i < longitud && registro != null; i++){
				
				bw_aux1.write(registro);
				bw_aux1.newLine();
				registro = br_fichero.readLine();//
				
			}
			
			for (int i = 0; i < longitud && registro != null; i++){
				bw_aux2.write(registro);
				bw_aux2.newLine();
				registro = br_fichero.readLine();//ya estamos actualizando aqu� la variable de salida del bucle
			}
		}
		bw_aux1.close();
		bw_aux2.close();
		br_fichero.close();
	}
	/**
	 * comentario: m�todo que mezcla conjuntos ordenados en un archivo
	 * void mezclar (String fichero, String aux1, String aux2, int longitud)
	 * Entrada: 3 cadenas y 1 entero
	 * Salida: no tiene.
	 * Precondici�n: las direcciones de las cadenas deben ser correctas, es decir, que los archivos existan, que no esten vac�os y
	 * la longitud no debe ser negativa.
	 * Postcondici�n: obtenemos un archivo ordenado a partir de dos.
	 * 
	 * */
	/* PSEUDOC�DIGO DE MEZCLAR
	 * 
	 * abrir fichero para escribir
	 * abrir aux1 para leer
	 * abrir aux2 para leer
	 * 
	 * leer aux1
	 * leer aux2
	 * 
	 * mientras no FF de aux1 y no FF aux2
	 * 		
	 * 		para mientras que contador1 sea menor que longitud y contador2 sea menor que longitud y no FF de aux1 y no FF de aux2
	 * 			
	 * 			si numero1 es menor que numero2
	 * 	
	 * 				escribir en fichero el registro de aux1
	 * 				leer aux1
	 * 				contador1++							
	 * 
	 * 			si no
	 * 
	 * 				escribir en fichero el registro de fichero2
	 * 				leer aux2
	 * 				contador2++
	 * 
	 * 			fin si
	 * 
	 * 		fin para
	 * 		
	 * 		si contador1 es menor que la longitud
	 * 			
	 * 			mientras no FF de aux1 y contador1 sea menor que la longitud
	 * 				
	 * 					escribir en fichero el registro de aux1
	 * 					leer aux1
	 * 					contador1++
	 * 			
	 * 			fin mientras
	 * 		
	 * 		si no
	 * 			mientras no FF de aux2 y contador2 sea menor que la longitud
	 * 				
	 * 					escribir en fichero el registro de aux2
	 * 					leer aux2
	 * 					contador2++
	 * 			
	 * 			fin mientras
	 * 
	 * 		fin si
	 * 
	 * 		si es FF de aux1
	 * 
	 * 				mientras no FF de aux2
	 * 
	 * 					escribir en fichero el registro de aux2 
	 * 					leer aux2
	 * 
	 * 				fin mientras
	 * 		fin si
	 * 		
	 * 		si es FF de aux2
	 * 				
	 * 			mientras no FF de aux1
	 * 			
	 * 				escribir en fichero el registro de aux1 
	 * 				leer aux1
	 * 
	 * 			fin mientras	
	 * 
	 * fin mientras
	 * 
	 * cerrar todos los archivos
	 * */
	public static void mezclar(String fichero, String aux1, String aux2, int longitud) throws IOException{
		FileWriter fw_fichero = new FileWriter(fichero);
		BufferedWriter bw_fichero = new BufferedWriter(fw_fichero);
		FileReader fr_aux1 = new FileReader(aux1);
		BufferedReader br_aux1 = new BufferedReader(fr_aux1);
		FileReader fr_aux2 = new FileReader(aux2);
		BufferedReader br_aux2 = new BufferedReader(fr_aux2);
		String registro1 = null, registro2 = null; 
		Cliente num_registro1 = null, num_registro2 = null;
		int contador1 = 0, contador2 = 0;
		
		registro1 = br_aux1.readLine();
		if(registro1 != null){
			num_registro1 = GestionCliente.transformarAObjeto(registro1);
		}
		registro2 = br_aux2.readLine();
		if(registro2 != null){
			num_registro2 = GestionCliente.transformarAObjeto(registro2);
		}
		
		while (registro1 != null && registro2 != null ){
			
			for(contador1 = 0, contador2 = 0; contador1 < longitud && contador2 < longitud && registro1 != null && registro2 != null;){
				
				if(num_registro1.compareTo(num_registro2)<1){
					bw_fichero.write(registro1);
					bw_fichero.newLine();
					registro1 = br_aux1.readLine();
					if(registro1 != null){
						num_registro1 = GestionCliente.transformarAObjeto(registro1);
					}
					contador1++;
				}
				else{
					bw_fichero.write(registro2);
					bw_fichero.newLine();
					registro2 = br_aux2.readLine();
					if(registro2 != null){
						num_registro2 = GestionCliente.transformarAObjeto(registro2);
					}
					contador2++;
				}
			}
			
			if(contador1 < longitud){
				while (registro1 != null && contador1 < longitud){
					bw_fichero.write(registro1);
					bw_fichero.newLine();
					registro1 = br_aux1.readLine();
					contador1++;
				}
			}
			else{
				while (registro2 != null && contador2 < longitud){
					bw_fichero.write(registro2);
					bw_fichero.newLine();
					registro2 = br_aux2.readLine();
					if(registro2 != null){
						num_registro2 =GestionCliente.transformarAObjeto(registro2);
					}
					contador2++;
				}
			}
			
			if(registro1 == null){
				while (registro2 != null){
					bw_fichero.write(registro2);
					bw_fichero.newLine();
					registro2 = br_aux2.readLine();
					if(registro1 != null){
						num_registro1 = GestionCliente.transformarAObjeto(registro1);
					}
				}
			}
			if(registro2 == null){
				while (registro1 != null){
					bw_fichero.write(registro1);
					bw_fichero.newLine();
					registro1 = br_aux1.readLine();
				}
			}
		}
		
		br_aux1.close();
		br_aux2.close();
		bw_fichero.close();
		
	}
	//voy a hacer el m�todo de ordenaci�n en el main porque lo que nos importa es saber c�mo se hace
	/*
	 * PSEUDOC�DIGO DE ORDENACION EXTERNA POR MEZCLA DIRECTA
	 * 
	 * contarRegistros ()
	 * 
	 * mientras que la longitud sea menor o igual que el total de registros
	 * 
	 * 		partir
	 * 		mezclar
	 * 
	 * 		actualizar variable (longitud = longitud*2)
	 * 
	 * fin mientras
	 * 
	 * */
/*	public static void main(String[] args) throws IOException {
		int longitud, total_registros;
		
		total_registros = contarRegistros("C:\\Users\\Wittel\\Dropbox\\DAM\\Programaci�n\\Boletines\\UNIDAD 10\\O.Externa\\ordenado.txt");
		
		longitud = 1;
		
		while(longitud <= total_registros){
			partir("C:\\Users\\Wittel\\Dropbox\\DAM\\Programaci�n\\Boletines\\UNIDAD 10\\O.Externa\\ordenado.txt", "C:\\Users\\Wittel\\Dropbox\\DAM\\Programaci�n\\Boletines\\UNIDAD 10\\O.Externa\\aux1.txt", "C:\\Users\\Wittel\\Dropbox\\DAM\\Programaci�n\\Boletines\\UNIDAD 10\\O.Externa\\aux2.txt", longitud);
			mezclar("C:\\Users\\Wittel\\Dropbox\\DAM\\Programaci�n\\Boletines\\UNIDAD 10\\O.Externa\\ordenado.txt", "C:\\Users\\Wittel\\Dropbox\\DAM\\Programaci�n\\Boletines\\UNIDAD 10\\O.Externa\\aux1.txt", "C:\\Users\\Wittel\\Dropbox\\DAM\\Programaci�n\\Boletines\\UNIDAD 10\\O.Externa\\aux2.txt", longitud);
			
			longitud = longitud*2;
		}
	}*/
}
