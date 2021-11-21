package mode;

import commands.CommandExecutor;
import commands.CommandExecutorFactory;
import logger.Logger;
import models.Command;

import java.io.IOException;

public abstract class Mode {

    CommandExecutorFactory commandExecutorfactory;
    Logger logger;

    public Mode(final CommandExecutorFactory commandExecutorfactory, final Logger logger) {
        this.commandExecutorfactory = commandExecutorfactory;
        this.logger = logger;
    }

    protected void processCommand(final Command command) {
        final CommandExecutor commandExecutor = commandExecutorfactory.getCommandExecutor(command);

        if (commandExecutor == null) return;
        if (commandExecutor.validate(command)) {
            commandExecutor.execute(command);
        } else {
            System.out.println("Invalid Params Passed");
        }
    }

    public abstract void process() throws IOException;
}
