
public class Pixel {
    private Location storedLocation;
    private int storedColor;
    
    public Pixel(Location p, int color ){
    	storedLocation = p;
    	storedColor = color;
    }
    public Location getLocation(){
    	return storedLocation;
    }
    public int getColor(){
    	return storedColor;
    	
    }
}
