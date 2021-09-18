package view;

import java.awt.Canvas;
import java.awt.event.KeyEvent;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import control.ControladorMenu;

public class MiVentanaPrincipal extends JFrame {
	
	public ControladorMenu controlador;
	public Canvas lienzo;

	public MiVentanaPrincipal(ControladorMenu c) {
		
		JMenuBar menuBar;
		JMenu menu;
		JMenuItem menuItem, menuItem2;
		
		this.controlador=c;

		menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);
		// Primer menú
		menu = new JMenu("Cargar");
		menu.setMnemonic(KeyEvent.VK_C);
		// Submenú 1
		menuItem = new JMenuItem("Sonido y Reproducir", KeyEvent.VK_S);		
		menuItem.addActionListener(controlador);
		menuItem.setActionCommand("Sonidos");
		menu.add(menuItem);

		// Submenú 2
		menuItem2 = new JMenuItem("Mover o Parar Imagen", KeyEvent.VK_I);		
		menuItem2.addActionListener(controlador);
		menuItem2.setActionCommand("Imagenes");
		menu.add(menuItem2);

		menuBar.add(menu);
		
		// Le pintamos un Lienzo dentro para dibujar las figuras.
		
		lienzo = new Canvas();
		this.getContentPane().add(lienzo);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500, 200);
		this.setTitle("Principal");
		this.setVisible(true);
	}

}