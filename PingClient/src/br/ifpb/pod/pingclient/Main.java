package br.ifpb.pod.pingclient;

import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import br.ifpb.pod.pingserver.PingServer;
import br.ifpb.pod.sender.Sender;


public class Main {
	
	public static void main(String[] args) throws RemoteException, NotBoundException, AlreadyBoundException{
		
		//PEGANDO O PING SERVER
		Registry registry = LocateRegistry.getRegistry("localhost", 8083);
		PingServer ps = (PingServer) registry.lookup("PingServer");
				
		
				
		//REGISTRANDO O PING CLIENT
		System.setProperty("java.rmi.server.hostname", "localhost");
		registry = LocateRegistry.createRegistry(8084);
		PingClient pc = new PingClientImpl(ps);
		System.out.println("construiu PingClientImpl");
		registry.bind("PingClient", pc);
		System.out.println("registrou PingClient!");
		
		pc.logLatency();
		
	}

}
