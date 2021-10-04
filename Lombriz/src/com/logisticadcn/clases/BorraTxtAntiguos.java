package com.logisticadcn.clases;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Date;

public class BorraTxtAntiguos {

	public BorraTxtAntiguos() {
		
	}
	public void borra() {
		
	// Instanciamos la clase para leer el archivo de config.	
	LeeConfig config = new LeeConfig();
	
	 //Cargamos el parametro que determina la antiguedad de los archivos
    int antiguedadArchivos= config.getDiasLog()*86400;

    //Armamos un filtro para todos los archivos TXT que se encuentran en el directorio de trabajo
   FilenameFilter filtro = new FilenameFilter() {
    	@Override
    	public boolean accept(File f, String name) {
    	
    		return name.endsWith(".txt");
    	}
    };
    
  //Obtenemos la ruta del directorio
    File directorio = new File(System.getProperty("user.dir"));
    
    //Cargamos los ficheros
    File[] ficheros = directorio.listFiles(filtro);

    //Obtenemos la fecha actual
    Date fechaActual = new Date();
    
    //Recorremos todos los ficheros
    for (int x = 0; x < ficheros.length; x++){
    	
        //Obtenemos la fecha de última modificación del fichero
        Date fechaFichero = new Date(ficheros[x].lastModified());
        
        //Comparamos con la actual
        long dif = fechaActual.getTime() - fechaFichero.getTime();
        
        long difSegundos = dif / 1000;
        
        //Si el fichero tiene más de los segundos de antFicheros lo borramos
        if (antiguedadArchivos<difSegundos ){
          
        	ficheros[x].delete();
       
        }
    }
}

}