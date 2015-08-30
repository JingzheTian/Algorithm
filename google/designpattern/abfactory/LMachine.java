package google.designpattern.abfactory;

public class LMachine implements IMachineFactory {

	@Override
	public IMemo getMemo() {
		// TODO Auto-generated method stub
		return new LMemo();
	}

	@Override
	public IProcessor getPro() {
		// TODO Auto-generated method stub
		return new LProcessor();
	}

}
