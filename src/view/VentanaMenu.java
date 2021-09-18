package view;

import java.awt.Frame;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import control.ControlLaberinto;
import control.Sistema;
import model.Celda;

public class VentanaMenu extends Frame {
	private static final long serialVersionUID = 1L;

	private Frame VM;
	public static Celda maze[][];

	public VentanaMenu() {
		VM = new Frame("Menu");
		MenuBar mb = new MenuBar();

		Menu menu = new Menu("Menu");
		MenuItem i1 = new MenuItem("Jugar");
		i1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				VM.dispose();
				Sistema.jugar(maze);
			}
		});

		MenuItem i2 = new MenuItem("Elegir Laberinto");
		i2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				maze = ControlLaberinto.seleccionarLaberinto(VM, maze);
			}
		});

		MenuItem i3 = new MenuItem("Elegir nº de Gatos");
		i3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				new VentanaGatos();
			}
		});

		menu.add(i1);
		menu.add(i2);
		menu.add(i3);

		mb.add(menu);

		VM.setMenuBar(mb);
		VM.setSize(400, 400);
		VM.setLayout(null);
		VM.setVisible(true);
		VM.setResizable(false);

		VM.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				System.exit(0);
			}
		});
	}
}