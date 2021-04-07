package org.cnu.realcoding.controller;

import lombok.Getter;
import org.cnu.realcoding.domain.Dog;
import org.cnu.realcoding.service.DogManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.data.mongodb.repository.Query;
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
        return dogManagementService.getAllDog();
    }

    // RequestParam -> localhost:8001/dogs?name=ian
    // PathVariable -> localhost:8001/
    @GetMapping("/dogs/{name}")
    public Dog getDogByName(@RequestParam("name") String name) {
        return dogManagementService.getDogByName(name);
    }

    @DeleteMapping("/dogs")
    public void deleteDogByName(@RequestBody Dog dog){
        dogManagementService.deleteDogByName(dog);
    }

    @PutMapping("/dogs/{name}")
    public void updateDogAll(@PathVariable String name, String newName, String newKind, String newOwnerName, String newOwnerPhoneNumber) {
        dogManagementService.updateDogAll(name, newName, newKind, newOwnerName, newOwnerPhoneNumber);
    }

    @PatchMapping("/dogs/{name1}")
    public void updateKind(String name1, String newKind){
        dogManagementService.updateKind(name1, newKind);
    }

    @PatchMapping("/dogs/{name2}")
    public void updateMedicalRecords(String name2, String newMedicalRecords){
        dogManagementService.updateMedicalRecords(name2, newMedicalRecords);
    }

    @GetMapping("/dogs/{ownername}")
    public Dog getDogByOwnername(@RequestParam("ownername") String ownername){
        return dogManagementService.getDogByOwnerName(ownername);
    }

    @GetMapping("/dogs/{ownerPhoneNumber}")
    public Dog getDogByOwnerPhoneNumber(@RequestParam("ownerPhoneNumber") String ownerPhoneNumber){
        return dogManagementService.getDogByOwnerPhoneNumber(ownerPhoneNumber);
    }


    @GetMapping("/dogs/")
    public Dog getDog(@RequestParam("name") String name,@RequestParam("ownerName") String ownerName,
                      @RequestParam("ownerPhoneNumber") String ownerPhoneNumber){
        return dogManagementService.getDog(name, ownerName, ownerPhoneNumber);
    }
}
