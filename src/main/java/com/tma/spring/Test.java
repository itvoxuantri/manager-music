package com.tma.spring;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.management.JMX;
import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import javax.naming.Context;
import javax.naming.InitialContext;

import org.wildfly.naming.client.WildFlyInitialContextFactory;

import com.tma.spring.entity.Music;
import com.tma.spring.mbean.MusicServiceImplMBean;

public class Test {

	public static void main(String[] args) throws URISyntaxException, Exception {
		JMXConnector jmxc = null;
		InitialContext initialContext = null;
		Connection connection = null;
		MessageConsumer consumer = null;
		try {
			// Remote jmx to wildFly
			JMXServiceURL url = new JMXServiceURL("service:jmx:remote+http://localhost:9990");
			jmxc = JMXConnectorFactory.connect(url, null);
			MBeanServerConnection mbsc = jmxc.getMBeanServerConnection();

			ObjectName mbeanMusic = new ObjectName("bean:name=musicService");

			MusicServiceImplMBean musicService = JMX.newMBeanProxy(mbsc, mbeanMusic, MusicServiceImplMBean.class, true);

			// Remote jms to wildFly
			Properties env = new Properties();
			env.put(Context.INITIAL_CONTEXT_FACTORY, WildFlyInitialContextFactory.class.getName());
			env.put(Context.PROVIDER_URL, "remote+http://localhost:8080");
			env.put(Context.SECURITY_PRINCIPAL, "admin");
			env.put(Context.SECURITY_CREDENTIALS, "admin");
			initialContext = new InitialContext(env);
			ConnectionFactory connectionFactory = (ConnectionFactory) initialContext
					.lookup("jms/RemoteConnectionFactory");
			connection = connectionFactory.createConnection("admin1", "admin1");
			connection.start();
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			Destination destination = (Destination) initialContext.lookup("jms/topic/testTopic");

			// MessageConsumer is used for receiving (consuming) messages
			consumer = session.createConsumer(destination);

			// Here we receive the message.
			consumer.setMessageListener(new MessageListener() {
				public void onMessage(Message message) {
					TextMessage msg = (TextMessage) message;
					try {
						System.out.println(msg.getText());
					} catch (JMSException e) {
						e.printStackTrace();
					}
				}

			});
			while (true) {
				System.out.println("Welcome to Manager Music");
				System.out.println("List Music");
				List<Music> musics = musicService.displayRecords();
				musics.stream().forEach(music -> System.out.println(music.toString()));
				System.out.println("***************************************************************");
				System.out.println("-Enter 0 to Exit.");
				System.out.println("-Enter 1 to Create Music.");
				System.out.println("-Enter 2 to Update Music.");
				System.out.println("-Enter 3 to Delete Music.");
				System.out.println("***************************************************************");
				int inputManager;
				@SuppressWarnings("resource")
				Scanner scanner = new Scanner(System.in);
				System.out.println("You can enter number to manager list music...");
				inputManager = scanner.nextInt();
				Music music = new Music();
				String inputInfoMusic = null;
				switch (inputManager) {

				case 0:
					break;
				case 1:
					inputInfoMusic = scanner.nextLine();
					System.out.println("Please Enter Name Music");
					inputInfoMusic = scanner.nextLine();
					music.setName(inputInfoMusic);
					System.out.println("Please Enter Name Author Music...");
					inputInfoMusic = scanner.nextLine();
					music.setNameAuthor(inputInfoMusic);
					System.out.println("Please Enter Name Category Music...");
					inputInfoMusic = scanner.nextLine();
					music.setNameCategory(inputInfoMusic);
					musicService.createRecord(music);
					break;
				case 2:

					break;
				case 3:

					break;
				default:
					break;
				}
				System.out.println("GoodBye");
				break;
			}
		} finally {
			if (jmxc != null || initialContext != null || consumer != null || connection != null) {
				try {
					jmxc.close();
					initialContext.close();
					consumer.close();
					connection.close();
				} catch (Exception e) {
				}

			}
		}
	}

}
