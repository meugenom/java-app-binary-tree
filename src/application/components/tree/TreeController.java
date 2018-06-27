package application.components.tree;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import application.DrawShapesController;
import application.ExportSVGController;
import application.config.Config;
import application.model.TreeDataModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class TreeController implements Initializable {

	@Override
	public void initialize(URL url, ResourceBundle resources) {
	}

	@FXML
	private BorderPane borderPane;

	@FXML
	private Button startButton;

	@FXML
	private TextField levelField;

	@FXML
	private TextField angleField;

	@FXML
	private TextField widthField;

	@FXML
	private TextField heightField;

	@FXML
	private Button exportButton;

	@FXML
	private Canvas canvas;

	private TreeDataModel model;

	@FXML
	private void initialize() {

	}

	public void start(TreeDataModel model, BorderPane borderPane) {

		this.model = model;

		levelField.setPromptText("Input new level");
		angleField.setPromptText("Input new angle");
		widthField.setPromptText("Input new width");
		heightField.setPromptText("Input new heigth");

		levelField.setText(Integer.toString(Config.Iteration));
		angleField.setText(Integer.toString((int) (Config.Angle)));
		widthField.setText(Integer.toString((int) (Config.a)));
		heightField.setText(Integer.toString((int) (Config.b)));

		levelField.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					newValue = newValue.replaceAll("[^\\d]", "");
					levelField.setText(newValue);
				}
			}
		});

		angleField.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					angleField.setText(newValue.replaceAll("[^\\d]", ""));
				}
			}
		});

		widthField.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					widthField.setText(newValue.replaceAll("[^\\d]", ""));
				}
			}
		});

		heightField.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					heightField.setText(newValue.replaceAll("[^\\d]", ""));
				}
			}
		});

		borderPane.widthProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observableValue, Number oldPaneWidth,
					Number newPaneWidth) {
				// System.out.println("Width: " + newPaneWidth);
				resizeCanvas(borderPane.getWidth(), borderPane.getHeight());
			}
		});

		borderPane.heightProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observableValue, Number oldPaneHeight,
					Number newHeight) {
				// System.out.println("Height: " + newHeight);
				resizeCanvas(borderPane.getWidth(), borderPane.getHeight());
			}
		});

		// Instantiating the HBox class
		VBox vbox = new VBox();

		// Setting the space between the nodes of a HBox pane
		// vbox.setSpacing(10);
		startButton.setMinWidth(100);

		VBox.setMargin(levelField, new Insets(10, 10, 10, 10));
		VBox.setMargin(angleField, new Insets(10, 10, 10, 10));
		VBox.setMargin(widthField, new Insets(10, 10, 10, 10));
		VBox.setMargin(heightField, new Insets(10, 10, 10, 10));
		VBox.setMargin(startButton, new Insets(10, 10, 10, 10));
		VBox.setMargin(exportButton, new Insets(10, 10, 10, 10));

		vbox.getChildren().add(levelField);
		vbox.getChildren().add(angleField);
		vbox.getChildren().add(widthField);
		vbox.getChildren().add(heightField);
		vbox.getChildren().add(startButton);
		vbox.getChildren().add(exportButton);

		borderPane.setLeft(vbox);
		borderPane.setCenter(canvas);

		this.model = model;

		if (this.model == null) {
			throw new IllegalStateException("Model can only be initialized once");
		}

		GraphicsContext gc = canvas.getGraphicsContext2D();
		DrawShapesController drawShapesController = new DrawShapesController();
		drawShapesController.draw(gc, model.start());
	}

	private void resizeCanvas(double borderPaneWidth, double borderPaneHeight) {

		double leftPaneRegion = Config.leftBorderPaneRegion;
		double canvasFactor = Config.CanvasHeight / Config.CanvasWidth; // by default 2/3
		double currentBorderPaneFactor = borderPaneHeight / (borderPaneWidth - leftPaneRegion);
		double factorX, factorY;

		// System.out.println(canvasFactor);
		// System.out.println(currentBorderPaneFactor);

		// when height is so more, need calculate by width
		if (currentBorderPaneFactor > canvasFactor) {
			factorX = (borderPaneWidth - leftPaneRegion) / canvas.getWidth();
			factorY = factorX;

			canvas.setScaleX(factorX);
			canvas.setScaleY(factorY);
		}

		if (currentBorderPaneFactor <= canvasFactor) {
			factorY = borderPaneHeight / canvas.getHeight();
			factorX = factorY;

			canvas.setScaleX(factorX);
			canvas.setScaleY(factorY);
		}
	}

	@FXML
	public void handleStartButtonAction(ActionEvent event) {

		String text = startButton.getText();
		text = (text == "Run") ? "Run" : "Next";
		startButton.setText(text);
		
		Config.Iteration = Integer.parseInt(levelField.getText());
		Config.a = Integer.parseInt(widthField.getText());
		Config.b = Integer.parseInt(heightField.getText());
		Config.Angle = Integer.parseInt(angleField.getText());
					
		
		GraphicsContext gc = canvas.getGraphicsContext2D();
		DrawShapesController drawShapesController = new DrawShapesController();

		double w = canvas.getWidth();
		double h = canvas.getHeight();
		gc.clearRect(0, 0, w, h);

		drawShapesController.draw(gc, model.start());

	}

	@FXML
	public void handleExportButtonAction(ActionEvent event) throws IOException {
		ExportSVGController export = new ExportSVGController();
		export.exportSVG();
	}

	@FXML
	private Pane canvasContainer;
	
	@FXML
	private void breakpointAction(ActionEvent e) {
		double a = canvas.getWidth();
		double b = canvas.getHeight();
		double c = canvasContainer.getWidth();
		double d = canvasContainer.getHeight();
		double g = borderPane.getWidth();
		double f = borderPane.getHeight();
		return;
	}
}