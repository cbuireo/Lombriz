package com.logisticadcn.clases;

import java.io.DataOutputStream;
import java.net.Socket;

public class EnviaTCP {
	
	//Atributos de la clase
	private String mensaje;
	private String ip;
	private int puerto;
	private boolean estado = false;
	
	
	//	Metodos Construcotres 
	
	public EnviaTCP(String mensaje, String ip, int puerto) {
		
		this.mensaje = mensaje;
		this.ip = ip;
		this.puerto = puerto;
	}
	
	public EnviaTCP() {
		
	}
	
	public void envia() {
	
	
	//Instanciamos la clase que graba el TXT
	EscribeTXT txt = new EscribeTXT();
	
	// declaramos un objeto socket para realizar la comunicación
	 Socket socket;
	 
	// declaramos e instanciamos un objeto de tipo byte
	 //byte[] mensaje_bytes = new byte[256];
	 
	// Declaramos un bloque try y catch para controlar la ejecución del subprograma
	 try {
	 
	// Instanciamos un socket con la dirección del destino y el
	// puerto que vamos a utilizar para la comunicación
	 socket = new Socket(ip,puerto);
	 
	this.estado= socket.isConnected();
	// Declaramos e instanciamos el objeto DataOutputStream
	// que nos valdrá para enviar datos al servidor destino
	 DataOutputStream out =
	 new DataOutputStream(socket.getOutputStream());
	
	 //Envía mensaje
	 out.writeChars(mensaje);
	 
	 //Cierra Conexión
	 socket.close();

	 }
	 
	// utilizamos el catch para capturar los errores que puedan surgir
	 catch (Exception e) {
	// si existen errores los mostrará en la consola y después saldrá del
	// programa
	//txt.grabaError(e.getMessage() + "error al enviar por UDP " + mensaje);
			txt.setMensaje(e.getMessage()+" error al enviar por TCP " + mensaje+" No se pudo establecer la conexión");
			txt.grabaError();
	 }
	}

	public boolean isEstado() {
		return estado;
	}


}
