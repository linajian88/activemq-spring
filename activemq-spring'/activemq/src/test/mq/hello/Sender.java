package test.mq.hello;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ActiveMQSession.DeliveryListener;
import org.apache.activemq.command.ActiveMQTextMessage;

public class Sender {

	public static void main(String[] args) throws Exception {
		// ��������
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnectionFactory.DEFAULT_USER,
				ActiveMQConnectionFactory.DEFAULT_PASSWORD, "tcp://localhost:61616");
		// ����һ��connection
		Connection connection = connectionFactory.createConnection();
		// Ĭ�Ϲر�Ҫ����
		connection.start();
		// ����session,�Ƿ���������,ǩ��ģʽ
		// Session session = connection.createSession(Boolean.TRUE,
		// Session.AUTO_ACKNOWLEDGE);
		// ʹ�ô���ķ�ʽǩ��
		Session session = connection.createSession(Boolean.TRUE, Session.CLIENT_ACKNOWLEDGE);

		// session����DestinationĿ�Ķ���(����Queue)
		// ��������Ŀ�ĵؽ�ʲô
		// �����ߺ�������ָ��һ�������ֲ����õ�����
		Destination destination = session.createQueue("queue1");
		// ������
		MessageProducer messageProducer = session.createProducer(destination);
		// ���ó־û��ͷǳ־û�������,�־û����Է������ݿ�֮���
		// �˴�Ϊ�ǳ־û�

		messageProducer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

		// ������Ϣ����

		for (int a = 0; a < 5; a++) {
			ActiveMQTextMessage textMessage = (ActiveMQTextMessage) session.createTextMessage("���ڵ�a=" + a);
			messageProducer.send(textMessage);
		}
		session.commit();
		// close�Ժ���Զ��ر��¼��ڵ�
		if (connection != null)
			connection.close();
	}
}
