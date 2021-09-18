package control;

import javax.swing.JOptionPane;

import model.Laberinto;
import model.Raton;
import model.Gato;
import view.VentanaLaberinto;

public class Sistema {
	
	public final static int ANCHO_CELDA=40;
	public final static int ANCHO_LABERINTO=10;
	public Laberinto laberinto;

	public static void main(String[] args) {
		
		// Creo el Sistema
		Sistema sistema = new Sistema ();
		
		//Creo el laberinto
		sistema.laberinto = new Laberinto();
		sistema.laberinto.crearLaberintoAMano(Sistema.ANCHO_LABERINTO);
		sistema.laberinto.mostrarLaberinto();
		
		//Creo la ventana para mostrar el laberinto y lo dibujo
		VentanaLaberinto v = new VentanaLaberinto(Sistema.ANCHO_CELDA, Sistema.ANCHO_LABERINTO);
		JOptionPane.showMessageDialog(v, "Que comience la batalla");
		v.dibujarLaberinto(sistema); //Paso Sistema para que luego la ventana encuentre el laberinto a dibujar
		
		//Creo el raton y el gato en esquinas opuestas y los añado al laberinto
		Raton r = new Raton(0,0);
		Gato g = new Gato(Sistema.ANCHO_LABERINTO-1,Sistema.ANCHO_LABERINTO-1);
		sistema.laberinto.addGato (g);
		sistema.laberinto.addRaton (r);
		
		//Creo el controlador de Personajes y se lo paso a la ventana
		ControladorPersonajes controlador = new ControladorPersonajes(v, Sistema.ANCHO_CELDA);
		v.addKeyListener(controlador);
		sistema.laberinto.cp=controlador;
		
		//Dibujo a los personajes
		sistema.laberinto.dibujarPersonajes();

		//Inicio el bucle del juego
		while (!sistema.laberinto.encontradaSalida() && !sistema.laberinto.ratonComido()){
			sistema.laberinto.moverPersonajes();
			try {
				Thread.sleep(250); // Hago espero 250 milisegundos entre un movimiento y otro porque si no va muy rápido
			} catch (Exception e) {
				System.err.println("Falló el bucle del juego");
			}
		}
		if (!sistema.laberinto.ratonComido()){
			JOptionPane.showMessageDialog(v,"El ratón salió");
		} else {
			g.dibujar();
			JOptionPane.showMessageDialog(v,"El Gato se comio al ratón");
		}
		System.exit(1);
	}

}
