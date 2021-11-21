package commands;

import logger.Logger;
import models.Command;
import services.FeedService;
import services.UserService;

import java.util.List;
import java.util.Locale;

public class SignupCommandExecutor extends CommandExecutor {

    public final static String COMMAND_NAME = "signup";

    SignupCommandExecutor(final FeedService feedService, final UserService userService, final Logger logger) {
        super(feedService,userService, logger);
    }
    @Override
    public boolean validate(Command command) {
        List<String> params = command.getParams();
        return params.size() == 1;
    }

    @Override
    public void execute(Command command) {
        String userName = command.getParams().get(0);
        userService.signUpUser(userName.toLowerCase(Locale.ROOT));
    }

}
