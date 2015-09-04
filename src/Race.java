

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Race 
{
	private ArrayList <Player> jugadores;
	private ArrayList <Horse> caballos;
	private Timer cron;

	public Race()
	{
		jugadores = new ArrayList();
		caballos = new ArrayList();
        cron = new Timer();
	}
	
	public static void main(String[] args) 
	{
		BufferedReader teclado = new BufferedReader (new InputStreamReader(System.in));
		String respuesta = null;
		Race carrera= new Race ();
		
		carrera.crearJugadores();
		do{
			carrera.apostar();


            try {
                Thread.sleep(1500);
                System.out.println("\nThe horses are placed in the starting line");
                Thread.sleep(1500);
                System.out.println("The race will start in 10 seconds\n");
                Thread.sleep(10000);
                System.out.println("GOOD LUCK!! BAAAAANNNNNNG!!!!\n");
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }

            carrera.empezar();
			
			carrera.repartirPremios();

            carrera.borrarApuestas();
			
			

			try {
				 	System.out.print("\nOther race? (S/N)");
				 	respuesta = teclado.readLine();
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
			
		}while(respuesta.equalsIgnoreCase("s"));
		
		System.out.print("\nThanks for betting in BetDaniel'");
		
		
	}

    // Creacion de jugadores


    public void crearJugadores()
    {
        Player juga;

        String linea = null;
        int nJugadores = leerInt("Enter number of players: ");

        for(int i=0; i<nJugadores; i++)
        {
            juga = new Player();
            juga.leerDatos();
            jugadores.add(juga);
            System.out.println("Player "+(i+1)+ " created\n");
        }
    }


    // Apuestas de los jugadores

    public void apostar()
    {
        caballos.clear();
        Horse caba;
        boolean wrong;
        float apuesta;

        int nCaballos = (int)(Math.random()*5 + 2);
        System.out.println("\n\nIn this race there are "+nCaballos+" horses");

        for(int i=0; i<nCaballos; i++){
            caba = new Horse("Horse "+(i+1));
            caba.setRatio((float)(Math.random()*3+1));
            caballos.add(caba);
            System.out.printf("The " + caba.getNombre() + " is paid at " + "%.2f\n",caba.getRatio());
        }


        for(int i=0; i<jugadores.size(); i++){
            int c = 0;

            if(jugadores.get(i).getSaldo() <= 0){
                System.out.println(jugadores.get(i).getNombre() + " your balance is 0 or less Euros and you will can not participate.");
            }
            else{

                do{
                    wrong = false;
                    c = leerInt("\n" + jugadores.get(i).getNombre()+ " enter the number of the winner horse: ");

                    if(c > nCaballos || c == 0){
                        System.out.println("You have entered an unexisting horse.\n");
                        wrong = true;
                    }
                }while(wrong);

                System.out.println("Your balance is " + jugadores.get(i).getSaldo()+ " Euros.");

                do{
                    wrong = false;
                    apuesta = leerFloat("Enter how much money you want to bet in the horse "+(c)+": ");

                    if(apuesta > jugadores.get(i).getSaldo() || apuesta <= 0){
                        System.out.println("Your bet is more than your actual balance, 0 Euros or less.\n");
                        wrong = true;
                    }

                }while(wrong);
                jugadores.get(i).setApuesta(apuesta);

            }

            jugadores.get(i).setCaballo(c-1);

        }
    }

    // Empieza la carrera, cronometro en marcha

    public void empezar () 
    {
    	cron = new Timer();
    	int contador = 0;
    	int lastpos = 0;
    	
        // Arrancamos los caballos  y el cronometro
        cron.start();

        for(int i=0; i<caballos.size(); i++){
			caballos.get(i).start();
		}
    
       contador = caballosVivos();
        
        // Esperamos a que el ganador acabe, mientras no acabe ninguno seguimos dentro del while
    	while (contador == caballos.size()){

    		int totPos;

    		totPos = totalPosiciones();
        		
    		if(lastpos != totPos){
    			for (int i=0; i<30; i++){
    				System.out.println();
    			}
    				
    			for(int i=0; i<caballos.size(); i++){
    				System.out.print("\n"+caballos.get(i).getNombre()+": ");
    				for(int j=0; j<caballos.get(i).getPosicion(); j++)
    		        {
    		        	System.out.print("*");
    		        }
            	}
    		}
    			
    		lastpos = totalPosiciones();
    		
    		contador = caballosVivos();
    	}
    	
    	for(int i=0; i<caballos.size(); i++){
			if(caballos.get(i).isAlive() == true){
				caballos.get(i).stop();
			}
			else{
				System.out.println("\nThe horse number "+(i+1)+" is the winner.");
				caballos.get(i).setGanador(true);
			}
		}	
    	cron.stop();
        System.out.println("\nThe race time has been: "+cron.getTime() + "\n");
        
    }

    // Reparto de premios a los ganadores.

    public void repartirPremios()
    {
    	int ganador = 0;
    	
    	for(int i=0; i<caballos.size(); i++){
    		if(caballos.get(i).isGanador() == true){
    			ganador = i;
    		}
		}	
    	
    	for(int i=0; i<caballos.size(); i++){
			System.out.printf("El " + caballos.get(i).getNombre() + " was paid " + "%.2f\n",caballos.get(i).getRatio());
		}
    	
    	for(int i=0; i<jugadores.size(); i++)
    	{
    		float ganancias = 0;
    		
	    	if(jugadores.get(i).getCaballo() == ganador)
	    	{
	    			ganancias = jugadores.get(i).getApuesta() + jugadores.get(i).getApuesta() * caballos.get(ganador).getRatio();
                    ganancias = (float)(Math.round(ganancias*100.0)/100.0);
	    			
	    			System.out.println(jugadores.get(i).getNombre() + " bet on the horse " + (jugadores.get(i).getCaballo()+1) + " and won.");
	    			System.out.println(jugadores.get(i).getNombre() + " bet " + jugadores.get(i).getApuesta() + " and won " + ganancias);
	    			
	    			jugadores.get(i).setSaldo(jugadores.get(i).getSaldo() + ganancias);
	    			
	    			System.out.println("Total balance: " + jugadores.get(i).getNombre() + " " + jugadores.get(i).getSaldo() + " Euros.\n");
	    	}
	    	else{
                if(jugadores.get(i).getCaballo() != -1){
	    		    System.out.println(jugadores.get(i).getNombre() + " bet on the horse " + (jugadores.get(i).getCaballo()+1) + " and not won.");
                }
    	    }
        }
    }


    // Reiniciar apuestas de todos los jugadores

    public void borrarApuestas(){
        for(int i=0; i<jugadores.size(); i++){
           jugadores.get(i).setCaballo(-1);
        }
    }

    // Ver en que posicion esta cada caballo
   
    public int totalPosiciones(){
    	
    	int totPos = 0;
		
		for(int i=0; i<caballos.size(); i++){
			
			totPos += caballos.get(i).getPosicion();
		}	
		
		return totPos;
    }


    // Ver que caballo esta vivo

    public int caballosVivos(){
    	int contador = 0;
    	
    	for(int i=0; i<caballos.size(); i++){
			if(caballos.get(i).isAlive() == true){
				contador++;
			}	
		}
    	
    	return contador;
    }


    // Lectura de datos
    
    public int leerInt(String texto)
    {
    	String linea = null;
    	int numero = 0;

    	DataInputStream in = new DataInputStream(System.in);
    	
		try
		{
			System.out.print(texto);
			linea = in.readLine();
		}
		
		catch (IOException e)
		{
			System.out.println (e.getMessage());
		}
			
		try
		{
			numero = Integer.parseInt(linea);
		}
		catch (NumberFormatException e)
		{
			System.out.println (e.getMessage());
		}
    	
		return numero;
    }
    
    public float leerFloat(String texto)
    {
    	String linea = null;
    	float numero = 0;

    	DataInputStream in = new DataInputStream(System.in);
    	
		try
		{
			System.out.print(texto);
			linea = in.readLine();
		}
		
		catch (IOException e)
		{
			System.out.println (e.getMessage());
		}
			
		try
		{
			numero = Float.parseFloat(linea);
		}
		catch (NumberFormatException e)
		{
			System.out.println (e.getMessage());
		}
    	
		return numero;
    }
    
}