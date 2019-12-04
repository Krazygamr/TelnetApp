package telnetapp;
import telnetapp.consolereader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.net.*;
import java.util.*;

//referencing the following tutorial for this project
//http://www.ejbtutorial.com/distributed-systems/hello-world-for-socket-programming-using-java

//research notes:
//seems that cisco takes live key input and echos it back to the client in 2 to 3 character chunks.
//Another thing to note is the customer new line delimiters used by the router to communicate.



public class main {

	   public static void main(String arg[])
	      {
		   String connectedIP;
		   Scanner userinput = new Scanner(System.in);
		   System.out.println("Hello, please tell me what IP you want to connect to:");
		   connectedIP = userinput.next();
	   		char[] outputarray;
		   try {
			   //First we need to create a Socket object to talk over our network port
			   //so we request an IP to telnet to. In this case, since we already know we're
			   //using telnet, we only need to request an IP, and no port number.

			   Socket socketClient = new Socket(connectedIP ,23); //telnet default port is 23
			    System.out.println("Client: "+"Connection Established");
			    BufferedReader reader = 
			    		new BufferedReader(new InputStreamReader(socketClient.getInputStream()));

			    BufferedWriter writer= 
			    		new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
			    System.out.println("Client: "+"Starting reader thread");
			    consolereader ccr = new consolereader(reader);
			    ccr.start();
			    
			    String outputtorouter = null;
			    String serverMsg;
			    //lets check the active interfaces
		   		writer.write("sh ip int brief \n");
		   		writer.flush();
		   		//check version info on the router and active modules as well as serial number
		   		writer.write("sh ver \n");
		   		writer.flush();
		   		//this is pagenated, so we need to send a couple of spaces to the router so it goes through everything.
		   		writer.write("     \n");
		   		writer.flush();
		   		//check actively connected devices.
		   		writer.write("sh arp \r");
		   		writer.flush();
		   		
		   		writer.write("enable \r");
		   		writer.flush();
		   		writer.write("supersuper\r");
		   		writer.flush();
		   		writer.write("sh run \r");
		   		writer.flush();
		   		writer.write("                                    ");
		   		writer.flush();
		 

		   		}
		   		catch(Exception e)
		   		{
		   			e.printStackTrace();
		   		}
		   		
	      }
	   
	   public void updatescreentwo(BufferedReader br)
  		{
	   		String serverMsg;
	   		try
	   		{
		   		while((serverMsg = br.readLine()) != null)
		   		{
					System.out.println(serverMsg);
					System.out.println("iterating");
				} 
	   		}
	   		catch (Exception e)
	   		{
	   			e.printStackTrace();
	   		}

  		}
	   static void read(BufferedReader br) throws IOException
	   {
	     char[] ca = new char[1024];
	     int rc = br.read(ca);
	     String s = new String(ca).trim();

	     Arrays.fill(ca, (char)0);
	     if(rc != -1)
	     {
	     System.out.println("RC=" + rc + ":" + s);
	     }
	   }
	   
}