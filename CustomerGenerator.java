/**
 * Author - Satyam Ramawat(19210520)
 * Email - satyam.ramawat2@mail.dcu.ie
 */
package assignment_1_sleeping_barber_problem;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.Random;

import assignment_1_sleeping_barber_problem.Customer;
import assignment_1_sleeping_barber_problem.Barber;
import assignment_1_sleeping_barber_problem.BarberShop;

public class CustomerGenerator implements Runnable
{
    /**
     * Below serialVersionUID key from JDK 1.1 for interoperability
     * Which is required in order to leverage synchronization 
     */
    private static final long serialVersionUID = 1L;
    BarberShop shop;
    public int customer_counter=1;
 
    public CustomerGenerator(BarberShop shop)
    {
        this.shop = shop;
    }
 
    public void run()
    {
        while(true)
        {
            /**
             * Since nextGaussian() is method of Random Class, so in order to use this method
             * with Customer Class Instance, thus created reference variable of Random Class(Parent Class)
             * and Object of Customer Class(Child Class) i.e Dynamic Dispatch Mechanism(Runtime Polymorphism) 
             */
            Random customer = new Customer(shop);                // Dynamic Dispatch(Runtime Polymorphism)
            ((Customer) customer).setInTime(new Date());         // Mechanism of Casting Object
            Thread thcustomer = new Thread((Runnable) customer); // Mechanism of Casting Object
            ((Customer) customer).setName("Customer " + customer_counter + "(Thread id" + thcustomer.getId() + ")");
            thcustomer.start();
            customer_counter++;
            try
            {
                TimeUnit.SECONDS.sleep((long)customer.nextGaussian()*2+6);
            }
            catch(InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
 
}