package telnetapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;

public class consolereader extends Thread 
{
	boolean keepgoing = true;
	BufferedReader br;
	public consolereader(BufferedReader reader) {
		br = reader;
	}

	public void run()
	{
		//System.out.println("thread iterating");
		while (keepgoing == true)
		{
			try {
				read();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//System.out.println("thread iterating");
		}
	}
	
	   void read() throws IOException
	   {
	     char[] ca = new char[1024];
	     int rc = br.read(ca);
	     String s = new String(ca);

	     Arrays.fill(ca, (char)0);
	     if(rc != -1)
	     {
	     System.out.println("RC=" + rc + ":" + s);
	     }
	   }
	   
	   public void updatescreen()
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
}
