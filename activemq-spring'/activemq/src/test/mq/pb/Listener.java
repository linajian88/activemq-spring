package test.mq.pb;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

	class Listerner implements MessageListener {

		@Override
		public void onMessage(Message message) {
			try {
				System.out.println("������03���յ���Ϣ��" + ((TextMessage) message).getText());
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}
}
