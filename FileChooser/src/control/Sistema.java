package control;

import view.MiVentanaPrincipal;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sistema {

	public boolean hayQueMover=false; // para poder controlar si hay que ejecutar movimientos o est� quieto el rat�n
	public int posicion=100;

	//m�todo para dibujar una imagen
	public void dibujar(MiVentanaPrincipal ventana) {
		// borro lo que estaba dibujado antes, para conseguir el efecto de que algo se est� moviendo
		ventana.lienzo.getGraphics().clearRect(80,50,400,100);
		//Utilizo el fichero para crear una imagen
		File file = new File (".\\imagenes\\raton.jpg");
		BufferedImage img;
		try {
			//creo una imagen
			img = ImageIO.read(file);
			// utilizo el m�todo drawImage del grapics del lienzo
			ventana.lienzo.getGraphics().drawImage(img, posicion,50,50,50,ventana.lienzo);
			//en la posici�n horizontal que se va incrementando o decrementando, a la altura 50 y en tama�o 50x50
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//m�todo para reproducir un sonido determinado.
	public void reproducirAudio(File file) {
		// creamos un Clip de sonido
		Clip sonido;
		try { // dentro de un try-catch por si falla algo, abro el clip de sonido
			sonido = AudioSystem.getClip();
			sonido.open(AudioSystem.getAudioInputStream(file));
			// ponemos a reproducir el sonido.
			sonido.start();
		} catch (Exception tipoError) {
			System.out.println("" + tipoError);
		}
	}

	public static void main(String[] args) {

		Sistema s = new Sistema ();
		ControladorMenu controlador = new ControladorMenu (s);
		MiVentanaPrincipal miV = new MiVentanaPrincipal (controlador);
		// asociar la ventana principal y el controlador
		controlador.setVentanaControlada(miV);
		// poner visible la ventana
		miV.setVisible(true);

		int incremento=5;
		// forzamos un bucle infinito para que el programa s�lo acabe cuando cerramos la ventana
		while (true) {
			if (s.hayQueMover) {
				s.dibujar(miV); //dibujar la imagen
				s.posicion=s.posicion+incremento; // calcular la siguiente posici�n
				System.out.println("mover"); // escribo por consola "mover"
			} else {
				System.out.println("quieto");
			}
			// en cada iteraci�n del bucle hay que ver si ya he llegado a un extremo u otro de la ventan
			if (s.posicion>400) { // he llegado al l�mite derecho de la ventana, as� que hay que empezar a moverse a la izda
				incremento=-5;
			} else if (s.posicion<100) { // he llegado al l�mite izquierdo de la ventana, as� que hay que empezar a moverse a la derecha
				incremento=5;
			}
			try {
				Thread.sleep(100); // Hago espero 100 milisegundos entre un movimiento y otro porque si no va muy r�pido
			} catch (Exception e) {
				System.err.println("Fall� el bucle del juego");
			}
		}
	}
}