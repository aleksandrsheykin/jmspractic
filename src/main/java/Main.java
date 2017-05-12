import java.util.Scanner;

/**
 * Created by admin on 12.05.2017.
 */
public class Main {

    public static void main(String[] args) {
        Thread client = new Thread(new Runnable() {
            @Override
            public void run() {
                MyMessageProvider myMessageProvider = new MyMessageProvider();
                String msg;
                Scanner scanner = new Scanner(System.in);
                while (scanner.hasNext()) {
                    msg = scanner.next();
                    if (msg.equals("exit"))
                        System.exit(0);

                    System.out.println("I sending: "+msg);
                    myMessageProvider.sendMessage(msg);
                }
            }
        });

        Thread server = new Thread(new Runnable() {
            @Override
            public void run() {
                MyMessageConsumer myMessageConsumer = new MyMessageConsumer();

                while (true) {
                    myMessageConsumer.receiveMessage();
                }
            }
        });

        server.start();
        client.start();
    }

}