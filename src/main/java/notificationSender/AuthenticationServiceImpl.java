package notificationSender;

public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordValidator passwordValidator;

    public AuthenticationServiceImpl(UserRepository userRepository, PasswordValidator passwordValidator) {

        this.userRepository = userRepository;
        this.passwordValidator = passwordValidator;

    }

    @Override
    public boolean authenticate(String username, String password) {

        User user = userRepository.findUserByUsername(username);

        if (user == null) {
            System.out.println("User not found: " + username);
            return false;
        }

        if (passwordValidator.validate(password, user.getPasswordHash())) {
            System.out.println("User authenticated: " + username);
            System.out.println("Role: " + user.getRole());
            return true;
        } else {
            System.out.println("Invalid password for user: " + username);
            return false;
        }

    }

}