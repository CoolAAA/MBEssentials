package plugins.mbes.managers;

import java.util.ArrayList;
import plugins.mbes.misc.Report;

public class ReportManager{
	private ArrayList<Report>report = new ArrayList<Report>();
	
	public void newReport(Report e){
		e.setId(report.size());
		
		report.add(e);
	}
	
	public Report getReport(Report e){
		int idd = report.indexOf(e);
		
		if(idd == -1)
			return null;
		
		return report.get(idd);
	}
	
	public boolean delReport(Report e){
		return report.remove(e);
	}
	
	public Report[] getAll(){
		if(report.isEmpty())
			return null;
		return (Report[])report.toArray();
	}
}
