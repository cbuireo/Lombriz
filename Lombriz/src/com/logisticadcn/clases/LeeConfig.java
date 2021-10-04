package com.logisticadcn.clases;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class LeeConfig {
	
	private int puertoEntrada;
	private int puertoSalida;
	private int modeloEquipo;
	private String serverSql;
	private String usuarioSql;
	private String contraseniaSql;
	private String base;
	private String baseIp;
	private String ipDestino;
	private String usuarioTopo;
	private String contraseñaTopo;
	private int puertoDestino;
	private int timerMacros;
	private int timerPrograma;
	private int timerPausaComandosProg;
	private boolean modoTCP;
	private boolean grabaErrores;
	private boolean grabaLog;
	private boolean grabaMacros;
	private boolean grabaPrograma;
	private int diasLog;
	
	
	//Métodos Constructores
	
		
	//public LeeConfig() {
		
	//}
	
	public LeeConfig(int puertoEntrada,int puertoSalida, int modeloEquipo, String serverSql, String usuarioSql, String contraseniaSql,
			String base, String baseIp, String ipDestino, String usuarioTopo, String contraseñaTopo, int puertoDestino, int timerMacros, int timerPrograma, int timerPausaComandosProg,boolean modoTCP , boolean grabaErrores,
			boolean grabaLog,boolean grabaMacros,boolean grabaPrograma,int diasLog) {
		
		this.puertoEntrada = puertoEntrada;
		this.puertoSalida = puertoSalida;
		this.modeloEquipo = modeloEquipo;
		this.serverSql = serverSql;
		this.usuarioSql = usuarioSql;
		this.contraseniaSql = contraseniaSql;
		this.base = base;
		this.baseIp = baseIp;
		this.ipDestino = ipDestino;
		this.usuarioTopo = usuarioTopo;
		this.contraseñaTopo = contraseñaTopo;
		this.puertoDestino = puertoDestino;
		this.timerMacros = timerMacros;
		this.timerPrograma = timerPrograma;
		this.timerPausaComandosProg = timerPausaComandosProg;
		this.grabaErrores = grabaErrores;
		this.modoTCP = modoTCP;
		this.grabaLog = grabaLog;
		this.grabaMacros = grabaMacros;
		this.grabaPrograma = grabaPrograma;
		this.diasLog = diasLog;
	}

	
	public  LeeConfig() {
		// Instanciamos el objeto properties
		Properties config = new Properties();
		try {
			// cargammos el archivo utilizando la ruta relativa donde esta el proyecto
			config.load(new FileInputStream("config.properties"));
			// leemos las propiedades del archivo
//			this.puertoEntrada =Integer.valueOf(config.getProperty("puerto.entrada"));
//			this.puertoSalida =Integer.valueOf(config.getProperty("puerto.salida"));
			this.serverSql = config.getProperty("db.url");
			this.usuarioSql = config.getProperty("db.user");
			this.contraseniaSql = config.getProperty("db.pass");
			this.modoTCP = (Integer.valueOf(config.getProperty("modo.tcp"))==1)?true:false;
			this.grabaErrores = (Integer.valueOf(config.getProperty("graba.errores"))==1)?true:false;
			this.grabaLog = (Integer.valueOf(config.getProperty("graba.log"))==1)?true:false;
//			this.grabaMacros = (Integer.valueOf(config.getProperty("graba.macros"))==1)?true:false;
//			this.grabaPrograma = (Integer.valueOf(config.getProperty("graba.programa"))==1)?true:false;
			this.base = config.getProperty("db.base");
//			this.baseIp = config.getProperty("ip.base");
			this.ipDestino= config.getProperty("ip.destino");
			this.puertoDestino = Integer.valueOf(config.getProperty("puerto.destino"));
			this.usuarioTopo = config.getProperty("topo.usuario");
			this.contraseñaTopo = config.getProperty("topo.contraseña");
			this.diasLog= Integer.valueOf(config.getProperty("tiempo.txt"));
//			this.timerMacros = Integer.valueOf(config.getProperty("timer.macro"));
		}
		catch (IOException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());

		}
	}
	
	// gets & sets
	public int getPuertoEntrada() {
		return puertoEntrada;
	}

	public int getPuertoSalida() {
		return puertoSalida;
	}

	public int getModeloEquipo() {
		return modeloEquipo;
	}


	public String getServerSql() {
		return serverSql;
	}


	public String getUsuarioSql() {
		return usuarioSql;
	}


	public String getContraseniaSql() {
		return contraseniaSql;
	}
	
	public String getUsuarioTopo() {
		return usuarioTopo;
	}


	public String getContrasenñaTopo() {
		return contraseñaTopo;
	}

	public String getBase() {
		return base;
	}
	
	public String getBaseIp() {
		return baseIp;
	}
	
	public String getIpDestino() {
		return ipDestino;
	}

	public int getPuertoDestino() {
		return puertoDestino;
	}

	public int getTimerMacros() {
		return timerMacros;
	}


	public int getTimerPrograma() {
		return timerPrograma;
	}


	public int getTimerPausaComandosProg() {
		return timerPausaComandosProg;
	}


	public boolean getGrabaErrores() {
		return grabaErrores;
	}

	public boolean getModoTCP() {
		return modoTCP;
	}
	
	public boolean getGrabaLog() {
		return grabaLog;
	}
	
	public boolean getGrabaMacros() {
		return grabaMacros;
	}
	public boolean getGrabaPrograma() {
		return grabaPrograma;
	}
	
	public int getDiasLog() {
		return diasLog;
	}

	
}
