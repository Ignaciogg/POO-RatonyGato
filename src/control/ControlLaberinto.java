package control;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.swing.JFileChooser;

import model.Celda;
import view.VentanaLaberinto;
import view.VentanaMenu;

public class ControlLaberinto {

	VentanaLaberinto ventana;

	public static Celda [][] seleccionarLaberinto(Frame vM, Celda maze[][]) {
		JFileChooser selector = new JFileChooser("./laberintos/"); // JFileChooser para elegir el fichero del
																	// directorio laberintos
		int result = selector.showOpenDialog(vM); // lo muestra en un Dialog
		if (result == JFileChooser.APPROVE_OPTION) {
			File file = selector.getSelectedFile(); // el resultado es el que utiliza para escoger el fichero
													// seleccionado.
			System.out.println("Ha elegido " + selector.getSelectedFile());

			try {
                FileReader fileReader = new FileReader(file);
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
				return maze;
			} catch (Exception e) {
				System.out.println("Error al leer el laberinto");
			}
		}
		return maze;
	}

}