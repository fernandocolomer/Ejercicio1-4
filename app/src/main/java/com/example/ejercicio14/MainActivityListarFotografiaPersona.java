package com.example.ejercicio14;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.ejercicio14.Configuracion.SQLiteConexion;
import com.example.ejercicio14.Configuracion.Transacciones;
import com.example.ejercicio14.Tablas.PersonaFotografia;
import java.util.ArrayList;

public class MainActivityListarFotografiaPersona extends AppCompatActivity {

    SQLiteConexion conexion;
    ListView lista;
    ArrayList<PersonaFotografia> listaPersonaImangen;
    ArrayList <String> ArregloPersonaImagen;
    Button btnregresar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_listar_fotografia_persona);

        lista = (ListView) findViewById(R.id.btnListarFtos);
        conexion = new SQLiteConexion(this, Transacciones.NameDatabase,null,1);

        ArrayAdapter arp = new ArrayAdapter(this, android.R.layout.simple_list_item_1,ArregloPersonaImagen);
        lista.setAdapter(arp);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                obtenerImagenPersona(position);
            }
        });
    }




    private void obtenerImagenPersona(int id) {
        PersonaFotografia personaImg =  listaPersonaImangen.get(id);

        Intent intent = new Intent(getApplicationContext(),MainActivityTomarfoto.class);

        intent.putExtra("codigo", personaImg.getId()+"");
        intent.putExtra("imagen",personaImg.getImagen());
        intent.putExtra("Nombres", personaImg.getNombres());
        intent.putExtra("Descripcion", personaImg.getDescripcion());

    }

    private void obtenerlistPersonas() {
        SQLiteDatabase db = conexion.getReadableDatabase();
        PersonaFotografia list_personasIMg = null;
        listaPersonaImangen=new ArrayList<PersonaFotografia>();
        Cursor cursor = db.rawQuery("SELECT * FROM "+ Transacciones.tablapersonasFoto,null);//consulta ala BD
        while (cursor.moveToNext())
        {
            list_personasIMg =new PersonaFotografia();
            list_personasIMg.setId(cursor.getInt(0));
            list_personasIMg.setImagen(cursor.getString(1));
            list_personasIMg.setNombres(cursor.getString(2));
            list_personasIMg.setDescripcion(cursor.getString(3));

            listaPersonaImangen.add(list_personasIMg);
        }
        cursor.close();
        llenarlistaPersonas();
    }
    private void llenarlistaPersonas()
    {
        ArregloPersonaImagen = new ArrayList<String>();
        for (int i=0; i<listaPersonaImangen.size();i++)
        {
            ArregloPersonaImagen.add(listaPersonaImangen.get(i).getId()+"|"+listaPersonaImangen.get(i).getImagen()+"|"+listaPersonaImangen.get(i).getImagen()+"|"+listaPersonaImangen.get(i).getNombres()+"|"+listaPersonaImangen.get(i).getDescripcion());
        }
    }
}