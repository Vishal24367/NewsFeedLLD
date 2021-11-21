package services;

import comparators.FeedComparator;
import databases.FeedsData;
import databases.UsersData;
import lombok.NonNull;
import models.Comment;
import models.Post;
import models.User;
import models.VoteStatus;

import java.util.*;

public class FeedService {
    static FeedsData feedsData;

    public FeedService(@NonNull FeedsData feedsData) {
        this.feedsData = feedsData;
    }

    public void createFeeds(@NonNull final String feedDescription, @NonNull String userName) {
        Post newFeed = new Post(userName, feedDescription);
        feedsData.getFeedsData().put(newFeed.getFeedId(), newFeed);
    }

    public void showAllFeeds(@NonNull User user, @NonNull UsersData usersData, @NonNull Boolean addAllPosts) {
        Map<String, Post> allPostsData = this.feedsData.getFeedsData();
        HashSet<String> userFollowingListUsers = user.getFollowingLists();
        List<Post> followingUsersPosts = new ArrayList<>();
        List<Post> nonFollowingUsersPosts = new ArrayList<>();
        for(Map.Entry<String, Post> entry : allPostsData.entrySet()){
            Post userPost = entry.getValue();
            if(userPost.getUserId() != user.getName())
            {
                if(userFollowingListUsers.contains(userPost.getUserId())) followingUsersPosts.add(userPost);
                else nonFollowingUsersPosts.add(userPost);
            }
            if(!addAllPosts){
                nonFollowingUsersPosts.add(userPost);
            }
        }
        sortAndPrintPostsByPriorities(followingUsersPosts, usersData);
        sortAndPrintPostsByPriorities(nonFollowingUsersPosts, usersData);
    }

    public void voteFeed(@NonNull String feedId, @NonNull String userId, @NonNull String choice) {
        Map<String, Post> allFeeds = feedsData.getFeedsData();
        if (!allFeeds.containsKey(feedId)) {
            System.out.println("Error : Invalid Feed Id");
            return;
        }
        Post currentPost = allFeeds.get(feedId);
        Map<String, VoteStatus> allVotes = currentPost.getVotes();
        if (allVotes.containsKey(userId) && choice == "upvote" && allVotes.get(userId) == VoteStatus.Null) {
            allVotes.put(userId, VoteStatus.UpVote);
            currentPost.setUpVotesCount(currentPost.getUpVotesCount() + 1);
            return;
        }
        if (allVotes.containsKey(userId) && choice == "downvote" && allVotes.get(userId) == VoteStatus.Null) {
            allVotes.put(userId, VoteStatus.DownVote);
            currentPost.setDownVotesCount(currentPost.getDownVotesCount() + 1);
            return;
        }
        if (allVotes.containsKey(userId) && choice == "upvote" && allVotes.get(userId) == VoteStatus.DownVote) {
            allVotes.put(userId, VoteStatus.UpVote);
            currentPost.setUpVotesCount(currentPost.getUpVotesCount() + 1);
            currentPost.setDownVotesCount(currentPost.getDownVotesCount() - 1);
            return;
        }
        if (allVotes.containsKey(userId) && choice == "downvote" && allVotes.get(userId) == VoteStatus.UpVote) {
            allVotes.put(userId, VoteStatus.DownVote);
            currentPost.setUpVotesCount(currentPost.getUpVotesCount() - 1);
            currentPost.setDownVotesCount(currentPost.getDownVotesCount() + 1);
            return;
        }
        if (allVotes.containsKey(userId) && choice == "upvote" && allVotes.get(userId) == VoteStatus.UpVote) {
            allVotes.put(userId, VoteStatus.Null);
            currentPost.setDownVotesCount(currentPost.getUpVotesCount() - 1);
            return;
        }
        if (allVotes.containsKey(userId) && choice == "downvote" && allVotes.get(userId) == VoteStatus.DownVote) {
            allVotes.put(userId, VoteStatus.Null);
            currentPost.setDownVotesCount(currentPost.getDownVotesCount() - 1);
            return;
        }
        if (!allVotes.containsKey(userId) && choice == "upvote") {
            allVotes.put(userId, VoteStatus.UpVote);
            currentPost.setUpVotesCount(currentPost.getUpVotesCount() + 1);
            return;
        }
        if (!allVotes.containsKey(userId) && choice == "downvote") {
            allVotes.put(userId, VoteStatus.DownVote);
            currentPost.setDownVotesCount(currentPost.getDownVotesCount() + 1);
            return;
        }
    }

    public void createComment(@NonNull String commentDescription, @NonNull String feedId, @NonNull String userId) {
        Map<String, Post> allFeeds = feedsData.getFeedsData();
        if (!allFeeds.containsKey(feedId)) {
            System.out.println("Error : Invalid Feed Id");
            return;
        }
        Comment comment = new Comment(commentDescription, feedId, userId);
        Post currentPost = allFeeds.get(feedId);
        currentPost.getComments().put(comment.getCommentId(), comment);
    }

    void sortAndPrintPostsByPriorities(@NonNull List<Post> usersPost, @NonNull UsersData usersData){
        Collections.sort(usersPost, new FeedComparator());
        Collections.reverse(usersPost);
        for(Post userPost : usersPost){
            Map<String, Comment> userPostComments = userPost.getComments();
            Map<String, User> allUsers = usersData.getUsersData();
            User postAuthor = allUsers.get(userPost.getUserId());
            System.out.println("id: " + userPost.getFeedId());
            System.out.println("( " + userPost.getUpVotesCount() + " upvotes, " + userPost.getDownVotesCount() + " downvotes " + ")");
            System.out.println(postAuthor.getName());
            System.out.println(userPost.getFeedDescription());
            System.out.println(userPost.getCreatedAT().toString());
            if (userPostComments.size() != 0) {
                for (Map.Entry<String, Comment> commentEntry : userPostComments.entrySet()) {
                    Comment userPostCurrentComment = commentEntry.getValue();
                    User commentUser = allUsers.get(userPostCurrentComment.getUserId());
                    System.out.println("      id: " + userPostCurrentComment.getCommentId());
                    System.out.println("          " + commentUser.getName());
                    System.out.println("          " + userPostCurrentComment.getCommentText());
                    System.out.println("          " + userPost.getCreatedAT().toString());
                    System.out.println();
                }
            }
            System.out.println();
        }
    }
}
