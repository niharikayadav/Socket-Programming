package Server;

import java.io.*;
import java.net.*;

/* This a mathematical client-server based application,
 * where client sends a request to server with an expression,
 * server then calculates the result and sends the result back to client  
 */
public class WebServer {
	
    // a server socket waits for requests to come in over the network.
	private ServerSocket ss;
	
	//constructor to initialize the port which is entered at the time of execution.
	public WebServer(int port) throws IOException {
		ss = new ServerSocket(port);
	}

	/*connect method is doing two jobs- 
	 * 1. accepts requests from clients
	 * 2. initiates a thread for different clients
	 */
	public void connect() {
		
		while (true) {
			
			try {
				System.out.println("Waiting for Client on port " + ss.getLocalPort() + "...");
				Socket nayaclient = ss.accept();
				System.out.println("Just Connected to " + nayaclient.getRemoteSocketAddress());
				ClientHandler t = new ClientHandler(nayaclient);
				t.start();
			} catch (SocketTimeoutException s1) {
				System.out.println("Socket timed out! ");
				break;
			} catch (IOException e) {
				e.printStackTrace();
				break;
			}
		}
	}

	public static void main(String[] args) {
		int port = Integer.parseInt(args[0]);
		try {
			WebServer w = new WebServer(port);
			w.connect();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
