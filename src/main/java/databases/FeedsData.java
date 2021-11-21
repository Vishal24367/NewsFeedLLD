package databases;

import lombok.Getter;
import models.Post;

import java.util.HashMap;
import java.util.Map;

@Getter
public class FeedsData {
    private Map<String, Post> feedsData = new HashMap<>();
}
