package com.urena.calculator.rest;

import com.urena.calculator.operation.Operation;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class MainRestController {

    @GetMapping("/calculator/{operation}")
    public Operation getResult(@PathVariable String operation){
        return new Operation(operation);
    }
}
