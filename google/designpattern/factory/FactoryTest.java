package google.designpattern.factory;

public class FactoryTest {
	
	public static void main(String[] args) {
		Producer p = new MailSenderFactory();
		Sender s = p.produce();
		s.send();
	}
	
	
}
