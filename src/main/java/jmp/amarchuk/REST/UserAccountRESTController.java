package jmp.amarchuk.REST;

import jmp.amarchuk.model.UserAccount;
import jmp.amarchuk.service.UserAccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * UserAccountRESTController implementation - annotation-based controller.
 *
 * @author Anastasiya Marchuk
 *
 */
@RestController
@RequestMapping("/api/account")
public class UserAccountRESTController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserAccountRESTController.class);

    @Autowired
    UserAccountService userAccountService;

    @GetMapping("/accounts")
    public List<UserAccount> getAllTickets() {
        LOGGER.debug("Get all events");
        List<UserAccount> accounts = userAccountService.getAllAccounts();
        List<UserAccount> jsonList=new ArrayList<>();
        for (UserAccount ua: accounts){
            UserAccount account=new UserAccount();
            account.setId(ua.getId());
            account.setUserId(ua.getUserId());
            account.setMoney(ua.getMoney());
            jsonList.add(account);
        }
        return jsonList;
    }


}
