package com.linecorp.example.moviesdata;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class Event {

    private Boolean success;
    private List<Data> data;
    public Boolean getSuccess ()
    {
        return success;
    }
    public List<Data> getData(){
        return data;
    }
}
