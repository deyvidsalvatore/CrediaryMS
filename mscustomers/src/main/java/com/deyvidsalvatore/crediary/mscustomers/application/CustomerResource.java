package com.deyvidsalvatore.crediary.mscustomers.application;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("customers")
public class CustomerResource {

    @GetMapping
    public String status() {
        return "Ok";
    }
}
