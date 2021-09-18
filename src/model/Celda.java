package model;

public class Celda {

	private boolean pared = false;
	private int numeroVisitas = 0;

	public boolean isPared() {
		return pared;
	}
	public Celda (boolean pared) {
		this.pared = pared;
	}
	public boolean haSidoExplorada() {
		//Si el personaje ha pasado por allí ya 3 veces, marcamos la celda como explorada
		return numeroVisitas>=3;
	}
	public void visitada() {
		this.numeroVisitas++;
	}
	public String toString() {

		if (isPared()) {
			return "#";
		} else {
			return " ";
		}
	}
}