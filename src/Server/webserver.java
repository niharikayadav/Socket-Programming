package Server;

import java.io.*;
import java.net.*;
/* class webserver will run till the time it gets connection from the client. 
 * webserver listens to the requests from the clients.
 * When a client sends a request to server, it accepts it.
 */
public class webserver {
	
    //A server socket waits for requests to come in over the network.
	private ServerSocket ss;
	
	//constructor to initialize the port which is entered at the time of execution.
	public webserver(int port) throws IOException {
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
				multithreaded t = new multithreaded(nayaclient);
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

	public static void main(String[] args) throws IOException {
		int port = Integer.parseInt(args[0]);
		try {
				webserver w = new webserver(port);
				w.connect();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
