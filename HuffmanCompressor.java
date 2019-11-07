import java.io.File;
public class HuffmanCompressor {
	public static String HuffmanCompressors(String inputFileName, String outputFileName){
		
		File inputFile = new File(inputFileName);
		File outputFile = new File(outputFileName);
		// The Output File for the Chars, Frequency, and Encoded
		File encodedTableFile = new File("encodedTable.txt");
		HuffmanTree tree = new HuffmanTree();
		
		tree.createNodeList(inputFile);
		tree.makelist();
		tree.mapEncodings();
		tree.encodeFile(inputFile, outputFile);
		tree.outputList(encodedTableFile);
		
		// Outputs the Height of the Tree
		System.out.println("Tree Height = " + tree.maxDepth(tree.root));
		// Outputs the Number of Leaves in the Tree
		System.out.println("Number of Leaves = " + tree.getLeaves(tree.root));
		// Outputs the Tree Balance
		System.out.println("Tree Balance = " + tree.treeBalance(tree.root));
		// Outputs the Total Memory Saved
		System.out.println("Total Memory Saved = " + tree.memSaved());	
        return "Success";
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HuffmanCompressors("C:\\Users\\morri\\eclipse-workspace\\P2_mgl62_Lee\\testFile.txt", "encodedfile.txt");
	}
}


