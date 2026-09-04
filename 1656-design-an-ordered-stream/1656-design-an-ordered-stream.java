class OrderedStream {
    private int currentPointer = 1;
    private String[] streamData;
    public OrderedStream(int n) {
        streamData = new String[n + 1];
    }
    
    public List<String> insert(int idKey, String value) {
        streamData[idKey] = value;
        List<String> result = new ArrayList<>();
        while(currentPointer < streamData.length && streamData[currentPointer] != null){
            result.add(streamData[currentPointer]);
            currentPointer++;
        }
        return result;
    }
}

/**
 * Your OrderedStream object will be instantiated and called as such:
 * OrderedStream obj = new OrderedStream(n);
 * List<String> param_1 = obj.insert(idKey,value);
 */