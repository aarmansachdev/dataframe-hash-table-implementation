public class HashTable<V> {
    private static final Object BRIDGE = new String("[BRIDGE]".toCharArray());
    private int size;
    private int capacity;
    private String[] keys;
    private V[] values;

    public HashTable() {
        this.size = 0;
        this.capacity = 4;
        keys = new String[capacity];
        values = (V[]) new Object[capacity];
    }

    public String toString() {
        StringBuilder result = new StringBuilder("printing the hash table ...\n==================\n");
        for (int i = 0; i < capacity; i++) {
            result.append("index:\t").append(i)
                    .append(",\tkey:\t").append(keys[i])
                    .append(",\tdata:\t").append(values[i])
                    .append("\n");
        }
        return result.toString();
    }

    public int getSize() {
        return size;
    }

    public int getCapacity() {
        return capacity;
    }

    public String[] getKeyArray() {
        String[] copy = new String[capacity];
        for (int i = 0; i < capacity; i++) {
            copy[i] = keys[i];
        }
        return copy;
    }

    public V[] getDataArray() {
        V[] copy = (V[]) new Object[capacity];
        for (int i = 0; i < capacity; i++) {
            copy[i] = values[i];
        }
        return copy;
    }

    public String[] getValidKeys() {
        String[] valid = new String[size];
        int index = 0;
        for (int i = 0; i < capacity; i++) {
            if (keys[i] != null && keys[i] != BRIDGE) {
                valid[index++] = keys[i];
            }
        }
        return valid;
    }

    public int getHashIndex(String k) {
        int hashValue = 0;
        for (int i = 0; i < k.length(); i++) {
            int letter = k.charAt(i) - 96;
            hashValue += (hashValue * 27 + letter);
        }
        return hashValue % this.getCapacity();
    }

    public V lookup(String k) throws NullPointerException {
        if (k == null) {
            throw new NullPointerException("lookup(String key): key is null");
        }

        int index = getHashIndex(k);
        int startIndex = index;

        do {
            if (keys[index] == null) {
                return null;
            }
            if (keys[index] != BRIDGE && keys[index].equals(k)) {
                return values[index];
            }
            index = (index + 1) % capacity;
        } while (index != startIndex);

        return null;
    }

    public int insert(String k, V v) throws NullPointerException {
        if (k == null) {
            throw new NullPointerException("insert(String k, V v): k is null");
        }
        if (v == null) {
            throw new NullPointerException("insert(String k, V v): v is null");
        }

        int existingIndex = findExistingKeyIndex(k);
        if (existingIndex != -1) {
            values[existingIndex] = v;
            return existingIndex;
        }

        if ((size + 1) * 100 / capacity >= 55) {
            resizeUp();
        }

        int index = getHashIndex(k);
        int startIndex = index;

        do {
            if (keys[index] == null || keys[index] == BRIDGE) {
                if (keys[index] == null) size++;
                keys[index] = k;
                values[index] = v;
                return index;
            }
            index = (index + 1) % capacity;
        } while (index != startIndex);

        return -1;
    }

    private int findExistingKeyIndex(String k) {
        int index = getHashIndex(k);
        int startIndex = index;

        do {
            if (keys[index] == null) {
                return -1;
            }
            if (keys[index] != BRIDGE && keys[index].equals(k)) {
                return index;
            }
            index = (index + 1) % capacity;
        } while (index != startIndex && keys[index] != null);

        return -1;
    }

    private void resizeUp() {
        int newCapacity = capacity * 2;
        String[] oldKeys = keys;
        V[] oldValues = values;
        int oldSize = size;

        capacity = newCapacity;
        keys = new String[capacity];
        values = (V[]) new Object[capacity];
        size = 0;

        for (int i = 0; i < oldKeys.length; i++) {
            if (oldKeys[i] != null && oldKeys[i] != BRIDGE) {
                insert(oldKeys[i], oldValues[i]);
            }
        }
    }

    private void resizeDown() {
        if (capacity <= 4) return;

        int newCapacity = capacity / 2;
        String[] oldKeys = keys;
        V[] oldValues = values;
        int oldSize = size;

        capacity = newCapacity;
        keys = new String[capacity];
        values = (V[]) new Object[capacity];
        size = 0;

        for (int i = 0; i < oldKeys.length; i++) {
            if (oldKeys[i] != null && oldKeys[i] != BRIDGE) {
                insert(oldKeys[i], oldValues[i]);
            }
        }
    }

    public int delete(String k) throws NullPointerException {
        if (k == null) {
            throw new NullPointerException("delete(String k): k is null");
        }

        int index = getHashIndex(k);
        int startIndex = index;

        do {
            if (keys[index] == null) {
                return getHashIndex(k);
            }
            if (keys[index] != BRIDGE && keys[index].equals(k)) {
                keys[index] = (String) BRIDGE;
                values[index] = null;
                size--;

                if (size * 100 / capacity <= 30) {
                    resizeDown();
                }
                return index;
            }
            index = (index + 1) % capacity;
        } while (index != startIndex);

        return getHashIndex(k);
    }
}