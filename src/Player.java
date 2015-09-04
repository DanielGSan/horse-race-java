import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Player
{
	private String nombre;
	private float saldo;
	private int caballo;
	private float apuesta;
	
	public Player() 
	{
		this.nombre = "";
		this.saldo = (float) 0.0;
	}
	
	public Player(String nombre, float saldo) 
	{
		this.nombre = nombre;
		this.saldo = saldo;
	}

	public String getNombre() 
	{
		return nombre;
	}

	public void setNombre(String nombre)
	{
		this.nombre = nombre;
	}

	public float getSaldo() 
	{
		return saldo;
	}

	public void setSaldo(float saldo) 
	{
        saldo = (float)(Math.round(saldo*100.0)/100.0);
		this.saldo = saldo;
	}

	public int getCaballo() 
	{
		return caballo;
	}

	public void setCaballo(int caballo) 
	{
		this.caballo = caballo;
	}
	
	public void setApuesta(float dinero)
	{
		this.apuesta = dinero;
		setSaldo(getSaldo()-dinero);
	}
	public float getApuesta()
	{
		return apuesta;
	}

	public void leerDatos()
	{ 
		BufferedReader teclado = new BufferedReader (new InputStreamReader(System.in));

		try {
			 	System.out.print("Enter your name: ");
			 	setNombre(teclado.readLine());
			 	setSaldo(10);
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}

}