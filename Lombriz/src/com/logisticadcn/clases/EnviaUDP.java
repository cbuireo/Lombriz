package com.logisticadcn.clases;

import java.net.*;

public class EnviaUDP {
	
	//Atributos de la clase
	private String mensaje;
	private String ip;
	private int puerto;
	private boolean salio = false;
	
	//	Metodos Construcotres 
	
	public EnviaUDP(String mensaje, String ip, int puerto) {
		super();
		this.mensaje = mensaje;
		this.ip = ip;
		this.puerto = puerto;
	}
	
	public EnviaUDP() {
		
	}
	
	//Metodos
	
	public void envia() {
				
				// Instanciamos la clase Config para leer el puerto base
				LeeConfig config = new LeeConfig();
				
				// Definimos el sockets, número de bytes del buffer, y mensaje.
				DatagramSocket socketE;
				
				InetAddress address;
				byte[] mensaje_bytes = new byte[256];
				
				mensaje_bytes = mensaje.getBytes();

				//Instanciamos la clase que graba el TXT
				EscribeTXT txt = new EscribeTXT();
				
				// Paquete
				DatagramPacket paquete;


				try {
					//mensaje= mensaje;
					System.out.println("abre puerto"+config.getPuertoSalida()+" ipeq "+ip+" puertoeq "+puerto);
					socketE = new DatagramSocket(config.getPuertoSalida(),InetAddress.getByName(config.getBaseIp()));

					address = InetAddress.getByName(ip);
					
					mensaje_bytes = mensaje.getBytes();
					
					paquete = new DatagramPacket(mensaje_bytes, mensaje.length(), address, puerto);
					
					socketE.send(paquete);
					
					socketE.close();
					System.out.println("Cerró puertpo");
					salio = true;
					
				} catch (Exception e) {
					
					//txt.grabaError(e.getMessage() + "error al enviar por UDP " + mensaje);
					txt.setMensaje(e.getMessage()+" error al enviar por UDP " + mensaje+" Puerto ocupado");
					txt.grabaError();
					
				}
			
		
		
	}

	//Gets y Sets
	public boolean isSalio() {
		return salio;
	}
	
	
	
	
	

	
}
