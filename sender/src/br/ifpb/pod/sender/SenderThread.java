package br.ifpb.pod.sender;

import java.util.Date;

import br.ifpb.pod.receiver.Receiver;

public class SenderThread extends Thread {

	private String msg;
	private SenderImpl sender;
	private Receiver receiver;
	
	public SenderThread(String msg, SenderImpl sender, Receiver receiver){
		this.sender = sender;
		this.msg = msg;
		this.receiver = receiver;
	}
	
	public void send(){
		try{
			String msgresp = receiver.receive(msg);
			sender.logSuccessDelivery(msgresp);
			System.out.println("recebeu: " + msgresp);
		}
		catch(Exception e){
			sender.addTimeoutMsg(msg);
			System.out.println("timeOut ao enviar mensagem");
			send();
		}
	}
	
	public void run(){
		send();
	}
	
}
