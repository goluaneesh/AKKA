import akka.actor.UntypedActor;

public class PrimeWorker extends UntypedActor {

	public void onReceive(Object message) throws Exception {

		if (message instanceof NumberRangeMessage) {
			NumberRangeMessage numberRangeMessage = (NumberRangeMessage) message;
			System.out.println("NUMBER RANGE : "+numberRangeMessage.getStartNumber()+" To "+numberRangeMessage.getEndNumber());
			Result result=new Result();
			for(long i=numberRangeMessage.getStartNumber();i<=numberRangeMessage.getEndNumber();i++){
				if(isPrime(i)){
					result.getResults().add(i);
				}
			}
			getSender().tell(result, getSelf());
		} else {
			unhandled(message);
		}

	}

	private boolean isPrime(long n) {
		long i;
		if (n == 1 || n == 2) {
			return true;
		}
		for (i = 2; i * i <= n; i++) {
			if (n % i == 0) {
				return false;
			}
		}

		return true;
	}

}
