package mode;

import commands.CommandExecutorFactory;
import logger.Logger;
import models.Command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InteractiveMode extends Mode{

    public InteractiveMode(final CommandExecutorFactory commandExecutorFactory, final Logger logger) {
        super(commandExecutorFactory,logger);
    }

    @Override
    public void process() throws IOException {
        logger.welcome();
        final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            final String input = reader.readLine();
            final Command command = new Command(input);
            processCommand(command);
        }
    }
}
