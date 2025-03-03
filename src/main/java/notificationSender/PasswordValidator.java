package notificationSender;

public class PasswordValidator {

    public boolean validate(String inputPassword, String storedPasswordHash) {

        return inputPassword.equals(storedPasswordHash);

    }

}