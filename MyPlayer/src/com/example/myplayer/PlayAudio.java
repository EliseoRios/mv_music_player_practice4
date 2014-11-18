package com.example.myplayer;

import android.support.v7.app.ActionBarActivity;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.VideoView;

public class PlayAudio extends ActionBarActivity 
{
	Bundle bundle;//para recuperar datos
	private MediaPlayer mp;
	Uri directorioUri;
	
	TextView txtNombreCancion;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_play_audio);
		
		txtNombreCancion = (TextView) findViewById(R.id.txtNombreCancion);
		
		bundle = getIntent().getExtras();
		String directorio = bundle.getString("txtNameCancion");
		directorioUri = Uri.parse(directorio);
		
		txtNombreCancion.setText(directorio);
		
		mp = MediaPlayer.create(getApplication(), directorioUri);
	}
	
	public void reproducirAudio(View v)
	{
		mp = MediaPlayer.create(getApplication(), directorioUri);
		mp.start();
	}
	
	public void pausarAudio(View v)
	{
		if(mp.isPlaying())
		 mp.pause();
	}
	
	public void detenerAudio(View v)
	{
		if(mp.isPlaying())
		 mp.stop();
	}
}
