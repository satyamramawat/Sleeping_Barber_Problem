/**
 * Author - Satyam Ramawat(19210520)
 * Email - satyam.ramawat2@mail.dcu.ie
 */
package assignment_1_sleeping_barber_problem;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import assignment_1_sleeping_barber_problem.CustomerGenerator;
import assignment_1_sleeping_barber_problem.Customer;
import assignment_1_sleeping_barber_problem.BarberShop;

public class Barber implements Runnable
{
    BarberShop shop;
    int id;

    public Barber(BarberShop shop, int id)
    {
        this.shop = shop;
        this.id=id;  
    }

    public void run()
    {
        try
        {
            Thread.sleep(10000);
        }
        catch(InterruptedException iex)
        {
            iex.printStackTrace();
        }
        while(true)
        {
            shop.cutHair(id);
        }
    }
}