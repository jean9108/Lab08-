package com.example.joana.camera;

import java.io.Serializable;

/**
 * Created by Joana on 20/03/2017.
 */

public class Post implements Serializable {
    private String text;
    private String photo;

    public String getText(){
        return text;
    }

    public void setText(String text){
        this.text = text;
    }

    public String getPhoto(){
        return  photo;
    }

    public void setPhoto(String photo){
        this.photo = photo;

    }
}
