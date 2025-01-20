package com.example.cinemamng.controller;

import com.example.cinemamng.model.Promotion;
import com.example.cinemamng.service.IPromotionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Controller
@RequestMapping("/")
public class IndexController {

    @Autowired
    private IPromotionService promotionService;

    @RequestMapping("/")
    public String index(
            @RequestParam(value="discount" , required = false,defaultValue = "0") int discount,
            @RequestParam(value="startDate" , required = false,defaultValue = "0") String startDate,
            @RequestParam(value="endDate" , required = false,defaultValue = "0") String endDate,
            Model model){
        System.out.println("discount: " + discount);

            if(discount == 0){
                discount = 0;
            }

            if(startDate.equals("0")){
                startDate = null;
            }
            if(endDate.equals("0")){
                endDate = null;
            }
            List<Promotion> promotionList = promotionService.searchPromotions(discount, startDate, endDate);
            model.addAttribute("promotions", promotionList);


        List<Promotion> startDateList = promotionService.findDistinctStartDate();
        List<Promotion> endDateList = promotionService.findDistinctEndDate();

        model.addAttribute("startDateList", startDateList);
        model.addAttribute("endDateList", endDateList);
        return "index";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam(value = "id",required = false,defaultValue = "0") Long id){
        if(id == 0){
            return "redirect:/";
        }
        promotionService.delete(id);
        return "redirect:/";
    }

    @GetMapping("/create")
    public String showCreate(Model model){
        Promotion promotion = new Promotion();
        model.addAttribute("promotion", promotion);
        return "create";
    }

    @PostMapping("/create")
    public String create(@Valid  @ModelAttribute Promotion promotion,
                        BindingResult bindingResult,
                         Model model){
        if(bindingResult.hasErrors()){
            return "create";
        }

        promotionService.save(promotion);
        return "redirect:/";
    }
}
