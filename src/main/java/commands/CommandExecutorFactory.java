package commands;

import logger.Logger;
import models.Command;
import services.FeedService;
import services.UserService;

import java.util.HashMap;
import java.util.Optional;

public class CommandExecutorFactory {

    private HashMap<String, CommandExecutor> commands = new HashMap<>();

    public CommandExecutorFactory(final FeedService feedService, final UserService userService, final Logger logger) {
        commands.put(PostCommandExecutor.COMMAND_NAME, new PostCommandExecutor(feedService, userService, logger));
        commands.put(LoginCommandExecutor.COMMAND_NAME, new LoginCommandExecutor(feedService, userService, logger));
        commands.put(SignupCommandExecutor.COMMAND_NAME, new SignupCommandExecutor(feedService, userService, logger));
        commands.put(FollowCommandExecutor.COMMAND_NAME, new FollowCommandExecutor(feedService, userService, logger));
        commands.put(UpvoteCommandExecutor.COMMAND_NAME, new UpvoteCommandExecutor(feedService, userService, logger));
        commands.put(DownVoteCommandExecutor.COMMAND_NAME, new DownVoteCommandExecutor(feedService, userService, logger));
        commands.put(ReplyCommandExecutor.COMMAND_NAME, new ReplyCommandExecutor(feedService, userService, logger));
        commands.put(ShowNewsFeedCommandExecutor.COMMAND_NAME, new ShowNewsFeedCommandExecutor(feedService, userService, logger));
    }

    public CommandExecutor getCommandExecutor(final Command command) {
        try {
            Optional<CommandExecutor> executor = Optional.ofNullable(commands.get(command.getCommandName()));
            return executor.orElseThrow(RuntimeException::new);
        } catch (Exception e) {
            System.out.println("Invalid Command: " + e.getMessage());
            return null;
        }
    }

}
