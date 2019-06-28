package client;

import org.grios.tableadapter.DefaultTableAdapter;

import com.sun.javafx.application.LauncherImpl;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
	private Button Total;
	private TableView myTableView;
	private TableView Table;
	private TableView TableLocation;
	private TableView TableFrequentation;
	ObservableList<Personne> Data ;
	ObservableList<Personne> Data1 ;
	ObservableList<Personne> Data2 ;
	private TextField Name;
	private TextField FirstName;
	private TextField mois;
	private Label label1P;
	private Label totalz;
	private  ComboBox<String> comboBox ;
	private TextField p;
	private Label resultat;
	
	
	
	public Label getTotalz() {
		return totalz;
	}




	public void setTotalz(Label totalz) {
		this.totalz = totalz;
	}




	public Label getResultat() {
		return resultat;
	}




	public ObservableList<Personne> getData1() {
		return Data1;
	}




	public void setData1(ObservableList<Personne> data1) {
		Data1 = data1;
	}




	public ObservableList<Personne> getData2() {
		return Data2;
	}




	public void setData2(ObservableList<Personne> data2) {
		Data2 = data2;
	}




	public Label getLabel1P() {
		return label1P;
	}

	public void setLabel1P(Label label1p) {
		label1P = label1p;
	}
	
	
	




	public TextField getMois() {
		return mois;
	}




	public void setMois(TextField mois) {
		this.mois = mois;
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
		gridField.setVgap(8);
		gridField.setHgap(8);	
		Label resultat = new Label("");
		resultat.setFont(Font.font ("Verdana", 20));
		resultat.setTextFill(Color.DARKORANGE);
		GridPane.setConstraints(resultat, 3, 3);
		gridField.getChildren().add(resultat);
		
		Label label1 = new Label("Redevance");
		label1.setTextFill(Color.GREENYELLOW);
		GridPane.setConstraints(label1, 1, 1);
		gridField.getChildren().add(label1);
		
		label1P = new Label("0 $");
		label1P.setTextFill(Color.RED);
		GridPane.setConstraints(label1P, 2, 1);
		gridField.getChildren().add(label1P);
		
		
		
		Name = new TextField();
		Name.setPromptText("Magasin");
		Name.setPrefColumnCount(10);
		GridPane.setConstraints(Name, 1, 2);
		gridField.getChildren().add(Name);
		
		mois = new TextField();
		mois.setPromptText("Mois");
		mois.setPrefColumnCount(10);
		GridPane.setConstraints(mois, 3, 2);
		gridField.getChildren().add(mois);
   
		FirstName = new TextField();
		FirstName.setPromptText("Année");
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
        TableColumn mois = new TableColumn("mois");

        
        TableLocation = new TableView();
		TableColumn firstNameCol1 = new TableColumn("Magazin");
        TableColumn lastNameCol1 = new TableColumn("Annee");
        TableColumn emailCol1 = new TableColumn("surface");
        TableColumn mois1 = new TableColumn("emplacement");
        
        TableFrequentation = new TableView();
		TableColumn firstNameCol2 = new TableColumn("Magasin");
        TableColumn lastNameCol2 = new TableColumn("Annee");
        TableColumn emailCol2 = new TableColumn("mois");
        TableColumn mois2 = new TableColumn("nombre de frequentation");
        
        
       
        Total = new Button(" total ");
		GridPane.setConstraints(Total, 1, 6);
		gridField.getChildren().add(Total);
        comboBox = new ComboBox<String>();
        ObservableList<String> list = FXCollections.observableArrayList("2018","2017");
        comboBox.setItems(list);
        comboBox.getSelectionModel().select(1);
		Label pr = new Label("la redevance totale du centre commercial :  ");
		pr.setTextFill(Color.GREENYELLOW);
		GridPane.setConstraints(pr, 2, 6);
		gridField.getChildren().add(pr);
		
		totalz = new Label(" 0");
		totalz.setTextFill(Color.GREENYELLOW);
		GridPane.setConstraints(totalz, 4, 6);
		gridField.getChildren().add(totalz);
		
		
		GridPane.setConstraints(comboBox, 3, 6);
		gridField.getChildren().add(comboBox);
        
        
        Table.getColumns().addAll(firstNameCol, lastNameCol,emailCol,mois);
        firstNameCol.setCellValueFactory(
        	    new PropertyValueFactory<Personne,String>("Magazin")
        	);
        	lastNameCol.setCellValueFactory(
        	    new PropertyValueFactory<Personne,String>("Annee")
        	);
        	emailCol.setCellValueFactory(
        	 new PropertyValueFactory<Personne,String>("Ca")
        	);	
        	mois.setCellValueFactory(
               	 new PropertyValueFactory<Personne,String>("Mois")
               	);	
        Data = FXCollections.observableArrayList();
        Table.setItems(Data);
        GridPane.setConstraints(Table, 3, 5);
        Table.setMinHeight(130);
        gridField.getChildren().add(Table);
	    
        
        
        TableLocation.getColumns().addAll(firstNameCol1, lastNameCol1,emailCol1,mois1);
        firstNameCol1.setCellValueFactory(
        	    new PropertyValueFactory<Personne,String>("Magazin")
        	);
        	lastNameCol1.setCellValueFactory(
        	    new PropertyValueFactory<Personne,String>("Annee")
        	);
        	emailCol1.setCellValueFactory(
        	 new PropertyValueFactory<Personne,String>("Ca")
        	);	
        	mois1.setCellValueFactory(
               	 new PropertyValueFactory<Personne,String>("Mois")
               	);	
        	
        Data1 = FXCollections.observableArrayList();
        TableLocation.setItems(Data1);
        GridPane.setConstraints(TableLocation, 4, 5);
        TableLocation.setMinHeight(50);
        gridField.getChildren().add(TableLocation);
        
        
        
        
        TableFrequentation.getColumns().addAll(firstNameCol2, lastNameCol2,emailCol2,mois2);
        firstNameCol2.setCellValueFactory(
        	    new PropertyValueFactory<Personne,String>("Magazin")
        	);
        	lastNameCol2.setCellValueFactory(
        	    new PropertyValueFactory<Personne,String>("Annee")
        	);
        	emailCol2.setCellValueFactory(
        	 new PropertyValueFactory<Personne,String>("Ca")
        	);	
        	mois2.setCellValueFactory(
               	 new PropertyValueFactory<Personne,String>("Mois")
               	);	
        	
        Data2 = FXCollections.observableArrayList();
        TableFrequentation.setItems(Data2);
        GridPane.setConstraints(TableFrequentation, 5, 5);
        TableFrequentation.setMinHeight(100);
        gridField.getChildren().add(TableFrequentation);
        
        
        
        
        
        
        
        
        
        
        
        
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
   	public Button getTotal() {
		return Total;
	}




	public void setTotal(Button total) {
		Total = total;
	}




	public ComboBox<String> getComboBox() {
		return comboBox;
	}




	public void setComboBox(ComboBox<String> comboBox) {
		this.comboBox = comboBox;
	}

}