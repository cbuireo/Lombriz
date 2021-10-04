package com.logisticadcn.clases;

import java.text.*;
import java.time.*;
import java.util.Calendar;
import java.util.Date;

public class FechaHora {
	//atributos de la clase
	private String fecha;
	
	private String hora;
	
	private String fechaahora;
	
	private String sumaRestaHoraFecha;
	

	private int ano;
	
	private int mes;
	
	private int dia;
	
	private int horaa;
	
	private int min;
	
	private int sec;
	
	private int horas;
	
	
	//Metodos constructores

	public FechaHora(String fecha, String hora, String fechaahora, String sumaRestaHoraFecha) {
		super();
		this.fecha = fecha;
		this.hora = hora;
		this.fechaahora = fechaahora;
		this.sumaRestaHoraFecha = sumaRestaHoraFecha;
	}
	
	public FechaHora() {
		
	}
	
	public FechaHora(int ano, int mes, int dia, int horaa, int min, int sec, int horas) {

		this.ano = ano;
		this.mes = mes;
		this.dia = dia;
		this.horaa = horaa;
		this.min = min;
		this.sec = sec;
		this.horas = horas;
	}

	
	//Metodos
	public String ahoraHumano() {
		// TODO Auto-generated method stub

		this.fecha= LocalDate.now().toString();
		
		this.hora = LocalTime.now().toString();
		
		this.fechaahora = ( (fecha.substring(8, 10))+ "/" + (fecha.substring(5, 7)) +"/"+ fecha.substring(0, 4)) + " "
				+ hora.substring(0, 8);
		return fechaahora;
	}
	
	public void sumarRestarHorasFecha(){
		
		ano-=1900;	//Corrige el a�o para que lo tome la clase date
		
		mes-=1;		//corrige el mes para que lo tome la clase date
		
		@SuppressWarnings("deprecation")
		Date fecha = new Date(ano,mes,dia,horaa,min,sec);
				
	    Calendar calendar = Calendar.getInstance();
		
	    calendar.setTime(fecha); // Configuramos la fecha que se recibe
		
	    calendar.add(Calendar.HOUR, horas);  // numero de horas a a�adir, o restar en caso de horas<0
		
	    DateFormat formatoFechaHora = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
		
        
	    this.sumaRestaHoraFecha = formatoFechaHora.format(calendar.getTime()); // Devuelve el objeto Date con las nuevas horas a�adidas, en formato preestablecido y String
		
//	    return sumaRestaHoraFecha;

		
	 }
	
	public  String ahora() {
		// TODO Auto-generated method stub

		String fecha = LocalDate.now().toString();
		
		String hora = LocalTime.now().toString();
		
		this.fechaahora = (fecha.substring(0, 4)) + (fecha.substring(5, 7)) + (fecha.substring(8, 10)) + " "
				+ hora.substring(0, 8);
		
			
		return (fechaahora);
	}
	
	//GETS Y SETS
	
	public String getFecha() {
		return fecha;
	}

	public String getHora() {
		return hora;
	}

	public String getFechaahora() {
		return fechaahora;
	}

	public String getSumaRestaHoraFecha() {
		return sumaRestaHoraFecha;
	}
	
	
	
	
	
}
