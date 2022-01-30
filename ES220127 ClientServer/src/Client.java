import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

	public static void main(String[] args) {

		try {
			InetAddress addr = InetAddress.getByName(null);
			Socket socket = null;
			BufferedReader in = null;
			PrintWriter out = null;
			
			socket = new Socket(addr,9999);
			System.out.println("");

		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
