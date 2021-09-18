package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Vector;

import control.ControladorPersonajes;
import control.Sistema;

public class Laberinto {

	public static PrintWriter stdOut = new PrintWriter(System.out, true);
	public static PrintWriter stdErr = new PrintWriter(System.err, true);

	public int anchoLaberinto;
	public Celda maze[][];

	public Vector<Personaje> listaGatos = new Vector<Personaje>();
	public Personaje raton;
	public ControladorPersonajes cp;

	public void addGato(Personaje p) {
		listaGatos.add(p);
		p.laberinto = this;
	}

	public void addRaton(Raton r) {
		this.raton = r;
		r.laberinto = this;
	}

	public void crearLaberinto(int anchoLaberinto) {
		this.anchoLaberinto = anchoLaberinto;

		try {
			FileReader fileReader = new FileReader("./laberintos/lab1v2.txt");
			BufferedReader br = new BufferedReader(fileReader);

			maze = new Celda[10][10];

			String strCurrentLine;
			int i = 0;
			while ((strCurrentLine = br.readLine()) != null) {
				String[] fileRow = strCurrentLine.split(",");
				for (int j = 0; j < fileRow.length; j++) {
					Celda nuevaCelda;
					if (fileRow[j].equals("v")) {
						nuevaCelda = new Celda(false);
						maze[i][j] = nuevaCelda;
					} else if (fileRow[j].equals("p")) {
						nuevaCelda = new Celda(true);
						maze[i][j] = nuevaCelda;
					} else {
						System.out.println("Celda no valida");
					}
				}
				i++;

			}

			br.close();
		} catch (Exception e) {
			System.out.println("Error al leer el laberinto");
		}
	}

	public void crearLaberintoDesdeArchivo(Celda[][] maze, int anchoLaberinto) {
		this.anchoLaberinto = anchoLaberinto;
		this.maze = maze;
	}

	public void mostrarLaberinto() {
		for (int f = 0; f < anchoLaberinto; f++) {
			for (int c = 0; c < anchoLaberinto; c++) {
				System.out.print(maze[f][c]);
			}
			System.out.println();
		}
	}

	public void moverPersonajes() {
		raton.mover();
		for (Personaje p : this.listaGatos) {
			p.mover();
		}
	}

	public void dibujarPersonajes() {
		raton.dibujar();
		for (Personaje p : this.listaGatos) {
			p.dibujar();
		}
	}

	public boolean encontradaSalida() {
		return (raton.x == (Sistema.ANCHO_LABERINTO - 1) && raton.y == (Sistema.ANCHO_LABERINTO - 1));
	}

	public boolean ratonComido() {
		boolean comido = false;
		for (Personaje g : this.listaGatos) {
			comido = (comido || (raton.x == g.x && raton.y == g.y));
		}
		return comido;
	}

}