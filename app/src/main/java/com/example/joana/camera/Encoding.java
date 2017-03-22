package com.example.joana.camera;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.ByteArrayOutputStream;

/**
 * Created by Joana on 20/03/2017.
 */

public class Encoding {

    public static String encodeToBase64(Bitmap image, Bitmap.CompressFormat compressFormat,int quality){
        ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
        return Base64.encodeToString(byteArray.toByteArray(), Base64.DEFAULT);
    }

    public static Bitmap decodeBase64(String input){
        byte[] decodeByte = Base64.decode(input,0);
        return BitmapFactory.decodeByteArray(decodeByte,0,decodeByte.length);
    }
}
