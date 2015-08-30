package google.designpattern.abfactory;

public class HMachine implements IMachineFactory {

	@Override
	public IMemo getMemo() {
		// TODO Auto-generated method stub
		return new HMemo();
	}

	@Override
	public IProcessor getPro() {
		// TODO Auto-generated method stub
		return new HProcessor();
	}

}
