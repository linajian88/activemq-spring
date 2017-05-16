package test.mq.hello;

import java.text.DecimalFormat;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTextMessage;

public class Receiver {

	public static void main(String[] args) throws Exception {
		// ��������
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnectionFactory.DEFAULT_USER,
				ActiveMQConnectionFactory.DEFAULT_PASSWORD, "tcp://localhost:61616");
		// ����һ��connection
		Connection connection = connectionFactory.createConnection();
		// Ĭ�Ϲر�Ҫ����
		connection.start();
		// ����session,�Ƿ���������,ǩ��ģʽ(�ֶ�ǩ��)
		// ��������ֶ�ǩ�յķ����Ż������Ϣȷ��
		// һ�����ֶ���
		Session session = connection.createSession(Boolean.FALSE, Session.CLIENT_ACKNOWLEDGE);

		// session����DestinationĿ�Ķ���(����Queue)
		// ��������Ŀ�ĵؽ�ʲô
		// �����ߺ�������ָ��һ�������ֲ����õ�����
		Destination destination = session.createQueue("queue1");
		// ������
		MessageConsumer messageConsumer = session.createConsumer(destination);
		
		
		while (true) {
			TextMessage msg = (TextMessage) messageConsumer.receive();
			// �ֹ�ǩ�գ�����һ���̣߳�tcp����֪�Ѿ����յ���Ϣ
			msg.acknowledge();
			if (msg == null)
				break;
			System.out.println("Message=" + msg.getText());
		}
		// close�Ժ���Զ��ر��¼��ڵ�
		if (connection != null)
			connection.close();

	}
	
}
