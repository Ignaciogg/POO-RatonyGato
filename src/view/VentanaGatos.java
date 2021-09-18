package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import control.ControlGatos;

public class VentanaGatos extends JFrame{

	public JButton boton1,boton2;
	public static boolean gatos = false;
	ControlGatos control = new ControlGatos();

    public VentanaGatos() {

    	setLayout(null);
        boton1=new JButton("1");
        boton1.setBounds(80,100,70,70);
        add(boton1);
        boton1.addActionListener(control);
        boton1.setActionCommand("boton1");

        boton2=new JButton("2");
        boton2.setBounds(250,100,70,70);
        add(boton2);
        boton2.addActionListener(control);
        boton2.setActionCommand("boton2");

    	this.setBounds(0,0,400,400);
    	this.setVisible(true);
    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	this.setResizable(false);
    }

}