package Client;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class webclient {
	
	public static void clientOperation(Socket client) {
		String expr = "ok";
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		DataOutputStream o = null;
		DataInputStream it = null;
		try {
			//Here we are telling client where the data can be written
			OutputStream out = client.getOutputStream();
			o = new DataOutputStream(out);
			//Here we are telling client where the data can be read
			InputStream in = client.getInputStream();
			it = new DataInputStream(in);
		}catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Please enter expression for calculation with spaces. "
				+ "First enter the operator and then the operands.\n" 
				+ "Please type 'quit' to QUIT!! OR type 'ok' to continue with another calcultion");
		expr = input.nextLine();
		
		
		while(!expr.equals("quit")) {
			try {
				System.out.println("The expression recieved by the client is:"+ expr);
				if (expr.length() != 0) {
				o.writeUTF(expr);
				}
				System.out.println("Server says " + it.readUTF());
			}catch (IOException e) {
					e.printStackTrace();
			}
			System.out.println("Expression:");
			expr = input.nextLine();
		}
	}
		
	public static void main(String args[]) {
		
		/*Two command line arguments are taken one to know the server client wants to connect to 
		*and second the port
		*/
		String servername = args[0];
		int port = Integer.parseInt(args[1]);
		
		try {
		System.out.println("Connecting to " + servername + " on port " + port);
		Socket client = new Socket(servername, port);
		System.out.println("Just connected to " + client.getRemoteSocketAddress());
		webclient.clientOperation(client);
		client.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
