import akka.actor.UntypedActor;

public class PrimeListener extends UntypedActor{

	
	public void onReceive(Object message) throws Exception {
		if(message instanceof Result){
			Result result=(Result)message;
			System.out.println("Results: ");
			for(long value : result.getResults()){
			 System.out.println(value);
			}
			getContext().system().shutdown();
		}else{
			unhandled(message);
		}
		
	}

	
}
