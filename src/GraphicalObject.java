
public class GraphicalObject implements GraphicalObjectADT{

	private int Graphical_id;
	private int Graphical_width;
	private int Graphical_height;
	private String Graphical_type;
	private Location Graphical_pos;
	private BinarySearchTree tree;
	//constructor
	public GraphicalObject (int id, int width, int height, String type, Location pos){
		Graphical_id = id;
		Graphical_width = width;
		Graphical_height = height;
		Graphical_type = type;
		Graphical_pos = pos;
	    tree = new BinarySearchTree();
	}

	@Override
	//return width
	public int getWidth() {
		// TODO Auto-generated method stub
		return Graphical_width;
	}

	@Override
	//return height
	public int getHeight() {
		// TODO Auto-generated method stub
		return Graphical_height;
	}

	@Override
	//return type
	public String getType() {
		// TODO Auto-generated method stub
		return Graphical_type;
	}

	@Override
	//return its id
	public int getId() {
		// TODO Auto-generated method stub
		return Graphical_id;
	}

	@Override
	//return its offset
	public Location getOffset() {
		// TODO Auto-generated method stub
		return Graphical_pos;
	}

	@Override
	//set offset
	public void setOffset(Location value) {
		Graphical_pos = value;
		
	}

	@Override
	//set the type
	public void setType(String t) {
		Graphical_type = t;
		
	}

	@Override
	//add pixel into the tree
	public void addPixel(Pixel pix) throws DuplicatedKeyException {
		
		tree.put(tree.getRoot(),pix);
		
	}
//that returns true if
//	this object has a pixel in location p and it returns false otherwise.

	private boolean findPixel(Location p){
		Pixel temp = tree.get(tree.getRoot(), p);
		if(temp == null){
			
			return false;
		}

		else{
			return true;
		}
	}
	//check if it is insersects, return true if they intersecs, otherwise return false
	public boolean intersects(GraphicalObject fig) {
		// TODO Auto-generated method stub
		int xcoord = getOffset().xCoord();
		int ycoord = getOffset().yCoord();
		int x_coord = fig.getOffset().xCoord();
		int y_coord = fig.getOffset().yCoord();
		Pixel smallest = null;
		
		 try {
			smallest = fig.tree.smallest(tree.getRoot());
		} catch (EmptyTreeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// go through the entire tree ,from smallest to largest
		while(tree.successor(tree.getRoot(), smallest.getLocation()) != null){
			int x,y;
			x = smallest.getLocation().xCoord()+xcoord-x_coord;
			y = smallest.getLocation().yCoord()+ycoord-y_coord;
			if(fig.findPixel(new Location(x,y))){
				return true;
			}
			smallest = tree.successor(tree.getRoot(), smallest.getLocation());
		}
		
		return false;
		
	}

}
