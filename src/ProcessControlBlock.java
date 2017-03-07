
public class ProcessControlBlock {

	private IProcess processRef;
	private int instruction;
	private int reg1;
	private int reg2;
	private int reg3;
	private int reg4;

	public ProcessControlBlock(IProcess processRef) {
		this.processRef = processRef;
	}

	public int getInstruction(){
		return instruction;
	}
	
	public void setInstruction(int instruction){
		this.instruction = instruction;
	}
	public IProcess getProcessRef() {
		return processRef;
	}

	public int getReg1() {
		return reg1;
	}

	public void setReg1(int reg1) {
		this.reg1 = reg1;
	}

	public int getReg2() {
		return reg2;
	}

	public void setReg2(int reg2) {
		this.reg2 = reg2;
	}

	public int getReg3() {
		return reg3;
	}

	public void setReg3(int reg3) {
		this.reg3 = reg3;
	}

	public int getReg4() {
		return reg4;
	}

	public void setReg4(int reg4) {
		this.reg4 = reg4;
	}

	public String displayRegisters(){
		return "R1: "+reg1+" R2: "+reg2 +" R3: "+ reg3+ "R4: "+reg4;
	}
}
