package application;
	

import java.util.ArrayList;
import java.util.List;

import application.config.Constants;
import application.controllers.DrawShapesController;
import application.controllers.TreeController;
import application.extended.Line;
import application.extended.TreeNodeExtended;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.FlowPane;
import javafx.fxml.FXMLLoader;

import libraries.Vector;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			
			final Canvas canvas = new Canvas(Constants.CanvasWidth, Constants.CanvasHeight);
												
			drawShapes(canvas);												
			
			//Visualization
			FlowPane root = (FlowPane)FXMLLoader.load(getClass().getResource("Sample.fxml"));
			root.getChildren().addAll(canvas);
			
			Scene scene = new Scene(root, Constants.SceneWidth, Constants.SceneHeight);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle(Constants.ApplicationName + Constants.Version);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	private void drawShapes( final Canvas canvas) throws Exception{
		final GraphicsContext gc = canvas.getGraphicsContext2D();					
		
		//calculation our tree
		TreeController calculateTree = new TreeController();
		TreeNodeExtended<Line> node = new TreeNodeExtended<Line>();
		node = calculateTree.main();
		
		//draw shapes
		DrawShapesController drawShapes = new DrawShapesController();
		drawShapes.draw(gc, node);
				
		// graphics content apply
		gc.stroke();
						
	} 
		
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
