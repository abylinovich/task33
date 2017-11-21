package by.epam.task33.controller.command;

import by.epam.task33.controller.command.impl.DOMCommand;
import by.epam.task33.controller.command.impl.SAXCommand;
import by.epam.task33.controller.command.impl.StAXCommand;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {

    private static final CommandFactory commandFactory = new CommandFactory();
    private static final Map<String, Command> commands = new HashMap<>();
    static {
        commands.put("SAX", new SAXCommand());
        commands.put("StAX", new StAXCommand());
        commands.put("DOM", new DOMCommand());
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
