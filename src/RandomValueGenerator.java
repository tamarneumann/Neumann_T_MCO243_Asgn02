import java.util.Random;

public class RandomValueGenerator implements IRandomValueGenerator {

	private Random rand;
	
	public RandomValueGenerator()
	{
		rand = new Random(System.currentTimeMillis());
	}
	
	public int getNextInt(){
		
		return rand.nextInt();
	}
	
	public boolean getTrueWithProbability(double prob){
		return rand.nextInt(100)+1 <= prob;
	}
	
}
