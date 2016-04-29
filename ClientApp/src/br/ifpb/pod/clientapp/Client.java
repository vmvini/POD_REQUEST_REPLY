package br.ifpb.pod.clientapp;

import java.rmi.RemoteException;
import java.util.Scanner;

import br.ifpb.pod.sender.Sender;
import br.ifpb.pod.sender.SenderImpl;

public class Client {

	private Sender sender;
	private Scanner s;
	
	public Client(){
		s = new Scanner(System.in);
	}
	
	public void setSender(Sender sender) {
		this.sender = sender;
		
	}

	public void runConsole() {
			Thread t = new Thread(){
			
			public void run(){
				while(true){
					
					System.out.println("Digite sua msg: ");
					String msg = s.nextLine();
					try {
						sender.sendMessage(msg);
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
		};
		
		t.start();
	}
	
	public void executeAll(){
		
		checkConnection();
		checkTimeoutMsg();
		checkSlowConnection();
		checkSuccessDelivery();
		runConsole();
	}
	
	
	public void checkSuccessDelivery(){
		Thread t = new Thread(){
			
			public void run(){
				while(true){
					String resp;
					try {
						resp = sender.hasSuccessDelivery();
						if ( resp != null ){
							System.out.println("mensagem enviada");
							System.out.println("reposta: " + resp);
						}
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					
						
					
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
		};
		
		t.start();
	}
	
	
	public void checkConnection(){
		Thread t = new Thread(){
			
			public void run(){
				while(true){
					
					try {
						if ( !sender.isConnected() )
							System.out.println("Conexão caiu!");
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
		};
		
		t.start();
	}
	
	public void checkTimeoutMsg(){
		
		Thread t = new Thread(){
					
			public void run(){
				while(true){
					try {
						if( sender.isTimeout() )
							System.out.println("Sua mensagem nao foi enviada. Enviando novamente");
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
					
		};
				
		t.start();
		
	}
	
	public void checkSlowConnection(){
		
		Thread t = new Thread(){
			
			public void run(){
				while(true){
					try {
						if( sender.isSlow() ){
							System.out.println("Sua conexao está lenta!");
						}
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}		
			}
					
		};
				
		t.start();
		
	}
	
	
	
	

}
