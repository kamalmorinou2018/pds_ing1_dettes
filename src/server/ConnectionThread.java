package server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import org.json.simple.JSONObject;

import serialisation.Personne;
import serialisation.Serialisation;

public class ConnectionThread extends Thread {
	private Socket client;
	private int nombredeclient ;
	private int port=8041;
	private ServerSocket serversocket;
	private PoolConnexion pool;
	private PrintStream out;
	
	public ConnectionThread( PoolConnexion pool,Socket client, int nombredeclient, int port,
			ServerSocket serversocket) {
		super();
		this.client = client;
		this.nombredeclient = nombredeclient;
		this.port = port;
		this.serversocket = serversocket;
		this.pool=pool;
	}


	public Socket getClient() {
		return client;
	}



	public void setClient(Socket client) {
		this.client = client;
	}



	public int getNombredeclient() {
		return nombredeclient;
	}



	public void setNombredeclient(int nombredeclient) {
		this.nombredeclient = nombredeclient;
	}



	public int getPort() {
		return port;
	}



	public void setPort(int port) {
		this.port = port;
	}



	public ServerSocket getServersocket() {
		return serversocket;
	}



	public void setServersocket(ServerSocket serversocket) {
		this.serversocket = serversocket;
	}




	public void  run() {
		try {
		Socket client;
		client = serversocket.accept();
		while(true){
			nombredeclient=nombredeclient+1;
			System.out.print("le nombre de client est :"+nombredeclient);
			BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
			PrintStream ous = new PrintStream(client.getOutputStream());
		    	Serialisation ser = new Serialisation();
	    		JSONObject object =new JSONObject();
				 Serialisation sr = new Serialisation();
				JSONObject json =new JSONObject();
	    		String p = in.readLine();
	    		System.out.println(p+" recu le serveur");
	    		/*if(p.equals("insert")) {
	    		String person = in.readLine();
	    		//insertioon de person dans la bd
	    		System.out.println(person);
				object = ser.deserialisation(person);
				//Personne per = new Personne((String)object.get("firstname"),(String)object.get("secondname"),(String)object.get("age"));
				//System.out.println("pool connection disponible "+pool.getpoolnumcon());
				//Connection  conn =pool.getConnexion();
				//Statement state = conn.createStatement();
				//System.out.println("pool connection used "+pool.getConnectionused());
				//System.out.println("pool connection disponible "+pool.getpoolnumcon());
				//temps d'attente d'insertion d'une requette
				//this.sleep(30000);
				//int res = state.executeUpdate("INSERT INTO person VALUES ('"+per.getfirstname()+"',"+"'"+per.getsecondname()+"',"+"'"+per.getage()+"')");
				pool.ReturnConnectionTopool(conn);
				System.out.println("pool connection disponible "+pool.getpoolnumcon());	
	    		}*/
	    		if(p.equals("read")) {
	    			System.out.println("read entrer");
	    			System.out.println("pool connection disponible "+pool.getpoolnumcon());
					Connection  conn =pool.getConnexion();
					Statement state = conn.createStatement();
					System.out.println("pool connection used "+pool.getConnectionused());
					System.out.println("pool connection disponible "+pool.getpoolnumcon());
					//temps d'attente d'insertion d'une requette
					//this.sleep(30000);
					ResultSet res = state.executeQuery("SELECT * FROM masca");
					pool.ReturnConnectionTopool(conn);
					System.out.println("pool connection disponible "+pool.getpoolnumcon());	
	    			while(res.next()) {
	    				  String a = res.getString("magasin");
	    				  String b = res.getString("annee");
	    				  String c = res.getString("chiffreaffaire");
	    				  Personne ps = new Personne();
	    				  ps.setMagazin(a);
	    				  ps.setAnnee(b);
	    				  ps.setCa(c);
	    				  json = sr.serialisationDTO(ps);
	    					System.out.println(json.toJSONString());
	    					ous.println(json.toJSONString());
	    					ous.flush();
	    				  
	    			}
	    		}
	    		else if(p.equals("search")) {
	    			System.out.println("chercher");
	    			System.out.println("pool connection disponible "+pool.getpoolnumcon());
					Connection  conn =pool.getConnexion();
					Statement state = conn.createStatement();
					System.out.println("pool connection used "+pool.getConnectionused());
					System.out.println("pool connection disponible "+pool.getpoolnumcon());
					//temps d'attente d'insertion d'une requette
					//this.sleep(30000);
					String zer = in.readLine();
					String sdf = in.readLine();
					System.out.println(zer+" recu "+sdf);
					ResultSet res = state.executeQuery("SELECT * FROM masca where magasin = '"+sdf+"' and annee = '"+zer+"' ");
					pool.ReturnConnectionTopool(conn);
					System.out.println("pool connection disponible "+pool.getpoolnumcon());	
	    			if(res.wasNull()) {
	    				ous.println("rien");
    					ous.flush();
	    			}
					while(res.next()) {
	    				  String a = res.getString("magasin");
	    				  String b = res.getString("annee");
	    				  String c = res.getString("chiffreaffaire");
	    				  Personne ps = new Personne();
	    				  ps.setMagazin(a);
	    				  ps.setAnnee(b);
	    				  ps.setCa(c);
	    				  json = sr.serialisationDTO(ps);
	    				  System.out.println("trouver dans BD "+json);
	    					System.out.println(json.toJSONString());
	    					ous.println(json.toJSONString());
	    					ous.flush();
	    			}
	    		}
		
		}
		
		}catch(Exception e) {
			System.out.println("vous avez dépassé ");
			e.printStackTrace();
		}
		
		
	}
		
		
	

}
