package com.logisticadcn.clases;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class Ventana extends JFrame {
	//Armo la ventana
	  
		  public void Ventana() {
			  Toolkit miPantalla = Toolkit.getDefaultToolkit();
			  Dimension tamanoPantalla = miPantalla.getScreenSize();
			  int alturaPantalla=tamanoPantalla.height;
			  int anchoPantalla=tamanoPantalla.width;
			  setSize (1300,100);
			  setLocation(anchoPantalla/6,alturaPantalla/4);
			  Image icono = miPantalla.getImage("iot.png");
			  setIconImage(icono);
			  
		  }
		  
		  public void paintComponent(Graphics g) {
			  super.paintComponents(g);
						  
		  }
		  
	 }


