package databases;

import lombok.Getter;
import lombok.NonNull;
import models.User;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Getter
public class UsersData {
    private Map<String, User> usersData = new LinkedHashMap<>();

}
