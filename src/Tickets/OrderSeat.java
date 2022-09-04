package Tickets;

public class OrderSeat extends BaseNode
{
    OrderSeat()
    {
        super();
    }
    
    @Override
    public String toString()
    {
        return this.row + " " + this.seat + " " + this.ticketType;
    }
}
