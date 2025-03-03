package movieLister;

public class NetworkCommunicatorImpl implements NetworkCommunicator {

    @Override
    public void communicate() { System.out.println("Communicating over HTTP..."); }

}