package com.dicoding.temanngoding;

/**
 * Created by root on 1/27/17.
 */
public class Data {

    private String name;
    private String summary;
    private String description;
    private String address;
    private String begin_time;
    private String end_time;
    private int quota;
    private String owner_display_name;
    private String image_path;
    private String header_image;
    private String city_name;
    private String link;
    private int registrants;
    private int attenders;

    public String getOwner_display_name ()
    {
        return owner_display_name;
    }

    public String getImage_path ()
    {
        return image_path;
    }

    public String getName() {
        return name;
    }

    public String getSummary() {
        return summary;
    }

    public String getDescription() {
        return description;
    }

    public String getAddress() {
        return address;
    }

    public String getBegin_time() {
        return begin_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public int getQuota() {
        return quota;
    }

    public String getHeader_image() {
        return header_image;
    }

    public String getCity_name() {
        return city_name;
    }

    public String getLink() {
        return link;
    }

    public int getRegistrants() {
        return registrants;
    }

    public int getAttenders() {
        return attenders;
    }
}
