package br.ifpb.pod.receiver;

import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import br.ifpb.pod.serverapp.ServerApp;


public class Main {
	
	public static void main(String[] args) throws RemoteException, AlreadyBoundException, NotBoundException{
		
		//PEGANDO O SERVER APP
		Registry registry = LocateRegistry.getRegistry("localhost", 8080);
		ServerApp serverApp = (ServerApp) registry.lookup("ServerApp");
		
		
		//REGISTRANDO O RECEIVER
		System.setProperty("java.rmi.server.hostname", "localhost");
		registry = LocateRegistry.createRegistry(8081);
		Receiver r = new ReceiverImpl(serverApp);
		registry.bind("Receiver", r);
		
	}
	

}
