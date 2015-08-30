package google.designpattern.factory;

public class PicSenderFactory implements Producer {

	@Override
	public Sender produce() {
		// TODO Auto-generated method stub
		return new PicSender();
	}

}
