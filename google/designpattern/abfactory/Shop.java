package google.designpattern.abfactory;

public class Shop {
	private IMachineFactory type;

	public Shop(IMachineFactory _type) {
		type = _type;
	}
	
	public void Assemble(){
		IProcessor p = type.getPro();
		IMemo m = type.getMemo();
		
		p.performance();
		m.performace();
	}
	
	
	public static void main(String[] args) {
		IMachineFactory factory = new HMachine();
		Shop s = new Shop(factory);
		s.Assemble();
		
	}
	
}
