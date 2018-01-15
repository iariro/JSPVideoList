package kumagai.videolist;

import java.io.*;
import java.text.*;
import java.util.*;

class VideoList
{
	static private final SimpleDateFormat formatDate = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

	static public void main(String [] args)
		throws IOException
	{
		VideoList videoList = new VideoList(args[0]);
		TreeMap<Integer, ArrayList<File>> sortVideoList = videoList.sortByHour();
		for (Map.Entry<Integer, ArrayList<File>> entry : sortVideoList.entrySet())
		{
			System.out.printf("%d %d\n", entry.getKey(), entry.getValue().size());
		}
		new VideoListHtml(sortVideoList, "videolist.html");
	}

	File [] files;

	public VideoList(String path)
	{
		files = new File(path).listFiles();
	}

	public TreeMap<Integer, ArrayList<File>> sortByHour()
	{
		TreeMap<Integer, ArrayList<File>> list = new TreeMap<Integer, ArrayList<File>>();
		for (int i=0 ; i<24 ; i++)
		{
			list.put(i, new ArrayList<File>());
		}

		for (File file : files)
		{
			Calendar calendar = Calendar.getInstance();
			Date date = new Date(file.lastModified());
			calendar.setTime(date);
			list.get(calendar.get(Calendar.HOUR_OF_DAY)).add(file);
		}

		return list;
	}
}
