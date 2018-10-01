package com.mycomp.code.Process;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.Year;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

@Component
public class DateProcess {
	
	private static final int minAge=2015;
	private static final Map<Integer,Integer> map;
	private static LocalDate datenow=LocalDate.now();
	
	static {
		map=new HashMap<Integer,Integer>();
		map.put(1,31);
		map.put(2,28);
		map.put(3,31);
		map.put(4,30);
		map.put(5,31);
		map.put(6,30);
		map.put(7,31);
		map.put(8,31);
		map.put(9,30);
		map.put(10,31);
		map.put(11,30);
		map.put(12,31);
	}
	
	private LocalDate getDate(String date) {
		LocalDate data=null;
		int[] mas=new int[3]; int n=0;  
		Pattern p = Pattern.compile("\\d+");
		Matcher m = p.matcher(date);
		while (m.find())
			mas[n++]=Integer.parseInt(m.group());
		if (mas[2]<100) mas[2]=1900+mas[2];
		if (mas[1]>0&&mas[1]<13&&mas[0]<=map.get(mas[1])&&mas[2]<=minAge) //mas[0]-день mas[1]-месяц mas[2]-год   заранее извеняюсь за говно код
			data=LocalDate.of(mas[2], mas[1], mas[0]);
		return data;
	}
	
	public LocalDate validDate(String date) {
        LocalDate data=null;
		Pattern p1 = Pattern.compile("(0?[1-9]|[12][0-9]|3[01])-(0?[1-9]|1[012])-(((19|20)\\d\\d)|(\\d\\d))");
        Pattern p2 = Pattern.compile("(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/(((19|20)\\d\\d)|(\\d\\d))");
        Matcher m1 = p1.matcher(date);
        Matcher m2 = p2.matcher(date);
        if (m1.matches()||m2.matches()) {
        	data=getDate(date);
        }
        return data;  
	}
	
	public int getAge(LocalDate date){
		return Period.between(date,datenow).getYears();
	}
	
	public int getDay(LocalDate date){
		int day=(int)ChronoUnit.DAYS.between(LocalDate.now(),LocalDate.of(LocalDate.now().getYear(), date.getMonthValue(), date.getDayOfMonth()));
		if (day<0) {
			day=Year.now().length()+day;
		}
		return day;
	}
}
