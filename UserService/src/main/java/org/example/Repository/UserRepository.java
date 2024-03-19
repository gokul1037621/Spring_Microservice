package org.example.Repository;

import org.bson.types.ObjectId;
import org.example.Model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, ObjectId> {
    // JPA extended using User, to give access to built in db functions
    //SELECT * FROM ecommerce_user
    //WHERE user_email= :userName;

    // querying the database using the user entity to return a match during login in
    // used for token creation


}
