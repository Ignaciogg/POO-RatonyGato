package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFileChooser;

import view.MiVentanaPrincipal;

public class ControladorMenu implements ActionListener {

	MiVentanaPrincipal ventana;
	Sistema sistema;

	public ControladorMenu(Sistema s) {
		this.sistema = s;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand() == "Sonidos") {
			// se ha seleccionado el men� para reproducir un sonido
			JFileChooser selector = new JFileChooser(".\\sonidos\\"); // creo un JFileChooser para elegir el fichero del directorio sonidos
			int result = selector.showOpenDialog(ventana); // lo muestra en un Dialog
			if (result == JFileChooser.APPROVE_OPTION) {
				File file = selector.getSelectedFile(); // el resultado es el que utiliza para escoger el fichero seleccionado.
				sistema.reproducirAudio(file); //pasa el fichero seleccionado al m�todo reproducirAudo de la clase sistema.
			}
		} else {
			// he elegido el men� de mover o parar. Si estaba movi�ndose, lo para, si estaba parado, empieza a moverse
			// no metemos aqui el bucle de movimientos, el actionPerformed s�lo modifica la variable para seguir teniendo en cuenta el bucle de eventos
			// lo �nico que hacemos es modificar la variable de sistema que indica que si hay que moverse o no
			sistema.hayQueMover=!sistema.hayQueMover;
			// asigna el valor contrario: si se est� moviendo que pare, si est� parado que se mueva
		}
	}

	public void setVentanaControlada(MiVentanaPrincipal v) {
		this.ventana = v;
	}

}