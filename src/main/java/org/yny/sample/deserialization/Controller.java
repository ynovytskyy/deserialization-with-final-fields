package org.yny.sample.deserialization;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @PostMapping("/echo")
    public DomainClass echo(@RequestBody DomainClass input) {
        return new DomainClass(input.getStr() + "-echo", input.getI() + 10);
    }
}
