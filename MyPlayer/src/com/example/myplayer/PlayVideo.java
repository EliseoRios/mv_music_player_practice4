package com.example.myplayer;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class PlayVideo extends ActionBarActivity 
{
	VideoView videoView;
	Bundle bundle;//para recuperar datos

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_play_video);
		
		
		bundle = getIntent().getExtras();
		String directorio = bundle.getString("txtNameVideo");
		
		MediaController mediaController =new MediaController(this);
		videoView = (VideoView) findViewById(R.id.videoView_video);
		videoView.setMediaController(mediaController);
		
		Uri path = Uri.parse(directorio);
		videoView.setVideoURI(path);
		videoView.start();
	}
}