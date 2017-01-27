package com.linecorp.example.moviesdata;

public class Event {

    private EventData[] data;

    private String success;

    public EventData[] getData ()
    {
        return data;
    }

    public void setData (EventData[] data)
    {
        this.data = data;
    }

    public String getSuccess ()
    {
        return success;
    }

    public void setSuccess (String success)
    {
        this.success = success;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [data = "+data+", success = "+success+"]";
    }
}
