package notificationSender;

public class NotificationSender {

    private final AuthenticationService authenticationService;

    public NotificationSender(AuthenticationService authenticationService) {

        this.authenticationService = authenticationService;

    }

    public void sendNotification(String username, String password) {

        if (authenticationService.authenticate(username, password)) {
            System.out.println("Notification sent to " + username + ": " + "Welcome to the system!\n");
        } else {
            System.out.println("Failed to send notification because the authentication was failed for user: " + username + "\n");
        }

    }

}