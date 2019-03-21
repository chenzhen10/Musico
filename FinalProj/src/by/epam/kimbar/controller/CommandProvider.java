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
        commands.put("go_to_index",new GoToIndexCommand());
        commands.put("go_to_auth",new GoToAuthCommand());
        commands.put("find_news",new FindNewsCommand());
        commands.put("log_out",new LogOutCommand());
        commands.put("go_to_success",new GoToSuccessCommand());
        commands.put("create_news",new CreateNewsCommand());
    }

    public Command getCommand(String name) {
        return commands.get(name);
    }

    public Map<String, Command> getCommands() {
        return commands;
    }
}
