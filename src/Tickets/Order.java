package Tickets;

import java.util.*;

public class Order 
{
    private int auditoriumChoice;
    private int adult;
    private int child;
    private int senior;
    private ArrayList<OrderSeat> seats;
    
    Order()
    {
        this(0, 0, 0, 0, null);
    }
    
    Order(int auditoriumChoice, int adult, int child, int senior, ArrayList<OrderSeat> seats)
    {
        this.auditoriumChoice = auditoriumChoice;
        this.adult = adult;
        this.child = child;
        this.senior = senior;
        this.seats = seats;
    }
    
    public int getAuditoriumChoice(){return this.auditoriumChoice;}
    public int getAdult(){return this.adult;}
    public int getChild(){return this.child;}
    public int getSenior(){return this.senior;}
    public ArrayList<OrderSeat> getSeats(){return this.seats;}
    
    public void setAuditoriumChoice(int auditoriumChoice){this.auditoriumChoice = auditoriumChoice;}
    public void setAdult(int adult){this.adult = adult;}
    public void setChild(int child){this.child = child;}
    public void setSenior(int senior){this.senior = senior;}
    public void setSeats(ArrayList<OrderSeat> seats){this.seats = seats;}
    
    public void viewSeats()
    {
        int i = 0;
        
        System.out.println("Auditorium " + (this.getAuditoriumChoice() + 1));
        System.out.println("ROW\tSEAT\tTICKET");
        
        while (i < this.seats.size())
        {
            System.out.println(this.seats.get(i).toString());
            
            i++;
        }
    }
    
    public void copyValues(Order otherOrder)
    {
        this.adult = otherOrder.getAdult();
        this.child = otherOrder.getChild();
        this.senior = otherOrder.getSenior();
        this.seats = otherOrder.getSeats();
    }
}
