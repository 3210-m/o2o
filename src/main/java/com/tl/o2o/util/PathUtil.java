package com.tl.o2o.util;

/**
 * @author tangli
 * @create 2018-11-05 下午6:22
 **/
public class PathUtil {

	//获得分隔符
	private static String seperator = System.getProperty("file.separator");

	/**
	 * 获取项目图片的根路径
	 * @return
	 */
	public static String getImgBasePath(){
		String os = System.getProperty("os.name");		//获得操作系统名称
		String basePath;
		if(os.toLowerCase().startsWith("win")){
			basePath = "D:/prijectdev/image/";
		}else {
			basePath = "/Users/tangli/";
		}
		return basePath.replace("/",seperator);
	}

	/**
	 * 依据不同的需求，返回项目图片的子路径
	 * @param shopId
	 * @return
	 */
	public static String getShopImagePath(long shopId){
		String imagePath = "home/o2o/shop/"+shopId+"/";
		String i = imagePath.replace("/",seperator);
		return i;
	}
}
