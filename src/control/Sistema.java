package control;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JOptionPane;

import model.Laberinto;
import model.Raton;
import model.Celda;
import model.Gato;
import view.VentanaGatos;
import view.VentanaLaberinto;
import view.VentanaMenu;

public class Sistema {
	public final static int ANCHO_CELDA = 40;
	public final static int ANCHO_LABERINTO = 10;
	public static Laberinto laberinto;

    public static void main(String[] args) {
			new VentanaMenu();
	}


	public static void jugar(Celda[][] maze) {
		// Creo el laberinto
		laberinto = new Laberinto();
		if (maze != null) {
			laberinto.crearLaberintoDesdeArchivo(maze, ANCHO_LABERINTO);
		} else {
			laberinto.crearLaberinto(Sistema.ANCHO_LABERINTO);
		}
		laberinto.mostrarLaberinto();

		// Creo la ventana para mostrar el laberinto y lo dibujo
		VentanaLaberinto v = new VentanaLaberinto(Sistema.ANCHO_CELDA, Sistema.ANCHO_LABERINTO);
		try {
			AudioInputStream audioInputStream = AudioSystem
					.getAudioInputStream(new File("./sonidos/victory.wav").getAbsoluteFile());
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
			System.out.println("Error al reproducir el sonido.");
		}
		JOptionPane.showMessageDialog(v, "Comienza el juego!");
		v.dibujarLaberinto(laberinto); // Paso Sistema para que luego la ventana encuentre el laberinto a dibujar

		// Creo el raton y el gato en esquinas opuestas y los añado al laberinto
		Raton r = new Raton(0, 0);
		Gato g = new Gato(Sistema.ANCHO_LABERINTO - 1, Sistema.ANCHO_LABERINTO - 1);

		laberinto.addGato(g);
		if(VentanaGatos.gatos == true){
			Gato g2 = new Gato(Sistema.ANCHO_LABERINTO - 1, Sistema.ANCHO_LABERINTO - 1);
			laberinto.addGato(g2);
		}
		laberinto.addRaton(r);

		// Creo el controlador de Personajes y se lo paso a la ventana
		ControladorPersonajes controlador = new ControladorPersonajes(v, Sistema.ANCHO_CELDA);
		v.addKeyListener(controlador);
		laberinto.cp = controlador;

		// Dibujo a los personajes
		laberinto.dibujarPersonajes();

		// Inicio el bucle del juego
		while (!laberinto.encontradaSalida() && !laberinto.ratonComido()) {
			laberinto.moverPersonajes();
			try {
				Thread.sleep(250); // Hago espero 250 milisegundos entre un movimiento y otro porque si no va muy
									// rápido
			} catch (Exception e) {
				System.err.println("Falló el bucle del juego");
			}
		}
		if (!laberinto.ratonComido()) {
			try {
				AudioInputStream audioInputStream = AudioSystem
						.getAudioInputStream(new File("./sonidos/victory.wav").getAbsoluteFile());
				Clip clip = AudioSystem.getClip();
				clip.open(audioInputStream);
				clip.start();
			} catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
				System.out.println("Error al reproducir el sonido.");
			}
			JOptionPane.showMessageDialog(v, "El ratón salió");
		} else {
			g.dibujar();
			try {
				AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("./sonidos/gameover.wav"));
				Clip clip = AudioSystem.getClip();
				clip.open(audioInputStream);
				clip.start();
			} catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
				System.out.println("Error al reproducir el sonido.");
			}
			JOptionPane.showMessageDialog(v, "El Gato se comio al ratón");

		}
		System.exit(1);
	}

}