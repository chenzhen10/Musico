package by.epam.kimbar.controller;

import by.epam.kimbar.controller.impl.*;
import by.epam.kimbar.controller.impl.moving.*;
import by.epam.kimbar.controller.impl.news.CreateNewsCommand;
import by.epam.kimbar.controller.impl.news.DeleteNewsCommand;
import by.epam.kimbar.controller.impl.news.FindNewsCommand;
import by.epam.kimbar.controller.impl.user.AuthorizationCommand;
import by.epam.kimbar.controller.impl.user.DeleteUserCommand;
import by.epam.kimbar.controller.impl.user.GiveAdministrationPrivilegeCommand;
import by.epam.kimbar.controller.impl.user.RegistrationCommand;

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
        commands.put("create_news",new CreateNewsCommand());
        commands.put("delete_news",new DeleteNewsCommand());
        commands.put("go_to_create_news",new GoToCreateNewsCommand());
        commands.put("give_administration_privilege",new GiveAdministrationPrivilegeCommand());
        commands.put("go_to_give_privilege",new GoToGiveAdministrationPrivilegeCommand());
        commands.put("delete_user",new DeleteUserCommand());
    }

    public Command getCommand(String name) {
        return commands.get(name);
    }

    public Map<String, Command> getCommands() {
        return commands;
    }
}
