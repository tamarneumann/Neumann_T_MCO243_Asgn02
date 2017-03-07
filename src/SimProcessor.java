
public class SimProcessor implements IProcessor {

	private IRandomValueGenerator randValue;
	private IProcess processRef;
	private int[] registers;
	private int instruction;
	
	public SimProcessor(IRandomValueGenerator randValue)
	{
		this.randValue = randValue;
		registers = new int[4];
		instruction=0;
	}

	
	public void setProcess(IProcess processRef) {
		this.processRef = processRef;
		
	}

	
	public IProcess getProcess() {
		return processRef;
	}

	
	public void setInstruction(int instruction) {
		this.instruction = instruction;		
	}

	
	public int getInstruction() {
	    return instruction;
	}


	public ProcessState executeNextInstruction() {
		
		ProcessState procState= processRef.execute(instruction);
		
		instruction++;
		
		return procState;
	}

	
	public void setRegisterValue(int register, int val) {
		
		registers[register] = val;	
	}


	public int getRegister(int register) {
		return randValue.getNextInt();
	}
	
	
}
