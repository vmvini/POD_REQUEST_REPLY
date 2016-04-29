package br.ifpb.pod.clientapp;

import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import br.ifpb.pod.sender.Sender;
import br.ifpb.pod.sender.SenderImpl;

public class Main {
	
	
	public static void main(String[] args) throws AccessException, RemoteException, NotBoundException{
		
		Registry registry = LocateRegistry.getRegistry("localhost", 8082);
		Sender sender = (Sender) registry.lookup("Sender");
		
		Client client = new Client();
		
		client.setSender(sender);
		
		client.executeAll();
	}

}
