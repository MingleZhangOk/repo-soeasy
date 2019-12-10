package com.mingzhang.table.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;


public class FileUtil {
	
	private static final Logger LOG = LoggerFactory.getLogger(FileUtil.class);

	public static void deleteFile(File file){
		if(file.exists()){
			if(file.isFile()){
				LOG.info("删除文件：" + file.getPath());
				file.delete();
			}else if(file.isDirectory()){
				File[] files = file.listFiles();
				for(int i=0;i<files.length;i++){
					deleteFile(files[i]);
				}
				file.delete();
			}
		}
	}
	
	public static void transCode(String srcFile, String disFile, String distCode) throws Exception{
		// 如果源文件为空，转码的文件也为空，则手动创建目标文件
		File file = new File(disFile);
		if (!file.exists()) {
			file.createNewFile();
		}
		File src =new File(srcFile);
		if(src.length()==0){
			return;
		}
		//获取文件编码类型
		String fileEncode = EncodingDetect.getJavaEncode(srcFile);
		LOG.info("文件：" + srcFile + "编码为：" + fileEncode);
		
		if("ASCII".equals(fileEncode)){
			fileEncode = "UTF-8";
		}
		
		BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(srcFile), fileEncode));
		FileOutputStream fos = new FileOutputStream(disFile);
		String str;
		while((str = in.readLine()) != null){
			fos.write(str.getBytes(distCode));
			fos.write("\r\n".getBytes());
		}
		in.close();
		fos.close();
		
//		String shellStr = "iconv "+srcFile+" -f "+fileEncode+" -t "+distCode+" -o "+disFile;
//		LOG.info("执行脚本：" + shellStr);
//		Process	process = Runtime.getRuntime().exec(shellStr);
//		int i =process.waitFor();
//		process.destroy();
//		if(i != 0){
//			throw new Exception("文件：" + srcFile + "转码失败。。。");
//		}
	}
	/**
	 * gbk强制转换为gbk 去除乱码
	 * @param srcFile
	 * @param disFile
	 * @throws Exception
	 */
	public static void transCodeForce(String srcFile, String disFile) throws Exception{
		
		String shellStr = "iconv -f GBK -t GBK -c "+srcFile+" -o "+disFile;
		LOG.info("执行脚本：" + shellStr);
		Process	process = Runtime.getRuntime().exec(shellStr);
		int i =process.waitFor();
		process.destroy();
		if(i != 0){
			throw new Exception("文件：" + srcFile + "转码失败。。。");
		}
	}

	/**
	 * iconv 转码
	 * @param srcFile
	 * @param srcCode
	 * @param disFile
	 * @param disCode
	 * @throws Exception
	 */
	public static void transCodeIconv(String srcFile, String srcCode, String disFile, String disCode) throws Exception{
		String shellStr = "iconv -f "+srcCode+" -t "+disCode+" -c "+srcFile+" -o "+disFile;
		LOG.info("执行脚本：" + shellStr);
		Process	process = Runtime.getRuntime().exec(shellStr);
		int i =process.waitFor();
		process.destroy();
		if(i != 0){
			throw new Exception("文件：" + srcFile + "转码失败。。。");
		}
	}


	
}
