
public class Location {
   private int location_x;
   private int location_y;
   //constructor
   public Location(int x, int y ){
	   location_x = x;
	   location_y = y;
   }
   //get x coord
   public int xCoord(){
	   return location_x;
   }
   //get y coord
   public int yCoord(){
	   return location_y;
   }
   // compare to lcoation
   public int compareTo(Location p){
	   
	   if (location_x < p.xCoord()){
		   return -1;
	   }
	   else if (location_x == p.xCoord())
	   { if(location_y == p.yCoord())
	       {
		   return 0;
    	   }
	     else if (location_y < p.yCoord()) 
	     {
		   return -1;
	     }
		   
		   
	   }
	   return 1;
	   
   }
}

