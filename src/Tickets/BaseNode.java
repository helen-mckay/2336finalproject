//Helen McKay | hem160230

package Tickets;

//BaseNode class

public abstract class BaseNode 
{
    //variables
    
    protected int row;
    protected char seat;
    protected boolean reserved;
    protected char ticketType;
    
    //constructors
    
    BaseNode()
    {
        this.row = 0;
        this.seat = '!';
        this.reserved = false;
        this.ticketType = '!';
    }
    
    BaseNode(int row, char seat, boolean reserved, char ticketType)
    {
        this.row = row;
        this.seat = seat;
        this.reserved = reserved;
        this.ticketType = ticketType;
    }
    
    //accessors
    
    public final int getRow() {return this.row;}
    public final char getSeat() {return this.seat;}
    public final boolean getReserved() {return this.reserved;}
    public final char getTicketType() {return this.ticketType;}
    
    //mutators
    
    public void setRow(int row) {this.row = row;}
    public void setSeat(char seat) {this.seat = seat;}
    public void setReserved(boolean reserved) {this.reserved = reserved;}
    public void setTicketType(char ticketType) {this.ticketType = ticketType;}
}
