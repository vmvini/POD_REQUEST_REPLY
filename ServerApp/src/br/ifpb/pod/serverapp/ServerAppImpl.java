package br.ifpb.pod.serverapp;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ServerAppImpl extends UnicastRemoteObject implements ServerApp {

	protected ServerAppImpl() throws RemoteException {
		super();
		
	}

	public String processMessage(String msg) throws RemoteException {
		return msg + " VOLTE SEMPRE!";
	}

}
