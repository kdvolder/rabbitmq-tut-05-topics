import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

public class ReceiveLogsTopic {

	private String[] bindingKeys;

	public ReceiveLogsTopic(String... bindingKeys) {
		this.bindingKeys = bindingKeys;
	}
	
	private final static String EXCHANGE_NAME = "logs-topic";

	public void run() throws Exception {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		String queueName = channel.queueDeclare().getQueue();
		
		channel.exchangeDeclare(EXCHANGE_NAME, "topic");
		for (String bindingKeys : bindingKeys) {
			System.out.println("Subscribing topic: "+bindingKeys);
			channel.queueBind(queueName, EXCHANGE_NAME, bindingKeys);
		}
		System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

		QueueingConsumer consumer = new QueueingConsumer(channel);
		channel.basicConsume(queueName, /*autoack*/true, consumer);
		while (true) {
			QueueingConsumer.Delivery delivery = consumer.nextDelivery();
			String routingKey = delivery.getEnvelope().getRoutingKey();
			String message = new String(delivery.getBody());
			System.out.println("["+routingKey+"] " + message);
		}
	}

}
