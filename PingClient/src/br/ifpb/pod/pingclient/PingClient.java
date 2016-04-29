package br.ifpb.pod.pingclient;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public interface PingClient extends Remote {

	String[] ping() throws RemoteException;
	
	List<String> getWithoutConnectionMsgs() throws RemoteException;

	void checkWithoutConnectionMsg() throws RemoteException;

	long getLastLatency() throws RemoteException;;
		
	List<Long> getLatencyHistory() throws RemoteException;
		
	List<String> getSlowConnectionMsgs() throws RemoteException;
		
	void checkSlowConnectionMsg() throws RemoteException;


	void logLatency() throws RemoteException;

	boolean hasSlowConnection() throws RemoteException;

	boolean hasDisconnectMessage() throws RemoteException;
		
	long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) throws RemoteException;

}
