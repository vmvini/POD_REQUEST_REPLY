package br.ifpb.pod.sender;

import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import br.ifpb.pod.pingclient.PingClient;
import br.ifpb.pod.pingclient.PingClientImpl;
import br.ifpb.pod.receiver.Receiver;

public class Main {

	public static void main(String[] args) throws AccessException, RemoteException, AlreadyBoundException, NotBoundException{

		System.setProperty("sun.rmi.transport.tcp.responseTimeout", "3000");
		
		//PEGANDO O PING CLIENT
		Registry registry = LocateRegistry.getRegistry("localhost", 8084);
		PingClient p = (PingClient) registry.lookup("PingClient");
		
		
		//PEGANDO O RECEIVER
		registry = LocateRegistry.getRegistry("localhost", 8081);
		Receiver r = (Receiver) registry.lookup("Receiver");
		
		//REGISTRANDO O SENDER
		System.setProperty("java.rmi.server.hostname", "localhost");
		registry = LocateRegistry.createRegistry(8082);
		
		//CONSTRUINDO SENDER
		Sender s = new SenderImpl(r, p);
		registry.bind("Sender", s);
	}
	
}
