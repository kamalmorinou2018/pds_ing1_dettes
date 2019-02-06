package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import Serialisation.Personne;
import Serialisation.Serialisation;


public class Server {
private int nombredeclient =0;
private int port=8041;
private ServerSocket serversocket;
public void Start() throws IOException, ParseException, ClassNotFoundException, SQLException{
	serversocket = new ServerSocket(port);
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
			PoolConnexion pool = new PoolConnexion();
			System.out.println("pool connection disponible "+pool.getpoolnumcon());
			Connection  conn =pool.getConnexion();
			Statement state = conn.createStatement();
			System.out.println("pool connection used "+pool.getConnectionused());
			System.out.println("pool connection disponible "+pool.getpoolnumcon());
			int res = state.executeUpdate("INSERT INTO PERSON VALUES ('"+per.getfirstname()+"',"+"'"+per.getsecondname()+"',"+"'"+per.getage()+"')");
			pool.ReturnConnectionTopool(conn);
			System.out.println("pool connection disponible "+pool.getpoolnumcon());	
		}
	}

public static void main(String[] args) throws ClassNotFoundException, IOException, ParseException, SQLException {
	new Server().Start();
}


}