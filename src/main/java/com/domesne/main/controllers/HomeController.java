package com.domesne.main.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.TreeMap;

import static com.domesne.main.helpers.ReadCSV.readCSVFile;

@Controller
public class HomeController {
    private static ArrayList<TreeMap<String,String>> feedback;
    private static ArrayList<TreeMap<String,String>> projects;
    private static Boolean isFeedbackLoaded = false;
    private static Boolean isProjectsLoaded = false;

    @GetMapping
    public String index(Model model){
        getFeedback("src/main/resources/static/feedback/feedback.csv");
        getProjects("src/main/resources/static/projects/projects.csv");
        model.addAttribute("feedback",feedback);
        model.addAttribute("projects",projects);
        return "index";
    }
    @GetMapping("maker")
    public String maker(){
        return "maker/index";
    }
    @GetMapping("guess")
    public String guess(){
        return "guess/index";
    }
    @GetMapping("flaming")
    public String flaming(){
        return "flaming/index";
    }

    private static void getFeedback(String aPathToCSVFile){
        // Only load data once
        if (isFeedbackLoaded) {
            return;
        }
        feedback = readCSVFile(aPathToCSVFile);
        isFeedbackLoaded = true;
    }
    private static void getProjects(String aPathToCSVFile){
        if (isProjectsLoaded) {
            return;
        }
        projects = readCSVFile(aPathToCSVFile);
        isProjectsLoaded = true;
    }

}
