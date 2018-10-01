package com.mycomp.code;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mycomp.code.Process.DateProcess;


public class TestDateProcess{
	private DateProcess dateprocess;
	
	@Before
	public void init() {
		dateprocess=new DateProcess();
	}
	
	@Test
	public void getYear() {
		assertEquals(dateprocess.getAge(LocalDate.of(2000, 11, 5)),17);
		assertEquals(dateprocess.getAge(LocalDate.of(2000, 11, 5)),17);
		assertEquals(dateprocess.getAge(LocalDate.of(2000, 8, 10)),18);
		assertEquals(dateprocess.getAge(LocalDate.of(1997, 11, 28)),20);
	}
	
	@Test
	public void getDay() {
		assertEquals(dateprocess.getDay(LocalDate.of(2000, 10, 1)),1);
		assertEquals(dateprocess.getDay(LocalDate.of(2000, 10, 3)),3);
		assertEquals(dateprocess.getDay(LocalDate.of(2000, 9, 30)),0);
		assertEquals(dateprocess.getDay(LocalDate.of(1997, 7, 11)),20);
	}
	
	@Test
	public void Valid() {
		assertEquals(dateprocess.validDate("05-11-2000"),LocalDate.of(2000, 11, 5));
		assertEquals(dateprocess.validDate("05/11/2000"),LocalDate.of(2000, 11, 5));
		assertEquals(dateprocess.validDate("05/11/2000"),LocalDate.of(2000, 11, 5));
		assertEquals(dateprocess.validDate("05/11/98"),LocalDate.of(1998, 11, 5));
	}
}
