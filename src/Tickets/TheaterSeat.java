//Helen McKay | hem160230

package Tickets;

//TheaterSeat class

public class TheaterSeat extends BaseNode 
{
    //variables
    
    private TheaterSeat up;
    private TheaterSeat down;
    private TheaterSeat left;
    private TheaterSeat right;
    
    //constructors
    
    TheaterSeat()
    {
        super();
        
        this.up = null;
        this.down = null;
        this.left = null;
        this.right = null;
    }
    
    TheaterSeat(TheaterSeat up, TheaterSeat down, TheaterSeat left, TheaterSeat right, int row, char seat, boolean reserved, char ticketType)
    {
        super(row, seat, reserved, ticketType);
        
        this.up = up;
        this.down = down;
        this.left = left;
        this.right = right;
    }
    
    //accessors
    
    public final TheaterSeat getUp() {return this.up;}
    public final TheaterSeat getDown() {return this.down;}
    public final TheaterSeat getLeft() {return this.left;}
    public final TheaterSeat getRight() {return this.right;}
    
    //mutators
    
    public void setUp(TheaterSeat up) {this.up = up;}
    public void setDown(TheaterSeat down) {this.down = down;}
    public void setLeft(TheaterSeat left) {this.left = left;}
    public void setRight(TheaterSeat right) {this.right = right;}
}
