import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.*;

public class HuffmanTree {

	public class HuffmanNode {
		Character inCh;
		int freq;
		HuffmanNode left;
		HuffmanNode right;
		String encoding;
		// Constructor
		HuffmanNode(Character ch , int x){
			inCh = ch;
			freq = x;
			right = null;
			left = null;
			encoding = "";
		}
		
		// How it Outputs In the File in the Format:
		// char : freq : encode (Ex: x : 4 : 0011)
		public String toString() {
			return inCh +" : "+ freq +" : " + encoding ;
		}
	}
	
	ArrayList<HuffmanNode> nodeList;
	ArrayList<HuffmanNode> buildTreeList;
	HuffmanNode root;
	
	public HuffmanTree() {
		nodeList = new ArrayList<HuffmanNode>();
		buildTreeList = new ArrayList<HuffmanNode>();
		root = new HuffmanNode(null, -1);
	}
	
	public HuffmanNode findNode(Character ch) {
		for(int i = 0 ; i < nodeList.size() ; i++) 
			if(nodeList.get(i).inCh.equals(ch))
				return nodeList.get(i);	
		return null;
	}
	
	public void createNodeList(File inputFile) {
		try {
			String line;
			// Scanner Class for the Input File
            Scanner in = new Scanner(inputFile);
            HuffmanNode charNode = null;
           
            while(in.hasNext()) {
            	line = in.nextLine().toLowerCase();       	
            	for(int i = 0 ; i < line.length(); i++) {
            		charNode = findNode(line.charAt(i));
                    if(charNode!=null)
                        charNode.freq++;
                    else
                        nodeList.add(new HuffmanNode(line.charAt(i),1));
            	}
            }         
            in.close();       
		} 
		catch(FileNotFoundException e) {
			System.out.println("Input File Not Found");
            e.printStackTrace();
		}            
	}
	
	public HuffmanNode popSmall() {
		// Takes the smallest and removes
		int smallest = buildTreeList.get(0).freq;
		HuffmanNode remove = buildTreeList.get(0);	
		for(int i = 0; i < buildTreeList.size(); i++) {
			if(buildTreeList.get(i).freq < smallest) {
				smallest = buildTreeList.get(i).freq;
				// Remove
				remove = buildTreeList.get(i);
			}
		}
		buildTreeList.remove(remove);
		return remove;
	}
	
	public void makelist() {
		buildTreeList = (ArrayList<HuffmanNode>)nodeList.clone();
		
		if(buildTreeList.size() == 1) {
            root.left = buildTreeList.get(0);
            return;
        }

        while(buildTreeList.size()>1){
            HuffmanNode parentNode = new HuffmanNode(null,0);
            parentNode.right = popSmall();             
            parentNode.left = popSmall();
            parentNode.freq = parentNode.left.freq + parentNode.right.freq;
            buildTreeList.add(parentNode);
        }        
        root = buildTreeList.get(0);
    }

	// Make Encoded File
	public void nodeEncoder(HuffmanNode node, StringBuilder strBuild) {	
		// If left Exists
		if(node.left != null) {
			strBuild.append("0");
	        nodeEncoder(node.left, strBuild);
	    }
		// If right Exists
		if(node.right != null) {
	        strBuild.append("1");
	        nodeEncoder(node.right, strBuild);
	    }
	
	    if(node.inCh != null) 
	        node.encoding= strBuild.toString();    
	    if(strBuild.length() > 0)
	        strBuild.setLength(strBuild.length() - 1);
	}
	  
	public void mapEncodings() {
		nodeEncoder(root , new StringBuilder());
    }
 
	public void encodeFile(File inputFile, File outputFile) {
		// How to write to the File using BufferedWriter
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(outputFile));
			Scanner in = new Scanner(inputFile);
			String line;  
			// Goes through the Entire File
			while(in.hasNext()) {
				line = in.nextLine().toLowerCase();
                StringBuilder encodedLine = new StringBuilder();	  
    		    for(int i = 0; i < line.length(); i++)
    		    	// Copies over each Character
    		    	encodedLine.append(findNode(line.charAt(i)).encoding);    	        
                out.write(encodedLine.toString() + "\n");
			}
			out.close();
			in.close();
		 }
		 catch(IOException e) {
			 System.out.println("Unable to process file");
		 }      
      }
	  
	  int treeBalance(HuffmanNode node) {
		  if (node == null)
			  return 0;
		  else {
			  int left = maxDepth(node.left); 
		      int right = maxDepth(node.right);
		      int difference = left - right;
		      return difference;
		  }
	  }
		  
	  int maxDepth(HuffmanNode node) {
		  // If empty
		  if (node == null) 
			 return 0;
		  // To Traverse
	      else {        
	         int left = maxDepth(node.left); 
	         int right = maxDepth(node.right); 
	         if (left > right) 
	        	 return (left + 1); 
	         else 
	             return (right + 1); 
	      } 
	  }  

	  public void outputList(File table){
	        try {
	        	// Output File
	        	BufferedWriter fileOutput = new BufferedWriter(new FileWriter(table));
	            for(HuffmanNode node: nodeList)
	                fileOutput.write(node + "\n");           
	            fileOutput.write("Total Bits Saved: " + memSaved());
	            fileOutput.close();
	        } 
	        catch(IOException e){
	            System.out.println("Encoding File Error");         
	        }
	   }

	  int getLeaves(HuffmanNode node) { 
		  	// Checks if Empty
	        if (node == null) 
	            return 0; 
	        // Checks if it Only has Root
	        else if (node.left == null && node.right == null) 
	            return 1; 
	        // Otherwise Recursively calls to calculate Total
	        else
	            return getLeaves(node.left) + getLeaves(node.right); 
	  }
	  
	  // Calculates The Memory Saved
	  public int memSaved() {	 
		  int initial = 0;
		  int end = 0;	  
		  for(int i = 0; i < nodeList.size(); i++) {
			  end += (nodeList.get(i).encoding.length() * nodeList.get(i).freq);
	          initial += (nodeList.get(i).freq * 8);
	      }
		  // Calculates the Difference from Beginnning to End
	      return initial - end;	  
	  }
}

