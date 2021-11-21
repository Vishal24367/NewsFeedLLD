package models;


import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.UUID;

@Getter
public class User {
    private String uuid;
    private String name;

    @Setter private int following;
    @Setter private int followers;
    HashSet<String> followingLists;
    HashSet<String> followerLists;

    public User(@NonNull final String name){
        this.uuid = UUID.randomUUID().toString();
        this.name = name;
        this.following = 0;
        this.followers = 0;
        this.followingLists = new HashSet<>();
        this.followerLists = new HashSet<>();
    }
}
