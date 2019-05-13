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
import java.util.ArrayList;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;


public class Server {
private int nombredeclient =0;
private int port=8041;
private static ServerSocket serversocket;
private static ArrayList<Thread> pooldethread = new ArrayList<Thread>();
private static int numberclients = 3;
Socket socket;

public void rem(PoolConnexion pool) throws IOException, ParseException, ClassNotFoundException, SQLException{
	
	serversocket = new ServerSocket(8041);
	for(int i=0;i<numberclients;i++) {
		ConnectionThread ct = new ConnectionThread(pool,socket,nombredeclient,port,serversocket);
		pooldethread.add(ct);
	}
}

public static void main(String[] args) throws ClassNotFoundException, IOException, ParseException, SQLException {
	PoolConnexion pool = new PoolConnexion();
	new Server().rem(pool);
	
	for(int i=0;i<numberclients;i++) {
	
		new Service(pooldethread,i).start();
	
	}
	
	
}}