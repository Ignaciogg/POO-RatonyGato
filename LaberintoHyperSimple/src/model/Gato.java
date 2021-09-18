package model;

import java.awt.Color;

public class Gato extends Personaje {

	public Gato(int x, int y) {
		super(x, y);
	}
	
	public String toString () {
		
		return "Gato: "+super.toString();
	}

	public void dibujar() {

		laberinto.cp.g.setColor(Color.RED);
		laberinto.cp.g.fillOval((x * laberinto.cp.anchoCelda) + laberinto.cp.anchoCelda / 4, 
					  			(y * laberinto.cp.anchoCelda) + laberinto.cp.anchoCelda / 4, 
					  			laberinto.cp.anchoCelda / 2, laberinto.cp.anchoCelda / 2);
	}

	public void mover() {

		this.borrar();
		this.moverAleatorio();
		this.dibujar();
	}

	public void moverAleatorio() {

		int movimientoAleatorio = (int) (Math.random() * 10 % 4);
		while (!this.direccionPosible(movimientoAleatorio)) {
			movimientoAleatorio = (movimientoAleatorio + 1) % 4;
		} 
		this.moverEnUnaDireccion(movimientoAleatorio);
	}
}
