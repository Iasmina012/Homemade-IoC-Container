package notificationSender;

import java.util.*;

public class UserRepositoryImpl implements UserRepository {

    private final NetworkService networkService;
    private final Map<String, User> users = new HashMap<>();

    public UserRepositoryImpl(NetworkService networkService) {

        this.networkService = networkService;
        users.put("Iasmina012", new User("Iasmina012", "hashedPassword123", "admin"));
        users.put("Elena014", new User("Elena014", "secureHash456", "user"));

    }

    @Override
    public User findUserByUsername(String username) {

        networkService.connect();
        System.out.println("Accessing repository... ");
        return users.get(username);

    }

}