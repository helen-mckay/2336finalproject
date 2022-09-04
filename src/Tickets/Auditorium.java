//Helen McKay | hem160230

package Tickets;

import java.util.*;
import java.io.*;

//Auditorium class

public class Auditorium 
{
    //variables
    
    private TheaterSeat first;                      //the first seat of the auditorium
    private int numRows;                            //the number of rows of the auditorium
    private int seatsPerRow;                        //the number of seats per each row in the auditorium
    
    //constructors
    
    Auditorium(File theFile) throws IOException
    {        
        //variables
        
        Scanner readFile = new Scanner(theFile);    //reading file
        String line;                                //line
        int rowCount = 0;                           //count rows in the auditorium
        int seatCount = 0;                          //count seats per each row in the auditorium
        boolean firstTime = true;                   //flags if first read
        int rowLoop = 1;                            //looping variable for rows
        int seatLoop = 0;                           //looping seat for seats
        TheaterSeat prevNode = null;                //for connecting nodes
        TheaterSeat currNode = null;                //for connecting nodes
        TheaterSeat prevRow = null;                 //for connecting nodes
        TheaterSeat currRow = null;                 //for connecting nodes
        TheaterSeat prevRowTrav = null;             //for connecting nodes
        final int ASCII_OFFSET = 65;                //for converting between int and char
        char ticType = '!';                         //the ticket type
        boolean res = false;                        //flags if reserved or not
        
        //count the rows and seats in the auditorium
        
        while (readFile.hasNext())
        {
            line = readFile.nextLine();
            
            if (firstTime)
            {
                seatCount = line.length();
                
                firstTime = false;
            }
            
            rowCount++;
        }
        
        //set the numRows and seatsPerRow variables
        
        this.numRows = rowCount;
        this.seatsPerRow = seatCount;
        
        readFile.close();
        readFile = new Scanner(theFile);
        
        //read the file again, build the linked list
        
        for (rowLoop = 1; rowLoop <= rowCount; rowLoop++)
        {
            line = readFile.nextLine();
            
            for (seatLoop = 0; seatLoop < seatCount; seatLoop++)
            {
                switch(line.charAt(seatLoop))
                {
                    case 'A':
                    case 'C':
                    case 'S':
                        ticType = line.charAt(seatLoop);
                        res = true;
                        break;
                    default:
                        ticType = '!';
                        res = false;
                        break;
                }
                
                //create and connect all the necessary nodes
                
                //first row
                
                if (rowLoop == 1)
                {
                    //first seat on first row
                    
                    if (seatLoop == 0)
                    {                        
                        prevNode = null;
                        currNode = new TheaterSeat();
                        
                        this.first = currNode;
                        currRow = this.first;
                    }
                    else
                    {
                        prevNode = currNode;
                        currNode = new TheaterSeat();
                        
                        prevNode.setRight(currNode);
                        currNode.setLeft(prevNode);
                    }
                }
                else
                {
                    //first seat
                    
                    if (seatLoop == 0)
                    {
                        prevNode = null;
                        currNode = new TheaterSeat();
                        
                        prevRow = currRow;
                        prevRowTrav = prevRow;
                        currRow = currNode;
                        
                        currNode.setUp(prevRow);
                        prevRow.setDown(currNode);
                    }
                    else
                    {
                        prevNode = currNode;
                        currNode = new TheaterSeat();
                        
                        prevNode.setRight(currNode);
                        currNode.setLeft(prevNode);
                        
                        prevRowTrav = prevRowTrav.getRight();
                        
                        currNode.setUp(prevRowTrav);
                        prevRowTrav.setDown(currNode);
                    }
                }
                
                //update the current node's information
                
                currNode.setRow(rowLoop);
                currNode.setSeat((char)(ASCII_OFFSET + seatLoop));
                currNode.setTicketType(ticType);
                currNode.setReserved(res);
            }
        }
        
        readFile.close();
    }
    
    //accessors
    
    public TheaterSeat getFirst(){return this.first;}
    public int getNumRows(){return this.numRows;}
    public int getSeatsPerRow(){return this.seatsPerRow;}
    
    //mutators
    
    public void setFirst(TheaterSeat first) {this.first = first;}
    public void setNumRows(int numRows){this.numRows = numRows;}
    public void setSeatsPerRow(int seatsPerRow){this.seatsPerRow = seatsPerRow;}
    
    //findSeat
        //receives row and startingSeat
        //returns TheaterSeat of the exact seat in the audiorium
        //returns the node for the exact seat in the auditorium
    
    public TheaterSeat findSeat(int row, char startingSeat)
    {
        //variables
        
        TheaterSeat seat = this.first;              //initialie seat with the first variable
        int loop = 1;                               //looping variables
        final int ASCII_OFFSET = 65;                //for converting between char and int
        
        //move to the correct row
        
        while (loop < row)
        {
            seat = seat.getDown();
            loop++;
        }
        
        loop = 0;
        
        //move to the correct seat on the row
        
        while (loop < (int)(startingSeat - ASCII_OFFSET))
        {
            seat = seat.getRight();
            loop++;
        }
        
        return seat;
    }
}