package com.linecorp.example.moviesdata;

public class Movie {

    private String Title;
    private String Released;
    private String Director;
    private String Writer;
    private String Actors;
    private String Plot;
    private String Poster;
    private String Awards;
    
    
    // Getters and setters are not required for this example.
    // GSON sets the fields directly using reflection.
    
    public String getMovie(){
        String msg = "Plot: " + Plot + "\nReleased: " + Released + "\nDirector: " + Director + "\nWriter: " + Writer + "\nAwards: " + Awards + "\nActors: " + Actors;
        return msg;
    }
    
    public String getPlot(){
        String msg = "Plot: " + Plot;
        return msg;
    }
    
    public String getReleased(){
        String msg = "Released: " + Released;
        return msg;
    }
    
    public String getDirector(){
        String msg = "Director: " + Director;
        return msg;
    }
    
    public String getWriter(){
        String msg = "Writer: " + Writer;
        return msg;
    }
    
    public String getAwards(){
        String msg = "Awards: " + Awards;
        return msg;
    }
    
    public String getActors(){
        String msg = "Actors: " + Actors;
        return msg;
    }
    
    public String getPoster(){
        return Poster;
    }
    
    public String getTitle(){
        return Title;
    }
}
