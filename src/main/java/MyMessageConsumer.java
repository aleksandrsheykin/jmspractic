import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by admin on 12.05.2017.
 */
public class MyMessageConsumer {

    public Connection createConnection() throws JMSException {
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("vm://localhost");
        return factory.createConnection();
    }

    public void receiveMessage(){
        try {
            Connection connection = createConnection();
            connection.start();
            Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue("MyQueue");

            MessageConsumer messageConsumer = session.createConsumer(destination);

            Message message = messageConsumer.receive(15000);
            System.out.println("server got: "+((TextMessage)message).getText());

            messageConsumer.close();
            session.close();
            connection.close();

        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
