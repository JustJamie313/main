package com.domesne.main.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.TreeMap;

import static com.domesne.main.helpers.ReadCSV.readCSVFile;
import static java.util.Objects.isNull;

@Controller
@RequestMapping("gallery")
public class GalleryController {
    private static ArrayList<TreeMap<String,String>> gallery;
    private static Integer galleryIndex;
    private static Integer viewfinderIndex;
    private static Boolean isGalleryLoaded = false;
    private Boolean viewing = false;
    @GetMapping("/")
    public String index(Model model){
        loadGallery("src/main/resources/static/projects/gallery/gallery.csv");
        model.addAttribute("gallery",gallery);
        if(isNull(galleryIndex)) galleryIndex = generateRandom(gallery.size()-1);
        model.addAttribute("galleryIndex", galleryIndex);
        return "gallery/index";
    }
    @GetMapping("getNext")
    public String getNext(){
        if(galleryIndex == gallery.size()-1) galleryIndex = -1;
        galleryIndex++;
        return "redirect:";
    }
    @GetMapping("getPrevious")
    public String getPrevious(){
        if(galleryIndex == 0) galleryIndex = gallery.size();
        galleryIndex--;
        return "redirect:";
    }
    @GetMapping("viewfinder")
    public String viewfinder(Model model){
        model.addAttribute("gallery",gallery);
        model.addAttribute("viewing",viewing);
        if(isNull(viewfinderIndex)){
            viewfinderIndex = generateRandom(gallery.size()-1);
        } else {
            if(viewfinderIndex == gallery.size()-1){
                viewfinderIndex = 0;
            } else {
                viewfinderIndex++;
            }
        }
        model.addAttribute("index",viewfinderIndex);
        return "gallery/viewfinder/index";
    }
    @PostMapping("viewfinder")
    public String postViewfinder(@RequestParam Boolean aViewing){
        viewing = aViewing;
        return "redirect:";
    }
    private static void loadGallery(String aPathToCSVFile){
        if(isGalleryLoaded) return;
        gallery = readCSVFile(aPathToCSVFile);
        isGalleryLoaded = true;
    }
    private static Integer generateRandom(Integer maxValue){
        return (int)Math.ceil(Math.random()*maxValue);
    }
    public void toggleViewing(){
        if(viewing){
            viewing = false;
        } else {
            viewing = true;
        }
    }
}
