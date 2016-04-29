package br.ifpb.pod.receiver;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import br.ifpb.pod.serverapp.ServerApp;

public class ReceiverImpl extends UnicastRemoteObject implements Receiver {

	private ServerApp server;
	
	protected ReceiverImpl(ServerApp server) throws RemoteException {
		super();
		this.server = server;
		
	}

	public String sendServerApp(String msg) throws RemoteException {
		
		try {
			Thread.sleep(1);
			return server.processMessage(msg);
		} catch (InterruptedException e) {
			return null;
		}
		
	}

	public String receive(String msg) throws RemoteException {
		return sendServerApp(msg);
	}

}
