package model;

import java.io.PrintWriter;
import java.util.Vector;

import control.ControladorPersonajes;
import control.Sistema;

public class Laberinto {
	
	public static PrintWriter stdOut = new PrintWriter(System.out, true);
	public static PrintWriter stdErr = new PrintWriter(System.err, true);

	public int anchoLaberinto;
	public Celda maze[][];
	
	public Vector<Personaje> listaGatos = new Vector <Personaje> ();
	public Personaje raton;
	public ControladorPersonajes cp;
	
	public void addGato (Personaje p) {
		
		listaGatos.add(p);
		p.laberinto=this;
	}
	public void addRaton(Raton r) {
		// TODO Auto-generated method stub
		this.raton=r;
		r.laberinto=this;
	}	
	
	public void crearLaberintoAMano (int anchoLaberinto){
	
		this.anchoLaberinto = anchoLaberinto;
		
		Celda p = new Celda (true);
		Celda v = new Celda (false);
		
		maze = new Celda [][] { {v, p, p, v, p, p, v, v, p, v},
								{v, v, p, v, v, v, v, p, p, v},
								{p, v, v, v, p, p, v, v, v, v},
								{v, v, v, v, p, p, v, p, v, p},
								{p, v, p, v, v, p, v, p, v, p},
								{p, v, p, p, v, v, v, v, v, v},
								{v, v, v, v, v, p, v, p, p, v},
								{v, p, p, v, p, p, v, p, p, v},
								{v, p, v, v, v, v, v, v, v, v},
								{v, v, v, p, p, p, v, p, p, v}};
	}
		
	public void mostrarLaberinto(){
		
		for (int f=0; f<anchoLaberinto; f++) {
			for (int c=0; c<anchoLaberinto; c++) {
				System.out.print(maze [f][c]);
			}
			System.out.println();
		}
	}

	public void moverPersonajes () {
		
		raton.mover();
		for (Personaje p: this.listaGatos) {	
			p.mover();
		}
	}
	
	public void dibujarPersonajes () {
		
		raton.dibujar();
		for (Personaje p: this.listaGatos) {	
			p.dibujar();
		}
	}

	public boolean encontradaSalida () {
		
		return (raton.x==(Sistema.ANCHO_LABERINTO-1) && raton.y==(Sistema.ANCHO_LABERINTO-1));
		
	}
	
	public boolean ratonComido () {
		
		boolean comido=false;
		for (Personaje g: this.listaGatos) {	
			comido = (comido || (raton.x==g.x && raton.y==g.y));
		}
		return comido; 
	}


}
