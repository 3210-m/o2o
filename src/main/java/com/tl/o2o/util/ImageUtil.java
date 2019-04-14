package com.tl.o2o.util;

import com.tl.o2o.dto.ImageHolder;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @author tangli
 * @create 2018-11-04 下午11:54
 **/
public class ImageUtil {

	public static String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();

	private static final SimpleDateFormat sDateFormatnew =new SimpleDateFormat("yyyyMMddHHmmss");
	private static final Random r = new Random();

	public static String generateThumbnail(ImageHolder imageHolder, String targetAddr){
		String readfileName = getRandomFielName();
		String extension = getFileExtension(imageHolder.getImageName());
		makeDirPath(targetAddr);
		//相对路径
		String relativeAddr = targetAddr + readfileName + extension;
		File dest = new File(PathUtil.getImgBasePath()+relativeAddr);
		try {
			Thumbnails.of(imageHolder.getImage()).size(200,200)
					.watermark(Positions.BOTTOM_RIGHT,
							ImageIO.read(new File(basePath + "water.jpg")), 0.5f)
					.outputQuality(0.8f)
					.toFile(dest);
		}catch (IOException e){
			e.printStackTrace();
		}
		return relativeAddr;
	}

	public static String generateNormalImg(ImageHolder imageHolder, String targetAddr){
		String readfileName = getRandomFielName();
		String extension = getFileExtension(imageHolder.getImageName());
		makeDirPath(targetAddr);
		//相对路径
		String relativeAddr = targetAddr + readfileName + extension;
		File dest = new File(PathUtil.getImgBasePath()+relativeAddr);
		try {
			Thumbnails.of(imageHolder.getImage()).size(337,640)
					.watermark(Positions.BOTTOM_RIGHT,
							ImageIO.read(new File(basePath + "water.jpg")), 0.5f)
					.outputQuality(0.9f)
					.toFile(dest);
		}catch (IOException e){
			e.printStackTrace();
		}
		return relativeAddr;
	}
	/**
	 * 生成随机文件名,年月日时分秒+五位随机数
	 * @return
	 */
	public static String getRandomFielName() {
		int rannum = r.nextInt(89999)+10000;
		String nowTimeStr = sDateFormatnew.format(new Date());
		return nowTimeStr+rannum;
	}

	/**
	 * 获取输入文件流的扩展名（jpg,png）
	 * @return
	 */
	private static String getFileExtension(String fileName) {
		return fileName.substring(fileName.lastIndexOf("."));
	}

	/**
	 * 创建目标路径及涉及到的目录，即/Users/tangli/o2o/image/xxx.jpg
	 * 那么Users tangli o2o image都会被自动创建
	 * @param targetAddr
	 */
	private static void makeDirPath(String targetAddr) {
		String realFileParentPath = PathUtil.getImgBasePath()+targetAddr;
		File dirPath = new File(realFileParentPath);
		if(!dirPath.exists()){
			dirPath.mkdirs();
		}

	}

	/**
	 * 判断
	 * storePath:是文件的路径，则删除该文件
	 * storePath:是目录的路径，则删除目录下所有文件
	 * @param storePath
	 */
	public static void deleteFileOrPath(String storePath){
		File fileOrPath = new File(PathUtil.getImgBasePath()+storePath);
		if(fileOrPath.exists()){
			if(fileOrPath.isDirectory()){
				//将目录里的文件递归出来删除
				File files[] = fileOrPath.listFiles();
				for(int i = 0;i<files.length;i++){
					files[i].delete();
				}
			}
			//删除目录
			fileOrPath.delete();
		}
	}

	public static void main(String[] args) {
		try {
			Thumbnails.of(new File("/Users/tangli/Documents/xiaohuangren.jpg"))
					.size(200, 200).watermark(Positions.BOTTOM_RIGHT,
					ImageIO.read(new File(basePath + "water.jpg")), 0.5f).outputQuality(0.8f)
					.toFile("/Users/tangli/Documents/xiaohuangrennews.jpg");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
