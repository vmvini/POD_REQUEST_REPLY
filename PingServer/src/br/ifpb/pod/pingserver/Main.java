package br.ifpb.pod.pingserver;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class Main {
	
	
	public static void main(String[] args) throws RemoteException, AlreadyBoundException{
		System.out.println("PING SERVER EXECUTANDO");
		//REGISTRANDO O PING SERVER
		System.setProperty("java.rmi.server.hostname", "localhost");
		Registry registry = LocateRegistry.createRegistry(8083);
		PingServer ps = new PingServerImpl();
		registry.bind("PingServer", ps);
		
	}

}
