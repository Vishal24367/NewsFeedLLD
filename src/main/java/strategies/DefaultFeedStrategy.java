package strategies;

import models.Post;
import models.User;

import java.util.List;

public class DefaultFeedStrategy implements  FeedStrategy{

    @Override
    public List<Post> popularFeeds(List<Post> allPosts, User currentUser) {

        return null;
    }
}
