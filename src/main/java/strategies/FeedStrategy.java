package strategies;

import models.Post;
import models.User;

import java.util.List;

public interface FeedStrategy {
    List<Post> popularFeeds(List<Post> allFeeds, User currentUser);
}
