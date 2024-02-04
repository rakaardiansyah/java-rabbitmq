package rakaardiansyah.my.id;


import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

public class ConsumerApp {

	public static void main(String[] args) throws Exception {
		ConnectionFactory factory = new ConnectionFactory();
        factory.setUri("amqp://guest:guest@localhost:5672/");
        // factory.setVirtualHost("/");

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        DeliverCallback deliveryCallback = (consumerTag, message) -> {
             System.out.println(message.getEnvelope() .getRoutingKey());
             System.out.println(new String(message.getBody()));
        };

        CancelCallback cancelCallback = consumerTag -> {
            System.out.println("Consumer is cancel");
        };

        channel.basicConsume("whatsapp", true, deliveryCallback, cancelCallback);

        channel.close();
        connection.close();
	}

}