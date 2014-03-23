package plugins.mbes.misc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.mbserver.api.Server;

public class WorldBackup {

	public static void Backup(Server s) throws IOException{
		File worlds = new File("worlds");
		String date = new SimpleDateFormat("dd_MMM_yy_HH_mm_ss").format(new Date());
		
		for(File e: worlds.listFiles())
		{
			s.getLogger().info("[MBE] Backing up world '" + e.getName() + "'");
			
			if(!e.isDirectory())
				continue;
			
			for(File f : e.listFiles())
			{
				if(!f.getName().equals("info.world"))
					break;
				
				WorldBackup.copyFile(f,new File(String.format("world-backups\\%s\\%s\\info.world",e.getName(),date)));
				
				new File(String.format("world-backups\\%s\\%s\\regions",e.getName(),date)).mkdirs();
				
				for(File region : new File(String.format("worlds\\%s\\regions",e.getName())).listFiles())
				{
					if(!region.getName().endsWith(".region"))
						continue;
					
					WorldBackup.copyFile(region,new File(String.format("world-backups\\%s\\%s\\regions\\%s",e.getName(),date,region.getName())));
					
				}
				
			}
			
			s.getLogger().info("[MBE] Finished backing up world '" + e.getName() + "'");
		}
	}
	
	public static void copyFile(File src,File dest) throws IOException
	{
		if(!dest.exists())
			dest.createNewFile();
		
		FileChannel in,out;
		
		in = new FileInputStream(src).getChannel();
		out = new FileOutputStream(dest).getChannel();
		
		
		out.transferFrom(in,0,in.size());
		
		in.close();
		out.close();
	}
	
}
