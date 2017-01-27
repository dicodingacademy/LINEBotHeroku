package com.linecorp.example.moviesdata;

import java.util.Collection;
import java.util.List;

public class Event {

    private Boolean success;
    private Collection<Data> data;
    public Boolean getSuccess ()
    {
        return success;
    }
    public Collection<Data> getData(){
        return data;
    }
}
