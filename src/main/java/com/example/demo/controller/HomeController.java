package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.SalaryModel;
import com.example.demo.service.SalaryService;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class HomeController {
	@Autowired
	private SalaryService salaryService;

  @GetMapping("/")
  public ModelAndView getMethodName(HttpServletRequest request) {
    ModelAndView mav = new ModelAndView("index");
    return mav;
  }
  

  @RequestMapping(value="/home", method=RequestMethod.GET)
  public ModelAndView goHome(HttpServletRequest request) {
    ModelAndView mav = new ModelAndView("content/home");
  
    List<SalaryModel> salaryList = salaryService.getSalary();
    
    mav.addObject("salaryList", salaryList);
        
    return mav;
  } 

  @GetMapping("/reflected")
  public ModelAndView reflect(@RequestParam(required = false, defaultValue = "") String input) {
    ModelAndView mav = new ModelAndView("content/reflected");
    mav.addObject("input", input);
    return mav;
  }

  @PostMapping("/reflected")
  public ModelAndView reflect2(@RequestParam(required = false, defaultValue = "") String input) {
    ModelAndView mav = new ModelAndView("content/reflected");
    mav.addObject("input", input);
    return mav;
  }

  @GetMapping("/write")
  public ModelAndView write(@RequestParam(required = false, defaultValue = "") String input) {
    ModelAndView mav = new ModelAndView("content/write");
    mav.addObject("input", input);
    return mav;
  }

  @PostMapping("/done")
  public ModelAndView done(HttpServletRequest request) {
    ModelAndView mav = new ModelAndView("index");
    System.out.println(request.getParameter("id")+request.getParameter("content")+request.getParameter("name"));
    SalaryModel salaryModel = SalaryModel.builder().id((long) Integer.parseInt(request.getParameter("id"))).name(request.getParameter("name")).content(request.getParameter("content")).build();
    
    mav.addObject("input", request);
    salaryService.insert(salaryModel);
    return mav;
  }
}
