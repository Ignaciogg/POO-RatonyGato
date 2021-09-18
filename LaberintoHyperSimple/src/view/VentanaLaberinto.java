package view;

import java.awt.Canvas;
import java.awt.Color;

import javax.swing.JFrame;

import control.Sistema;

public class VentanaLaberinto extends JFrame {
	
	private static final long serialVersionUID = 1L;
	public int anchoCelda;
	public Canvas lienzo;
	
	
	public VentanaLaberinto (int anchoCelda, int anchoLaberinto) {
		
		this.anchoCelda = anchoCelda;
		lienzo = new Canvas();
		this.getContentPane().add(lienzo);
		this.setSize(anchoCelda*anchoLaberinto+20,anchoCelda*anchoLaberinto+50); 
		//20 y 50 es para dejar sitio al Marco de la ventana
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	public void dibujarLaberinto(Sistema s) {
		
		int alto = s.laberinto.maze.length;
		int ancho = s.laberinto.maze[0].length;
		for (int f = 0; f < alto; f++) {
			for (int c = 0; c < ancho; c++) {

				if (s.laberinto.maze[f][c].isPared()) {
					lienzo.getGraphics().setColor(Color.BLACK);
					lienzo.getGraphics().fillRect(anchoCelda * f, anchoCelda * c, anchoCelda, anchoCelda);
				}
			}
		}
	}
}
