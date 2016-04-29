package br.ifpb.pod.sender;

import java.net.SocketTimeoutException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.ifpb.pod.pingclient.PingClient;
import br.ifpb.pod.pingclient.PingClientImpl;
import br.ifpb.pod.receiver.Receiver;
import java.util.concurrent.TimeUnit;

public class SenderImpl extends UnicastRemoteObject implements Sender {

	private Receiver receiver;
	private PingClientImpl pg;
	private List<String> deliveredMessage;
	
	private Map<String, String> timeoutMsg; //mensagem n enviada , msg de timeout
	
	
	public void addTimeoutMsg(String msg){
		timeoutMsg.put(msg, "timeOut ao enviar mensagem; date: " + new Date());
	}
	
	
	public void logSuccessDelivery(String msg){
		deliveredMessage.add("envio confirmado: " + msg + " data: " + new Date());
	}
	
	protected SenderImpl(Receiver receiver, PingClientImpl pg) throws RemoteException {
		super();
		this.receiver = receiver;
		this.pg = pg;
		timeoutMsg = new HashMap<String, String>();
		deliveredMessage = new ArrayList();
	}
	
	public Map<String, String> getTimeoutMsg(){
		return timeoutMsg;
	}

	
	
	public String sendMessage(final String msg) throws RemoteException {
		
		SenderThread st = new SenderThread(msg, this, receiver);
		st.start();
		return null;
	}
	
	
	public String hasSuccessDelivery(){
		int lastPos = deliveredMessage.size() - 1;
		if(lastPos >= 0){
			String last = deliveredMessage.get( lastPos  );
			deliveredMessage.remove(lastPos);
			return last;
		}
		return null;
	}
	
	public boolean isConnected(){
		if ( pg.hasDisconnectMessage() ){
			pg.checkWithoutConnectionMsg();
			return true;
		}
		return false;
	}
	
	
	public boolean isTimeout(){
		int size = timeoutMsg.size();
		if(size > 0){
			timeoutMsg.remove(size - 1);
			return true;
		}
		return false;
	}
	
	public boolean isSlow(){
		
		if( pg.hasSlowConnection() ){
				
			pg.checkSlowConnectionMsg();
			return true;
		}
		return false;
		
	}
	

}
