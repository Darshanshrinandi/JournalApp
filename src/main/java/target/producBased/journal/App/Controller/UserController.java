package target.producBased.journal.App.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import target.producBased.journal.App.Entity.User;
import target.producBased.journal.App.Repository.UserRepo;
import target.producBased.journal.App.Service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

      @Autowired
      private UserService userService;
      @Autowired
      private UserRepo userRepo;


      @PostMapping
     public void createUser(@RequestBody User user){
            userService.saveNewEntry(user);
      }

      @PutMapping()
    public ResponseEntity<?> updateUser(@RequestBody User user){
          Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
          String username = authentication.getName();
          User userIndb = userService.findByuserName(username);
          userIndb.setUsername(user.getUsername());
          userIndb.setPassword(user.getPassword());
          userService.saveNewEntry(userIndb);
          return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }

      @DeleteMapping
    public ResponseEntity<?> deleteUserById(){
          Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
          userRepo.deleteByUsername(authentication.getName());
          return new ResponseEntity<>(HttpStatus.NO_CONTENT);


      }


}
