package target.producBased.journal.App.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import target.producBased.journal.App.Entity.User;
import target.producBased.journal.App.Service.UserService;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserService userService;


    @GetMapping("/health-check")
    public String healthCheck(){
        return "OK";

    }
    @PostMapping("create-user")
    public void createUser(@RequestBody User user){
        userService.saveNewEntry(user);
    }
}
