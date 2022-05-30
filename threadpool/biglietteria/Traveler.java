
public class Traveler implements Runnable
{
	private int id;

	public Traveler(int id)
	{
		this.id = id;
	}

	public void run()
	{
		try
		{
			System.out.printf("Viaggiatore %d: sto acquistando il biglietto\n", id);
			long time = (long)(Math.random() * 1000);
			Thread.sleep(time);
			System.out.printf("Viaggiatore %d: ho acquistato il biglietto\n", id);
		} catch (InterruptedException e)
		{
			System.out.println("Traveller interrupted");
		}
	}

}
