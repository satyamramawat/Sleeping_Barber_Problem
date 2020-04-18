/**
 * Author - Satyam Ramawat(19210520)
 * Email - satyam.ramawat2@mail.dcu.ie
 * 
 * This is the Main Functional class of this project
 * Various Functionality has been loosely Coupled
 */
package assignment_1_sleeping_barber_problem;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.Random;

import assignment_1_sleeping_barber_problem.CustomerGenerator;
import assignment_1_sleeping_barber_problem.Customer;
import assignment_1_sleeping_barber_problem.Barber;

public class BarberShop extends Random
{
    /**
     * Below serialVersionUID key from JDK 1.1 for interoperability
     * Which is required in order to leverage synchronization between Random Generation
     */
    private static final long serialVersionUID = 1L;
    public static int nchair;
    int b_id;
    List<Customer> WaitingCustomer;

    // Below Creating object for Random Interval object to Cut the hair at Mean and STD
    Random normal_distribution_generator = new Random();  
     // Below Constructor creates list of customer via LinkedList java collection.
    public BarberShop()
    {
        WaitingCustomer = new LinkedList<Customer>();
    }
 
    public void cutHair(int b_id)
    {
        this.b_id=b_id;
        Customer customer;

        synchronized (WaitingCustomer)
        {
            // Below logic check the number of customers in the Queue.
            while(WaitingCustomer.size()==0)
            {
                System.out.println("Barber "+b_id+" fall asleep while waiting for customer.");
                try
                {
                    WaitingCustomer.wait();
                }
                catch(InterruptedException e)
                {
                    e.printStackTrace();
                }
            }

            System.out.println("Barber "+b_id+" has been awaken by a customer from the queue.");
            customer = (Customer)((LinkedList<?>)WaitingCustomer).poll();
        }
        long duration=0;
        try
        {    
            System.out.println("Cuting hair of Customer : "+customer.getName()+"by Barber "+b_id);
            duration = (int)normal_distribution_generator.nextGaussian()*5+15;    // Generating time to cut a customer's hair has a mean standard deviation 
            TimeUnit.SECONDS.sleep(duration);
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
        }
        System.out.println("Completed Cuting hair of Customer : "+customer.getName()+"by Barber "+b_id+" in "+duration+ " seconds.");
        if(WaitingCustomer.size()==0)
            System.out.println("Barber "+b_id+" waiting for customer from the queue and fall asleep.");
        else
            //This will take care if any customer has been taken by any other barber at same moment.
            System.out.println("Barber "+b_id+" waiting for customer, awake customer if any customer avaialble in queue else fall asleep");

    }
 
    public void add(Customer customer)
    {
        System.out.println("Customer : "+customer.getName()+ " entering the shop at "+customer.getInTime());
 
        synchronized (WaitingCustomer)
        {
            if(WaitingCustomer.size() == nchair)
            {
                System.out.println("No chair available for customer "+customer.getName());
                System.out.println("Customer "+customer.getName()+"Exits...");
                return ;
            }
 
            ((LinkedList<Customer>)WaitingCustomer).offer(customer);
            System.out.println("Customer : "+customer.getName()+ " got the chair.");
             
            if(WaitingCustomer.size()==1)
                WaitingCustomer.notify();
        }
    }
}