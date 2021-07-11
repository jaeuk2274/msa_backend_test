package io.naradrama.easyboard;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"envoy"})
public class HealthCheck {
    //
    @GetMapping({"healthCheck"})
    public String healthCheck() {
        //
        return "OK";
    }
}
