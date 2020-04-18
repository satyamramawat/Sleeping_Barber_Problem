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

import assignment_1_sleeping_barber_problem.CustomerGenerator;
import assignment_1_sleeping_barber_problem.Barber;
import assignment_1_sleeping_barber_problem.BarberShop;

public class Customer extends Random implements Runnable
{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    String name;
    Date inTime;
 
    BarberShop shop;
 
    public Customer(BarberShop shop)
    {
        this.shop = shop;
    }
 
    public String getName() {
        return name;
    }
 
    public Date getInTime() {
        return inTime;
    }
 
    public void setName(String name) {
        this.name = name;
    }
 
    public void setInTime(Date inTime) {
        this.inTime = inTime;
    }
 
    public void run()
    {
        goForHairCut();
    }
    private synchronized void goForHairCut()
    {
        shop.add(this);
    }
}