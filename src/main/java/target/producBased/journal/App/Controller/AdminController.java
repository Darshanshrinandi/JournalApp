package target.producBased.journal.App.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import target.producBased.journal.App.Entity.User;
import target.producBased.journal.App.Service.UserService;

import java.util.List;


@RestController
@RequestMapping("/admin")
public class AdminController
{

    @Autowired
    private UserService userService;


    @GetMapping("/all-users")
    public ResponseEntity<?> getAllUser(){
        List<User> all = userService.getAll();
        if(all !=null && !all.isEmpty()){

            return new ResponseEntity<>(all,HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);


    }

    @PostMapping("/create-admin-user")
    public void creatuser(@RequestBody User user){
        userService.saveAdmin(user);
    }
}




