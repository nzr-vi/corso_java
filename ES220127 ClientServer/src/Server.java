import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) {

		try {
			ServerSocket serverSocket = new ServerSocket(9999);
			Socket clientSocket = null;
			BufferedReader in = null;
			PrintWriter out = null;
			
			clientSocket = serverSocket.accept();
			System.out.println("Server: Connessione effettuata tra client e server!");
			InputStreamReader isr = new InputStreamReader(clientSocket.getInputStream());
			in = new BufferedReader(isr);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
