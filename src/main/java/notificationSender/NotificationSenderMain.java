package notificationSender;

import core.IoCContainer;

import java.util.Objects;

public class NotificationSenderMain {

    public static void main(String[] args) {

        IoCContainer container = new IoCContainer();

        container.buildConfiguration(
                Objects.requireNonNull(IoCContainer.class.getClassLoader().getResource("components_notificationSender.xml")).getPath()
        );

        //Obtinem NotificationSender din container
        NotificationSender notificationSender = (NotificationSender) container.getComponent("notificationSender");

        notificationSender.sendNotification("Iasmina012", "hashedPassword123"); //Successful
        notificationSender.sendNotification("Elena014", "wrongPassword"); //Invalid Password
        notificationSender.sendNotification("Elena014", "secureHash456"); //Successful
        notificationSender.sendNotification("UnknownUser", "anyPassword"); //User Not Found

    }

}