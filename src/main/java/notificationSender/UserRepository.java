package notificationSender;

public interface UserRepository {

    User findUserByUsername(String username);

}