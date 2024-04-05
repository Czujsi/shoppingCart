package org.example.user_interfaces.web_interface;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/homepage")
@RequiredArgsConstructor
public class WebInterface {
    @GetMapping
    public String hello() {
        return "Hello";
    }


}
