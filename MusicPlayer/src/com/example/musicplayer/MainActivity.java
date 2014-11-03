package com.example.musicplayer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.support.v7.app.ActionBarActivity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class MainActivity extends ActionBarActivity{
	
	String ruta = "/system/media/audio/ringtones";
	private ListView list;
	Button detener;
	Button pausar;
	private List <String>  canciones = new ArrayList<String>();
	MediaPlayer reproductor = new MediaPlayer();
	
	Filtro filtro = new Filtro();//objeto tipo class
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//Code
		detener = (Button) findViewById(R.id.btnStop);
		pausar = (Button) findViewById(R.id.btnPause);
		list = (ListView)findViewById(R.id.listview);
		
		//Update List
        File archivo = new File(ruta);
		
		if(archivo.listFiles(filtro).length > 0)
		{
			for(File cancion:archivo.listFiles(filtro))
			{
				canciones.add(cancion.getName());
			}
			
			ArrayAdapter <String> listaCanciones = new ArrayAdapter<String>(this, R.layout.item_cancion, canciones);
			list.setAdapter(listaCanciones);
		}
		
		//Events
		detener.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				detener();
			}
		});
		
		pausar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				pausar();
			}
		});
		
		list.setOnItemClickListener(new OnItemClickListener(){
			public void onItemClick(AdapterView<?> parent, View view, int position, long id){
				Toast.makeText(getApplicationContext(), "Reproduciendo canción " + position, Toast.LENGTH_SHORT).show();
				reproducir(position);
			}
		});
	}
	
	private void reproducir(int posicion)
	{
		try {
			reproductor.reset();
			reproductor.setDataSource(ruta + canciones.get(posicion));
			reproductor.prepare();
			reproductor.start();
			
		} catch (IllegalArgumentException | SecurityException
				| IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void pausar()
	{
		reproductor.pause();
	}
	
	private void detener()
	{
		reproductor.stop();
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
