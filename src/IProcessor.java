
public interface IProcessor {

	public void setProcess(IProcess processRef);
	
	public IProcess getProcess();
	
	public void setInstruction(int instruction);
	
	public int getInstruction();
	
	public ProcessState executeNextInstruction();
	
	public void setRegisterValue(int register, int val);
	
	public int getRegister(int register);
	
}
