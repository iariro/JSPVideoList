package kumagai.videolist;

import java.io.*;
import java.util.*;

class VideoListHtml
{
	public VideoListHtml(TreeMap<Integer, ArrayList<File>> sortVideoList, String filename)
		throws IOException
	{
		File htmlfile = new File(filename);
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(htmlfile)));
		pw.println("<html>");
		for (Map.Entry<Integer, ArrayList<File>> entry : sortVideoList.entrySet())
		{
			pw.printf("<h1>%dji %dken</h1>\n", entry.getKey(), entry.getValue().size());
			for (File file : entry.getValue())
			{
				pw.printf("<embed src='%s'></embed>\n", file.getAbsolutePath());
			}
		}
		pw.println("</html>");
		pw.close();
	}
}
