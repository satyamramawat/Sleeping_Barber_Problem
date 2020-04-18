# Sleeping Barber Problem
Multithreading and Multicore concepts used to solve sleeping barber problem
## INTRODUCTION
One of the classic Multi-threaded and Multi-Cores problem is Sleeping Barber Problem, which demonstrates synchronization, inter-process communication, and parallelism between Operating System Threads and Cores.
The scenario of Sleeping Barber Problem is, there are N barbers in Barbershop with their chairs respectively, and there are M waiting chairs where customers can wait and occupy a seat to get their hair cut done, also there are 2 doors from where customers come into the shop and from the exit from the shop after having their haircut.
Overall, Solution is written in Java Programming language and demonstrate the various operating system and concurrency concepts, Multi-Threading, Multi-Core, Standard Safety Properties (Mutual Exclusion, Absence of Deadlock) and Liveness Properties (absence of Starvation, Fairness).
DESIGN
The solution is designed in Java, whereas Object Oriented Programming concepts have been used keenly. The solution has been encapsulated into package assignment_1_Sleeping_barber_problem, where pertaining classes are:
* SleepingBarber.java (Main Class)
* Barber.java
* BarberShop.java
* Customer.java
* CustomerGenerator.java
   
Object-Oriented Concepts used are Java Multi-Threading, Java Synchronization(Keyword, Block), Inheritance, Static Variability, Command Line Argument, Parsing of Data Types, Packages, Runtime Polymorphism (Dynamic Dispatch), Super/this keyword, Java Collections (LinkedList), Java 8 Lambda Function.
Libraries Used:
* import java.util.Date;
* import java.util.LinkedList;
* import java.util.List;
* import java.util.concurrent.TimeUnit;
* import java.util.concurrent.ExecutionException;
* import java.util.concurrent.Executor;
* import java.util.concurrent.ExecutorService;
* import java.util.concurrent.Executors;
* import java.util.logging.Level;
* import java.util.logging.Logger;

Description about classes:
1. SleepingBarber.java - This class is the main class which Creates N barbers and M Customer waiting chairs and also execute multi-core and multi-thread. It creates and aligns CustomerGenerator Object to ThreadPool.
2. Barber.java – Contains Functionality of Sleep and calls CutHair() Function/Method which is defined in BarberShop class.
3. BarberShop – Contains Functionality of Synchronization of Customers and Barbers along with Sleep/Awake of Customer and Barber while waiting for each other. Also cutting hair time takes to cut a customer’s hair has a mean and standard
deviation whereas to achieve this java.util.Random class and its
method nextGaussian() have been used which generates Normal Distributed Mean and Standard Deviation Values at Random Interval. This class also has methods,
* AddCustomer() – This function sends customers into the shop for a haircut. If M
waiting chairs are full then it sends customers out of barbershop.
* CutHair() – This method cuts customer's hairs and allow the customer to the
awake sleeping barber and also barber to the awake customer while waiting in
Queue.
* Default Constructor – It creates a List of Customers.
4. Customer – This class specifically designed to get all customer information which has methods,
* getName() – This Function Retrieve Customer Number, Thread ID.
* setName() – This Function set Customer Number, Thread ID.
* getInTime() – This Function Retrieve Customer Time when it enters/exit from
shop.
* setInTime() – This Function set Customer Time when it enters and exit from shop,
it uses java.util.Date() method to save current time.
Synchronized void goForHairCut() – This method sends customers for a hair cut to the barbershop.

5. CustomerGenerator – This class generates customer and set customer id and Timings, also start customer thread.
Compilation / Execution Steps:
1. Java Packages concepts has been used,
To compile the package, command is: javac *.java -d .
2. Now we will Execute the program, by providing N Barbers as 1st and M waiting chairs as a 2nd command line Arguments. To Reduce use of variable, command line argument has been used whereas command will be:
java SleepingBarber 3 6
// 3 is No. of Barbers and 6 is No. of Waiting Chairs
Correctness of the Solution
The Solution is working and executing correctly according to the needs of the requirement. In Justification, there are N Barbers Threads which can be accessed by N Customers from M Customers Queue, Firstly, Barbers are sleeping if there is no Customer. Secondly, the Customer arrives in Barber Shop and grab a seat from the queue. Thirdly, the Customer awakes a barber and grab a barber chair for a haircut. Fourthly, If N Barbers are busy in cutting hairs of N Customer, then the customer will look for seat/chair in from available M seats/chairs, if the customer finds seat then it will take a seat and fall asleep until barber gets free from cutting the hair of his customer and awake the customer for a haircut, if the customer doesn’t find a seat then it will exit the shop, and if the customer finds barber sleeping on his chair then it will awake barber and take a haircut. The correctness of the Solution can be also verified from the output by the solution. The output is stored in the Output.txt file.
Fairness and Starvation
This solution for Sleeping barber problem is coded with the respect of Fairness property, where the concept of First Come First Serve (FCFS) has been followed, which thread come first in Thread Pool will be executed first. In Sleeping barber problem, which customers come first from M list of customers has been aligned to the first available barber from N barbers.
This solution also focusses on the Absence of Starvation property where it means, if a particular thread waits for a long time for Lock and takes longer than usual time, then this causes a situation known as Starvation. Here Solution has been focused to prevent Starvation where Customer and Barber do not go in Starvation, the customer does not go in waiting for the state even though barber is still available, and if the barber is not available then the customer will go in a sleep state until barber gets free from prior customer haircut and wakes up the customer for its haircut. The same case has been implemented for barbers as well where if no customer is available it will go in sleep state, it won’t starve or wait for the customer. The solution is a combination of synchronizing block and keyword and lock which is used to handle the starvation situation.

## Output:
![Image of Output](https://github.com/satyamramawat/Sleeping_Barber_Problem/blob/master/Images/Output.png?raw=true)
  
