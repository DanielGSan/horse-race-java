public class Caballo extends Thread
{
    private String nombre;
    private int avance;
    private int posicion;
    private float ratio;
    private boolean ganador;
 
    public Caballo() 
    {
    	this.nombre = "";
    	this.avance = 0;
    	this.posicion = 0;
    	this.ratio = (float) 0.0;
    	this.ganador = false;
    }
    
    public Caballo(String s) 
    {
       this.nombre = s;
    }

    public String getNombre() 
    {
		return nombre;
	}

	public void setNombre(String nombre) 
	{
		this.nombre = nombre;
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
		return posicion;
	}

	public void setPosicion(int posicion)
	{
		this.posicion = posicion;
	}
	
    public float getRatio() {
		return ratio;
	}

	public void setRatio(float ratio) 
	{
		this.ratio = ratio;
	}

	public boolean isGanador() {
		return ganador;
	}

	public void setGanador(boolean ganador) {
		this.ganador = ganador;
	}

	public void run() 
    {
        int duerme = 0;

        if(this.ratio <= 1.50){
            duerme = 1400;
        }
        else if (this.ratio > 1.50 & this.ratio <= 2.00){
            duerme = 1500;
        }
        else if (this.ratio > 2.00 & this.ratio <= 2.50){
            duerme = 1600;
        }
        else if (this.ratio > 2.50 & this.ratio <= 3.00){
            duerme = 1699;
        }
        else if (this.ratio > 3.00 & this.ratio <= 3.50){
            duerme = 1750;
        }
        else if (this.ratio > 3.50){
            duerme = 1850;
        }

        try {
        	while(posicion < 50)
        	{
                sleep((int)(Math.random()*duerme));
        		posicion++;
        	}	
        } 
        catch( InterruptedException e ) 
        {
            ;
        }
    }
}
