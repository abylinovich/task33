package by.epam.task33.controller.command;

import by.epam.task33.controller.command.impl.ParseCommand;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {

    private static final String PARSE_COMMAND = "parse";

    private static final CommandFactory commandFactory = new CommandFactory();
    private static final Map<String, Command> commands = new HashMap<>();
    static {
        commands.put(PARSE_COMMAND, new ParseCommand());
    }

    private CommandFactory() {
    }

    public static CommandFactory getInstance() {
        return commandFactory;
    }

    public static Map<String, Command> getCommands() {
        return commands;
    }

    public static Command getCommand(String commandName) {
        return commands.get(commandName);
    }

}
