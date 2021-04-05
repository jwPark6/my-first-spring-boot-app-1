package org.cnu.realcoding.realcoding.service;

import lombok.Getter;
import org.cnu.realcoding.domain.Dog;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DogManagementService {
    @Getter
    private List<Dog> dogs = new ArrayList<>();

    public void insertDog(Dog dog) {
        dogs.add(dog);
    }

    public Dog getDogByName(String name) {
        for (Dog dog : dogs) {
            if (dog.getName().equals(name)) {
                return dog;
            }
        }
        return null;
    }

//     throw new DogNotFoundException();
//        Dog dog = dogRepository.findDog(name);
//        if (dog == null)
//            throw new DogNotFoundException();
//        return dog;
//}

}
