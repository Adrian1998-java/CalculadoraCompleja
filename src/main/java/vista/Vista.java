package vista;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Vista extends Application {
		
	ObservableList<String> opciones = FXCollections.observableArrayList("+", "-", "x", "/");
	
	@Override
	public void start(Stage primaryStage) throws Exception{
		
		ComboBox Operaciones = new ComboBox(opciones);
		VBox cajaOperaciones = new VBox();
		cajaOperaciones.setAlignment(Pos.BASELINE_CENTER);
		cajaOperaciones.getChildren().addAll(Operaciones);
		
		HBox main = new HBox();
		main.getChildren().addAll(cajaOperaciones);
		
		Scene escena = new Scene(main, 320, 200);
		
		primaryStage.setScene(escena);
		primaryStage.setTitle("Calculadora Compleja");
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
