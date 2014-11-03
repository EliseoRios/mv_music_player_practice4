package com.example.musicplayer;

import java.io.File;
import java.io.FilenameFilter;

public class Filtro implements FilenameFilter
{
	@Override
	public boolean accept(File directorio, String nombreArchivo)
	{
		// TODO Auto-generated method stub
		return nombreArchivo.endsWith(".ogg");
	}
}
