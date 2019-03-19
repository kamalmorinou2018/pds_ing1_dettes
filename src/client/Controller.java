package client;



import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.json.simple.JSONObject;

import javafx.event.EventHandler;
import serialisation.Personne;
import serialisation.Serialisation;
import javafx.event.ActionEvent;

public class Controller  {
private View v;
private Socket socket;
private PrintStream out;
private EventHandler<ActionEvent> SELECT;

public Controller(View v) {
	this.v = v;
}
public Controller() {
	
}

public int redevance(int x) {
	return(x*13/100);
}

public void action() {
	// TODO Auto-generated method stub
	try {
		//socket = new Socket("192.168.10.9",8041);
		socket = new Socket("localhost",8041);
		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new PrintStream(socket.getOutputStream());
		Serialisation sr = new Serialisation();
		JSONObject json =new JSONObject();
		SELECT = e2 ->{
		if(this.v.getFirstName().getText().equals("") && this.v.getName().getText().equals("")) {
		try {
		out.println("read");
		out.flush();
		this.v.getData().clear();
		do{
			JSONObject jso =new JSONObject();
			String person = in.readLine();
    		//insertioon de person dans la bd
    		System.out.println("object recu de serveur"+person);
    		jso = sr.deserialisation(person);
    		
			Personne per = new Personne((String)jso.get("Magazin"),(String)jso.get("Annee"),(String)jso.get("Ca"));
			this.v.getData().add(per);
		}while(in.ready());
			}catch(Exception a) {
			a.printStackTrace();
			}}
		else if(this.v.getFirstName().getText().length()!=0 && this.v.getName().getText().length()!=0) {
				this.v.getData().clear();
				try{
				System.out.println("chercher");
				out.println("search");
				out.flush();
				out.println(this.v.getFirstName().getText());
				out.flush();
				out.println(this.v.getName().getText());
				out.flush();
				do{
					JSONObject jso =new JSONObject();
					String prson = in.readLine();
		    	if(prson!="rien") {
		    		
					//insertioon de person dans la bd
		    		System.out.println("object recu de serveur"+prson);
		    		jso = sr.deserialisation(prson);
					Personne per = new Personne((String)jso.get("Magazin"),(String)jso.get("Annee"),(String)jso.get("Ca"));
					this.v.getData().add(per);
					//calcule redevance
					this.v.getLabel1P().setText(Long.toString(Long.parseLong(per.getCa())*13/100)+" $");
				
		    	}
		    	
				
				}while(in.ready());
				}catch(Exception e) {
					
				}
				
			}
			
			};this.v.getImport().setOnAction(SELECT);
    
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	}
public static void main(String[] args) {
	Controller c = new Controller();
	System.out.println(c.redevance(100)==(100*13/100));
}

}