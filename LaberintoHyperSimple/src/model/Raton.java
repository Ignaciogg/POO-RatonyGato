package model;

import java.awt.Color;

public class Raton extends Personaje {
	
	public Raton(int x, int y) {
		super(x, y);
	}

	public String toString () {		
		return "Raton: "+super.toString();
	}

	public void dibujar () {
		
		laberinto.cp.g.setColor(Color.BLUE);
		laberinto.cp.g.fillOval((x*laberinto.cp.anchoCelda)+laberinto.cp.anchoCelda/4, 
				      			(y*laberinto.cp.anchoCelda)+laberinto.cp.anchoCelda/4, 
				      			laberinto.cp.anchoCelda/2, laberinto.cp.anchoCelda/2);
	}
	
	public void mover() {
		if (this.direccionPosible(laberinto.cp.haciaDonde)) {
			this.borrar();
			this.moverEnUnaDireccion(laberinto.cp.haciaDonde);
			this.dibujar();
		}
	}
}