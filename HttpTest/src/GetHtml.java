import java.io.*;
import java.net.*;

public class GetHtml{
	
	public static void main(String[] args){
		try {
			String hostname = "www.baidu.com";
			int port = 80;
			
			System.out.println("Connecting to server on port " + port);
			Socket connectionSock = new Socket(hostname, port);
			
			BufferedReader serverInput = new BufferedReader(
					new InputStreamReader(connectionSock.getInputStream()));
			DataOutputStream  serverOutput = new DataOutputStream(
					connectionSock.getOutputStream());
			
			System.out.println("Connection made, sending request.");
			serverOutput.writeBytes("GET / HTTP/1.1\nHost:" + hostname +"\n\n");
			
			System.out.println("Waiting for reply.\n\n");
			
			String serverData = serverInput.readLine();
			while(serverData.length() >= 0)
			{	
				serverData = serverInput.readLine();
				System.out.println(serverData);
				
				System.out.println("\nReceive!");
			}
			
			serverOutput.close();
			serverInput.close();
			connectionSock.close();
			
			System.out.println("\n\nReceive complete!");
		}catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}
