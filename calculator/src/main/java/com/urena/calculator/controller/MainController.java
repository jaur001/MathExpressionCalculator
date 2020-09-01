package com.urena.calculator.controller;

import com.urena.calculator.operation.Operation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Objects;

@Controller
@RequestMapping("/calculator")
public class MainController {

    private static Operation operation;
    @GetMapping("/")
    public String initCalculator(Model model){
        model.addAttribute("operation", Objects.requireNonNullElseGet(operation, Operation::new));
        return "calculator";
    }

    @PostMapping("/calculation")
    public String calculate(@ModelAttribute Operation operation){
        MainController.operation = operation;
        return "redirect:/calculator/";
    }
}
