package com.prasad.java;

class Sender{
	public void send(String msg) {
		System.out.println("Sending  "+"\t"+msg);
		try {
			Thread.sleep(2000);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println(msg+" sent");
	}
}
class ThreadSafe extends Thread{
	private String msg;
	Sender sender;
	ThreadSafe(String msg , Sender sender){
		this.msg = msg;
		this.sender = sender;
	}
	@Override
	public void run() {
		synchronized(sender) {
			sender.send(msg);
		}
	}
}


public class SynchronizationExample {

	public static void main(String[] args) {
		        Sender sender = new Sender();
                ThreadSafe ts1= new ThreadSafe("Hi",sender);
                ThreadSafe ts2= new ThreadSafe("Bye",sender);
                ts1.start();
                ts2.start();
                try {
                	ts1.join();
                	ts2.join();
                }
                catch(Exception e) {
                	e.printStackTrace();
                }
	}

}
