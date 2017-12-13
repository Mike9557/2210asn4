// binary search tree class
public class BinarySearchTree implements BinarySearchTreeADT{
	 BinaryNode root ; //each tree must have a root
	
	 //constructor of binary search tree
	public BinarySearchTree(){
		root = new BinaryNode();
	}
	//return the root of the tree
	public BinaryNode getRoot(){
		return root;
	}
	//get the pixel with same location k
	public Pixel get(BinaryNode r, Location k)
	{
		return getNode(r,k).getData();
	}
	// insert the pixel to the tree
	public void put(BinaryNode r, Pixel data) throws DuplicatedKeyException{
		 //get the position where the pixel should be inserted
		 BinaryNode p = getNode(r,data.getLocation());
		 // if this node is not null, return duplicatedkeyexception
		 if (!p.isLeaf()){
			 throw new DuplicatedKeyException();
		 }
		 //store the pixel with store function
		 else{
			 store(p,data);
		 }
	}
	
	private void store(BinaryNode p, Pixel data) {
		//set pixel into the node, and set parent of each child equal to this node
		p.setData(data);
		BinaryNode left = new BinaryNode();
		BinaryNode right = new BinaryNode();
		p.setLeft(left);
		p.setRight(right);
		left.setParent(p);
		right.setParent(p);	
		
	}
	// helper function for finding the right position
	private BinaryNode getNode(BinaryNode r, Location key){
	    //if r is a leaf, return r 
		if (r.isLeaf()){
			return r;
		}
		else{
			// if location is equal to this internal node return r
			if (r.getData().getLocation().compareTo(key) == 0){
				return r;
			}
			// if location is greater than this node, go to right
			else if (r.getData().getLocation().compareTo(key) == 1){
				return getNode(r.getLeft(),key);
			}
			//other wise go left
			else {
				return getNode(r.getRight(),key);
			}
		}
	}
	// helper function for finding the smallest node.
	private BinaryNode getSmallest(BinaryNode r){
		
			BinaryNode p = r;
			while (!p.isLeaf()){
				p = p.getLeft();
			}
			return p.getParent();
		}
		
    //remove the pixel with same location
	public void remove(BinaryNode r, Location key) throws InexistentKeyException {
		BinaryNode temp = getNode(r,key);// find the right ndoe
		if (temp.isLeaf()){
			// if there no such pixel , throw this exception.
			throw new InexistentKeyException();
		}
		
		else{
			//at least one of child is leaf
			if (temp.getLeft().isLeaf() || temp.getRight().isLeaf()){
				//left child is leaf
				if(temp.getLeft().isLeaf()){
					
					BinaryNode right = temp.getRight();
					BinaryNode parent = temp.getParent();
					//if this node is root, we need set a new root(non-leaf child)
					if (parent == null){
						this.root = right;
						right.setParent(null);
					}
					//otherwise , the parent points to its non-leaf child
					else{
						if(parent.getRight().equals(temp)){
							parent.setRight(right);
							right.setParent(parent);
						}
						else{
							parent.setLeft(right);
							right.setParent(parent);
						}
					}
				}
				//when right child is a leaf,
				else{
					BinaryNode left = temp.getLeft();
					BinaryNode parent = temp.getParent();
					//if the node is root, we need set a new root(non-leaf child)
					if (parent == null){
						this.root = left ;
						left.setParent(null);
					}
					else{
						//otherwise , the parent points to its non-leaf child
						if(parent.getRight().equals(temp)){
							parent.setRight(left);
							left.setParent(parent);
				}
						else{
							parent.setLeft(left);
							left.setParent(parent);
						}
				}}}
			
			else{
				//when the node is internal, find the smallest of its right sub tree and replace it
				BinaryNode small = getSmallest(temp.getRight());
				//replace the data with smallest node of subtree data;
				temp.setData(small.getData());
				BinaryNode smallparent = small.getParent();
				//set its parent points to smallest ndoe 's right;
				if(smallparent.getLeft().equals(small)){
					smallparent.setLeft(small.getRight());
				}
				else{
					smallparent.setRight(small.getRight());
				}
				small.getRight().setParent(smallparent);
			}
			
		}
		
	}
	//helper function of finding smallest node
	private BinaryNode smallestNode(BinaryNode r){
		if (r.isLeaf()){
			return null;
		}
		else{
			BinaryNode p = r;
			while (!p.isLeaf()){
				p = p.getLeft();
			}
			return p.getParent();
			
		}
	}
	//helper function of finding largest node
	private BinaryNode largestNode(BinaryNode r){
		//if the leaf  , return null;
		if(r.isLeaf()){
			return null;
		}
		// else return the most right leaf's parent;
		else{
			BinaryNode p = r;
			while(!p.isLeaf()){
				p = p.getRight();
			}
			return p.getParent();
		}
	}
	//successor method
	public Pixel successor(BinaryNode r, Location key) {
		// TODO Auto-generated method stub
		//if r is leaf return null
		if (r.isLeaf()){
			return null;
		}
		else{
			// else find the the node who is least larger than current node
			BinaryNode p = getNode(r,key);
			if (!p.isLeaf()&&!p.getRight().isLeaf()){
				//if right child is not leaf, find the smallest of its right sub tree
				return smallestNode(p.getRight()).getData();
			}
			else{
				// otherwise, go through its parents
				BinaryNode parent = p.getParent();
				 //check the closet parent that the right child is temp
				while(parent != null && parent.getRight()==p)
				{
					p = parent;
					parent = parent.getParent();
				}
				if(parent!= null){
				return parent.getData();
				}
				else{
					return null;
				}
				}
			}
			
		}


	//predecessor function
	public Pixel predecessor(BinaryNode r, Location key) {
		// TODO Auto-generated method stub
		//if r is a leaf return null
		if(r.isLeaf()){
			return null;
		}
		else{
			//find the the node who is least smaller than current node
		  BinaryNode temp = getNode(r,key);
		  //if right child is not leaf, find the largest of its left sub tree
		  if (!temp.isLeaf() && !temp.getLeft().isLeaf()){
			return largestNode(temp.getLeft()).getData();
		}
		  else{
			 // other wise go through its parents
			  BinaryNode p = temp.getParent();
			 //check the closet parent that the left child is temp
			  while ( p!= null && p.getLeft()==(temp)){
				  temp = p;  
				  p = p.getParent();
			  }
			  if(p == null){
				  
				  return null;
			  }
			  else{
			     return p.getData();
			  }
		  }
		}
		
	}
	// find the smallest pixel
	public Pixel smallest(BinaryNode r) throws EmptyTreeException {
		// TODO Auto-generated method stub
		if (r.isLeaf())
			throw new EmptyTreeException();
		else{
			//return the most left node's parent's pixel
			BinaryNode p = r;
			while (!p.isLeaf()){
				p = p.getLeft();
			}
			return p.getParent().getData();
		}
	}
	//find the largest pixel.
	public Pixel largest(BinaryNode r) throws EmptyTreeException {
		// TODO Auto-generated method stub
		if (this.getRoot().isLeaf())
			throw new EmptyTreeException();
		else{
			//return the most right node's parent's pixel
			BinaryNode p = r;
			while (!p.isLeaf()){
				p = p.getRight();
			}
			return p.getParent().getData();
		}
	}
	

}
