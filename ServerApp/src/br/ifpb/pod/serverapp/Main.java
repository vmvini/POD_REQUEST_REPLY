package br.ifpb.pod.serverapp;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class Main {
	
	
	public static void main(String[] args) throws RemoteException, AlreadyBoundException{
		System.out.println("SERVER APP EXECUTANDO");
		//REGISTRANDO O SERVER APP
		System.setProperty("java.rmi.server.hostname", "localhost");
		Registry registry = LocateRegistry.createRegistry(8080);
		ServerApp s = new ServerAppImpl();
		registry.bind("ServerApp", s);
	}

}
