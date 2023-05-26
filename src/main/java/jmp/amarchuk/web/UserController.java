package jmp.amarchuk.web;

import jmp.amarchuk.model.User;
import jmp.amarchuk.model.UserAccount;
import jmp.amarchuk.service.UserAccountService;
import jmp.amarchuk.service.UserService;
import jmp.amarchuk.web.handler.HandlerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

/**
 * UserController implementation - annotation-based controller that will delegate to BookingFacade methods.
 *
 * @author Anastasiya Marchuk
 *
 */
@Controller
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;
    @Autowired
    UserAccountService userAccountService;

    @GetMapping("/")
    public String start(Model model) {
        LOGGER.debug("get start page");
        model.addAttribute("message","Welcome!");
        model.addAttribute("question","What do you want to do?");
        LOGGER.info("Method start. UserController.");
        return "welcome";
    }


    @GetMapping("/allUsers")
    public String getAllUsers(Model model) throws HandlerException {

        List<User> allUsers = userService.getAllUsers();
        List<UserAccount> allAccounts = userAccountService.getAllAccounts();
        LOGGER.debug("get all users => {}", allUsers);
        LOGGER.debug("get all users => {}", allAccounts);
        model.addAttribute("allUsers", allUsers);
        model.addAttribute("allAccounts", allAccounts);
        model.addAttribute("heading", "List of all users in DB");
        LOGGER.info("Method start. UserController (-- / --)");
        return "list_users";
    }

    @GetMapping("/user/{id}")
    public String getUser(@PathVariable("id") int id,Model model) {

        LOGGER.debug("get user with id => {}", id);
        User user=userService.getUserById(id);
        List<User> list=new LinkedList<>();
        list.add(user);
        model.addAttribute("allUsers",list);
        model.addAttribute("heading", "User with id "+id);
        LOGGER.info("Method start. UserController.");
        return "list_users";
    }

    @GetMapping("/email")
    public String getByEmail() {
        return "find_by_email";
    }

    @GetMapping("/user/email")
    public String getUserByEmail(@RequestParam("email") String email,Model model) {

        LOGGER.debug("get user with => {}", email);
        User user=userService.getUserByEmail(email);
        List<User> list=new LinkedList<>();
        list.add(user);
        model.addAttribute("message",user.toString());
        model.addAttribute("allUsers",list);
        model.addAttribute("heading", "User with email "+email);
        LOGGER.info("Method start. UserController.");

        return "list_users";
    }


    @GetMapping("/user/name")
    public String getUserByName(@RequestParam("name") String name,Model model) {

        LOGGER.debug("get user with name => {}", name);
        List<User> allUsers=userService.getUsersByName(name,1,1);
        model.addAttribute("allUsers",allUsers);
        model.addAttribute("heading", "User(s) with name "+name);
        LOGGER.info("Method start. UserController.");

        return "facade";
    }


    @GetMapping("/updateUser")
    public String updateUserById(@RequestParam("name") String name,@RequestParam("id") long id,@RequestParam("email") String email,@RequestParam("wallet") double wallet, Model model) throws HandlerException {

            LOGGER.debug("Update user with name ({}) and email ({})", name, email);
            User user = new User();
            user.setName(name);
            user.setEmail(email);
            user.setId(id);
            userService.updateUser(user);
            userAccountService.updateMoney(user, wallet);
           LOGGER.info("Method start. UserController.");
            return "redirect:/";

    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable("id") long id, Model model) {
        LOGGER.debug("Update user with id ({}) ", id);
        model.addAttribute("user", id);
        LOGGER.info("Method start. UserController.");
        return "update_user";
    }


    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id,Model model) {
        LOGGER.debug("Delete user with id ({}) ", id);
        userService.deleteUser(id);
        model.addAttribute("message","User with id "+id);
        model.addAttribute("allUsers",null);
        model.addAttribute("heading", "Delete: ");
        LOGGER.info("Method start. UserController.");
        return "facade";
    }

    @GetMapping("/create")
    public String name(@RequestParam("name") String name, @RequestParam("email") String email, @RequestParam("wallet") double wallet) throws HandlerException {
        LOGGER.debug("Create user with name ({}) and email ({})", name, email);
        User user = new User();

        user.setName(name);
        user.setEmail(email);
        User u=userService.createUser(user);

        UserAccount userAccount=new UserAccount();
        int userId= (int) u.getId();
        userAccount.setUserId(userId);
        userAccount.setMoney(wallet);
        userAccountService.createAccount(userAccount);

        LOGGER.info("Method start. UserController.");
        return "redirect:/";
    }

    @GetMapping("/new")
    public String create() {
        LOGGER.debug("Create new user ");
        return "new_user";
    }


}
