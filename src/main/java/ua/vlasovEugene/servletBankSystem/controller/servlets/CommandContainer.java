package ua.vlasovEugene.servletBankSystem.controller.servlets;

import org.apache.log4j.Logger;
import ua.vlasovEugene.servletBankSystem.controller.*;

import java.util.HashMap;
import java.util.Map;

class CommandContainer {
    private static Map<String,Command> commands = new HashMap<>();
    private static final Logger LOG = Logger.getLogger(CommandContainer.class);

    static {
        commands.put("/logout", new LogOut());
        commands.put("/noCommand", new NoCommand());
        commands.put("refillAcc", new RefillAccount());
        commands.put("/",new GetIndexPage());
        commands.put("createCreditRequest", new CreditAccRequestCreator());
        commands.put("/adminpage",new GetAdminPage());
        commands.put("/userpage", new GetUserPage());
        commands.put("/adminpage/newuserpage", new GetNewUserPage());
        commands.put("loginUser", new LoginUser());
        commands.put("/adminpage/addnewuser", new CreateUser());
        commands.put("languageChanger", new LanguageChanger());
        commands.put("/userpage/accountoperation",new AccountHistory());
        commands.put("/userpage/workwithaccount",new AccountWork());
        commands.put("sendMoney", new MoneySender());
        commands.put("/userpage/createdepositeacc", new DepositeAccFormGetter());
        commands.put("/userpage/createcreditacc", new CreditAccRequest());
        commands.put("/adminpage/confirmrequest", new CreditAccountCreator());
        commands.put("/adminpage/deleterequest", new CreditRequestDeleter());
        commands.put("createNewDepositAccount", new DepositAccCreator());
    }

    static Command getCommand(String uri){
        if(uri==null||!commands.containsKey(uri)){
            return commands.get("/noCommand");
        }
        LOG.info(String.format("Command %s was created", commands.get(uri).getClass().getSimpleName()));
        return commands.get(uri);
    }
}
