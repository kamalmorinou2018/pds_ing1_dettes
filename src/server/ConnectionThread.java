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
		System.out.println("le serveur est en attente");
		client = serversocket.accept();
		nombredeclient=nombredeclient+1;
		System.out.println("le nombre de client est :"+nombredeclient);
		while(true){
			BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
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
	    			PrintStream ous = new PrintStream(client.getOutputStream());
	    			System.out.println("read entrer");
	    			System.out.println("pool connection disponible "+pool.getpoolnumcon());
	    			if(pool.getpoolnumcon()==0) {
						System.out.println("------------pool de connexion est saturé--------------- ");
						
						
					}else {
					Connection  conn =pool.getConnexion();
					Statement state = conn.createStatement();
					System.out.println("pool connection used "+pool.getConnectionused());
					System.out.println("pool connection disponible "+pool.getpoolnumcon());
					//temps d'attente d'insertion d'une requette
					this.sleep(10000);
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
	   
	    			
	    		}}
	    		else if(p.equals("search")) {
	    			PrintStream ous = new PrintStream(client.getOutputStream());
	    			System.out.println("chercher");
	    			System.out.println("pool connection disponible "+pool.getpoolnumcon());
	    			if(pool.getpoolnumcon()==0) {
						System.out.println("------------pool de connexion est saturé--------------- ");
						
						
					}else {
					Connection  conn =pool.getConnexion();
					Statement state = conn.createStatement();
					Statement state2 = conn.createStatement();
					Statement state3 = conn.createStatement();
					Statement state4 = conn.createStatement();
					Statement state5 = conn.createStatement();
					Statement state6 = conn.createStatement();
					System.out.println("pool connection used "+pool.getConnectionused());
					System.out.println("pool connection disponible "+pool.getpoolnumcon());
					//temps d'attente d'insertion d'une requette
					//this.sleep(30000);
					String zer = in.readLine();
					String sdf = in.readLine();
					System.out.println(zer+" recu "+sdf);
					ResultSet res = state2.executeQuery("SELECT * FROM masca where magasin = '"+sdf+"' and annee = '"+zer+"' ");
					ResultSet loc = state3.executeQuery("SELECT * FROM location where magasin = '"+sdf+"' and annee = '"+zer+"' ");
					ResultSet fre = state.executeQuery("SELECT * FROM frequentation where magasin = '"+sdf+"' and annee = '"+zer+"' ");					
					pool.ReturnConnectionTopool(conn);
					System.out.println("pool connection disponible "+pool.getpoolnumcon());	
	    			if(res.wasNull() || fre.wasNull() || loc.wasNull() ) {
	    				ous.println("rien");
    					ous.flush();
	    			}
	    			while(fre.next() && loc.next()) {
							System.out.println(" fdfv "+fre.getString("numbrefr"));
							ous.println(fre.getString("numbrefr"));
    						ous.flush();
    					if(loc.getString("emplacement").equals("privilegie")) {
    						ous.println(Long.parseLong(loc.getString("surface"))*2500);
    						ous.flush();
	    			    }else {
    						ous.println(Long.parseLong(loc.getString("surface"))*2000);
        					ous.flush();
    					}
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
					
				
					}}
	    		else if(p.equals("total")) {
	    			PrintStream ous = new PrintStream(client.getOutputStream());
	    			System.out.println("pool connection disponible "+pool.getpoolnumcon());
	    			if(pool.getpoolnumcon()==0) {
						System.out.println("------------pool de connexion est saturé--------------- ");
						
						
					}else {
					Connection  conn =pool.getConnexion();
					Statement state4 = conn.createStatement();
					Statement state5 = conn.createStatement();
					Statement state6 = conn.createStatement();
					System.out.println("pool connection used "+pool.getConnectionused());
					System.out.println("pool connection disponible "+pool.getpoolnumcon());
					//temps d'attente d'insertion d'une requette
					//this.sleep(30000);
					String zer = in.readLine();
					ResultSet totalca = state4.executeQuery("SELECT * FROM masca ");
					ResultSet totalloc = state5.executeQuery("SELECT * FROM location ");
					ResultSet totalfre = state6.executeQuery("SELECT * FROM frequentation ");
					long ltotalca=0;
					long ltotalloc=0;
					long ltotalfre=0;
					long totalcomercial=0;
					String year =zer;
					while(totalca.next()) {
						if(totalca.getString("annee").equals(year)) {
						ltotalca=ltotalca+Long.parseLong(totalca.getString("chiffreaffaire"));
					}
					}
					while(totalloc.next()) {
					if(totalloc.getString("emplacement").equals("privilegie") && totalloc.getString("annee").equals(year)) {
						ltotalloc=ltotalloc+Integer.parseInt(totalloc.getString("surface"))*2500;
					}else if(totalloc.getString("annee").equals(year)) {
						ltotalloc=ltotalloc+Integer.parseInt(totalloc.getString("surface"))*2000;
					}
					
					}
					
					while(totalfre.next()) {
						if(totalfre.getString("annee").equals(year)) {
						ltotalfre=ltotalfre+Integer.parseInt(totalfre.getString("numbrefr"));
						}
					}
					totalcomercial=(long) (ltotalca*0.05+ltotalloc+ltotalfre*0.001);
					  
					System.out.println("le total de redevance pour le centre commercial est : "+totalcomercial);
					
					pool.ReturnConnectionTopool(conn);
					
					System.out.println("pool connection disponible "+pool.getpoolnumcon());	
	    			
					if(totalca.wasNull() || totalloc.wasNull() || totalfre.wasNull() ) {
	    				ous.println("rien");
    					ous.flush();
	    			}
	    			
						ous.println(totalcomercial);
    					ous.flush();
    					
    					
	    		}
	    		
	    		
	    		
	    		
		
		}
	    		}
		
		}catch(Exception e) {

		}
		
		
	}
		
		
	

}
