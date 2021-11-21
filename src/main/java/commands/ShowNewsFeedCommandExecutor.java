package commands;

import logger.Logger;
import models.Command;
import models.User;
import services.FeedService;
import services.UserService;

import java.util.List;
import java.util.Locale;

public class ShowNewsFeedCommandExecutor extends CommandExecutor{
    public final static String COMMAND_NAME = "shownewsfeed";

    ShowNewsFeedCommandExecutor(final FeedService feedService, final UserService userService, final Logger logger) {
        super(feedService,userService, logger);
    }
    @Override
    public boolean validate(Command command) {
        List<String> params = command.getParams();
        return params.size() == 0;
    }

    @Override
    public void execute(Command command) {
        User currentUser = userService.getCurrentUser();
        if (currentUser != null) feedService.showAllFeeds(currentUser, userService.getAllUsers(), false);
    }
}
