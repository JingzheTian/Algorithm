package google.designpattern.factory;

public class MailSenderFactory implements Producer{

	@Override
	public Sender produce() {
		// TODO Auto-generated method stub
		return new MailSender();
	}
	
}
