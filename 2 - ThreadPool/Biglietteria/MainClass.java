import java.util.concurrent.*;

public class MainClass
{
	public static void main(String[] args)
	{
		ExecutorService ticket_room = new ThreadPoolExecutor(
				5,
				5,
				120,
				TimeUnit.SECONDS,
				new ArrayBlockingQueue<Runnable>(10)
		);

		for(int i = 0; i < 50; i++)
		{
			try
			{
				Thread.sleep(50);
			}catch (InterruptedException e)
			{
				System.out.println("Interrupt");
			}

			try
			{
				ticket_room.execute(new Traveler(i));
			} catch (RejectedExecutionException e)
			{
				System.out.printf("Traveler %d: sala esaurita\n", i);
			}
		}

		ticket_room.shutdown();
	}
}
