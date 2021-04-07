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

    public List<Dog> getAllDog(){
        return dogRepository.findAllDog(Dog.class);
    }

    public void deleteDogByName(Dog dog) {
        if(dogRepository.existDogByName(dog.getName())){
            dogRepository.deleteDog(dog);
        }
        else{
            throw new DogNotFoundException();
        }
    }

    public void updateDogAll(String name, String newName, String newKind, String newOwnerName, String newOwnerPhoneNumber) {
        if (!dogRepository.existDogByName(name)){   // name이 존재하지 않을 경우 DogNotFoundException error
            throw new DogNotFoundException();
        }
        else if (dogRepository.existDogByName(newName) || dogRepository.existDogByOwnerName(newOwnerName)
        || dogRepository.existDogByOwnerPhoneNumber(newOwnerPhoneNumber)) {   //
            throw new DogFoundException();
        }
        else{
            dogRepository.updateDogAll(name, newName, newKind, newOwnerName, newOwnerPhoneNumber);
        }
    }

    public Dog getDogByName(String name) {
        if (dogRepository.existDogByName(name)){
            return dogRepository.findDog(name);
        }
        else{
            throw new DogNotFoundException();
        }
    }

    public Dog getDogByOwnerName(String ownerName) {
        if (dogRepository.existDogByOwnerName(ownerName)){
            return dogRepository.findDogOwner(ownerName);
        }
        else{
            throw new DogNotFoundException();
        }
    }

    public Dog getDogByOwnerPhoneNumber(String ownerPhoneNumber) {
        if (dogRepository.existDogByOwnerPhoneNumber(ownerPhoneNumber)){
            return dogRepository.findDogPhoneNumber(ownerPhoneNumber);
        }
        else{
            throw new DogNotFoundException();
        }
    }

    public Dog getDog(String name, String ownerName, String ownerPhoneNumber) {
        if (dogRepository.findEqualDogExists(name, ownerName, ownerPhoneNumber)){
            return dogRepository.findEqualDog(name, ownerName, ownerPhoneNumber);
        }
        else{
            throw new DogNotFoundException();
        }
    }
}
