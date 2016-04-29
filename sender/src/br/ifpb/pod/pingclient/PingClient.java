package br.ifpb.pod.pingclient;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public interface PingClient extends Remote {

	String[] ping() throws RemoteException;
	
	List<String> getWithoutConnectionMsgs();

	void checkWithoutConnectionMsg();

	long getLastLatency();
		
	List<Long> getLatencyHistory();
		
	List<String> getSlowConnectionMsgs();
		
	void checkSlowConnectionMsg();


	void logLatency();

	boolean hasSlowConnection();

	boolean hasDisconnectMessage();
		
		
	long getDateDiff(Date date1, Date date2, TimeUnit timeUnit);

}
