package application;
	
import java.util.List;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.fxml.FXMLLoader;

import libraries.Vector;
import libraries.TreeNode;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			
			final Canvas canvas = new Canvas(Constants.CanvasWidth, Constants.CanvasHeight);
			
			//declare values
			Vector currentVector = new Vector();
			Vector startPointVector = new Vector();
			currentVector.setX(Constants.StartLength);
			currentVector.setY(Constants.StartLength);
			startPointVector.setX(Constants.StartPointX);
			startPointVector.setY(Constants.StartPointY);

						
			drawShapes(canvas, currentVector, startPointVector);
												
			
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
	
	
	private void drawShapes( final Canvas canvas, Vector currentVector, Vector startPointVector) throws Exception{
		final GraphicsContext gc = canvas.getGraphicsContext2D();					
		
		//calculation our tree
		TreeController calculateTree = new TreeController();
		TreeNode<Vector> node = calculateTree.main();
				
		DrawShapesController drawShapes = new DrawShapesController();
		drawShapes.draw(gc, node);
		
		
		// plot strokes
		gc.stroke();
						
	} 
		
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
