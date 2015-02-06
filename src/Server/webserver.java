package Server;

import java.io.*;
import java.net.*;

public class webserver {
	/* 
	 * 
	 */

	private ServerSocket ss;

	public webserver(int port) throws IOException {
		ss = new ServerSocket(port);
	}

	public void connect() {
		while (true) {
			try {
				System.out.println("Waiting for Client on port " + ss.getLocalPort() + "...");
				Socket nayaclient = ss.accept();
				System.out.println("Just Connected to " + nayaclient.getRemoteSocketAddress());
				multithreaded t = new multithreaded(nayaclient);
				t.start();
			}

			catch (SocketTimeoutException s1) {
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
