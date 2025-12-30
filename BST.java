public class BST<I extends Comparable<I>, T>{

    class BSTNode {
        private I index;
        private T data;
        private BSTNode left;
        private BSTNode right;

        /**
         * Default constructor. Sets all instance variables to be null.
         */
        public BSTNode() {
            this.index = null;
            this.data = null;
            this.left = null;
            this.right = null;
        }

        /**
         * Constructor. Sets data and index to be _data and _index respectively.
         */
        public BSTNode(I _index, T _data) {
            this.index = _index;
            this.data = _data;
            this.left = null;
            this.right = null;
        }

        /**
         * Returns the index stored in this node.
         */
        public I getIndex() {
            return index;
        }

        /**
         * Returns the data stored in this node.
         */
        public T getData() {
            return data;
        }

        /**
         * Updates the data in this node to the specified value.
         */
        public void setData(T d) {
            this.data = d;
        }

        /**
         * Returns a string representation of the node, indicating its index and data.
         */
        public String toString() {
            return "index:\t" + index + ",\tdata:\t" + data + "\n";
        }
    }


    private BSTNode root;
    private int size;

    /**
     * Constructor. Initializes an empty BST with root set to null and size set to 0.
     */
    public BST() {
        root = null;
        size = 0;
    }


    /**
     * Performs an in-order traversal of the BST and records indices and data values.
     */
    private String inOrderTraversal(BSTNode node) {
        if (node == null) {
            return "";
        }
        StringBuilder result = new StringBuilder();
        result.append(inOrderTraversal(node.left));
        result.append(node.toString());
        result.append(inOrderTraversal(node.right));
        return result.toString();
    }

    /**
     * Returns a string representation of the entire BST using in-order traversal.
     */
    public String toString() {
        return "In-order Traversal of the BST ...\n==================\n" + inOrderTraversal(root);
    }

    /**
     * Returns the size of the BST, i.e., the number of valid nodes.
     */
    public int getSize() {
        return size;
    }

    /**
     * Adds a new node with the specified index and data to the BST.
     */
    public void addNode(I _index, T _data) {
        root = addNodeHelper(root, _index, _data);
        size++;
    }

    private BSTNode addNodeHelper(BSTNode node, I _index, T _data) {
        if (node == null) {
            return new BSTNode(_index, _data);
        }

        if (_index.compareTo(node.index) < 0) {
            node.left = addNodeHelper(node.left, _index, _data);
        } else if (_index.compareTo(node.index) > 0) {
            node.right = addNodeHelper(node.right, _index, _data);
        }

        return node;
    }

    /**
     * Searches for a node with the specified index in the BST.
     */
    public BSTNode searchNode(I _index) {
        return searchNodeHelper(root, _index);
    }

    private BSTNode searchNodeHelper(BSTNode node, I _index) {
        if (node == null || node.index.equals(_index)) {
            return node;
        }

        if (_index.compareTo(node.index) < 0) {
            return searchNodeHelper(node.left, _index);
        } else {
            return searchNodeHelper(node.right, _index);
        }
    }

    /**
     * Removes a node with the specified index from the BST.
     */
    public void removeNode(I _index) {
        if (searchNode(_index) == null) {
            throw new IllegalArgumentException("removeNode(I _index): No node with an index " + _index + " in the BST");
        }
        root = removeNodeHelper(root, _index);
        size--;
    }

    private BSTNode removeNodeHelper(BSTNode node, I _index) {
        if (node == null) return null;

        if (_index.compareTo(node.index) < 0) {
            node.left = removeNodeHelper(node.left, _index);
        } else if (_index.compareTo(node.index) > 0) {
            node.right = removeNodeHelper(node.right, _index);
        } else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;

            BSTNode successor = findMin(node.right);
            node.index = successor.index;
            node.data = successor.data;
            node.right = removeNodeHelper(node.right, successor.index);
        }
        return node;
    }

    private BSTNode findMin(BSTNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    /**
     * Updates a node's data with a new value, given its index.
     */
    public void updateNode(I _index, T _newData) {
        BSTNode node = searchNode(_index);
        if (node == null) {
            throw new IllegalArgumentException("updateNode(I _index, T _newData): No node with an index " + _index + " in the BST");
        }
        node.setData(_newData);
    }

/************************************ GRADING CODE (DO NOT MODIFY) ************************************ */
    /**
     * Performs a pre-order traversal of the BST.
     */
    private void preOrderTraversal(BSTNode node, int[] idx, String[] arr, boolean dataFlag) {
        // DO NOT CHANGE THIS. THIS FOR TESTING PURPOSES
        if(node == null)
            return;

        if(dataFlag)
            arr[idx[0]] = String.valueOf(node.getData());
        else
            arr[idx[0]] = String.valueOf(node.getIndex());
        idx[0]++;

        preOrderTraversal(node.left, idx, arr, dataFlag);
        preOrderTraversal(node.right, idx, arr, dataFlag);
    }

    /**
     * Returns an array of data values in pre-order traversal order.
     * @return A String array containing the data values of all nodes in pre-order order
     */
    public String[] getDataArray() {
        /// DO NOT CHANGE THIS. THIS FOR TESTING PURPOSES
        String[] dataArr = new String[size];
        preOrderTraversal(this.root, new int[1], dataArr, true);
        return dataArr;
    }

    /**
     * Returns an array of index values in pre-order traversal order.
     * @return A String array containing the index values of all nodes in pre-order order
     */
    public String[] getIndexArray() {
        // DO NOT CHANGE THIS. THIS FOR TESTING PURPOSES
        String[] indexArr = new String[size];
        preOrderTraversal(this.root, new int[1], indexArr, false);
        return indexArr;
    }

/****************************************************************************************************** */

}