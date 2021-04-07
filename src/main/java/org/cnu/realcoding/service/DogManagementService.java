package org.cnu.realcoding.service;

import lombok.Getter;
import org.cnu.realcoding.domain.Dog;
import org.cnu.realcoding.exception.DogFoundException;
import org.cnu.realcoding.exception.DogNotFoundException;
import org.cnu.realcoding.repository.DogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DogManagementService {
    @Autowired
    private DogRepository dogRepository;

    public void insertDog(Dog dog) {
        if(!dogRepository.existDogByName(dog.getName()) && !dogRepository.existDogByOwnerName(dog.getOwnerName())
                && !dogRepository.existDogByOwnerPhoneNumber(dog.getOwnerPhoneNumber())){
            dogRepository.insertDog(dog);
        }
        else {
            throw new DogFoundException();
        }
    }

    public Dog getDogByName(String name) {
        return dogRepository.findDog(name);
    }

    public List<Dog> getAllDog(){
        return dogRepository.findAllDog(Dog.class);
    }

    public void updateDogAll(String name, String newName, String newKind, String newOwnerName, String newOwnerPhoneNumber) {
        dogRepository.updateDogAll(name, newName, newKind, newOwnerName, newOwnerPhoneNumber);
    }

    public void deleteDogByName(Dog dog) {
        if(dogRepository.existDogByName(dog.getName())){
            dogRepository.deleteDog(dog);
        }
        else{
            throw new DogNotFoundException();
        }
    }

    public Dog getDogByOwnerName(String name) {
        Dog dog = dogRepository.findDogOwner(name);

        return dog;
    }

    public Dog getDogByOwnerPhoneNumber(String ownerPhoneNumber) {
        Dog dog = dogRepository.findDogPhoneNumber(ownerPhoneNumber);

        return dog;
    }

    public Dog getDog(String name, String ownerName, String ownerPhoneNumber) {
        if (dogRepository.findEqualDogExists(name,ownerName,ownerPhoneNumber)){
            return dogRepository.findEqualDog(name, ownerName, ownerPhoneNumber);
        }
        else{
            throw new DogNotFoundException();
        }
    }
}
