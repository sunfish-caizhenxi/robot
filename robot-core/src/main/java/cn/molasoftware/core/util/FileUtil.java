package cn.molasoftware.core.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {
	
	public static void mk(String path) {
		File p = new File(path);
		if (!p.exists()) {
			p.mkdirs();
		}
	}
	
	/**
	 * 获取目录下的所有文件（包含子目录的文件）
	 * 
	 * @param path
	 * @return
	 */

	public static List<File> getFilesOfDir(String path, boolean isSub) {
		if (isSub)
			return getFilesOfDir(new File(path));
		else
			return getNotSubFilesOfDir(new File(path));
	}

	/**
	 * 获取目录下的所有文件（包含子目录的文件）
	 * 
	 * @param directorie
	 * @return
	 */

	public static List<File> getFilesOfDir(File directorie) {
		List<File> fileList = new ArrayList<File>();
		File[] files = directorie.listFiles();
		for (int i = 0; i < files.length; i++) {
			File file = files[i];
			// 判断是文件还是目录
			if (file.isDirectory()) {
				fileList.addAll(getFilesOfDir(file));
			} else {
				fileList.add(file);
			}
		}
		return fileList;
	}

	/**
	 * 获取目录下的所有文件（当前目录的文件）
	 * 
	 * @param directorie
	 * @return
	 */

	public static List<File> getNotSubFilesOfDir(File directorie) {
		List<File> fileList = new ArrayList<File>();
		File[] files = directorie.listFiles();
		for (int i = 0; i < files.length; i++) {
			File file = files[i];
			// 判断是文件还是目录
			if (!file.isDirectory()) {
				fileList.add(file);
			}
		}
		return fileList;
	}

	public static void write(File file, String content) {
		if (file == null || StrUtil.isBlank(content)) {
			return;
		}
		FileOutputStream fout;
		OutputStreamWriter writer;
		try {
			fout = new FileOutputStream(file);
			writer = new OutputStreamWriter(fout, "utf8");
			writer.write(content);
			writer.flush();
			writer.close();
			fout.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String read(File file) {
		String content = "";
		if (!(file.exists())) {
			return content;
		}
		try {
			BufferedReader in = new BufferedReader(new FileReader(file));
			String str;
			while ((str = in.readLine()) != null) {
				content = content + str;
			}
			in.close();
		} catch (IOException e) {
			e.getStackTrace();
		}
		return content.trim();
	}
	
	public static String read(InputStream inputStream){
		String content = "";
		if (inputStream==null) {
			return content;
		}
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
			String str;
			while ((str = in.readLine()) != null) {
				content = content + str;
			}
			in.close();
		} catch (IOException e) {
			e.getStackTrace();
		}
		return content.trim();
	}
}
