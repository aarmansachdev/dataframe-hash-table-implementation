public class DataFrame {
    private HashTable<SeriesV2<Object>> tabularData;
    private int numRows;
    private int numCols;

    public DataFrame() {
        tabularData = new HashTable<>();
        numRows = 0;
        numCols = 0;
    }

    public DataFrame(String k, SeriesV2<Object> series) {
        this();
        tabularData.insert(k, series);
        numCols = 1;
        numRows = series.getLength();
    }

    public SeriesV2<Object> colLoc(String k) {
        return tabularData.lookup(k);
    }

    public String toString() {
        StringBuilder result = new StringBuilder("printing the dataframe ...\n==================\n");
        String[] colNames = tabularData.getValidKeys();
        for (String colName : colNames) {
            result.append("[colName:\t").append(colName).append("]\n");
            result.append(tabularData.lookup(colName).toString());
            result.append("\n");
        }
        return result.toString();
    }

    public int getNumRows() {
        return numRows;
    }

    public int getNumCols() {
        return numCols;
    }

    public String[] getColNames() {
        return tabularData.getValidKeys();
    }

    public void addColumn(String k, SeriesV2<Object> s) throws IllegalArgumentException {
        if (numCols > 0 && s.getLength() != numRows) {
            throw new IllegalArgumentException("addColumn(String k, SeriesV2<Object> s): the length of s does not match the dataframe's # of rows");
        }
        tabularData.insert(k, s);
        numCols = tabularData.getSize();
        if (numCols == 1) {
            numRows = s.getLength();
        }
    }

    public void removeColumn(String k) {
        tabularData.delete(k);
        numCols = tabularData.getSize();
        if (numCols == 0) {
            numRows = 0;
        }
    }
}