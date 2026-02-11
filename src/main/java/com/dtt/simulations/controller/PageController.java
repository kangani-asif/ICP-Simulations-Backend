package com.dtt.simulations.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class PageController {

    @Value("${portal.url}")
    String url;

    @Value("${request.uri}")
    String requestUri;


    @Value("${verify.uri}")
    String verifyUri;

    @Value("${verify.vp.token}")
    String verifyVpToken;

    @Value("${project.name}")
    String projectName;

    @Value("${exchange.currency}")
    String exchangeCurrency;


    @Value("${title.for.claims.pid}")
    String titleForClaimsPid;

    @Value("${title.for.claims.mdl}")
    String titleForClaimsMdl;

    @Value("${attribute.name.mdl}")
    String attributeNameMdl;

    @Value("${attribute.name.pid}")
    String attributeNamePid;






    @GetMapping("/")
    public ModelAndView index()
    {
        return new ModelAndView("index");


    }

    @GetMapping("/kepass")
    public ModelAndView kePass()
    {
        return new ModelAndView("index_kenya");


    }

    @GetMapping("/kepass-hotel")
    public ModelAndView kePassHotel(Model model)

    {
        model.addAttribute("url",url);
        return new ModelAndView("hotel_kenya");


    }

    @GetMapping("/kepass-car")
    public ModelAndView kePassCar(Model model)

    {
        model.addAttribute("url",url);
        return new ModelAndView("car_kenya");


    }

    @GetMapping("/kepass-bank")
    public ModelAndView kePassBank(Model model)

    {
        model.addAttribute("url",url);
        return new ModelAndView("bank_kenya");


    }

    @GetMapping("/hotel")
    public ModelAndView hotel(Model model)
    {
        model.addAttribute("url",url);
        model.addAttribute("verifyUri",verifyUri);
        model.addAttribute("requestUri",requestUri);
        model.addAttribute("titleForClaimsPid",titleForClaimsPid);
        model.addAttribute("attributeNamePid",attributeNamePid);
        return new ModelAndView("hotel");


    }

    @GetMapping("/moneyExchange")
    public ModelAndView moneyExchange(Model model)
    {
        model.addAttribute("url",url);
        model.addAttribute("verifyUri",verifyUri);
        model.addAttribute("requestUri",requestUri);
        model.addAttribute("projectName",projectName);
        model.addAttribute("exchangeCurrency",exchangeCurrency);
        model.addAttribute("titleForClaimsPid",titleForClaimsPid);
        model.addAttribute("attributeNamePid",attributeNamePid);
        return new ModelAndView("moneyExchange");


    }

    @GetMapping("/bank")
    public ModelAndView bank(Model model)
    {
        model.addAttribute("url",url);
        model.addAttribute("verifyUri",verifyUri);
        model.addAttribute("requestUri",requestUri);
        model.addAttribute("projectName",projectName);
        model.addAttribute("titleForClaimsPid",titleForClaimsPid);
        model.addAttribute("attributeNamePid",attributeNamePid);
        return new ModelAndView("bank");


    }


    @GetMapping("/carRental")
    public ModelAndView carRental(Model model)
    {
        model.addAttribute("url",url);
        model.addAttribute("verifyUri",verifyUri);
        model.addAttribute("requestUri",requestUri);
        model.addAttribute("projectName",projectName);
        model.addAttribute("titleForClaimsPid",titleForClaimsPid);
        model.addAttribute("attributeNamePid",attributeNamePid);
        model.addAttribute("titleForClaimsMdl",titleForClaimsMdl);
        model.addAttribute("attributeNameMdl",attributeNameMdl);
        return new ModelAndView("carRental");


    }
    @GetMapping("/hospital")
    public ModelAndView hospital(Model model)
    {
        model.addAttribute("url",url);
        model.addAttribute("verifyUri",verifyUri);
        model.addAttribute("requestUri",requestUri);
        return new ModelAndView("hospitalInsurance");


    }

    @GetMapping("/admission")
    public ModelAndView admissionSchool(Model model)
    {
        model.addAttribute("url",url);
        model.addAttribute("verifyUri",verifyUri);
        model.addAttribute("requestUri",requestUri);
        return new ModelAndView("admission");


    }

    @GetMapping("/financial-transactions")
    public ModelAndView poa(Model model)
    {
        model.addAttribute("url",url);
        model.addAttribute("verifyUri",verifyUri);
        model.addAttribute("requestUri",requestUri);
        model.addAttribute("verifyVpToken",verifyVpToken);
        model.addAttribute("projectName",projectName);
        return new ModelAndView("financialTransaction");


    }


}
