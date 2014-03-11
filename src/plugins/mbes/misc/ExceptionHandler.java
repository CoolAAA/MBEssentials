package plugins.mbes.misc;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class ExceptionHandler extends Handler{
	
	@Override
	public void close() throws SecurityException {
		
	}

	@Override
	public void flush() {
		
	}

	@Override
	public void publish(LogRecord record) {
		if(record.getThrown() != null)
		{
			File errfile = new File("plugin\\MBEssentials\\crash" + new SimpleDateFormat("dd_MMM_yy_HH_mm_ss").format(new Date()) + ".txt");
			try {
				errfile.createNewFile();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				PrintWriter err = new PrintWriter(errfile);
				
				record.getThrown().printStackTrace(err);
				err.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}	
		}

	}
}
