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
  public ModelAndView reflect2(HttpServletRequest request) {
    ModelAndView mav = new ModelAndView("content/reflected");

    String input = request.getParameter("input");
    if (request.getParameter("level2") != null) {
      input = input.toLowerCase();
    }

    if (request.getParameter("level1") != null) {
      input = input.replaceAll("script","");
    }

    if (request.getParameter("level3") != null) {
      input = input.replaceAll("script","*");
    }
    if (request.getParameter("level4") != null) {
      input = input.replaceAll("\\(","*");
    }

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
    String content = request.getParameter("content");
    String name = request.getParameter("name");

    if (request.getParameter("level2") != null) {
      content = content.toLowerCase();
      name = name.toLowerCase();
    }

    if (request.getParameter("level1") != null) {
      content = content.replaceAll("script","");
      name = name.replaceAll("script","");
    }

    if (request.getParameter("level3") != null) {
      content = content.replaceAll("script","*");
      name = name.replaceAll("script","*");
    }
    if (request.getParameter("level4") != null) {
      content = content.replaceAll("\\(","*");
      name = name.replaceAll("\\(","*");
    }

    SalaryModel salaryModel = SalaryModel.builder().name(name).content(content).build();

    mav.addObject("input", request);
    salaryService.insert(salaryModel);
    return mav;
  }
}
