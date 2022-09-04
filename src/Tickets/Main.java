package Tickets;

import java.util.*;
import java.io.*;

public class Main 
{
    public static void main(String[] args) throws IOException
    {
        Auditorium A1 = new Auditorium(new File("A1.txt"));
        Auditorium A2 = new Auditorium(new File("A2.txt"));
        Auditorium A3 = new Auditorium(new File("A3.txt"));
        Auditorium auditoriums[] = new Auditorium[]{A1, A2, A3};
        HashMap<String, Customer> theMap = new HashMap<>();
        Scanner readFile = new Scanner(new File("userdb.dat"));
        String line;
        String username = "";
        String password;
        Customer customer = new Customer();
        boolean showLogin = true;
        boolean loggedIn = false;
        Scanner userInput = new Scanner(System.in);
        int numAttempts = 0;
        String userChoice;
        boolean valid = false;
        
        while (readFile.hasNext())
        {
            line = readFile.nextLine();
            
            username = line.substring(0, line.indexOf(" "));
            password = line.substring(line.indexOf(" ") + 1);
            
            customer = new Customer(username, password);
            
            theMap.put(username, customer);
        }
        
        readFile.close();
            
        while (showLogin)
        {
            while (!loggedIn)
            {
                System.out.print("\nUsername: ");
                username = userInput.nextLine();
                
                System.out.print("Password: ");
                password = userInput.nextLine();
                numAttempts++;
                
                if (theMap.containsKey(username))
                {
                    while (!loggedIn && (numAttempts < 3))
                    {
                        try
                        {
                            if (!theMap.get(username).getPassword().equals(password))
                            {
                                throw new Exception();
                            }
                            
                            loggedIn = true;
                        }
                        catch (Exception e)
                        {
                            System.out.print("\nPassword: ");
                            password = userInput.nextLine();
                            numAttempts++;
                        }
                    }
                }
                
                numAttempts = 0;
            }
            
            while (loggedIn)
            {
                if (username.equals("admin"))
                {
                    System.out.println("\n1. Print report");
                    System.out.println("2. Log out");
                    System.out.println("3. Exit\n");
                    
                    userChoice = userInput.nextLine();
                    
                    try
                    {
                        switch(userChoice)
                        {
                            case "1":
                                printReport(auditoriums);
                                break;
                            case "2":
                                loggedIn = false;
                                break;
                            case "3":
                                loggedIn = false;
                                showLogin = false;
                                
                                writeToFiles(auditoriums);
                                break;
                            default:
                                throw new Exception();
                        }
                    }
                    catch (Exception e){}
                }
                else
                {
                    System.out.println("\n1. Reserve seats");
                    System.out.println("2. View orders");
                    System.out.println("3. Update order");
                    System.out.println("4. Display receipt");
                    System.out.println("5. Log out\n");
                    
                    userChoice = userInput.nextLine();
                    
                    try
                    {
                        switch (userChoice)
                        {
                            case "1":
                                
                                //submenu: auditoriums
                                //get and validate user's choice of auditorium
                                //display that auditorium
                                //ordering process, get an order object from here
                                //have an order object with all of the above information
                                
                                //send the order object to checkAvailability and bestAvailable)
                                
                                //if the order object is not null,
                                    //update the customer's orders
                                    //reserve the seats in the auditorium
                                
                                Order theOrder = new Order();
                                
                                while (!valid)
                                {
                                    System.out.println("\n1. Auditorium 1");
                                    System.out.println("2. Auditorium 2");
                                    System.out.println("3. Auditorium 3");
                                    
                                    userChoice = userInput.nextLine();
                                    
                                    try
                                    {
                                        switch(userChoice)
                                        {
                                            case "1":
                                            case "2":
                                            case "3":
                                                valid = true;
                                                theOrder.setAuditoriumChoice(Integer.parseInt(userChoice));
                                                displayAuditorium(auditoriums[Integer.parseInt(userChoice)], false);
                                                break;
                                            default:
                                                throw new Exception();
                                        }
                                    }
                                    catch (Exception e){}
                                }
                                
                                valid = false;
                                
                                theOrder.copyValues(getOrder(userInput, auditoriums[theOrder.getAuditoriumChoice()]));
                                
                                if (checkAvailability(theOrder, auditoriums[theOrder.getAuditoriumChoice()]))
                                {
                                    
                                }
                                else
                                {
                                    
                                }
                                
                                break;
                            case "2":
                                customer.viewOrders();
                                break;
                            case "3":
                                break;
                            case "4":
                                displayReceipt(customer);
                                break;
                            case "5":
                                loggedIn = false;
                                break;
                            default:
                                throw new Exception();
                        }
                    }
                    catch (Exception e){}
                }
            }
        }
    }
    
    public static void printReport(Auditorium[] auditoriums)
    {
        
    }
    
    public static void writeToFiles(Auditorium[] auditoriums)
    {
        
    }
    
    public static void displayReceipt(Customer customer)
    {
        
    }
    
     public static void displayAuditorium(Auditorium auditorium, boolean showTypes)
    {
        //variables
        
        TheaterSeat movePtr = auditorium.getFirst();                    //ptr for moving through the list by each node
        TheaterSeat currRow = auditorium.getFirst();                    //ptr for moving through the list by each row
        int rowLoop;                                                    //looping variable for seat
        int seatLoop;                                                   //looping variable for row
        final int ASCII_OFFSET = 65;                                    //for converting between char and int
        
        System.out.print("\n  ");
        
        //output the seats header
        
        for (seatLoop = 0; seatLoop < auditorium.getSeatsPerRow(); seatLoop++)
        {
            System.out.print((char)(seatLoop + ASCII_OFFSET));
        }
        
        System.out.print("\n");
        
        //loop through the auditorium
        
        for (rowLoop = 1; rowLoop <= auditorium.getNumRows(); rowLoop++)
        {
            //output the row numbers
            
            System.out.print(rowLoop + " ");
            
            //loop through each seat on the row
            
            for (seatLoop = 0; seatLoop < auditorium.getSeatsPerRow(); seatLoop++)
            {
                //show # and .
                
                if (!showTypes)
                {
                    switch(movePtr.getTicketType())
                    {
                        case 'A':
                        case 'C':
                        case 'S':
                            System.out.print("#");
                            break;
                        case '!':
                            System.out.print(".");
                            break;
                    }
                
                    movePtr = movePtr.getRight();
                }
                
                //show ticket types for each seat
                
                else
                {
                    switch(movePtr.getTicketType())
                    {
                        case 'A':
                        case 'C':
                        case 'S':
                            System.out.print(movePtr.getTicketType());
                            break;
                        case '!':
                            System.out.print(".");
                            break;
                    }
                
                    movePtr = movePtr.getRight();
                }
            }
            
            System.out.print("\n");
            
            currRow = currRow.getDown();
            movePtr = currRow;
        }
        
        System.out.print("\n");
    }
     
    public static Order getOrder(Scanner userInput, Auditorium theAuditorium)
    {
        Order theOrder = new Order();
        boolean valid = false;
        int loop = 0;
        String userChoice = "";
        int row = 0;
        char startingSeat = '!';
        int adult = 0;
        int child = 0;
        int senior = 0;
        int totalSeats = 0;
        final int ASCII_OFFSET = 65;
        
        while (!valid)
        {
            System.out.print("Row: ");
            userChoice = userInput.nextLine();
            
            try
            {
                while (loop < userChoice.length())
                {
                    if (!Character.isDigit(userChoice.charAt(loop)))
                    {
                        throw new Exception();
                    }
                    
                    loop++;
                }
                
                if ((Integer.parseInt(userChoice) < 1) || (Integer.parseInt(userChoice) > theAuditorium.getNumRows()))
                {
                    throw new Exception();
                }
                
                valid = true;
                row = Integer.parseInt(userChoice);
            }
            catch (Exception e)
            {
                loop = 0;
            }
        }
        
        valid = false;
        loop = 0;
        
        while (!valid)
        {
            System.out.print("Starting seat: ");
            userChoice = userInput.nextLine();
            
            try
            {
                if (userChoice.length() != 1)
                {
                    throw new Exception();
                }
                
                if ((userChoice.charAt(0) < (char)ASCII_OFFSET) || (userChoice.charAt(0) > (char)(ASCII_OFFSET + theAuditorium.getSeatsPerRow() - 1)))
                {
                    throw new Exception();
                }
                
                valid = true;
                startingSeat = userChoice.charAt(0);
            }
            catch(Exception e){}
        }
        
        valid = false;
        
        while (!valid)
        {
            System.out.print("Adults: ");
            
            userChoice = userInput.nextLine();
            
            try
            {
                while (loop < userChoice.length())
                {
                    if (!Character.isDigit(userChoice.charAt(0)))
                    {
                        throw new Exception();
                    }
                    
                    loop++;
                }
                
                if ((Integer.parseInt(userChoice) < 0) || (Integer.parseInt(userChoice) > theAuditorium.getSeatsPerRow()))
                {
                    throw new Exception();
                }
                
                valid = true;
                adult = Integer.parseInt(userChoice);  
            }
            catch (Exception e)
            {
                loop = 0;
            }
        }
        
        valid = false;
        loop = 0;
        
        while (!valid)
        {
            System.out.print("Children: ");
            
            userChoice = userInput.nextLine();
            
            try
            {
                while (loop < userChoice.length())
                {
                    if (!Character.isDigit(userChoice.charAt(0)))
                    {
                        throw new Exception();
                    }
                    
                    loop++;
                }
                
                if ((Integer.parseInt(userChoice) < 0) || (Integer.parseInt(userChoice) > theAuditorium.getSeatsPerRow()))
                {
                    throw new Exception();
                }
                
                valid = true;
                child = Integer.parseInt(userChoice); 
            }
            catch (Exception e)
            {
                loop = 0;
            }
        }
        
        valid = false;
        loop = 0;
        
        while (!valid)
        {
            System.out.print("Seniors: ");
            
            userChoice = userInput.nextLine();
            
            try
            {
                while (loop < userChoice.length())
                {
                    if (!Character.isDigit(userChoice.charAt(0)))
                    {
                        throw new Exception();
                    }
                    
                    loop++;
                }
                
                if ((Integer.parseInt(userChoice) < 0) || (Integer.parseInt(userChoice) > theAuditorium.getSeatsPerRow()))
                {
                    throw new Exception();
                }
                
                valid = true;
                senior = Integer.parseInt(userChoice);   
            }
            catch (Exception e)
            {
                loop = 0;
            }
        }
        
        loop = 0;
        
        totalSeats = adult + child + senior;
        
        int adultCopy = adult;
        int childCopy = child;
        int seniorCopy = senior;
        
        if (totalSeats != 0)
        {
            ArrayList<OrderSeat> seats = new ArrayList<>();
            
            while (loop < totalSeats)
            {
                OrderSeat newSeat = new OrderSeat();
                newSeat.setRow(row);
                newSeat.setSeat((char)(startingSeat + loop));
            
                if (adult > 0)
                {
                    newSeat.setTicketType('A');
                
                    adult--;
                }
                else if (child > 0)
                {
                    newSeat.setTicketType('C');
                
                    child--;
                }
                else if (senior > 0)
                {
                    newSeat.setTicketType('S');
                
                    senior--;
                }
            
                loop++;
                
                seats.add(newSeat);
            }
            
            theOrder.setAdult(adultCopy);
            theOrder.setChild(childCopy);
            theOrder.setSenior(seniorCopy);
            theOrder.setSeats(seats);
        }
        else
        {
            theOrder = null;
        }
        
        return theOrder;
    }
    
    public static boolean(Order theOrder, Auditorium theAuditorium)
    {
        return checkAvailabilityMain(theAuditorium, theOrder.getRow())
    }
    
    public static boolean checkAvailabilityMain(Auditorium auditorium, int row, char startingSeat, int totalSeats)
    {
        //variables
        
        boolean available = true;                                       //flags if the seats are available
        TheaterSeat loopPtr = auditorium.findSeat(row, startingSeat);   //looping ptr to move through the array, set to the specific seat
        int loop = 0;                                                   //looping variable
        
        //loop through the seats desired
        
        while (available && (loop < totalSeats))
        {
            if (loopPtr == null)
            {
                available = false;
            }
            else if (loopPtr.getReserved())
            {
                available = false;
            }
            else
            {
                loop++;
                loopPtr = loopPtr.getRight();
            }
        }
        
        return available;
    }
}
