
public class SimProcess implements IProcess {
	
	
	private int pid;
	private String procName;
	private int numInstruct;
	private IRandomValueGenerator randValue;

	
	public SimProcess(int pid, String procName, int numInstruct, IRandomValueGenerator randValue)
	{
		this.pid=pid;
		this.procName=procName;
		this.numInstruct=numInstruct;
		this.randValue=randValue;
	
	}
	
	public ProcessState execute(int i){
		
		System.out.println("Process name: "+ procName + " PID: "+pid+ " executing instruction: "+(++i));
		
		if(i >= numInstruct)
			return ProcessState.FINISHED;
		
		if(randValue.getTrueWithProbability(15))
			return ProcessState.BLOCKED;
		
		else
			return ProcessState.READY;
	}

	
	public int getPid() {
		
		return pid;
	}

	public String getProcName() {
		return procName;
	}

}
