package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Shape;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import java.io.IOException;

public class Gato extends Personaje {

	public Gato(int x, int y) {
		super(x, y);
	}

	public String toString () {
		return "Gato: "+ super.toString();
	}

	public void dibujar() {
		try {
            BufferedImage img = ImageIO.read(new File("./imagenes/gato.png"));
            laberinto.cp.g.drawImage(img, (x * laberinto.cp.anchoCelda) + laberinto.cp.anchoCelda / 4,
                    (y * laberinto.cp.anchoCelda) + laberinto.cp.anchoCelda / 4, 30, 30, null);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        };
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