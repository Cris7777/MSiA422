package AppointmentBook;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public abstract class Appointment {
	private String description;
	abstract public void datesinfo();
	abstract public boolean occursOn(int y, int m, int d);
	
	public Appointment() {
		this.description = "";
	}
	
	public Appointment(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String toString() {
		return "Description: " + this.description + "\r";
	}
}

class Onetime extends Appointment {	
	
	int month, day, year;
	SimpleDateFormat sdf;
	Calendar date;
	
	public void datesinfo() {
		
	}
	
	public Onetime() {
		super();
		this.month = 0;
		this.day = 0;
		this.year = 0;
	}
	
	public Onetime(String description, String exactdate) {
		super(description);
		this.sdf = new SimpleDateFormat("MM/dd/yyyy");	
		this.month = Integer.valueOf(exactdate.substring(0,2)).intValue();
		this.day = Integer.valueOf(exactdate.substring(3,5)).intValue();
		this.year = Integer.valueOf(exactdate.substring(6,10)).intValue();
		this.date = new GregorianCalendar(this.year,this.month-1,this.day);	
	}
	
	public String getDatesinfo() {
		return sdf.format(this.date.getTime());
	}
	
	@Override
	public boolean occursOn(int y, int m , int d) {
		Calendar occur = new GregorianCalendar(y,m-1,d);
		int i = this.date.compareTo(occur);
		if (i == 0) {
			boolean check =  true;
			return check;
		}
		
		else {
			boolean check =  false;
			return check;
		}
	}
	
	@Override
	public String toString() {
		return "Type: Onetime" + "\r" + super.toString() + "Dates Info: " + getDatesinfo();
	}
	
	
}

class Daily extends Appointment {
	
	int smonth, sday, syear, emonth, eday, eyear;
	SimpleDateFormat sdf;
	Calendar sdate, edate;
	
	public void datesinfo() {
		
	}
	
	public Daily() {
		super();
		this.smonth = 0;
		this.sday = 0;
		this.syear = 0;
		this.emonth = 0;
		this.eday = 0;
		this.eyear = 0;
	}
	
	public Daily(String description, String startdate, String enddate) {
		super(description);
		this.sdf = new SimpleDateFormat("MM/dd/yyyy");	
		this.smonth = Integer.valueOf(startdate.substring(0,2)).intValue();
		this.sday = Integer.valueOf(startdate.substring(3,5)).intValue();
		this.syear = Integer.valueOf(startdate.substring(6,10)).intValue();
		this.emonth = Integer.valueOf(enddate.substring(0,2)).intValue();
		this.eday = Integer.valueOf(enddate.substring(3,5)).intValue();
		this.eyear = Integer.valueOf(enddate.substring(6,10)).intValue();
		this.sdate = new GregorianCalendar(this.syear,this.smonth-1,this.sday);	
		this.edate = new GregorianCalendar(this.eyear,this.emonth-1,this.eday);	
	}
	
	public String getDatesinfo() {
		return sdf.format(this.sdate.getTime()) + " to " + sdf.format(this.edate.getTime());
	}
	
	@Override
	public boolean occursOn(int y, int m, int d) {
		Calendar date = new GregorianCalendar(y,m-1,d);
		List<Calendar> alldates = new ArrayList<>();
		Calendar s = this.sdate;
		int d1 = this.sday;
		while(s.compareTo(this.edate) < 0){
			alldates.add(s);
			s = new GregorianCalendar(this.syear,this.smonth-1,d1++);
		}
		int count = 0;
		for (Calendar day : alldates) {
			if(date.compareTo(day) == 0) {
				count++;
			}
		}
		if(count != 0) {
			boolean check = true;
			return check;
		}
		else {
			boolean check = false;
			return check;
		}
	}
	
	@Override
	public String toString() {
		return "Type: Daily" + "\r" + super.toString() + "Dates Info: " + getDatesinfo();
	}
}

class Monthly extends Appointment {	
	
	int day, smonth, syear, emonth, eyear;
	SimpleDateFormat sdf;
	Calendar sdate, edate;
	
	public void datesinfo() {
		
	}
	
	public Monthly() {
		super();
		day = 0;
		smonth = 0;
		syear = 0;
		emonth = 0;
		eyear = 0;
		
	}
	
	public Monthly(String description, String day, String start, String end) {
		super(description);
		this.sdf = new SimpleDateFormat("MM/dd/yyyy");
		this.day = Integer.valueOf(day.substring(0,2)).intValue();
		this.smonth = Integer.valueOf(start.substring(0,2)).intValue();
		this.syear = Integer.valueOf(start.substring(3,7)).intValue();
		this.emonth = Integer.valueOf(end.substring(0,2)).intValue();
		this.eyear = Integer.valueOf(end.substring(3,7)).intValue();
		this.sdate = new GregorianCalendar(this.syear,this.smonth-1,this.day);
		this.edate = new GregorianCalendar(this.eyear,this.emonth-1,this.day);
		
	}
	
	public String getDatesinfo() {
		return sdf.format(this.sdate.getTime()) + " to " + sdf.format(this.edate.getTime());
		//return sdf.format(this.sdate.getTime());
	}
	
	@Override
	public boolean occursOn(int y, int m, int d) {
		Calendar date = new GregorianCalendar(y,m-1,d);
		List<Calendar> alldates = new ArrayList<>();
		Calendar s = this.sdate;
		int m1 = this.smonth-1;
		while(s.compareTo(this.edate) < 0){
			alldates.add(s);
			s = new GregorianCalendar(this.syear,m1++,this.day);
		}
		int count = 0;
		for (Calendar day : alldates) {
			if(date.compareTo(day) == 0) {
				count++;
			}
		}
		if(count != 0) {
			boolean check = true;
			return check;
		}
		else {
			boolean check = false;
			return check;
		}
	}
	
	@Override
	public String toString() {
		return "Type: Monthly" + "\r" + super.toString() + "Dates Info: " + getDatesinfo();
	}
	
}
