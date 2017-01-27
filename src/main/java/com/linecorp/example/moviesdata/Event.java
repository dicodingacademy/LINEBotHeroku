package com.linecorp.example.moviesdata;

import java.util.ArrayList;
import java.util.List;

public class Event {

//    private EventData[] data;

    private Boolean success;
    public List<EventData> data = new ArrayList<EventData>();
//    public EventData[] getData ()
//    {
//        return data;
//    }
//
//    public void setData (EventData[] data)
//    {
//        this.data = data;
//    }

    public Boolean getSuccess ()
    {
        return success;
    }

    public List<EventData> getData() {
        return data;
    }
//    public void setSuccess (Boolean success)
//    {
//        this.success = success;
//    }
//
//    @Override
//    public String toString()
//    {
//        return "ClassPojo [data = "+data+", success = "+success+"]";
//    }
}
