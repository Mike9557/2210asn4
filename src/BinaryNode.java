
public class BinaryNode {
	private Pixel p;
	private BinaryNode childleft,childright,nodeparent;
	//constructor
	public BinaryNode(Pixel value, BinaryNode left, BinaryNode right, BinaryNode parent){
		p = value;
	    childleft = left;
	    childright = right;
	    nodeparent = parent;
	}
	//constructor for leaf
	public BinaryNode(){
		p = null;
		childleft = childright = nodeparent = null;
	}
	//return parent
	public BinaryNode getParent(){
		return nodeparent;
		
	}
	//set parent
	public void setParent(BinaryNode parent){
		nodeparent = parent;
	}
	//set left
	public void setLeft(BinaryNode left){
		childleft = left;
	}
	//set right
	public void setRight(BinaryNode right){
		childright = right;
	}
	// set data
	public void setData(Pixel p1 ){
		p = p1;
	}
	//check if it is leaf
	public boolean isLeaf(){
		if (p == null && childleft == null && childright == null){
			return true;
		}
		else{
			return false;
		}
		
	}
	//get the data
	public Pixel getData(){
		return p;
	}
	//get the left child
	public BinaryNode getLeft(){
		return childleft;
	}
	// get the right child
	public BinaryNode getRight(){
		return childright;
	}
	
	

}
