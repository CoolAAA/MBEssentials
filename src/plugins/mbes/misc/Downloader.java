package plugins.mbes.misc;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.util.Scanner;


public class Downloader {
	
	@SuppressWarnings("resource")
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
	
	@SuppressWarnings("resource")
	public static boolean checkUpdate(final String pluginUrl,final String versionUrl
			,final String pPath,final String vPath,final float version) throws IOException
	{
		
		File vFile = Downloader.downloadFile(versionUrl,vPath);
		float vnum = 0;
		Scanner scan = new Scanner(vFile);
		
		try{
			 vnum = scan.nextFloat();
		}catch(Exception e){
			e.printStackTrace();
			scan.close();
			return false;
		}
		
		if(version < vnum)
		{
			Downloader.downloadFile(pluginUrl,pPath);
			Downloader.downloadFile("http://mbessentials.bl.ee/update/whatsnew.txt", "plugins/MbEssentials/Data/wn.dat");
			return true;
		}
		
		scan.close();
		return false;
	}
	
	@SuppressWarnings("resource")
	public static boolean checkUpdateNoDownload(final float version) throws IOException{
		
		File vFile2 = Downloader.downloadFile("http://mbessentials.bl.ee/update/version.txt","plugins/MbEssentials/Data/ver.dat");
		float vnum2 = 0;
		Scanner scan = new Scanner(vFile2);
		
		try{
			 vnum2 = scan.nextFloat();
		}catch(Exception e){
			e.printStackTrace();
			scan.close();
			return false;
		}
		
		if(version < vnum2)
		{
			return true;
		}
		
		scan.close();
		return false;
		}
	

}
