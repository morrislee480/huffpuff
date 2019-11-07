# huffpuff
By Morris Lee

<h2>Implements the Huffman encoding of English characters, using a combined list and binary tree data structure</h2>


huffmanCoder reads and compresses an input text file <i>inputFileName</i>, (based on the Huffman
encoding produced on the input file) and output the compressed file in <i>outputFileName</i>

The program prints out:

<i>● The Huffman encoding of characters prints in the form of a table of character/frequency/encoding triples – one triple per line, with “:” separating the elements </i>
      (i.e. d : 23 : 1011)

<i>● The calculated amount of space savings </i>

<i>● Implements the HuffmanNode class with the following fields:
    inChar: the character denoted by the node
    frequency: The number of occurrences of characters in the subtree rooted at this node.
    left: left child of a node in the Huffman tree
    right: right child of a node in the Huffman tree </i>

<i>● Basic tree properties. Specifically:
    The number of leaves
    The height of your tree</i>

The program also contains the following helper methods:

<i>● A method to scan the input text file to generate the initial list of HuffmanNodes</i>

<i>● A method to merge two HuffmanNodes and return the combined HuffmanNode

<i>● A method to run the Huffman encoding algorithm to produce the Huffman tree

<i>● A method to traverse the Huffman tree to output the character encoding

<i>● A method to scan the input text file, produce the encoded output file, and compute the savings</i>

<i>● Miscellaneous methods to calculate the properties (height, number of leaf nodes) of the tree</i>
