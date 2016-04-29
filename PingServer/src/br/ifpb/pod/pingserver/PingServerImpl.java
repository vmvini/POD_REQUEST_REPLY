package br.ifpb.pod.pingserver;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class PingServerImpl extends UnicastRemoteObject implements PingServer {

	protected PingServerImpl() throws RemoteException {
		super();
		
	}

	public String[] ping() throws RemoteException {
		return null;
	}

}
