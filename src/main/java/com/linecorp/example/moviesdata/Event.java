package com.linecorp.example.moviesdata;

public class Event {

    private Boolean success;
//    public List<Data> data = new ArrayList<Data>();
    private Data[] data;
    public Boolean getSuccess ()
    {
        return success;
    }
    public Data[] getData ()
    {
        return data;
    }

//    public List<Data> getData() {
//        return data;
//    }
}
