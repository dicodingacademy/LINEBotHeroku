package com.linecorp.example.moviesdata;

public class Event {

    private EventData[] data;

    private Boolean success;

    public EventData[] getData ()
    {
        return data;
    }

    public void setData (EventData[] data)
    {
        this.data = data;
    }

    public Boolean getSuccess ()
    {
        return success;
    }

    public void setSuccess (Boolean success)
    {
        this.success = success;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [data = "+data+", success = "+success+"]";
    }
}
