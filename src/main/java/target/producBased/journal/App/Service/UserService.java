package target.producBased.journal.App.Service;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import target.producBased.journal.App.Entity.User;
import target.producBased.journal.App.Repository.UserRepo;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class UserService {

          @Autowired
       private UserRepo userRepo;

        private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

       public void saveNewEntry(User userEntry) {
               userEntry.setPassword(passwordEncoder.encode(userEntry.getPassword()));
               userEntry.setRoles(Arrays.asList("USER"));
              userRepo.save(userEntry);
       }

       public void saveAdmin(User userEntry){
            userEntry.setPassword(passwordEncoder.encode(userEntry.getPassword()));
            userEntry.setRoles(Arrays.asList("USER","ADMIN"));
            userRepo.save(userEntry);

       }

    public void saveUser(User userEntry) {

        userRepo.save(userEntry);
    }

       public List<User> getAll(){
          return userRepo.findAll();
       }

       public Optional<User> findById(ObjectId id){
             return  userRepo.findById(id);
       }

       public void deleteById(ObjectId id){
            userRepo.deleteById(id);
       }

       public User findByuserName(String userName){
           return userRepo.findByUsername(userName);
       }
}
