package consumer;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestConsumer {

	// ��Ϊ�˴� �ڲ��м�����������ֱ�Ӽ��������ļ����Զ�ִ�м�����
	public static void main(String[] args) {
		try {
			ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
					new String[] { "spring-context.xml" });
			context.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
