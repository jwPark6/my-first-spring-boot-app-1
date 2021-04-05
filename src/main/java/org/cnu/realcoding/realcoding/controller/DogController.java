package org.cnu.realcoding.realcoding.controller;

import org.cnu.realcoding.domain.Dog;
import org.cnu.realcoding.service.DogManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DogController {

    @Autowired
    private DogManagementService dogManagementService;

    @PostMapping("/dogs")
    @ResponseStatus(HttpStatus.CREATED)
    public void createDogs(@RequestBody Dog dog){
        dogManagementService.insertDog(dog);
    }

    @GetMapping("/dogs")
    public List<Dog> getAllDogs(){
        return dogManagementService.getDogs();
    }

    // RequestParam -> localhost:8001/dogs?name=ian
    // PathVariable -> localhost:8001/
    @ResponseStatus
    @GetMapping("/dogs/{name}")
    public Dog getDogByName(@PathVariable String name) {
        return dogManagementService.getDogByName(name);
    }
}
