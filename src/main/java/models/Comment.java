package models;


import lombok.Getter;
import lombok.NonNull;

import java.time.LocalDateTime;
import java.util.*;

@Getter
public class Comment {
    private String userId;
    private String commentId;
    private String feedId;
    private String commentText;
    private Set<String> upVotes;
    private Set<String> downVotes;
    private LocalDateTime createdAT;
    private LocalDateTime updatedAt;
    
    public Comment(@NonNull String commentText, @NonNull String feedId, @NonNull String userId) {
        this.commentId = UUID.randomUUID().toString();
        this.feedId = feedId;
        this.userId = userId;
        this.commentText = commentText;
        this.upVotes = new HashSet<>();
        this.downVotes = new HashSet<>();
        this.createdAT = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
}
