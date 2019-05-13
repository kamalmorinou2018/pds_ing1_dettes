package client;

import javafx.application.Application;
import javafx.stage.Stage;

public class Client extends Application{
	View a;
public static void main(String[] args) {
	launch(args);
}

@Override
public void start(Stage arg0) throws Exception {
	// TODO Auto-generated method stub
	a = new View();
	arg0=a.getStage1();
	arg0.show();
	Controller c = new Controller(a);
	c.action();
}}
