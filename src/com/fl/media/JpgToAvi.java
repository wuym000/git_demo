package com.fl.media;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

import javax.media.format.VideoFormat;

import org.jim2mov.core.DefaultMovieInfoProvider;
import org.jim2mov.core.ImageProvider;
import org.jim2mov.core.Jim2Mov;
import org.jim2mov.core.MovieInfoProvider;
import org.jim2mov.utils.MovieUtils;
/**
 * 
 * @author li.feng
 * 
 *
 */
public class JpgToAvi {

	/**
	 * @param args
	 */

	public static void main(String[] args) throws Exception {
		//服务器修改行v0.2
		// jpgs目录放置jpg图片,图片文件名为(1.jpg,2.jpg...)
		final File[] jpgs = new File("E:\\Users\\dell\\Desktop\\pic\\").listFiles();
		String a = "test";
		System.out.println(a);
		System.out.println("2");
		System.out.println("2");
		System.out.println("2");
		// 对文件名进行排序(本示例假定文件名中的数字越小,生成视频的帧数越靠前)
		/*Arrays.sort(jpgs, new Comparator<File>() {
			public int compare(File file1, File file2) {
				String numberName1 = file1.getName().replace(".JPEG", "");
				String numberName2 = file2.getName().replace(".JPEG", "");
				return new Integer(numberName1) - new Integer(numberName2);
			}
		});*/
		/*String path = "videos/2015";
		File file = new File(path);
		if(!file.exists()){
			file.mkdirs();
		}*/
		DefaultMovieInfoProvider dmip = new DefaultMovieInfoProvider("test00900.avi");//生成视频的名称
		dmip.setFPS(5); // 设置每秒帧数
		dmip.setNumberOfFrames(jpgs.length); // 总帧数
		//视频宽和高，最好与图片宽高保持一直
		/*dmip.setMWidth(1440);
		dmip.setMHeight(860);*/
		dmip.setMWidth(1280);
		dmip.setMHeight(720);

		new Jim2Mov(new ImageProvider() {
			public byte[] getImage(int frame) {
				try {
					// 设置压缩比
					return MovieUtils.convertImageToJPEG((jpgs[frame]), 1.0f);
				} catch (IOException e) {
					e.printStackTrace();
				}
				return null;
			}
//		}, dmip, null).saveMovie(MovieInfoProvider.TYPE_AVI_MJPEG);
	}, dmip, null).saveMovie(MovieInfoProvider.TYPE_AVI_MJPEG+"",new VideoFormat(VideoFormat.JPEG_RTP));
	}
}
