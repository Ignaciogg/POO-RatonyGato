package model;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import view.VentanaLaberinto;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Raton extends Personaje {

	VentanaLaberinto ventana;
	public int posicion = 100;

	public Raton(int x, int y) {
		super(x, y);
	}

	public String toString () {
		return "Raton: "+super.toString();
	}

	public void dibujar () {

		try {
            BufferedImage img = ImageIO.read(new File("./imagenes/raton.png"));
            laberinto.cp.g.drawImage(img, (x * laberinto.cp.anchoCelda) + laberinto.cp.anchoCelda / 4,
                    (y * laberinto.cp.anchoCelda) + laberinto.cp.anchoCelda / 4, 30, 30, null);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        };

	}

	public void mover() {
		if (this.direccionPosible(laberinto.cp.haciaDonde)) {
			this.borrar();
			this.moverEnUnaDireccion(laberinto.cp.haciaDonde);
			this.dibujar();
		}
	}
}