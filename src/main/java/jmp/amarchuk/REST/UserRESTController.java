package jmp.amarchuk.REST;

import jmp.amarchuk.model.User;
import jmp.amarchuk.model.UserAccount;
import jmp.amarchuk.service.UserAccountService;
import jmp.amarchuk.service.UserService;
import jmp.amarchuk.web.handler.HandlerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * UserRestController implementation - annotation-based controller.
 *
 * @author Anastasiya Marchuk
 *
 */
@RestController
@RequestMapping("/api/user")
public class UserRESTController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserRESTController.class);

    @Autowired
    UserService userService;
    @Autowired
    UserAccountService userAccountService;


    @GetMapping("/users")
    public  List<User> getAllUsers() throws HandlerException {

        List<User> allUsers = userService.getAllUsers();
        LOGGER.debug("get all users => {}", allUsers);
        List<User> jsonList=new ArrayList<>();
        for (User u: allUsers){
            User user=new User();
            user.setId(u.getId());
            user.setName(u.getName());
            user.setEmail(u.getEmail());
            jsonList.add(user);
        }
        return jsonList;
    }

    @GetMapping("/id/{id}")
    public User getUser(@PathVariable("id") int id) {

        LOGGER.debug("get user with id => {}", id);
        User user=userService.getUserById(id);
        LOGGER.info("Method start. UserController.");
        return user;
    }


    @GetMapping("/email/{email}")
    public User getUserByEmail(@PathVariable (name="email") String email) {

        LOGGER.debug("get user with => {}", email);
        User user=userService.getUserByEmail(email);
        List<User> list=new LinkedList<>();
        list.add(user);
        LOGGER.info("Method start. UserController.");
        return user;
    }


    @GetMapping("/name/{name}")
    public List<User> getUserByName(@PathVariable (name="name") String name) throws HandlerException {

        LOGGER.debug("get user with name => {}", name);
        List<User> allUsers=userService.getUsersByName(name,1,1);
        LOGGER.info("Method start. UserController.");
        List<User> jsonList=new ArrayList<>();
        for (User u: allUsers){
            User user=new User();
            user.setId(u.getId());
            user.setName(u.getName());
            user.setEmail(u.getEmail());
            jsonList.add(user);
        }
        return jsonList;
    }


    @PutMapping("/update/{wallet}")
    public User updateUserById(@RequestBody User user, @PathVariable ("wallet") double wallet) throws HandlerException {

         userService.updateUser(user);
         userAccountService.updateMoney(user, wallet);
         LOGGER.info("Method start. UserController.");
         return user;

    }



    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        LOGGER.debug("Delete user with id ({}) ", id);
        userService.deleteUser(id);
        LOGGER.info("Method start. UserController.");
        return "Delete user with id - "+id;
    }

    @PostMapping("/create/{wallet}")
    public User name(@RequestBody User user, @PathVariable ("wallet") double wallet) throws HandlerException {
        User u=userService.createUser(user);
        UserAccount userAccount=new UserAccount();
        int userId= (int) u.getId();
        userAccount.setUserId(userId);
        userAccount.setMoney(wallet);
        userAccountService.createAccount(userAccount);
        LOGGER.info("Method start. UserController.");
        return u;
    }


}
