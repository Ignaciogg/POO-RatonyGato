package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import view.VentanaGatos;

public class ControlGatos implements ActionListener{

	public VentanaGatos ventana;

	public void setVentanaControlada(VentanaGatos VG) {
		this.ventana = VG;
	}


	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand()=="boton1") {
        	System.out.println("Pulsaste el botón 1");
        }
        if (e.getActionCommand()=="boton2") {
        	System.out.println("Pulsaste el botón 2");
        	VentanaGatos.gatos = true;
        }

	}

}