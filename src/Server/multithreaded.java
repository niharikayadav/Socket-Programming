package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

//Multithreaded function is a Thread 
public class multithreaded extends Thread {

	private Socket naya;
	public multithreaded(Socket naya)
	{
		this.naya = naya;
	}
	@Override
	public void run() {
		DataInputStream in;
		try {
			Socket localSock = naya;
			in = new DataInputStream(localSock.getInputStream());
			String expression = in.readUTF();
			System.out.println("Expression Server recieved from client -" + expression);
			MathematicalCalc mm = new MathematicalCalc(expression);
			DataOutputStream ou = new DataOutputStream(localSock.getOutputStream());
			ou.writeUTF("Result:" + mm.calculate());
			//run method is called again so that all the expressions from the client can be handled
			run();
			} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
