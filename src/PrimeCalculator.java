import akka.actor.Actor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.actor.UntypedActorFactory;
//hello change
public class PrimeCalculator {

	public void calculate(long startNumber,long endNumber){
		ActorSystem actorSystem=ActorSystem.create("primeCalculator");
		
		final ActorRef primeListener=actorSystem.actorOf(new Props(PrimeListener.class),"primeListener");
		
		ActorRef primeMaster=actorSystem.actorOf(new Props(new UntypedActorFactory() {
			
			@Override
			public Actor create() throws Exception {
				return new PrimeMaster(100,primeListener);
			}
		}), "primeMaster");
	primeMaster.tell(new NumberRangeMessage(startNumber, endNumber),primeMaster);
	}
	
	public static void main(String[] args){
		PrimeCalculator primeCalculator =new PrimeCalculator();
		primeCalculator.calculate(1,100000);	
	}
	
}
