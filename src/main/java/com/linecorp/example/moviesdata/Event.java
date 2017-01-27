package com.linecorp.example.moviesdata;

import java.util.ArrayList;
import java.util.List;

public class Event {

    private Boolean success;
    public List<EventData> data = new ArrayList<EventData>();

    public Boolean getSuccess ()
    {
        return success;
    }

    public List<EventData> getData() {
        return data;
    }
}
