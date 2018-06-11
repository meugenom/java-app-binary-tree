package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import libraries.TreeNode;
import libraries.Vector;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.fxml.FXMLLoader;
import application.MainController;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		
		try {
			
			//BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("Sample.fxml"));								
			//BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("Sample.fxml"));
			
			final Canvas canvas = new Canvas(Constants.CanvasWidth, Constants.CanvasHeight);
			
			// reflections
			/*
			 * final Reflection reflection = new Reflection(); reflection.setFraction(0.7);
			 * canvas.setEffect(reflection);
			 */
			
			//declare values
			Vector currentVector = new Vector();
			Vector startPointVector = new Vector();
			currentVector.setX(Constants.StartLength);
			currentVector.setY(Constants.StartLength);
			startPointVector.setX(Constants.StartPointX);
			startPointVector.setY(Constants.StartPointY);

			// first vector drawing
			drawParentShape(canvas, currentVector, startPointVector, Constants.Angle);
			
			// calculate  tree and fill him
			MainController calculations = new MainController();
			TreeNode<Vector> node = new TreeNode<Vector>();
			
			node.value = currentVector;
			calculations.createTree(node, currentVector, Constants.Iteration);			
			
			
			final FlowPane flowPane = new FlowPane();						
			// flowPane.setPadding(new Insets(3));
			flowPane.getChildren().addAll(canvas);
			
						
			Scene scene = new Scene(flowPane, Constants.SceneWidth, Constants.SceneHeight);
			
			 
			primaryStage.setScene(scene);			
			primaryStage.setTitle(Constants.ApplicationName + Constants.Version);
			primaryStage.show();
			
			/*
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			*/
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	private void drawParentShape(final Canvas canvas, Vector currentVector, Vector startPointVector, double angle) throws Exception{
		final GraphicsContext gc = canvas.getGraphicsContext2D();

		// Canvas clear
		// gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

		gc.setStroke(Color.ROSYBROWN);
		gc.setLineWidth(2);

		// start point
		gc.moveTo(startPointVector.getX(), startPointVector.getY());

		Vector a = new Vector();				
		a = currentVector.add(startPointVector);

		gc.lineTo(a.getX(), startPointVector.getY());
		gc.lineTo(a.getX(), a.getY());
		gc.lineTo(startPointVector.getX(), a.getY());
		gc.lineTo(startPointVector.getX(), startPointVector.getY());

		// recalc startPointVector for other calculations
		// need think for right and left variants for tree
		startPointVector.setX(a.getX());
		startPointVector.setY(startPointVector.getY());

		// to childs
		drawChildShapeRight(canvas, currentVector, startPointVector, angle);
				
		
		startPointVector.setX(Constants.StartPointX);
		startPointVector.setY(Constants.StartPointY);		
		drawChildShapeLeft(canvas, currentVector, startPointVector, angle);
		// plot strokes
		gc.stroke();

	}

	private void drawChildShapeRight(final Canvas canvas, Vector currentVector, Vector  startPointVector, double angle)
			throws Exception {

		final GraphicsContext gc = canvas.getGraphicsContext2D();

		// Canvas clear
		// gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

		gc.setStroke(Color.FORESTGREEN);
		gc.setLineWidth(2);

		//calculate new length 
		double factor = Math.sin(angle * Constants.ConvertAngle);
		currentVector = currentVector.scale(factor);
		
		
		//calculate orientation .... need left right?!
		MatrixExtended currentMatrix = new MatrixExtended(1, 2);
		currentMatrix.set(0, 0, currentVector.getX());
		currentMatrix.set(0, 1, currentVector.getY());		
		currentMatrix.rotateRight(angle);
		currentVector.setX(currentMatrix.get(0, 0));
		currentVector.setY(currentMatrix.get(0, 1));
				
		// start point
		gc.moveTo(startPointVector.getX(), startPointVector.getY());
								
		gc.lineTo(startPointVector.getX() + currentVector.length()/2 , startPointVector.getY() - currentVector.length()/2);
		gc.lineTo(startPointVector.getX() + currentVector.getX(), startPointVector.getY() - currentVector.getY());
		gc.lineTo(startPointVector.getX() - currentVector.length()/2 , startPointVector.getY() - currentVector.length()/2);
		gc.lineTo(startPointVector.getX(), startPointVector.getY());
		
		// plot strokes
		gc.stroke();
				
		//new start point		
		startPointVector.setX(startPointVector.getX() + currentVector.getX());			
		startPointVector.setY(startPointVector.getY() - currentVector.getY());
				

	}
	
	private void drawChildShapeLeft(final Canvas canvas, Vector currentVector, Vector  startPointVector, double angle)
			throws Exception {

		final GraphicsContext gc = canvas.getGraphicsContext2D();

		// Canvas clear
		// gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

		gc.setStroke(Color.FORESTGREEN);
		gc.setLineWidth(2);

		//calculate new length 
		double factor = Math.sin(angle * Constants.ConvertAngle);
		currentVector = currentVector.scale(factor);
		
		
		//calculate orientation .... need left right?!
		MatrixExtended currentMatrix = new MatrixExtended(1, 2);
		currentMatrix.set(0, 0, currentVector.getX());
		currentMatrix.set(0, 1, currentVector.getY());		
		currentMatrix.rotateLeft(angle);
		currentVector.setX(currentMatrix.get(0, 0));
		currentVector.setY(currentMatrix.get(0, 1));
				
		// start point
		gc.moveTo(startPointVector.getX(), startPointVector.getY());
								
		gc.lineTo(startPointVector.getX() + currentVector.length()/2 , startPointVector.getY() - currentVector.length()/2);
		gc.lineTo(startPointVector.getX(), startPointVector.getY() - currentVector.length());
		gc.lineTo(startPointVector.getX() - currentVector.length()/2 , startPointVector.getY() - currentVector.length()/2);
		gc.lineTo(startPointVector.getX(), startPointVector.getY());
		
		// plot strokes
		gc.stroke();
				
		//new start point		
		startPointVector.setX(startPointVector.getX() + currentVector.getX());
		startPointVector.setY(startPointVector.getY() - currentVector.getY());
		
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
