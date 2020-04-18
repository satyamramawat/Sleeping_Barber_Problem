/**
 * Author - Satyam Ramawat(19210520) 
 * Email - satyam.ramawat2@mail.dcu.ie
 * 
 * Java Threads - Sleeping Barbers Problem
 * 
 * This Java Program has been developed in order to fulfill need of 
 * CA670 Concurrent Programming Assignment 1,
 * 
 * Code has been encapsulated into Package "assignment_1_sleeping_barber_problem"
 */
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

import assignment_1_sleeping_barber_problem.CustomerGenerator;
import assignment_1_sleeping_barber_problem.Customer;
import assignment_1_sleeping_barber_problem.Barber;
import assignment_1_sleeping_barber_problem.BarberShop;

public class SleepingBarber{
 
    public static void main(String args[]) throws InterruptedException, ExecutionException
    {
        /**
         * To generate customers at interval of at random intervals, 
         * with mean M and standard deviation SD. CustomerGenerator object has been created,
         * and started with Thread object. 
         * CustomerGenerator class has been developed by use of Dynamic Dispatch(Runtime Polymorphism)
         * whereas, Reference of Random class and object of child class, in order to use nextGaussian function.
         * 
         * CustomerGenerator class needs object of BarberShop class,
         * because it has method of align customers in a queue of waiting chairs
         */

        //Try Catch block for Input Validation for Barber should be Number 
        try 
        { 
            Integer.parseInt(args[0]); 
        }  
        catch (NumberFormatException e)  
        { 
            System.out.println("Kindly Enter a Number for Barbers"); 
            System.exit(0);        
        }

        //Try Catch block for Input Validation for Customer Waiting Chair should be Number 
        try 
        { 
            Integer.parseInt(args[1]); 
        }  
        catch (NumberFormatException e)  
        { 
            System.out.println("Kindly Enter a Number for Customer Waiting Chairs"); 
            System.exit(0);        
        }

        //BarberShop and CustomerGenerator object creation                                            
        BarberShop shop = new BarberShop();                     
        CustomerGenerator cg = new CustomerGenerator(shop);

        //Initializing and accessing available core for Parellel processing using Multi-core 
        int cores = Runtime.getRuntime().availableProcessors();
        //Using available core in Executor Services for Multi Core
        final ExecutorService executor = Executors.newFixedThreadPool(cores);
        long startTime = System.currentTimeMillis();
        
        //Printing useful informations
        System.out.println("*****************************************************************************");
        System.out.println("Available processor cores is " + cores+"\n");
        //Used Input for Number of Barbers from command line argument, in order to reduce variable in Heap
        System.out.println("\nNumber of Barbers in the shop: "+Integer.parseInt(args[0])+" and each barber has its own chair");
        System.out.println("Number of Waiting Chairs in the shop: "+Integer.parseInt(args[1])+"\n");
        System.out.println("*****************************************************************************");

        // Creating N barbers Object Array
        Barber[] barber = new Barber[Integer.parseInt(args[0])]; 
        // Creating M Waiting Chairs, since nchair variable is Static variable i.e class variable  
        BarberShop.nchair = Integer.parseInt(args[1]);  
        
        //Starting Executor
        executor.execute(() -> {
            //Barber counter starts from 1
            int b_id =1;
            try {
                /**
                * Below Loop will generate N barbers and Start Thread of each barber
                * Variable b_id has been used to keep count of Barbers has been created dynamically
                */
                    for(int loop=0; loop<Integer.parseInt(args[0]); loop++ ){
                    barber[loop] = new Barber(shop,b_id);
                    b_id++;
                    System.out.println("Barber "+(loop+1)+" id: "+barber[loop].hashCode());
                    System.out.println("Barber "+(loop+1)+" waiting for customer and fall asleep");
                    Thread thbarber = new Thread(barber[loop]);
                    thbarber.start();}
                    Thread thcg = new Thread(cg);
                    thcg.start();
                }
            catch (Exception ex1) {
                Logger.getLogger(Executor.class.getName()).log(Level.SEVERE, null, ex1);
             }
         });
 
         executor.shutdown();
         if (executor.awaitTermination(1, TimeUnit.DAYS)) {
         } else {
             executor.shutdownNow();
         }
         
         long endTime = System.currentTimeMillis();
 
         System.out.println("\nParallel Execution Time : " + (endTime - startTime)
                 + " milliseconds.\n");
        
    }
}