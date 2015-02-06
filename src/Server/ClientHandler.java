package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;

// Multithreaded function is a Thread 
public class ClientHandler extends Thread {

	private Socket clientThread;
	
	public ClientHandler(Socket clientThread)
	{
		this.clientThread = clientThread;
	}
	
	@Override
	public void run() {

		DataInputStream in;
		DataOutputStream ou;

		try {
			Socket localSock = clientThread;
			in = new DataInputStream(localSock.getInputStream());
			String expression = in.readUTF();
			System.out.println("Expression Server recieved from client -" + expression);
			MathematicalCalc mm = new MathematicalCalc(expression);
			ou = new DataOutputStream(localSock.getOutputStream());
			ou.writeUTF("Result:" + mm.calculate());
			//run method is called again so that all the expressions from the client can be handled
			run();
		} catch(EOFException eof) {
				//System.out.println("Disconnected with one client");
		} catch (IOException e) {
				e.printStackTrace();
		} 
	}
}
