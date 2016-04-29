package br.ifpb.pod.pingclient;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import br.ifpb.pod.pingserver.PingServer;
import br.ifpb.pod.sender.Sender;

public class PingClientImpl extends UnicastRemoteObject implements PingClient {

	private PingServer ps;
	
	
	private int MAX_LATENCY = 2;
	
	private List<Long> latencyHistory;
	
	private List<String> slowConnectionMsg;
	
	private List<String> withoutConnectionMsg;
	
	public List<String> getWithoutConnectionMsgs(){
		return withoutConnectionMsg;
	}
	
	public void checkWithoutConnectionMsg(){
		withoutConnectionMsg.remove( withoutConnectionMsg.size() - 1 );
	}
	
	public long getLastLatency(){
		return latencyHistory.get( latencyHistory.size() - 1 );
	}
	
	public List<Long> getLatencyHistory(){
		return latencyHistory;
	}
	
	public List<String> getSlowConnectionMsgs(){
		return slowConnectionMsg;
	}
	
	public void checkSlowConnectionMsg(){
		slowConnectionMsg.remove( slowConnectionMsg.size() - 1 );
	}
	
	protected PingClientImpl(PingServer ps) throws RemoteException {
		super();
		this.ps = ps;
		latencyHistory = new ArrayList<Long>();
		slowConnectionMsg = new ArrayList<String>();
		withoutConnectionMsg = new  ArrayList<String>();
		
	}

	public String[] ping() throws RemoteException {
		
		return ps.ping();
		
	}
	
	public void logLatency(){
		
		while(true){
			try{
				Date t1 = new Date();
				ping();
				Date t2 = new Date();
				long lat_sec = getDateDiff(t1, t2, TimeUnit.SECONDS);
				latencyHistory.add(lat_sec);
				
				if(lat_sec >= MAX_LATENCY){
					slowConnectionMsg.add("Conexao lenta. Latencia: " + lat_sec + " data: " + new Date());
				}
				
				
			}
			catch(Exception e){
				withoutConnectionMsg.add("sem conexao! data: " + new Date());
				System.out.println("sem conexao!");
			}
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
	
	public boolean hasSlowConnection(){
		if(slowConnectionMsg != null)
			return (slowConnectionMsg.size() > 0);
		return false;
	}
	
	public boolean hasDisconnectMessage(){
		if (withoutConnectionMsg != null)
			return withoutConnectionMsg.size() > 0;
		return false;
	}
	
	
	
	
	
	
	
	
	
	public long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
	    long diffInMillies = date2.getTime() - date1.getTime();
	    return timeUnit.convert(diffInMillies,TimeUnit.MILLISECONDS);
	}

}
