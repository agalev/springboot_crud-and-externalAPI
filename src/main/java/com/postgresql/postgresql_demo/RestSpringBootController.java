package com.postgresql.postgresql_demo;

import java.util.List;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class RestSpringBootController {

    @GetMapping("/countries")
    public List<Object> getCountries() {
        String url = "https://restcountries.com/v3.1/all";
        RestTemplate restTemplate = new RestTemplate();

        List<Object> countries = restTemplate.getForObject(url, List.class);
        return countries;
    }
}
