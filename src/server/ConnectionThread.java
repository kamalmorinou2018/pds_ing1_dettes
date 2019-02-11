package server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;

import org.json.simple.JSONObject;

import Serialisation.Personne;
import Serialisation.Serialisation;

public class ConnectionThread extends Thread {
	private Socket client;
	private int nombredeclient ;
	private int port=8041;
	private ServerSocket serversocket;
	private PoolConnexion pool;
	
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
		while(true){
			client = serversocket.accept();
			nombredeclient=nombredeclient+1;
			System.out.print("le nombre de client est :"+nombredeclient);
			BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
		    	Serialisation ser = new Serialisation();
	    		JSONObject object =new JSONObject();
	    		String person = in.readLine();
	    		System.out.println(person);
				object = ser.deserialisation(person);
				Personne per = new Personne((String)object.get("firstname"),(String)object.get("secondname"),(String)object.get("age"));
				System.out.println("pool connection disponible "+pool.getpoolnumcon());
				Connection  conn =pool.getConnexion();
				Statement state = conn.createStatement();
				System.out.println("pool connection used "+pool.getConnectionused());
				System.out.println("pool connection disponible "+pool.getpoolnumcon());
				this.sleep(30000);
				int res = state.executeUpdate("INSERT INTO PERSON VALUES ('"+per.getfirstname()+"',"+"'"+per.getsecondname()+"',"+"'"+per.getage()+"')");
				pool.ReturnConnectionTopool(conn);
				System.out.println("pool connection disponible "+pool.getpoolnumcon());	
			}
		}catch(Exception e) {
			System.out.println("vous avez dépassé ");
			e.printStackTrace();
		}
	}
		
		
	

}
