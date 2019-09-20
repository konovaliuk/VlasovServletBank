package ua.vlasovEugene.servletBankSystem.controller.servlets;

import ua.vlasovEugene.servletBankSystem.controller.*;

import java.util.HashMap;
import java.util.Map;

class CommandContainer {
    private static Map<String,Command> commands = new HashMap<>();

    static {
        commands.put("/noCommand", new NoCommand());
        commands.put("/",new GetIndexPage());
        commands.put("/adminpage",new GetAdminPage());
        commands.put("/userpage", new GetUserPage());
        commands.put("/adminpage/newuserpage", new GetNewUserPage());
        commands.put("/loginuser",new LoginUser());
        commands.put("/adminpage/addnewuser", new CreateUser());
        commands.put("/changeLang", new LanguageChanger());
        commands.put("/userpage/accountoperation",new AccountHistory());
        commands.put("/userpage/workwithaccount",new AccountWork());
        commands.put("/userpage/refill", new RefillAccount());
        commands.put("/userpage/sendToAnAcc", new MoneySender());
        commands.put("/adminpage/confirmrequest",null);//todo добавить механизм подтверждения запроса
        commands.put("/adminpage/deleterequest",null);//todo добавить механизм отказа в запросе
    }

    static Command getCommand(String uri){
        if(uri==null||!commands.containsKey(uri)){
            return commands.get("/noCommand");
        }
        return commands.get(uri);
    }
}
