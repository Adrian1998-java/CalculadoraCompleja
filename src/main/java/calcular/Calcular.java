package calcular;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Calcular {
	
	private DoubleProperty real = new SimpleDoubleProperty();
	private DoubleProperty imaginario = new SimpleDoubleProperty();
	
	public Calcular() {}
	
	public Calcular(Double real, Double imaginario) {
		super();
		setReal(real);
		setImaginario(imaginario);
	}
	//Parte Real
	public final DoubleProperty realProperty() {
		return this.real;
	}
	
	public final double getReal() {
		return this.realProperty().get();
	}
	public final void setReal(final double real) {
		this.realProperty().get();
	}
	
	//Parte Imaginaria
	public final DoubleProperty imaginarioProperty() {
		return this.imaginario;
	}
	public final Double getImaginario() {
		return this.imaginarioProperty().get();
	}
	public final void setImaginario(final double imaginario) {
		this.imaginarioProperty().set(imaginario);
	}

	@Override
	public String toString() {
		return getReal() + "+" + getImaginario() + "i";
	}
	
	public Calcular add(Calcular c) {
		Calcular a = new Calcular();
		a.realProperty().bind(realProperty().add(c.realProperty()));
		a.imaginarioProperty().bind(imaginarioProperty().add(c.imaginarioProperty()));
		return a;
	}
	/*
	public static void main(String[] args) {
		
		Calcular a = new Calcular(5.0, 5.0);
		Calcular b = new Calcular(3.0, 4.0);
		
		Calcular c = a.add(b);
		
		System.out.println(a);
		System.out.println(b);
		
		a.setReal(50);
		
		System.out.println(c);		
		
	}
	
	*/
	
}
