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
private EventHandler<ActionEvent> SELECT;
private EventHandler<ActionEvent> TOTAL;

public Controller(View v) {
	this.v = v;
}
public Controller() {
	
}

public long redevance(long l, long m , long n) {
	return (long) ((l*5/100)+m/12+(n*0.001));
}

public void action() {
	// TODO Auto-generated method stub
	try {
		socket = new Socket("192.168.5.181",8041);
		//socket = new Socket("localhost",8041);
		PrintStream out = new PrintStream(socket.getOutputStream());
		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	} catch (Exception e) {
		System.out.println(" impossible de se connecter au serveur le nombre des clients atteint");
		e.printStackTrace();
	}
		Serialisation sr = new Serialisation();
		JSONObject json =new JSONObject();
		SELECT = e2 ->{
		if(this.v.getFirstName().getText().equals("") && this.v.getName().getText().equals("")) {
		try {
		PrintStream out = new PrintStream(socket.getOutputStream());
		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out.println("read");
		out.flush();
		this.v.getData().clear();
		this.v.getData1().clear();
		this.v.getData2().clear();
		System.out.println("h1");
		do{
			JSONObject jso =new JSONObject();
			String person = in.readLine();
			if(person.equals("0")) {break;}
			else{
			//insertioon de person dans la bd
    		System.out.println("object recu de serveur"+person);
    		jso = sr.deserialisation(person);	
			Personne per = new Personne((String)jso.get("Magazin"),(String)jso.get("Annee"),(String)jso.get("Ca"),(String)jso.get("Mois"));
			this.v.getData().add(per);
    		}
			}while(in.ready());
			
		do{
			JSONObject jso =new JSONObject();
			String person = in.readLine();
			if(person.equals("1")) {break;}
			else{
			//insertioon de person dans la bd
    		System.out.println("object recu de serveur"+person);
    		jso = sr.deserialisation(person);	
			Personne per = new Personne((String)jso.get("Magazin"),(String)jso.get("Annee"),(String)jso.get("Ca"),(String)jso.get("Mois"));
			this.v.getData1().add(per);
    		}
			}while(in.ready());
		do{
			JSONObject jso =new JSONObject();
			String person = in.readLine();
			//insertioon de person dans la bd
    		System.out.println("object recu de serveur"+person);
    		jso = sr.deserialisation(person);	
			Personne per = new Personne((String)jso.get("Magazin"),(String)jso.get("Annee"),(String)jso.get("Ca"),(String)jso.get("Mois"));
			this.v.getData2().add(per);
			}while(in.ready());
		
		
		System.out.println("h2");
		
		
		}catch(Exception a) {
			a.printStackTrace();
			}}
		else if(this.v.getFirstName().getText().length()!=0 && this.v.getName().getText().length()!=0 && this.v.getMois().getText().length()!=0) {
			try{
			PrintStream out = new PrintStream(socket.getOutputStream());
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				this.v.getData().clear();
				System.out.println("chercher");
				out.println("search");
				out.flush();
				out.println(this.v.getFirstName().getText());
				out.flush();
				out.println(this.v.getName().getText());
				out.flush();
				out.println(this.v.getMois().getText());
				out.flush();
				int i=0;
		    	String fre="0";
		    	String surf ="0";
		    	String prson = in.readLine();
		    	System.out.println("0");
				if(prson!="rien") {
					fre = prson;
					JSONObject jso =new JSONObject();
					 surf = in.readLine();
					 System.out.println("1");
					//insertioon de person dans la bd
		    		System.out.println("object recu de serveur"+prson+" fre"+fre+"surface"+surf);
		    		prson = in.readLine();
		    		System.out.println("2");
		    		jso = sr.deserialisation(prson);
					Personne per = new Personne((String)jso.get("Magazin"),(String)jso.get("Annee"),(String)jso.get("Ca"),(String)jso.get("Mois"));
					this.v.getData().add(per);
					//calcule redevance
					this.v.getLabel1P().setText(Long.toString(this.redevance(Long.parseLong(per.getCa()), Long.parseLong(surf), Long.parseLong(fre))));
		    	
				}
				}catch(Exception e) {
					e.printStackTrace();
				}
				
				
			}
		
			
			
		};this.v.getImport().setOnAction(SELECT);
			
			TOTAL = e4 ->{
				try{
				String str = null; 			
				PrintStream out = new PrintStream(socket.getOutputStream());
				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				while(in.ready()){ 
					  System.out.println(in.readLine()+"pour vider"); 
					}
				
				out.println("total");
				out.flush();
				out.println(this.v.getComboBox().getSelectionModel().getSelectedItem());
				out.flush();
				String prson = in.readLine();
				this.v.getTotalz().setText(prson);
				
				}catch(Exception e) {
				e.printStackTrace();
				}
					
			};this.v.getTotal().setOnAction(TOTAL);	
    
	
	
	}
public static void main(String[] args) {
	Controller c = new Controller();
	System.out.println(c.redevance(100,100,100)==10);
}

}