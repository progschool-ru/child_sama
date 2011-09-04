import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

class FilesToucher
{
	private static String [] files = {"data.txt", "list.txt", "DataForGraph.txt"};
	
	public void touch() throws IOException
	{
		for(int i = 0; i < files.length; i++)
		{
			File file= new File(files[i]);
			if(!file.exists())
			{
				createZeroFile(files[i]);
			}
		}
	}
	
	private void createZeroFile(String fileName) throws IOException
	{
		BufferedWriter w = new BufferedWriter(new FileWriter(fileName));
		w.write("0\n");
		w.close();
	}
}
