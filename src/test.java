import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class test {
	private static BinarySearchTree tree = new BinarySearchTree();
	private static void insertPoints(BufferedImage im, int xf, int yf, int xl, 
			int yl, int width) throws DuplicatedKeyException{
		/* =================================================================== */
		/* Insert the pixels into the binary search tree */
		int m, xm, ym, rgb;
		int xm1, xm2, ym1, ym2;


		/* Perform a binary splitting of the set of pixels and insert them 
           in that order in the binary search tree  */
		if ((yf < yl) || ((yf == yl) && (xf <= xl))) {
			m = (yf*width+xf+yl*width+xl)/2;   // Pixel in the middle of figure
			xm = m % width;           // Coordinates of the middle pixel
			ym = m / width;
			rgb = im.getRGB(xm,ym);   // Color of middle pixel
			tree.put(tree.getRoot(),new Pixel(new Location(xm,ym),rgb));
            System.out.println(tree.getRoot().isLeaf());
			// Split the figure around the middle pixel
			if (xm > 0) {
				xm1 = xm -1;
				ym1 = ym;
			}
			else {
				xm1 = width -1;
				ym1 = ym -1;
			}
			if (xm < width -1) {
				xm2 = xm+1;
				ym2 = ym;
			}
			else {
				xm2 = 0;
				ym2 = ym+1;
			}

			// Insert the pixels in each half of the figure
			insertPoints(im,xf,yf,xm1,ym1,width);
			insertPoints(im,xm2,ym2,xl,yl,width);
			}

		}

	public static void main(String[] args) throws DuplicatedKeyException, InexistentKeyException {
		tree = new BinarySearchTree();
		Pixel res;
		Pixel test = null;

		// Tests with few data items

		Pixel pixels[] = new Pixel[5];
		Location points[] = new Location[5];
		int colors[] = {5, 3, 1, 2, 4};
		int x[] = {4,2,1,1,3};
		int y[] = {4,3,1,2,4};

		for (int i = 0; i < 5; ++i) {
			points[i] = new Location(x[i],y[i]);
			pixels[i] = new Pixel(points[i],colors[i]);
		}
		tree.put(tree.getRoot(),pixels[0]); 
		tree.put(tree.getRoot(),pixels[1]);
		try {
			 test = tree.smallest(tree.getRoot());
		} catch (EmptyTreeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (test == pixels[1]){
			System.out.println("wo ri le gou");
		}
		res = tree.get(tree.getRoot(),points[1]);  // Test data deletion
		if (res == null) System.out.println("Test 4 passed");
		else System.out.println("Test 4 failed");
		
		
	
		
	}}
