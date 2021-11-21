package comparators;

import models.Post;

import java.time.LocalDateTime;
import java.util.Comparator;

public class FeedComparator implements Comparator {
    @Override
    public int compare(Object post1, Object post2) {
        Post firstPost = (Post) post1;
        Post secondPost = (Post) post2;
        int firstPostScores = firstPost.getUpVotesCount() - firstPost.getDownVotesCount();
        int secondPostScores = secondPost.getUpVotesCount() - secondPost.getDownVotesCount();
        if (firstPostScores < secondPostScores) return -1;
        else if (firstPostScores > secondPostScores) return 1;
        else {
            int firstPostCommentsSize = firstPost.getComments().size();
            int secondPostCommentsSize = secondPost.getComments().size();
            if (firstPostCommentsSize < secondPostCommentsSize) return -1;
            else if (firstPostCommentsSize > secondPostCommentsSize) return 1;
            else {
                LocalDateTime firstPostTimeStamp = firstPost.getUpdatedAt();
                LocalDateTime secondPostTimeStamp = secondPost.getUpdatedAt();
                return firstPostTimeStamp.compareTo(secondPostTimeStamp);
            }
        }
    }
}
