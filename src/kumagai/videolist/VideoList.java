package kumagai.videolist;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

/**
 * 動画ファイルのリスト
 */
class VideoList
{
	/**
	 * 動画リストを読み込みリストを出力
	 * @param args [0]=動画のあるパス
	 */
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

	/**
	 * 動画リストを生成する
	 * @param path 動画のあるパス
	 */
	public VideoList(String path)
	{
		files = new File(path).listFiles();
	}

	/**
	 * 時間でソートする
	 * @return 時間でソートしたリスト
	 */
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
