package com.socure.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloSpringController {

	@Autowired
	private HelloSpring helloSpring;

	@RequestMapping(value="/helloSpring", method=RequestMethod.GET)
	public void helloWorld() {
		helloSpring.setMessage("Hello Spring from Spring MVC to JSF");
	}

	@RequestMapping(value="/helloSpring", method=RequestMethod.POST)
	public void helloWorld(@RequestParam String msg) {
		helloSpring.setMessage(msg);
	}

	@RequestMapping(value="/hello", method=RequestMethod.GET)
	public void hello() {
		helloSpring.setMessage("dasdfsa");
	}


}
