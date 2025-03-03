package movieLister;

public class DatabaseAccessImpl implements DatabaseAccess {

    private final NetworkCommunicator networkCommunicator;

    public DatabaseAccessImpl(NetworkCommunicator networkCommunicator) { this.networkCommunicator = networkCommunicator; }

    @Override
    public void accessDatabase() {

        networkCommunicator.communicate();
        System.out.println("Accessing database...");

    }

}