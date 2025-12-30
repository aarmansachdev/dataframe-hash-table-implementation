public class LL<T> {
    private LLNode head;
    private LLNode tail;
    private int length;

    public LL() {
        head = new LLNode();
        tail = new LLNode();
        head.next = tail;
        length = 0;
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("print the series ...");
        result.append("\n");
        result.append("==================");
        result.append("\n");
        result.append("null\t:\tnull\n");

        LLNode current = head.next;
        while (current != tail) {
            result.append(current.index).append("\t:\t").append(current.data).append("\n");
            current = current.next;
        }

        result.append("null\t:\tnull\n");
        return result.toString();
    }

    public int getLength() {
        return length;
    }

    public String[] getDataArray() {
        String[] dataArray = new String[length];
        LLNode current = head.next;
        int i = 0;
        while (current != tail) {
            dataArray[i] = String.valueOf(current.data);
            current = current.next;
            i++;
        }
        return dataArray;
    }

    public String[] getIndexArray() {
        String[] indexArray = new String[length];
        LLNode current = head.next;
        int i = 0;
        while (current != tail) {
            indexArray[i] = current.index;
            current = current.next;
            i++;
        }
        return indexArray;
    }

    public void appendNode(String _index, T _data) {
        if (_index == null || _index.isEmpty()) {
            _index = String.valueOf(length);
        }

        LLNode newNode = new LLNode(_index, _data);
        LLNode last = head;
        while (last.next != tail) {
            last = last.next;
        }
        newNode.next = tail;
        last.next = newNode;
        length++;
    }

    public LLNode searchNode(String _index) {
        LLNode current = head.next;
        while (current != tail) {
            if (current.index.equals(_index)) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    public void removeNode(String _index) throws IllegalArgumentException {
        LLNode current = head;
        while (current.next != tail) {
            if (current.next.index.equals(_index)) {
                current.next = current.next.next;
                length--;
                return;
            }
            current = current.next;
        }
        throw new IllegalArgumentException("removeNode(String _index): No node with an index " + _index + " in the list");
    }

    public void updateNode(String _index, T value) throws IllegalArgumentException {
        LLNode node = searchNode(_index);
        if (node != null) {
            node.setData(value);
        } else {
            throw new IllegalArgumentException("updateNode(String _index, T value): No node with an index " + _index + " in the list");
        }
    }

    public class LLNode {
        private String index;
        private T data;
        private LLNode next;

        public LLNode() {
            this.index = null;
            this.data = null;
            this.next = null;
        }

        public LLNode(String _index, T _data) {
            this.index = _index;
            this.data = _data;
            this.next = null;
        }

        public String getIndex() {
            return index;
        }

        public T getData() {
            return data;
        }

        public void setData(T d) {
            this.data = d;
        }
    }
}