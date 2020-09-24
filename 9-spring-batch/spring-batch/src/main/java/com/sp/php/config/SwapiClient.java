package com.sp.php.config;

import com.sp.php.domain.PeopleDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name ="swapi-client", url = "https://swapi.dev/api/")
public interface SwapiClient {

    @GetMapping( "/people/" )
    PeopleDTO getPeople(@RequestParam(value="search") String name);
}
