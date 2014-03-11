package plugins.mbes.misc;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;


public class Downloader {
	
	public static File downloadFile(String fileUrl,String path) throws IOException
	{
		URL url = new URL(fileUrl);
		
		InputStream in = url.openStream();
		FileOutputStream out = new FileOutputStream(new File(path));
		
		byte[] buffer = new byte[4026];
		int bRead = 0;
		while( (bRead = in.read(buffer)) != -1)
		{
			out.write(buffer,0,bRead);
		}
		
		out.close();
		in.close();
		
		return new File(path);
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
