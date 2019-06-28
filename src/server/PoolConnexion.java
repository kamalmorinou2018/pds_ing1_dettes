package server;

import java.io.IOException;

import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;


public class PoolConnexion  {
	String url = "jdbc:mysql://localhost:3306/pdsing";
	//private String url = "jdbc:mysql://localhost:3306/pdsing";
	private String user = "root";
	private String passwd = "pds";
	//private String user = "root";
	//private String passwd = "root";
	 private  ArrayList<Connection> pool = new ArrayList<Connection>();
	 static int Connectionused=0;
	 private static int poolnumcon=2;
	 
	
	 public PoolConnexion() throws SQLException, ClassNotFoundException {
		 Class.forName("com.mysql.jdbc.Driver");
		 System.out.println("Connection to BD done");
    for( int j=0;j<poolnumcon;j++) {
			 Connection conn = DriverManager.getConnection(url, user, passwd);
			 pool.add(conn);
		}

	 }
	public void ReturnConnectionTopool(Connection conn) {
		this.pool.add(conn);
		poolnumcon=poolnumcon+1;
		Connectionused=Connectionused-1;
	}
	 public Connection getConnexion() {
		 if(pool.size()==2) {
			 Connectionused=0;
		 }
		 Connectionused=Connectionused+1;
		 poolnumcon=poolnumcon-1;
		 Connection conn = pool.get(0);
		 pool.remove(0);
		 
		 return conn;
			
		}
	 public int getConnectionused() {
		 
		 return Connectionused;
	 }
	 public void setConnectionused(int x) {
		 Connectionused=x;
	 }
public int getpoolnumcon() {
		 
		 return poolnumcon;
	 }
	 public void setpoolnumcon(int x) {
		 poolnumcon=x;
	 }
	

}