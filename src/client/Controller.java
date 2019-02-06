package client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.EventHandler;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import org.json.simple.JSONObject;

import Serialisation.Personne;
import Serialisation.Serialisation;

public class Controller implements ActionListener {
private Vue v;
private Socket socket;
private PrintStream out;
public Controller(Vue v) {
	this.v = v;
	this.v.getButton().addActionListener(this);
}

@Override
public void actionPerformed(ActionEvent arg0) {
	// TODO Auto-generated method stub
	try {
	socket = new Socket("localhost",8041);
	Personne personne = new Personne(v.getFirstname().getText(),v.getSecondname().getText(),v.getAge().getText());
	Serialisation sr = new Serialisation();
	JSONObject json =new JSONObject();
	json = sr.serialisationDTO(personne);
	System.out.println(json.toJSONString());
	out = new PrintStream(socket.getOutputStream());
	out.println(json.toJSONString());
    out.flush();
    
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	}


}