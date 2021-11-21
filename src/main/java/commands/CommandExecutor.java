package commands;

import logger.Logger;
import models.Command;
import services.FeedService;
import services.UserService;

public abstract class CommandExecutor {

    protected UserService userService;
    protected FeedService feedService;
    protected Logger writer;

    public CommandExecutor(final FeedService feedService, final UserService userService, final Logger logger) {
        this.userService = userService;
        this.feedService = feedService;
        this.writer = logger;
    }

    public abstract void execute(Command command);

    public abstract boolean validate(Command command);
}
