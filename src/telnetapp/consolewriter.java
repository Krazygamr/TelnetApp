package telnetapp;

import java.io.BufferedWriter;
import java.io.IOException;

public class consolewriter extends Thread {
	boolean keepgoing = true;
	BufferedWriter bw;
	public void consolewriter(BufferedWriter out)
	{
		bw = out;
	}
	public void run()
	{
		//System.out.println("thread iterating");
		while (keepgoing == true)
		{
			try {
				write();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				keepgoing = false;
			}
			//System.out.println("thread iterating");
		}
	}
	
	public void write() throws IOException
	{
		
	}
}
