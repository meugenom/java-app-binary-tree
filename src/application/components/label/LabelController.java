package application.components.label;

import application.config.Config;
import application.model.TreeDataModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class LabelController {

	@FXML
	private Label levelLabel;

	@FXML
	private ListView<String> levelList;

	public void start(TreeDataModel treeDataModel, BorderPane borderPane) {

		borderPane.setTop(levelLabel);

		treeDataModel.nodeProperty().addListener(new ChangeListener<Object>() {
			@Override
			public void changed(ObservableValue o, Object oldVal, Object newVal) {
				String level = String.format("CurrentLevel is: %s", Config.Iteration);
				levelLabel.setText(level);
			}
		});

	}

	@FXML
	private void initialize() {
		levelLabel.setText("Current Level is: 0");
	}

}
