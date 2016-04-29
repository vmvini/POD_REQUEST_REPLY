package br.ifpb.pod.receiver;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Receiver extends Remote  {

	String sendServerApp(String msg);
	
	String receive(String msg) throws RemoteException;
	
	
}
