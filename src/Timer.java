

public class Timer  extends Thread {
	
	private int segundos;
	
	public String getTime()
	{
		int horas;
		int min;
		int secs;
		secs = segundos % 60;
		min = segundos/60;
		horas = segundos/3600;
		return horas+":"+min+":"+secs;
	}
	
	 public void run() 
	    {
		 segundos=0;
	       
		 while(true)
		 {
			 try {
				sleep(1000);
				 segundos++;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			 
		 }
		
		 
	    }
	 
}