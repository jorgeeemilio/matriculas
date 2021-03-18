package es.studium.MisMatriculas;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MisMatriculas
{
	public static void main(String[] args)
	{
		// Procesar información
		// 1º Averiguar si matrícula NUEVA o ANTIGUA
		// 2º Correcta o No
		// Generar otro fichero llamado correctas.txt
		// Solo con las matriculas correctas
		
		Pattern nuevas, antiguas;
		Matcher m;
		nuevas = Pattern.compile("[0-9]{4}[A-Z&&[^AEIOUÑ]]{3}");
		antiguas = Pattern.compile("[A-Z&&[^Ñ]]{1,2}[0-9]{4,6}[A-Z&&[^Ñ]]{0,2}");

		// Leer fichero
		try
		{
			//Origen de los datos en el proyecto anterior
			FileReader fr = new FileReader("matriculas.txt");
			//Buffer de lectura
			BufferedReader entrada = new BufferedReader(fr);
			// Destino de los datos
			FileWriter fw = new FileWriter("correctas.txt", true);
			// Buffer de escritura
			BufferedWriter bw = new BufferedWriter(fw);
			// Objeto para la escritura
			PrintWriter salida = new PrintWriter(bw);

			String s;
			//Bucle para sacar la información del archivo
			while((s=entrada.readLine())!=null)
			{
				System.out.print(s);
				m = nuevas.matcher(s);
				if(!m.matches())
				{
					m = antiguas.matcher(s);
					if(!m.matches())
					{
						System.out.print("-->Incorrecta");
					}
					else
					{
						System.out.print("-->Correcta");
						//Guardamos la primera línea
						salida.println(s);
					}
				}
				else
				{
					System.out.print("-->Correcta");
					salida.println(s);
				}
				System.out.println("");
			}
			//Cerrar el objeto entrada
			entrada.close();
			fr.close();
			//Cerrar el objeto salida, el objeto bw y el fw
			salida.close();
			bw.close();
			fw.close();
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Archivo NO encontrado");
		}
		catch(IOException i)
		{
			System.out.println("Se produjo un error de Archivo");
		}
	}
}
