package application;
	
import application.components.label.LabelController;
import application.components.tree.TreeController;
import application.config.Config;
import application.model.TreeDataModel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		try {
						
			TreeDataModel treeDataModel = new TreeDataModel();			
			BorderPane root = new BorderPane();
						
			root.setStyle("-fx-background-color: DAE6F3;-fx-border-color: black;");				
		    						
			FXMLLoader labelLoader = new FXMLLoader(getClass().getResource("components/label/Label.fxml"));
			root.getChildren().add(labelLoader.load());
			// root.setTop(labelLoader.load());
			LabelController labelController = labelLoader.getController();
			labelController.start(treeDataModel, root);
			

			FXMLLoader treeLoader = new FXMLLoader(getClass().getResource("components/tree/Tree.fxml"));
			root.getChildren().add(treeLoader.load());
			// root.setCenter(treeLoader.load());
			TreeController treeController = treeLoader.getController();
			treeController.start(treeDataModel, root);
			
									
			Scene scene = new Scene(root, Config.SceneWidth, Config.SceneHeight);			
			scene.getStylesheets().add(getClass().getResource("scene.css").toExternalForm());									
			
			
			primaryStage.setScene(scene);
			primaryStage.setTitle(Config.ApplicationName + Config.Version);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
