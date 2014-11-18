package com.example.myplayer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

public class Video extends Fragment 
{
	View rootView;
	private ListView lvVideo;
	private List <String>  videos;
    private EditText directorio;
	
	ImageButton btnBuscar;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.activity_video,container, false);
		
		  //inicializar 
		    lvVideo = (ListView)rootView.findViewById(R.id.lvVideo);
			videos = new ArrayList<String>();
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
			
			lvVideo.setOnItemClickListener(new OnItemClickListener()
			{
				@Override
				public void onItemClick(AdapterView<?> parent, View view,int position, long id) 
				{
					// TODO Auto-generated method stub
					Intent intent = new Intent(getActivity(), PlayVideo.class);
					intent.putExtra("txtNameVideo",directorio.getText().toString()+"/"+videos.get(position));
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
			Filtro filtro = new Filtro(".mp4");
			
			if(archivo.listFiles(filtro).length > 0)
			{
				for(File cancion:archivo.listFiles(filtro))
				{
					videos.add(cancion.getName());
				}
				
				ArrayAdapter <String> listavideos = new ArrayAdapter<String>(this.getActivity(), R.layout.text_listview, videos);
				lvVideo.setAdapter(listavideos);
			}
		}
	}
}
