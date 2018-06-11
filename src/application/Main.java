package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.Reflection;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

//import java.util.Vector;

public class Main extends Application {
    
    @Override
    public void start(Stage primaryStage) throws Exception{
        
        /*
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
        
            
            public void handle(ActionEvent event) {                
                System.out.println("Hello World!");
            }
        });
        */
        
            
        final Canvas canvas = new Canvas(Constants.CanvasWidth, Constants.CanvasHeight);
        
        // reflections
        /*
        final Reflection reflection = new Reflection();
        reflection.setFraction(0.7);
        canvas.setEffect(reflection);
        */

        
        Vector currentVector = new Vector();
        Vector startPointVector = new Vector();
        
        currentVector.setX(Constants.StartLength);
        currentVector.setY(Constants.StartLength);
        
        
        startPointVector.setX(Constants.StartPointX);
        startPointVector.setY(Constants.StartPointY);
                        
        drawParentShape(canvas, currentVector, startPointVector, Constants.Angle);
        

        
        final FlowPane flowPane = new FlowPane();
        //flowPane.setPadding(new Insets(3));
        flowPane.getChildren().addAll(canvas);        
                
        primaryStage.setScene(new Scene(flowPane, Constants.SceneWidth, Constants.SceneHeight));
        primaryStage.setTitle(Constants.ApplicationName + Constants.Version);         
        primaryStage.show();        
    }
    
        
    
    
    private void drawParentShape(
    		final Canvas canvas, 
    		Vector currentVector, 
    		Vector startPointVector, 
    		double angle){
      final GraphicsContext gc = canvas.getGraphicsContext2D();
     
      // Canvas clear
      //gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
     
      gc.setStroke(Color.ROSYBROWN);
      gc.setLineWidth(2);            
      
      //start point
      gc.moveTo(startPointVector.getX(), startPointVector.getY());
      
      Vector a = new Vector();
      a = currentVector.add(startPointVector);
      
      gc.lineTo(a.getX(), startPointVector.getY());
      gc.lineTo(a.getX(), a.getY());
      gc.lineTo(startPointVector.getX(), a.getY());
      gc.lineTo(startPointVector.getX(), startPointVector.getY());      
      
      //recalc startPointVector for other calculations
      //need think for right and left variants for tree
      startPointVector.setX(a.getX());
      startPointVector.setY(startPointVector.getY());
      
      
      // to childs      
      drawChildShape(canvas, currentVector, startPointVector, angle); 
      
      
      // plot strokes 
      gc.stroke();
          
    }
        
    private void drawChildShape(final Canvas canvas, 
    		Vector currentVector, 
    		Vector startPointVector, 
    		double angle) throws Exception {
        
        final GraphicsContext gc = canvas.getGraphicsContext2D();
       
        // Canvas clear
        //gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        
        gc.setStroke(Color.FORESTGREEN);
        gc.setLineWidth(2);            
                        
        
        //!!! MUSS AN DEN VECTOR CLASS GEDACHT WERDEN !!! KEINE WIDTH UND HEIGHT 
                        
        double factor = Math.sin(angle*Constants.ConvertAngle);        
        
        currentVector = currentVector.scale(factor);
        
        //new vector with new angle
        Matrix  tempMatrix = new turnMatrix(2,2);
        
        //start point
        gc.moveTo(startPointVector.getX(), startPointVector.getY());
        
        Vector a = new Vector();
        a = currentVector.add(startPointVector);
        
        gc.lineTo(a.getX(), startPointVector.getY());
        gc.lineTo(a.getX(), a.getY());
        gc.lineTo(startPointVector.getX(), a.getY());
        gc.lineTo(startPointVector.getX(), startPointVector.getY());      
        
        // plot strokes 
        gc.stroke();
            
      }    
      
    
 public static void main(String[] args) {
        launch(args);
    }
}