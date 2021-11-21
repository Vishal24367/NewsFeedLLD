import databases.FeedsData;
import databases.UsersData;
import mode.InteractiveMode;
import commands.CommandExecutorFactory;
import logger.Logger;
import services.FeedService;
import services.UserService;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        final UsersData newUserData = new UsersData();
        final FeedsData newFeedsData = new FeedsData();

        final UserService newUserService = new UserService(newUserData);
        final FeedService feedService = new FeedService(newFeedsData);
        final Logger logger = new Logger();
        final CommandExecutorFactory commandExecutorFactory = new CommandExecutorFactory(feedService, newUserService, logger);

        new InteractiveMode(commandExecutorFactory, logger).process();

    }
}
