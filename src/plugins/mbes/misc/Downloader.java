package plugins.mbes.misc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.util.Scanner;


public class Downloader {
	
	public static File downloadFile(String fileUrl,String path) throws IOException
	{
		
		URL url = new URL(fileUrl);
		FileChannel out;
		ReadableByteChannel in;
		
		in = Channels.newChannel(url.openStream());
		File ofile = new File(path);
		ofile.createNewFile();
		
		out = new FileOutputStream(ofile).getChannel();
		out.transferFrom(in,0,Long.MAX_VALUE);
		return ofile;
	}
	
	public static boolean checkUpdate(String url[],String path[],final int version) throws IOException
	{
		if(version == -1)
		{
			Downloader.downloadFile(url[1], path[1]);
			return true;
		}
		
		else
		{
			File vfile = Downloader.downloadFile(url[0], path[0]);
			Scanner sc = new Scanner(vfile);
			int num = sc.nextInt();
			sc.close();
			if(version != num)
			{
				return Downloader.checkUpdate(url,path, -1);
			}
			
				return false;
		}
	}

}
