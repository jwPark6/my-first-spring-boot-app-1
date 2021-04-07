package org.cnu.realcoding.repository;

import org.cnu.realcoding.domain.Dog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;

@Repository
public class DogRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    public Dog findDog(String name){
        return mongoTemplate
                .findOne(
                        Query.query(Criteria.where("name").is(name)),
                        Dog.class
                );
    }

    public void insertDog(Dog dog){
        mongoTemplate.insert(dog);
    }

    public List<Dog> findAllDog(Class<Dog> dogClass) {
        return mongoTemplate.findAll(Dog.class);
    }

    public boolean existDogByName(String name){
        return mongoTemplate
                .exists(
                        Query.query(Criteria.where("name").is(name)),
                        Dog.class
                );
    }
    public boolean existDogByOwnerName(String ownerName){
        return mongoTemplate
                .exists(
                        Query.query(Criteria.where("ownerName").is(ownerName)),
                        Dog.class
                );
    }
    public boolean existDogByOwnerPhoneNumber(String ownerPhoneNumber){
        return mongoTemplate
                .exists(
                        Query.query(Criteria.where("ownerPhoneNumber").is(ownerPhoneNumber)),
                        Dog.class
                );
    }

    public void deleteDog(Dog dog) {
        mongoTemplate
                .remove(Query.query(Criteria.where("name").is(dog.getName())),
                        Dog.class
                );
    }

    public Dog findDogOwner(String ownerName) {
        return mongoTemplate
                .findOne(
                        Query.query(Criteria.where("ownerName").is(ownerName)),
                        Dog.class
                );
    }

    public Dog findDogPhoneNumber(String ownerPhoneNumber) {
        return mongoTemplate
                .findOne(
                        Query.query(Criteria.where("ownerPhoneNumber").is(ownerPhoneNumber)),
                        Dog.class
                );
    }

    public void updateDogAll(String name, String newName, String newKind, String newOwnerName, String newOwnerPhoneNumber){
        Update update = new Update();
        update.set("name", newName);
        update.set("ownerName", newOwnerName);
        update.set("ownerPhoneNumber", newOwnerPhoneNumber);
        update.set("kind", newKind);
        mongoTemplate.updateFirst(Query.query(Criteria.where("name").is(name)),
                update, "dog");
    }

    public void updateKind(String name, String newKind){
        Update update = new Update();
        update.set("kind", newKind);

        mongoTemplate.updateFirst(Query.query(Criteria.where("name").is(name)),
                update, "dog");
    }

    public void updateMedicalRecords(String name, String newMedicalRecords){
        List oldMedicalRecords = findDog(name).getMedicalRecords();
        oldMedicalRecords.add(newMedicalRecords);

        Update update = new Update();
        update.set("medicalRecords", oldMedicalRecords);

        mongoTemplate.updateFirst(Query.query(Criteria.where("name").is(name)),
                update, "dog");

    }

    public Dog findEqualDog(String name, String ownerName, String ownerPhoneNumber) {
        return mongoTemplate.
                findOne(
                        Query.query(Criteria.where("name").is(name).and("ownerName").is(ownerName).and("ownerPhoneNumber").is(ownerPhoneNumber)),
                        Dog.class
                );
    }

    public boolean findEqualDogExists(String name, String ownerName, String ownerPhoneNumber){
        return mongoTemplate.
                exists(
                        Query.query(Criteria.where("name").is(name).and("ownerName").is(ownerName).and("ownerPhoneNumber").is(ownerPhoneNumber)),
                        Dog.class
                );
    }
}