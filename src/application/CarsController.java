package application;

import java.io.DataInputStream;
import java.io.DataOutputStream;

import connection.Client;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import logic.ClientType;

public class CarsController implements ClientType {
//	@FXML
//	private Button upButton;
//	@FXML
//	private Button downButton;
	@FXML
	private Button leftButton;
	@FXML
	private Button rightButton;
	@FXML
	private Label action;
	@FXML
	private VBox vBoxScore;
	@FXML
	private ScrollPane scroll;
	int index = 0;
	@FXML
	private Button BtnAddCoordinate;
	@FXML
	private ListView<String> listBoxMain;
	@FXML
	private TextField txtAddItem;
	@FXML
	private Button BtnBreak;
	@FXML
	private Button BtnAccelerate;
	@FXML
	private Button BtnStop;
	
	
	@FXML
	private ComboBox<String> comboBox;
	
	final ObservableList<String> listItems = FXCollections.observableArrayList("Start Drive!");
	DataInputStream inputStream ;
	DataOutputStream outputStream;
	
//    public static int remoteServerPort = 3001;
	public static String remoteServerAddress = "192.168.0.39"; // ip address of server
	public double speedUpdate;
	public final double ACCELERATE = 2.2;
	public final double BREAK = -2.2;
	public final double STOP = 0.0;
	
	private Client client;
	
	@FXML
	public void initialize() {
		this.client = new Client(this);
		this.client.connect();
		listBoxMain.setItems(listItems);

		// Set disable buttons to start
		BtnAddCoordinate.setDisable(false);
		leftButton.setDisable(false);
		rightButton.setDisable(false);
//		
		// Add a ChangeListener to TextField to look for change in focus
		txtAddItem.focusedProperty().addListener(new ChangeListener<Boolean>() {
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if (txtAddItem.isFocused()) {
					BtnAddCoordinate.setDisable(false);
					leftButton.setDisable(false);
					rightButton.setDisable(false);
				}
			}
		});
		comboBox.getItems().removeAll(comboBox.getItems());
		comboBox.getItems().addAll("Option A", "Option B", "Option C");
		comboBox.getSelectionModel().select("Option B");
	}
	
	@Override
	public void write(String command) {
		this.client.sendToServer(command);
	}
    

	// get speed from simulator
	private double getSpeed() {
		return speedUpdate;
	}
	
	private void setSpeed(Double speed) {
		speedUpdate = speed;
	}

	@FXML
	private void accelerateSpeed(ActionEvent action) {
		speedUpdate = getSpeed() + ACCELERATE;
		write(speedUpdate+"");
	}
	
	@FXML
	private void breakCar(ActionEvent action) {
		speedUpdate = getSpeed() + BREAK;
		write(speedUpdate+"");
	}
	
	@FXML
	private void stopCar() {
		speedUpdate = 0;
		write(speedUpdate+"");
	}
	
	@FXML
	private void showCoordinate(ActionEvent action) {
		listItems.add(txtAddItem.getText());
		write(txtAddItem.getText());
		System.out.println(txtAddItem.getText());
	}

	@FXML
	private void showLeft(ActionEvent action) {
		listItems.add("Left");
		write("Left");
		System.out.println("Left");
	}
	
	@FXML
	private void showRight(ActionEvent action) {
		listItems.add("Right");
		write("Right");
		System.out.println("Right");
		
	}
		
	
//	@FXML
//	private void showUp(ActionEvent action) {
//		listItems.add("Up");
//		send("Up");
//		System.out.println("Up");
//	}
//	
//	@FXML
//	private void showDown(ActionEvent action) {
//		listItems.add("Down");
//		send("Down");
//		System.out.println("Down");
//	}
//	
	
}
