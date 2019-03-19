package client;

import org.grios.tableadapter.DefaultTableAdapter;

import com.sun.javafx.application.LauncherImpl;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import serialisation.Personne;

public class View {
	private Button Import;
   	private TableView myTableView;
	private TableView Table;
	ObservableList<Personne> Data ;
	private TextField Name;
	private TextField FirstName;
	private Label label1P;

	private Label resultat;
	
	public Label getResultat() {
		return resultat;
	}




	public Label getLabel1P() {
		return label1P;
	}

	public void setLabel1P(Label label1p) {
		label1P = label1p;
	}
	private ObservableList<String> Items =FXCollections.observableArrayList ();
	private ObservableList<String> Items2 =FXCollections.observableArrayList ();
	
	public ObservableList<String> getItems2() {
		return Items2;
	}

	public void setItems2(ObservableList<String> items2) {
		this.Items2 = items2;
	}

	public ObservableList<String> getItems() {
		return Items;
	}
	

	
	public void setItems(ObservableList<String> items) {
		this.Items = items;
	}
	public ObservableList<Personne> getData() {
		return Data;
	}
	private Stage arg0 = new Stage();
	
	public Stage getStage() {
		return arg0;
	}
	public void setStage(Stage stage) {
		this.arg0 = stage;
	}

	public Button getImport() {
		return Import;
	}
	public void setImport(Button import1) {
		Import = import1;
	}
	
	public Stage getStage1() throws Exception {
		GridPane grid3 = new GridPane();
		GridPane gridField = new GridPane();
		gridField.setPadding(new Insets(10, 10, 10, 10));
		gridField.setVgap(5);
		gridField.setHgap(5);
		GridPane labels = new GridPane();
		labels.setPadding(new Insets(10, 10, 10, 10));
		labels.setVgap(8);
		labels.setHgap(4);
		GridPane.setConstraints(labels, 1, 4);
		gridField.getChildren().add(labels);
		
		Label resultat = new Label("");
		resultat.setFont(Font.font ("Verdana", 20));
		resultat.setTextFill(Color.DARKORANGE);
		GridPane.setConstraints(resultat, 3, 3);
		gridField.getChildren().add(resultat);
		
		Label label1 = new Label("Redevance");
		label1.setTextFill(Color.GREENYELLOW);
		GridPane.setConstraints(label1, 1, 1);
		labels.getChildren().add(label1);
		
		label1P = new Label("0 $");
		label1P.setTextFill(Color.RED);
		GridPane.setConstraints(label1P, 2, 1);
		labels.getChildren().add(label1P);
		Name = new TextField();
		Name.setPromptText("Name");
		Name.setPrefColumnCount(10);
		GridPane.setConstraints(Name, 1, 2);
		gridField.getChildren().add(Name);

		FirstName = new TextField();
		FirstName.setPromptText("FirstName");
		FirstName.setPrefColumnCount(10);
		GridPane.setConstraints(FirstName, 2, 2);
		gridField.getChildren().add(FirstName);
		Import = new Button("SELECT");
		GridPane.setConstraints(Import, 0, 2);
		gridField.getChildren().add(Import);
		myTableView = new TableView();

		myTableView = new TableView();
		
		Table = new TableView();
		TableColumn firstNameCol = new TableColumn("Magazin");
        TableColumn lastNameCol = new TableColumn("Annee");
        TableColumn emailCol = new TableColumn("Ca");
        
        Table.getColumns().addAll(firstNameCol, lastNameCol,emailCol);
        firstNameCol.setCellValueFactory(
        	    new PropertyValueFactory<Personne,String>("Magazin")
        	);
        	lastNameCol.setCellValueFactory(
        	    new PropertyValueFactory<Personne,String>("Annee")
        	);
        	emailCol.setCellValueFactory(
        	    new PropertyValueFactory<Personne,String>("Ca")
        	);
        	

        
        Data = FXCollections.observableArrayList();
        Table.setItems(Data);
        GridPane.setConstraints(Table, 3, 4);
        Table.setMinHeight(130);
        gridField.getChildren().add(Table);
	    GridPane.setConstraints(gridField, 0, 0);
	    grid3.getChildren().add(gridField);
	    grid3.setStyle("-fx-background-color: black");
	    arg0.setScene(new Scene(grid3, 1140, 500));
	    return arg0;	
	}
	


	public TextField getName() {
		return Name;
	}
	public void setName(TextField name) {
		this.Name = name;
	}
	public TextField getFirstName() {
		return FirstName;
	}
	public void setFirstName(TextField idShop) {
		FirstName = idShop;
	}

	public void setData(ObservableList<Personne> data) {
		this.Data = data;
	}
	

}