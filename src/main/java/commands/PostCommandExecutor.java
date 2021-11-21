package commands;

import logger.Logger;
import models.Command;
import models.User;
import services.FeedService;
import services.UserService;


import java.util.List;

public class PostCommandExecutor extends CommandExecutor {

    public final static String COMMAND_NAME = "post";

    PostCommandExecutor(final FeedService feedService, final UserService userService, final Logger logger) {
        super(feedService,userService, logger);
    }

    @Override
    public boolean validate(Command command) {
        List<String> params = command.getParams();
        return params.size() >= 1;
    }

    @Override
    public void execute(Command command) {
        String postBody = String.join(" ", command.getParams());
        User currentUser = userService.getCurrentUser();
        String userId = currentUser.getName();
        feedService.createFeeds(postBody, userId);
    }
}
