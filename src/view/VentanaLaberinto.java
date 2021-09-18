package view;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import control.Sistema;
import model.Laberinto;

public class VentanaLaberinto extends JFrame {

    private static final long serialVersionUID = 1L;
    public int anchoCelda;
    public Canvas lienzo;
    VentanaLaberinto ventana;
    public Sistema controlador;

    public VentanaLaberinto (int anchoCelda, int anchoLaberinto) {
        this.anchoCelda = anchoCelda;
        lienzo = new Canvas();
        this.getContentPane().add(lienzo);
        this.setSize(anchoCelda*anchoLaberinto+20,anchoCelda*anchoLaberinto+50);
        //20 y 50 es para dejar sitio al Marco de la ventana
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);
    }

    public void dibujarLaberinto(Laberinto laberinto) {
        int alto = laberinto.maze.length;
        int ancho = laberinto.maze[0].length;
        for (int f = 0; f < alto; f++) {
            for (int c = 0; c < ancho; c++) {

                if (laberinto.maze[f][c].isPared()) {
                    try {
                        BufferedImage img = ImageIO.read(new File("./imagenes/muro2.jpg"));
                        Graphics g = lienzo.getGraphics();
                        g.drawImage(img, anchoCelda * f, anchoCelda * c, anchoCelda, anchoCelda, null);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    };
                }
            }
        }
    }
}