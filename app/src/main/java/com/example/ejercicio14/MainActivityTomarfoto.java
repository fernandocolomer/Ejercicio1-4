package com.example.ejercicio14;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.ejercicio14.Configuracion.SQLiteConexion;
import com.example.ejercicio14.Configuracion.Transacciones;
import com.google.android.material.snackbar.Snackbar;

public class MainActivityTomarfoto extends AppCompatActivity {
    EditText nombre,descripcion;
    ImageView ObjectImagen;
    Button btnFotos,btnguardarbd;
    String PathFoto;
    static final int REQUESTCODECAMARA=100;
    static final int REQUESTTAKEPHOTO=101;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tomarfoto);

        nombre=(EditText) findViewById(R.id.txtNombres) ;
        descripcion=(EditText) findViewById(R.id.txtDescripcion);
        ObjectImagen=(ImageView) findViewById(R.id.idFoto);
        btnFotos=(Button) findViewById(R.id.btnFotografia);
        btnguardarbd=(Button) findViewById(R.id.btnGuardar);

        btnFotos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OtorgarPermiso();
            }
        });

        btnguardarbd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agregarPersonas();
            }
        });

    }

    private void OtorgarPermiso() {
        if(ContextCompat.checkSelfPermission(getApplicationContext(),Manifest.permission.CAMERA) !=
        PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CAMERA},REQUESTCODECAMARA);
        }
        else
        {TomarFotografia();}

    }

    private void TomarFotografia() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,REQUESTTAKEPHOTO);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == REQUESTCODECAMARA)
        {
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                TomarFotografia();
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Permiso Denegado",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUESTTAKEPHOTO && resultCode == RESULT_OK)
        {
            Bundle extraerfoto = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extraerfoto.get("data");
            ObjectImagen.setImageBitmap(imageBitmap);
        }
    }


    private void agregarPersonas() {

        SQLiteConexion conexion = new SQLiteConexion(this, Transacciones.NameDatabase, null, 1);

        SQLiteDatabase db = conexion.getWritableDatabase();

        ContentValues valor = new ContentValues();

        valor.put(Transacciones.imagenes,ObjectImagen.getImageAlpha());
        valor.put(Transacciones.nombres, nombre.getText().toString());
        valor.put(Transacciones.descripcion, descripcion.getText().toString());


        Long resultado = db.insert(Transacciones.createTablePersonasFoto, Transacciones.id, valor);

        Toast.makeText(getApplicationContext(), "Registro ingreso con exito, Codigo " + resultado.toString(),Toast.LENGTH_LONG).show();
        db.close();

        //Limpia el textPlain
        nombre.setText("");
        descripcion.setText("");

        //menu principal
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);


    }

}
