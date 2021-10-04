package com.logisticadcn.AppPricipal;

import java.awt.Font;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JFrame;

import com.logisticadcn.clases.ConexionBase;
import com.logisticadcn.clases.EnviaTCP;
import com.logisticadcn.clases.EnviaUDP;
import com.logisticadcn.clases.EscribeTXT;
import com.logisticadcn.clases.Etiqueta;
import com.logisticadcn.clases.FechaHora;
import com.logisticadcn.clases.LeeConfig;
import com.logisticadcn.clases.Ventana;

public class Lombriz {

	public static void main(String[] args) {
		
		//INSTANCIAMOS LA CLASE FECHA
				FechaHora fecha = new FechaHora();
		// Instanciamos la clase para leer el archivo de config.
		LeeConfig config = new LeeConfig();
		//Instanciamos la clase que graba el TXT
		EscribeTXT txt = new EscribeTXT();
		// instanciamos la ventana
				Ventana macro = new Ventana();
				
				//Instanciamos la Etiqueta de mensajes recibidos
				Etiqueta eti = new Etiqueta();
				
				//Instanciamos el titulo de Etiqueta de cantidad de mensajes recibidos
//				Etiqueta etiEstadoa = new Etiqueta();
				
				//Instanciamos la Etiqueta de cantidad de mensajes recibidos
				Etiqueta etiEstado = new Etiqueta();
				
				//Prendo la ventana
				macro.Ventana();
				macro.setVisible(true);
				//fuente de la ventana
				macro.setFont(new Font("Courier", Font.PLAIN,18));
				//Título de la ventana
				//macro.setTitle("Arrancado: "+fecha.ahoraHumano()"+" Lombriz:"+config.getIpDestino()+" Puerto:"+config.getPuertoDestino()+"";
				macro.setTitle("Arrancado: "+fecha.ahoraHumano()+" Lombriz IP destino: "+config.getIpDestino()+" puerto destino:"+config.getPuertoDestino());
				//Boton cerrar de la ventana cierra todo
				macro.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
				//Armo todas las etiquetas
				eti.setBounds(0,1, 1000, 18);
				eti.setFont(new Font("Courier", Font.PLAIN,18));
				//etiCantidad.setBounds(15,11, 2000, 18);
				//etiCantidad.setFont(new Font("Courier", Font.PLAIN,18));
				etiEstado.setBounds(0,4, 1000,18);
				etiEstado.setFont(new Font("Courier", Font.PLAIN,18));
				
				macro.add(etiEstado);
				macro.add(eti);
		
		
		String trama="";
		String userId="";
		int pausa;
		boolean salir = false;
		long recid=0;
		long recidAnterior=0;

		
		
		while (salir == false) {
			
			ConexionBase base = new ConexionBase();
			
			ResultSet usuarioContraseña = null;
			
			try{
				PreparedStatement validaUsuario = base.getConnection().prepareStatement("select UserId from dorsac.dbo.users where UserName = '"+config.getUsuarioTopo()+"' and Password = '"+config.getContrasenñaTopo()+"'");
				usuarioContraseña = validaUsuario.executeQuery();
				//System.out.println("paso"+usuarioContraseña.);
				
				if (usuarioContraseña.next()) {
				    //Usuario y contraseña válido
					do {
				       
				       userId= usuarioContraseña.getString(1);
				    } while(usuarioContraseña.next());
				    
				    //Busco RecID para traer las novedades
				    ConexionBase bd = new ConexionBase();
					
					//Devolución de la consulta
							ResultSet nrorecid = null;
					
							try{
						PreparedStatement insertaReporte = bd.getConnection().prepareStatement("s_AvlNovedadesSelect_MaxRecId");
						nrorecid = insertaReporte.executeQuery();
						while (nrorecid.next()) {
							//recid= nrorecid.getString(1);
							recid= Long.parseLong(nrorecid.getString(1));
							
						}
						nrorecid.close();

					}
					// Handle any errors that may have occurred.
					catch (Exception e) {
						txt.setMensaje(e.getMessage()+" error al buscar RECID");
						txt.grabaError();
						
					}
					
							
					bd.desconectar();
					if(recid-recidAnterior==0) {
						etiEstado.setText("Buscando Novedades...");
						
					} else {
																
								ConexionBase bd2 = new ConexionBase();
								
								//Devolución de la consulta
								ResultSet avll = null;
						
								try{
							
							PreparedStatement avl = bd2.getConnection().prepareStatement("s_AvlNovedadesSelect2 "+userId+","+recidAnterior);
							
							avll = avl.executeQuery();
							
//							// declaramos un objeto socket para realizar la comunicación
//							 Socket socket;
//							
//							// declaramos e instanciamos un objeto de tipo byte
//							 byte[] mensaje_bytes = new byte[256];
//							 
//							 try {
//								 
//									// Instanciamos un socket con la dirección del destino y el
//									// puerto que vamos a utilizar para la comunicación
//									 socket = new Socket(config.getIpDestino(),config.getPuertoDestino());
//									 
//									// Declaramos e instanciamos el objeto DataOutputStream
//									// que nos valdrá para enviar datos al servidor destino
//									 DataOutputStream out =
//									 new DataOutputStream(socket.getOutputStream());
							//System.out.println(avll.);
							
											while (avll.next()) {
												
																//int sensorv2;
																int velocidad = (int) Double.parseDouble(avll.getString(7));//velocidad
																int curso = (int) Double.parseDouble(avll.getString(13));	//curso
																trama+= avll.getString(3).replace(" ", "");  //domino
																trama+=",";	//Separador
																trama+= avll.getString(6).substring(8,8+2)+avll.getString(6).substring(5,5+2)+avll.getString(6).substring(2,2+2)+avll.getString(6).substring(11,11+2)+avll.getString(6).substring(14,14+2)+avll.getString(6).substring(17,19);	//Fechareporte
																trama+=",";	//Separador
																trama+=avll.getString(12);	//Latitud
																trama+=",";	//Separador
																trama+=avll.getString(11);	//Longitud
																trama+=",";	//Separador
																//trama+= String.valueOf(velocidad).format("%3s", String.valueOf(velocidad)).replace(" ","0");
																trama+= String.format("%3s", String.valueOf(velocidad)).replace(" ","0");
																trama+=",";	//Separador
																trama+= String.format("%3s", String.valueOf(curso)).replace(" ","0");
																trama+=",";	//Separador
																trama+=avll.getString(8);	//eventoID
																/* Se anula por no usarse, si se necesita se vuelve a habilitar
																trama+=",";	//Separador
																trama+="0";
																trama+=",";	//Separador
																if(avll.getString(15).contains("RUS")&&avll.getString(15).contains("TI")&&!avll.getString(15).contains("NoDisp")) {
																	 sensorv2 = Integer.parseInt(avll.getString(15).substring((avll.getString(15).indexOf("TI,") + 3), 2) );
														             switch (sensorv2)
														             {
														                 case 0:
														                     trama += avll.getString(15).substring((avll.getString(15).indexOf("TI,") + 5), 3) + "+99+99";	//Sensor 0
														                     break;
														                 case 1:
														                     trama += "+99" + avll.getString(15).substring((avll.getString(15).indexOf("TI,") + 5), 3) + "+99"; //Sensor 1
														                     break;
														                 case 2:
														                     trama += "+99+99" + avll.getString(15).substring((avll.getString(15).indexOf("TI,") + 5), 3); // Sensor 2
														                     break;
														                 default:
														                     break;
														             } 
																	
																	
																} else {
																	
																	trama+= "+99+99+99"; //Por defecto si no es reporte de temperatura
																}*/
																trama +="|"+(char) 0x0D+(char) 0x0A; //Fin de trama
																
																
																eti.setText(trama);
																 //out.writeUTF(trama);
																// out.writeChars(trama);
																//preparo para enviar la trama segun como esta definido el TCP
																if(config.getModoTCP()) {
																	
																	EnviaTCP tcp = new EnviaTCP(trama,config.getIpDestino(),config.getPuertoDestino());
																 							
																	tcp.envia();
																	if (tcp.isEstado()) {
																		etiEstado.setText("Conectó, enviando...");
																		
																	}else {
																		etiEstado.setText("No conectó, no se puede enviar...");
																		
																	}
																	} else {
																	
																	EnviaUDP udp = new EnviaUDP(trama,config.getIpDestino(),config.getPuertoDestino());
																	
																	udp.envia();
																	}
																	
																trama = "";
																System.out.println(avll.getFetchSize());
																System.out.println(avll.getRow());
													
																pausa = 99999999;
																
																//Pausa entre envío
																
																do {
																	
																	pausa--;
																	
																	}while (pausa !=0);
																}
							 }
								// utilizamos el catch para capturar los errores que puedan surgir
								 catch (Exception e) {
									txt.setMensaje(e.getMessage()+" error al paresear la lista del SP s_AvlNovedadesSelect2 ");
									txt.grabaError();
								 }
									avll.close();
									avll=null;
//								}
//						
//						
//						// Handle any errors that may have occurred.
//						catch (Exception e) {
//						//	txt.setMensaje(e.getMessage()+" error al insertar en buffer ---->>"+insertSql);
//						//	txt.grabaError();
//							System.out.println(e.getMessage());
//							//e.printStackTrace();
//						}
						bd2.desconectar();
						
						recidAnterior = recid;
						
						System.gc();
					}
				} else {
					etiEstado.setText("Usuario/Contraseña no válido");
					
				}
				usuarioContraseña.close();
				usuarioContraseña=null;
				}
				// Handle any errors that may have occurred.
				catch (Exception e) {
					txt.setMensaje(e.getMessage()+" error de usuario y contraseña");
					txt.grabaError();
					//e.printStackTrace();
				}
			
			base.desconectar();
		
		}

			 
	}

}
