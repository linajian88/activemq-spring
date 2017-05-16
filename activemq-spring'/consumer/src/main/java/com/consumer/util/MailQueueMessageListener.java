package com.consumer.util;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.SessionAwareMessageListener;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.consumer.model.Mail;
import com.consumer.service.MailService;

@Component
public class MailQueueMessageListener implements SessionAwareMessageListener<Message> {

	@Autowired
	private JmsTemplate jmsTemplate;
	@Autowired
	private Destination mailQueue;
	@Autowired
	private MailService mailService;

	// �˴��õ��߳�ͬ�����ƣ���ʾֻ��һ�����н���
	public synchronized void onMessage(Message message, Session session) throws JMSException {

		TextMessage msg = (TextMessage) message;
		final String ms = msg.getText();
		System.out.println("�յ���Ϣ��" + ms);

		// json�ṩ����Ӧ��ת������
		Mail mail = JSONObject.parseObject(ms, Mail.class);
		if (mail == null) {
			return;
		}
		try {
			// ִ�з���ҵ��
			mailService.mailSend(mail);
		} catch (Exception e) {
			// �����쳣�����·Żض���
			// jmsTemplate.send(mailQueue, new MessageCreator() {
			// @Override
			// public Message createMessage(Session session) throws
			// JMSException {
			// return session.createTextMessage(ms);
			// }
			// });
			e.printStackTrace();
		}
	}
}
