package com.logisticadcn.clases;

import java.io.File;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.LocalTime;

public class EscribeTXT {

	private String mensaje;

//	Metodos constructores
	public EscribeTXT(String mensaje) {
		
		this.mensaje = mensaje;
	}
	
	public EscribeTXT() {
		
		
	}


	
	//Metodos
	public void graba() {

		try {
			//Creo constructor para leer la config
			LeeConfig config = new LeeConfig();
			// Crear un objeto File se encarga de crear o abrir acceso a un archivo que se
			// especifica en su constructor
			File archivo = new File("Recibido_"+LocalDate.now() + ".txt");
			
			// Crear objeto FileWriter que sera el que nos ayude a escribir sobre archivo
			FileWriter escribir = new FileWriter(archivo, true);
			if(config.getGrabaLog()==true) {
			escribir.write(LocalTime.now() + "-->" + mensaje);
			escribir.write("\r\n");}
			// Cerramos la conexion
			escribir.close();
		} // Si existe un problema al escribir cae aqui
		catch (Exception e) {
			System.out.println("Error al escribir");
		}
	}
	
	public void grabaError() {

		try {
			//Creo constructor para leer la config
			LeeConfig config = new LeeConfig();
			// Crear un objeto File se encarga de crear o abrir acceso a un archivo que se
			// especifica en su constructor
			File archivo = new File("Errores_"+LocalDate.now() + ".txt");

			// Crear objeto FileWriter que sera el que nos ayude a escribir sobre archivo
			FileWriter escribir = new FileWriter(archivo, true);
			if(config.getGrabaErrores()==true) {
			escribir.write(LocalTime.now() + "-->" + mensaje);
			escribir.write("\r\n");}
			// Cerramos la conexion
			escribir.close();
		} // Si existe un problema al escribir cae aqui
		catch (Exception e) {
			System.out.println("Error al escribir");
		}
	}
	

	public void grabaMacros() {

		try {
			//Creo constructor para leer la config
			LeeConfig config = new LeeConfig();
			// Crear un objeto File se encarga de crear o abrir acceso a un archivo que se
			// especifica en su constructor
			File archivo = new File("Macro_"+LocalDate.now() + ".txt");

			// Crear objeto FileWriter que sera el que nos ayude a escribir sobre archivo
			FileWriter escribir = new FileWriter(archivo, true);
			if(config.getGrabaMacros()==true) {
			escribir.write(LocalTime.now() + "-->" + mensaje);
			escribir.write("\r\n");}
			// Cerramos la conexion
			escribir.close();
		} // Si existe un problema al escribir cae aqui
		catch (Exception e) {
			System.out.println("Error al escribir");
		}
	}
	
	public void grabaPrograma() {

		try {
			//Creo constructor para leer la config
			LeeConfig config = new LeeConfig();
			// Crear un objeto File se encarga de crear o abrir acceso a un archivo que se
			// especifica en su constructor
			File archivo = new File("Programa_"+LocalDate.now() + ".txt");

			// Crear objeto FileWriter que sera el que nos ayude a escribir sobre archivo
			FileWriter escribir = new FileWriter(archivo, true);
			if(config.getGrabaPrograma()==true) {
			escribir.write(LocalTime.now() + "-->" + mensaje);
			escribir.write("\r\n");}
			// Cerramos la conexion
			escribir.close();
		} // Si existe un problema al escribir cae aqui
		catch (Exception e) {
			System.out.println("Error al escribir");
		}
	}
	//Gets Y Sets
	
	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
	
}
