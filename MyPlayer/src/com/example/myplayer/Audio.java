package com.example.myplayer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class Audio extends Fragment 
{
	View rootView;
	private ListView lvAudio;
	private List <String>  canciones;
    private EditText directorio;
    
    ImageButton btnBuscar;
    
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		    rootView = inflater.inflate(R.layout.activity_audio,container, false);
		    
		    //inicializar 
		    lvAudio = (ListView)rootView.findViewById(R.id.lvVideo);
			canciones = new ArrayList<String>();
			directorio = (EditText) rootView.findViewById(R.id.txtDirectorio);
			btnBuscar = (ImageButton) rootView.findViewById(R.id.btnBuscarDirectorio);
			
			btnBuscar.setOnClickListener(new OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					// TODO Auto-generated method stub
					consultarLista();
				}
			});
			
			lvAudio.setOnItemClickListener(new OnItemClickListener()
			{
				@Override
				public void onItemClick(AdapterView<?> parent, View view,int position, long id) 
				{
					// TODO Auto-generated method stub
					Intent intent = new Intent(getActivity(), PlayVideo.class);
					intent.putExtra("txtNameCancion",directorio.getText().toString()+"/"+canciones.get(position));
					startActivity(intent);
				}
			});
		return rootView;
	}
	
	private void consultarLista() 
	{
		File archivo = new File(directorio.getText().toString());
		if(archivo.exists())
		{
			Filtro filtro = new Filtro(".ogg");
			
			if(archivo.listFiles(filtro).length > 0)
			{
				for(File cancion:archivo.listFiles(filtro))
				{
					canciones.add(cancion.getName());
				}
				
				ArrayAdapter <String> listavideos = new ArrayAdapter<String>(this.getActivity(), R.layout.text_listview, canciones);
				lvAudio.setAdapter(listavideos);
			}
		}
	}
}
