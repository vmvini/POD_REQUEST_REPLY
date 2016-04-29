package br.ifpb.pod.sender;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Sender extends Remote {
	
	String sendMessage(String msg) throws RemoteException;
	
	public String hasSuccessDelivery() throws RemoteException;
		
		public boolean isConnected()throws RemoteException;
		
		
		public boolean isTimeout()throws RemoteException;
		
		public boolean isSlow()throws RemoteException;
		


}
