package com.example.musicplayer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.support.v7.app.ActionBarActivity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends ActionBarActivity {
	
	String ruta = "/system/media/audio/ringtones";
	private List <String>  canciones = new ArrayList<String>();
	MediaPlayer reproductor = new MediaPlayer();
	
	Filtro filtro = new Filtro();//objeto tipo class
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		actualizarLista();
	}

	private void actualizarLista() 
	{
		File archivo = new File(ruta);
		
		if(archivo.listFiles(filtro).length > 0)
		{
			for(File cancion:archivo.listFiles(filtro))
			{
				canciones.add(cancion.getName());
			}
			
			ArrayAdapter <String> listaCanciones = new ArrayAdapter<String>();
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
