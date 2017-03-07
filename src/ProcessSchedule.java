
public class ProcessSchedule {

	public static void main(String[] args) {
		final int QUANTUM = 5;

		IProcessor processor;
		QueueArrayList<ProcessControlBlock> processReady = new QueueArrayList<ProcessControlBlock>();
		QueueArrayList<ProcessControlBlock> processBlocked = new QueueArrayList<ProcessControlBlock>();
		ProcessControlBlock pcb = null;
		ProcessState procSt;
		IRandomValueGenerator randValGen = new RandomValueGenerator();

		processReady.enque(new ProcessControlBlock(new SimProcess(1, "Process 1", 250, randValGen)));
		processReady.enque(new ProcessControlBlock(new SimProcess(2, "Process 2", 100, randValGen)));
		processReady.enque(new ProcessControlBlock(new SimProcess(3, "Process 3", 300, randValGen)));
		processReady.enque(new ProcessControlBlock(new SimProcess(4, "Process 4", 210, randValGen)));
		processReady.enque(new ProcessControlBlock(new SimProcess(5, "Process 5", 150, randValGen)));
		processReady.enque(new ProcessControlBlock(new SimProcess(6, "Process 6", 400, randValGen)));
		processReady.enque(new ProcessControlBlock(new SimProcess(7, "Process 7", 375, randValGen)));
		processReady.enque(new ProcessControlBlock(new SimProcess(8, "Process 8", 330, randValGen)));
		processReady.enque(new ProcessControlBlock(new SimProcess(9, "Process 9", 190, randValGen)));
		processReady.enque(new ProcessControlBlock(new SimProcess(10, "Process 10", 340, randValGen)));

		processor = new SimProcessor(randValGen);

		// get the first process for the PCB
		pcb = processReady.dequeue();

		// set the process in the processor.
		processor.setProcess(pcb.getProcessRef());

		// set the current instruction from the pcb on the processor.
		processor.setInstruction(pcb.getInstruction());

		// set the registers in the processor from the PCB
		processor.setRegisterValue(0, pcb.getReg1());
		processor.setRegisterValue(1, pcb.getReg2());
		processor.setRegisterValue(2, pcb.getReg3());
		processor.setRegisterValue(3, pcb.getReg4());

		// set the first process state to ready.
		procSt = ProcessState.READY;

		int numQuantum = 0; // keep track of the number of processes for the quantum.

		for (int i = 1; i <= 3200; i++) {

			System.out.print("Step " + i + ": ");

			if (procSt == ProcessState.READY && numQuantum < QUANTUM) {

				procSt = processor.executeNextInstruction();
				numQuantum++;

				if (procSt == ProcessState.FINISHED) {
					System.out.println(("***Process complete"));
				}

			}

			else {
				if (procSt != ProcessState.FINISHED) {

					// set the current instruction
					pcb.setInstruction(processor.getInstruction());

					// set the registers in the PCB from the processor
					pcb.setReg1(processor.getRegister(0));
					pcb.setReg2(processor.getRegister(1));
					pcb.setReg3(processor.getRegister(2));
					pcb.setReg4(processor.getRegister(3));

					// display the context switch.
					System.out.println("Context switch: Restoring process " + pcb.getProcessRef().getPid());
					System.out.println("\tInstruction: " + pcb.getInstruction() + " " + pcb.displayRegisters());

					if (numQuantum == QUANTUM) {
						System.out.println("***Quantum Expired");
						processReady.enque(pcb);

					} else if (procSt == ProcessState.BLOCKED) {
						processBlocked.enque(pcb);
						System.out.println("****Process blocked.");

					}

					numQuantum = 0;
				}

				if (processBlocked.isEmpty() && processReady.isEmpty()) {
					System.out.println("All Processes complete");
					break;
				}

				if (processReady.isFull()) {

					// get the first process from the PCB
					pcb = processReady.dequeue();

					// set the process in the processor.
					processor.setProcess(pcb.getProcessRef());

					// set the current instruction
					processor.setInstruction(pcb.getInstruction());

					// set the registers in the processor from the PCB
					processor.setRegisterValue(0, pcb.getReg1());
					processor.setRegisterValue(1, pcb.getReg2());
					processor.setRegisterValue(2, pcb.getReg3());
					processor.setRegisterValue(3, pcb.getReg4());

					// set the first process state to ready.
					procSt = ProcessState.READY;
				}

			}
			
			//go through the blocked processes and wake them up with 30% probability.
			if (processBlocked.isFull()) {
				for (int size = 0; size < processBlocked.size(); size++) {
					if (randValGen.getTrueWithProbability(30)) {
						processReady.enque(processBlocked.dequeue());
					} else {
						processBlocked.enque(processBlocked.dequeue());
					}
				}

			}

		}

	}

}
