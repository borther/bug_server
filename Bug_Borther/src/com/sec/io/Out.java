package com.sec.io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import com.sec.util.ContentUtil;

/**
 * 结果保存
 * @author Administrator
 *
 */
public class Out {
	private File file;
	private FileWriter fw;
	private PrintWriter pw;
	private static Out instance;

	private Out() {}
	// 单例
	public static synchronized Out getInstance() {
		if (instance == null) {
			instance = new Out();
		}
		return instance;
	}

	// 保存扫描结果
	public void savaResult(String text) {//System.getProperty("user.dir") + 
		file = new File("Vuln_Result" + ContentUtil.randNum +".txt");
		try {
			fw = new FileWriter(file, true);
			pw = new PrintWriter(fw);
			pw.println(text);
			pw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			pw.close();
			try {
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
