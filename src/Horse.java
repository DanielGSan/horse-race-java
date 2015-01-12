

public class Horse extends Thread
{
    private String name;
    private int avance;
    private int position;
    private float ratio;
    private boolean winner;
 
    public Horse() 
    {
    	this.name = "";
    	this.avance = 0;
    	this.position = 0;
    	this.ratio = (float) 0.0;
    	this.winner = false;
    }
    
    public Horse(String s) 
    {
       this.name = s;
    }

    public String getNombre() 
    {
		return name;
	}

	public void setNombre(String nombre) 
	{
		this.name = nombre;
	}

	public int getAvance() 
	{
		return avance;
	}

	public void setAvance(int avance)
	{
		this.avance = avance;
	}

	public int getPosicion() 
	{
		return position;
	}

	public void setPosicion(int posicion)
	{
		this.position = posicion;
	}
	
    public float getRatio() {
		return ratio;
	}

	public void setRatio(float ratio) 
	{
        ratio = (float)(Math.round(ratio*100.0)/100.0);
		this.ratio = ratio;
	}

	public boolean isGanador() {
		return winner;
	}

	public void setGanador(boolean ganador) {
		this.winner = ganador;
	}

	public void run()
    {
        int duerme = 0;

        if(this.ratio <= 1.50){
            duerme = 1400;
        }
        else if (this.ratio > 1.50 & this.ratio <= 2.00){
            duerme = 1480;
        }
        else if (this.ratio > 2.00 & this.ratio <= 2.50){
            duerme = 1520;
        }
        else if (this.ratio > 2.50 & this.ratio <= 3.00){
            duerme = 1590;
        }
        else if (this.ratio > 3.00 & this.ratio <= 3.50){
            duerme = 1630;
        }
        else if (this.ratio > 3.50){
            duerme = 1690;
        }

        try {
        	while(position < 50)
        	{
                sleep((int)(Math.random()*duerme));
        		position++;
        	}
        }
        catch( InterruptedException e )
        {
            ;
        }
    }
}