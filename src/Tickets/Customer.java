package Tickets;

import java.util.*;

public class Customer 
{
    String username;
    String password;
    ArrayList<Order> orders;
    
    Customer()
    {
        this("", "", null);
    }
    
    Customer(String username, String password)
    {
        this(username, password, null);
    }
    
    Customer(String username, String password, ArrayList<Order> orders)
    {
        this.username = username;
        this.password = password;
        this.orders = orders;
    }
    
    public String getUsername(){return this.username;}
    public String getPassword(){return this.password;}
    public ArrayList<Order> getOrders(){return this.orders;}
    
    public void setUsername(String username){this.username = username;}
    public void setPassword(String password){this.password = password;}
    public void setOrders(ArrayList<Order> orders){this.orders = orders;}
    
    public void viewOrders()
    {
        int i = 0;
        
        if (this.orders == null)
        {
            System.out.println("You have no orders to view.");
        }
        else
        {
            while (i < this.orders.size())
            {
                System.out.println("ORDER " + (i + 1) + ":");
                
                this.orders.get(i).viewSeats();
                
                System.out.print("\n");
                
                i++;
            }
        }
    }
}
