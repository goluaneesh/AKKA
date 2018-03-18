import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.routing.RoundRobinRouter;

public class PrimeMaster extends UntypedActor {
	private final ActorRef workerRouter;
	private final ActorRef listener;
	
	private final int numberOfWorkers;
	private int numberOfResults=0;
	private Result finalResult=new Result();
	
	public PrimeMaster(final int numberOfWorker,ActorRef listener){
		this.listener=listener;
		this.numberOfWorkers=numberOfWorker;
		workerRouter=this.getContext().actorOf(new Props(PrimeWorker.class).withRouter(new RoundRobinRouter(numberOfWorkers)));
		
	}
	
	
	public void onReceive(Object message) throws Exception {
		if(message instanceof NumberRangeMessage){
			NumberRangeMessage numberRangeMessage=(NumberRangeMessage)message;
			long numberOfNumbers=numberRangeMessage.getEndNumber()-numberRangeMessage.getStartNumber();
			long segmentLength=numberOfNumbers/numberOfWorkers;
			for(int i=0;i<=numberOfWorkers;i++){
				long startNumber=numberRangeMessage.getStartNumber()+i*segmentLength;
				long endNumber =startNumber+segmentLength-1;
				if(i==numberOfWorkers-1){
					endNumber=numberRangeMessage.getEndNumber();
				}
				
				workerRouter.tell(new NumberRangeMessage(startNumber, endNumber), getSelf());
			}
			
			
		}else if(message instanceof Result){
			Result result=(Result)message;
			finalResult.getResults().addAll(result.getResults());
			numberOfResults++;
			if(numberOfResults>=numberOfWorkers){
				listener.tell(finalResult,getSelf());
				getContext().stop(getSelf());
			}
		}
		else{
			unhandled(message);
		}
		
	}

}
