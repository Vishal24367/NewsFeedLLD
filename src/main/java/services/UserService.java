package services;

import databases.UsersData;
import lombok.Getter;
import lombok.NonNull;
import models.User;

import java.util.HashSet;
import java.util.Locale;
import java.util.Map;

public class UserService {

    @Getter
    static UsersData usersData;
    @Getter
    User currentUser;

    public UserService(@NonNull final UsersData usersData) {
        this.usersData = usersData;
        this.currentUser = null;
    }

    public void signUpUser(@NonNull final String userName) {
        User newUser = new User(userName);
        Map<String, User> allUsersData = this.usersData.getUsersData();
        if (allUsersData.containsKey(userName)) {
            System.out.println("User already have an account.");
            return;
        }
        allUsersData.put(userName, newUser);
        return;
    }

    public User loginUser(@NonNull final String name) throws RuntimeException {
        Map<String, User> allUsersData = this.usersData.getUsersData();
        if (!allUsersData.containsKey(name)) {
            System.out.println("User not created. Create the User first");
            return null;
        }
        if (this.currentUser != null && currentUser.getName().equals(name.toLowerCase(Locale.ROOT))) {
            System.out.println("User already LoggedIn.");
            return null;
        }
        this.currentUser = allUsersData.get(name);
        return allUsersData.get(name);
    }

    public void follow(String userName) {
        Map<String, User> allUsersData = usersData.getUsersData();
        if (!allUsersData.containsKey(userName)) {
            System.out.print("User not exist with this name.");
            return;
        }
        User followUser = allUsersData.get(userName);
        String currentUserName = this.currentUser.getName().toLowerCase(Locale.ROOT);
        String followUserName = followUser.getName().toLowerCase(Locale.ROOT);
        if (currentUserName.equals(followUserName)) {
            System.out.print("User cannot follow itself.");
            return;
        }

        HashSet<String> usersFollowingList = this.currentUser.getFollowingLists();
        HashSet<String> followUserFollowersList = followUser.getFollowerLists();
        if (usersFollowingList.contains(followUser.getName())) {
            System.out.print("User already followed this person.");
            return;
        }
        usersFollowingList.add(followUser.getName());
        followUserFollowersList.add(this.currentUser.getName());
        this.currentUser.setFollowing(usersFollowingList.size());
        followUser.setFollowers(followUserFollowersList.size());
        return;
    }

    public UsersData getAllUsers() {
        return this.usersData;
    }
}
