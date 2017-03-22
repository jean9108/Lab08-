package com.example.joana.camera;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.StringDef;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Xml;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private static final  String TAKE_PHOTO = "Take Photo";
    private static final String CHOOSE_GALLERY = "Choose from Gallery";
    private static final String CANCEL = "Cancel";
    private static final int TAKE_PHOTO_OPTION = 1;
    private static final int CHOOSE_GALLERY_OPTION = 2;
    public final static  String EXTRA_MESSAGE = "com.edi.eci.cosw.MainActivity.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void addPhoto(View view){
        final CharSequence[] options ={TAKE_PHOTO,CHOOSE_GALLERY,CANCEL};
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Add Photo!");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(options[which].equals(TAKE_PHOTO)){
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent,TAKE_PHOTO_OPTION);
                }else if(options[which].equals(CHOOSE_GALLERY)){
                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(intent,"Select Picture"), CHOOSE_GALLERY_OPTION);
                }else if(options[which].equals(CANCEL)){
                    dialog.dismiss();
                }
            }
        });
        builder.create();
        builder.show();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        ImageView image = (ImageView) findViewById(R.id.imageView);

        if(resultCode == RESULT_OK){
            switch (requestCode){
                case TAKE_PHOTO_OPTION:
                    Bitmap photo =(Bitmap) data.getExtras().get("data");
                    image.setImageBitmap(photo);
                    break;
                case CHOOSE_GALLERY_OPTION:
                    Uri selectedImageUri = data.getData();
                    if(selectedImageUri != null){
                        String path = getPathFromURI(selectedImageUri);
                        image.setImageURI(selectedImageUri);
                    }
                    break;
            }
        }
    }

    public String getPathFromURI(Uri selectedImageUri) {
        String res = null;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(selectedImageUri, proj,null,null,null);
        if(cursor.moveToFirst()){
            int column = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            res = cursor.getString(column);
        }
        cursor.close();
        return res;
    }

    public void save(View view){
        EditText editText = (EditText) findViewById(R.id.editText);
        ImageView image = (ImageView) findViewById(R.id.imageView);
        if(editText.length()>0){
            if(editText.length()<=50){
                editText.setError("Text must have more than 50 characters");
            }else{
                Intent intent = new Intent(this,DisplayMessageActivity.class);
                Post message = new Post();
                message.setText(editText.getText().toString());
                Bitmap bitmap =((BitmapDrawable) image.getDrawable()).getBitmap();
                String photo = Encoding.encodeToBase64(bitmap, Bitmap.CompressFormat.PNG,0);
                intent.putExtra(EXTRA_MESSAGE, message);
                startActivity(intent);
            }
        }else {
            editText.setError("Text must be failed");
        }
    }

}
