package models;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.*;

@Getter
public class Post {
    private String userId;
    private String feedId;
    private String feedDescription;
    private Map<String, Comment> comments;
    private Map<String, VoteStatus> votes;
    @Setter private Integer upVotesCount;
    @Setter private Integer downVotesCount;
    private LocalDateTime createdAT;
    private LocalDateTime updatedAt;

    public Post(@NonNull final String userId, String feedDescription){
        this.feedId = UUID.randomUUID().toString();
        this.userId = userId;
        this.feedDescription = feedDescription;
        this.comments = new HashMap<>();
        this.votes = new HashMap<>();
        this.upVotesCount = 0;
        this.downVotesCount = 0;
        this.createdAT = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
}
