package commands;

import logger.Logger;
import models.Command;
import models.User;
import services.FeedService;
import services.UserService;

import java.util.List;

public class ReplyCommandExecutor extends CommandExecutor {
    public final static String COMMAND_NAME = "reply";

    ReplyCommandExecutor(final FeedService feedService, final UserService userService, final Logger logger) {
        super(feedService, userService, logger);
    }

    @Override
    public boolean validate(Command command) {
        List<String> params = command.getParams();
        return params.size() >= 2;
    }

    @Override
    public void execute(Command command) {
        List<String> params = command.getParams();
        String feedId = params.get(0);
        String comment = String.join(" ", params.subList(1, params.size()));
        User currentUser = userService.getCurrentUser();
        feedService.createComment(comment, feedId, currentUser.getName());
    }
}
