package server;

import java.util.ArrayList;

public class Service extends Thread {
	private static ArrayList<Thread> pooldethread;
	private static int i;
	public Service(ArrayList<Thread> pooldethread,int i) {
		this.pooldethread=pooldethread;
		this.i=i;
	}
public void run() {
		pooldethread.get(i).run();
}
}
