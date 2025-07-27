package target.producBased.journal.App.Repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import target.producBased.journal.App.Entity.User;


public interface UserRepo extends MongoRepository<User, ObjectId> {
     User findByUsername(String username);

     void deleteByUsername(String username);
}
