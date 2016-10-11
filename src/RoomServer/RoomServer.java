//  Copyright (C) 2010 Lucas Catabriga Rocha <catabriga90@gmail.com>
//    
//  This file is part of Graphwar.
//
//  Graphwar is free software: you can redistribute it and/or modify
//  it under the terms of the GNU General Public License as published by
//  the Free Software Foundation, either version 3 of the License, or
//  (at your option) any later version.
//
//  Graphwar is distributed in the hope that it will be useful,
//  but WITHOUT ANY WARRANTY; without even the implied warranty of
//  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//  GNU General Public License for more details.

//  You should have received a copy of the GNU General Public License
//  along with Graphwar.  If not, see <http://www.gnu.org/licenses/>.


package RoomServer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import GraphServer.Constants;

public class RoomServer implements Runnable
{
  private String id;
  private Room room;
	private int numRooms;
	
	private boolean running;
	
	public RoomServer(String id)
	{
    this.id = id;
		try
		{
			this.room = new Room(id);
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public void stop()
	{
		running = false;
	}
	

	public void run() 
	{
		running = true;
		
		while(running)
		{
			try 
			{
				Thread.sleep(10000);
			} 
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}

      if (!room.isAcceptingConnections())
      {
        System.out.println("Restarting room "+room.getRoomId());
        room.stop();
        try
        {
          room = new Room(id);
        }
        catch (IOException e)
        {
          e.printStackTrace();
        }
      }
    }
		
    room.stop();
		
	}

	public static void handleArgs(String[] args)
	{
		if(args.length > 0)
		{
			// Overrides ip to create local server
			Constants.GLOBAL_IP = args[0];
		}
	}
	
	public static void main(String[] args)
	{
		handleArgs(args);

    String id = args[1];

		RoomServer roomServer = new RoomServer(id);
		
		new Thread(roomServer).start();
		
	}
}
