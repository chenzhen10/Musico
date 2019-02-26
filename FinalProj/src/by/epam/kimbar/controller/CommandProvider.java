package by.epam.kimbar.controller;

import by.epam.kimbar.controller.impl.*;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {
    private Map<String,Command> commands = new HashMap<>();

    public CommandProvider() {
        commands.put("authorization", new AuthorizationCommand());
        commands.put("registration", new RegistrationCommand());
        commands.put("change_locale",new ChangeLocaleCommand());
        commands.put("go_to_registration_page",new GoToRegistrationPageCommand());
        commands.put("go_to_authentication_page",new GoToAuthenticationPageCommand());
        commands.put("go_to_index",new GoToIndexCommand());
        commands.put("go_to_auth",new GoToAuthCommand());
        commands.put("find_news",new FindNewsCommand());
    }

    public Command getCommand(String name) {
        return commands.get(name);
    }
}
