package com.mycomp.code.Controller;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.servlet.http.HttpServletRequest;

import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mycomp.code.InfoMaven.InfMaven;
import com.sun.xml.internal.fastinfoset.sax.Properties;
import com.mycomp.code.Process.DateProcess;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	private static final String Name="Yan Efimov";
	
	@Autowired
	private DateProcess dateprocess;
	
	@Autowired
	private InfMaven infmaven;
	
	@PostConstruct
	public void init() {
		logger.info("Servlet context is created.");
	}
	
	@PreDestroy
	public void destroy() {
		logger.info("Servlet context is about to be shut down.");
	}
	private void insert_data(Locale locale,Model model) throws IOException {
		model.addAttribute("message",Name);
	}
	
	/**
	 * Simply selects the home view to render by returning its name.
	 * @throws IOException 
	 */
	@RequestMapping(value = {"/","/exit"}, method = RequestMethod.GET)
	public String home(Locale locale, Model model) throws IOException {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		insert_data(locale,model);
		
		return "home";
	}
	
	@RequestMapping(value = "dateProcess", method=RequestMethod.POST)
	public String DateProcess(Locale locale, Model model, HttpServletRequest request) throws ParseException, IOException {
		logger.info("Request in server!!!!");
		String date=request.getParameter("date");
		LocalDate dat = dateprocess.validDate(date);
		if (dat==null) {
			insert_data(locale,model);
			return "error";
		}
		model.addAttribute("age",dateprocess.getAge(dat));
		model.addAttribute("day",dateprocess.getDay(dat));
		return "result";
	}
}
