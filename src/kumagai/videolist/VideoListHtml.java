package kumagai.videolist;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/**
 * HTML出力ロジック。
 */
class VideoListHtml
{
	/**
	 * HTML出力ロジック。
	 * @param sortVideoList 動画リスト
	 * @param filename 出力ファイル名
	 */
	static public void output(TreeMap<Integer, ArrayList<File>> sortVideoList, String filename)
		throws IOException
	{
		File htmlfile = new File(filename);
		PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(htmlfile)));
		writer.println("<html>");
		for (Map.Entry<Integer, ArrayList<File>> entry : sortVideoList.entrySet())
		{
			writer.printf("<h1>%d時台 %d件</h1>\n", entry.getKey(), entry.getValue().size());
			for (File file : entry.getValue())
			{
				writer.printf("<embed src='file:///%s'></embed>\n", file.getAbsolutePath().replaceAll("\\", "/"));
			}
		}
		writer.println("</html>");
		writer.close();
	}
}
