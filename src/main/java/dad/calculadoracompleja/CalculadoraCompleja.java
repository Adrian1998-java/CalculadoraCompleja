package dad.calculadoracompleja;

import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringExpression;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

public class CalculadoraCompleja extends Application {

	//Modelo
	Complejo complejoPrimero = new Complejo();
	Complejo complejoSegundo = new Complejo();
	Complejo complejoResult = new Complejo();
	
	private StringProperty operator = new SimpleStringProperty();
	
	//Vista
	ComboBox<String> operaciones;
	
	TextField realNumUno;
	TextField realNumDos;
	TextField imaginarioNumUno;
	TextField imaginarioNumDos;
	
	TextField realResultado;
	TextField imaginarioResultado;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		//Inicializamos
		
		realNumUno = new TextField("0");
		realNumUno.setMaxWidth(50);
		realNumDos = new TextField("0");
		realNumDos.setMaxWidth(50);
		imaginarioNumUno = new TextField("0");
		imaginarioNumUno.setMaxWidth(50);
		imaginarioNumDos = new TextField("0");
		imaginarioNumDos.setMaxWidth(50);
		
		realResultado = new TextField("0");
		realResultado.setEditable(false);
		realResultado.setMaxWidth(50);
		imaginarioResultado = new TextField("0");
		imaginarioResultado.setEditable(false);
		imaginarioResultado.setMaxWidth(50);
		
		operaciones = new ComboBox<>();
		operaciones.getItems().addAll("+", "-", "*", "/");
		operaciones.getSelectionModel().selectFirst();
		
		VBox izquierda = new VBox();
		izquierda.setSpacing(5);
		izquierda.setAlignment(Pos.CENTER);
		izquierda.getChildren().addAll(operaciones);
		
		HBox boxUno = new HBox();
		boxUno.setSpacing(5);
		boxUno.getChildren().addAll(realNumUno, new Label(" + "), imaginarioNumUno, new Label("i"));
		
		HBox boxDos = new HBox();
		boxDos.setSpacing(5);
		boxDos.getChildren().addAll(realNumDos, new Label(" + "), imaginarioNumDos, new Label("i"));
		
		HBox boxTres = new HBox();
		boxTres.setSpacing(5);
		boxTres.getChildren().addAll(realResultado, new Label(" + "), imaginarioResultado, new Label("i"));
		
		VBox medio = new VBox();
		medio.setSpacing(5);
		medio.setAlignment(Pos.CENTER);
		medio.getChildren().addAll(boxUno, boxDos, new Separator(), boxTres);
		
		HBox root = new HBox();
		root.setSpacing(5);
		root.setAlignment(Pos.CENTER);
		root.getChildren().addAll(izquierda, medio);
		
		Scene scene = new Scene(root, 320, 200);
		
		primaryStage.setTitle("Calculadora Compleja");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		//Implementacion de Bindings.
		realNumUno.textProperty().bindBidirectional(complejoPrimero.realProperty(), new NumberStringConverter());
		imaginarioNumUno.textProperty().bindBidirectional(complejoPrimero.imaginarioProperty(), new NumberStringConverter());
		
		realNumDos.textProperty().bindBidirectional(complejoSegundo.realProperty(), new NumberStringConverter());
		imaginarioNumDos.textProperty().bindBidirectional(complejoSegundo.imaginarioProperty(), new NumberStringConverter());
		
		realResultado.textProperty().bindBidirectional(complejoResult.realProperty(), new NumberStringConverter());
		imaginarioResultado.textProperty().bindBidirectional(complejoResult.imaginarioProperty(), new NumberStringConverter());
		
		operator.bind(operaciones.getSelectionModel().selectedItemProperty());
		
		//listeners
		
		operator.addListener((o, ov, nv) -> onOperatorChanged(nv));
		
		operaciones.getSelectionModel().selectFirst();
//		realResultado.textProperty().bind(
//				Bindings
//					.when(operaciones.getValue().equalsIgnoreCase("+"))
//					.then()
//				
//				
//				);
	}
	
	private void onOperatorChanged(String nv) {
		
		switch(nv) {
		case "+":
			complejoResult.realProperty().bind(complejoPrimero.realProperty().add(complejoSegundo.realProperty()));
			complejoResult.imaginarioProperty().bind(complejoPrimero.imaginarioProperty().add(complejoSegundo.imaginarioProperty()));
			break;
		case "-":
			complejoResult.realProperty().bind(complejoPrimero.realProperty().subtract(complejoSegundo.realProperty()));
			complejoResult.imaginarioProperty().bind(complejoPrimero.imaginarioProperty().subtract(complejoSegundo.imaginarioProperty()));
			break;
		case "*":
			complejoResult.realProperty().bind(
					complejoPrimero.realProperty().multiply(complejoSegundo.realProperty())
						.subtract(complejoPrimero.imaginarioProperty().multiply(complejoSegundo.imaginarioProperty()))
					);
			complejoResult.imaginarioProperty().bind(
					complejoPrimero.realProperty().multiply(complejoSegundo.imaginarioProperty())
						.add(complejoPrimero.imaginarioProperty().multiply(complejoSegundo.realProperty()))
					);
			break;
		case "/":
			complejoResult.realProperty().bind(
					(complejoPrimero.realProperty().multiply(complejoSegundo.realProperty()).add(complejoPrimero.imaginarioProperty().multiply(complejoSegundo.imaginarioProperty())))
					.divide((complejoSegundo.realProperty().multiply(complejoSegundo.realProperty())).add(complejoSegundo.imaginarioProperty().multiply(complejoSegundo.imaginarioProperty())))
					);
			complejoResult.imaginarioProperty().bind(
					complejoPrimero.imaginarioProperty().multiply(complejoSegundo.realProperty()).subtract(complejoPrimero.realProperty().multiply(complejoSegundo.imaginarioProperty()))
					.divide((complejoSegundo.realProperty().multiply(complejoSegundo.realProperty())).add(complejoSegundo.imaginarioProperty().multiply(complejoSegundo.imaginarioProperty())))
					);
			break;
		}
		
	}

	public static void main(String[] args) {
		launch(args);

	}

}
