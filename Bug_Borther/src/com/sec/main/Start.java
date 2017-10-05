package com.sec.main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.sec.io.Scanner;
import com.sec.util.ContentUtil;

public class Start {
	public Start() {
		if (ContentUtil.param()) {
			this.startScan();
		} else {
			if (ContentUtil.args.length > 0) {
				System.out.println("请传入正确参数!");
			}
		}
	}
	
	// 开始扫描
	public void startScan() {
		try {
			ExecutorService es = Executors.newFixedThreadPool(ContentUtil.threads_num);
			BufferedReader reader = new BufferedReader(new FileReader(ContentUtil.target_file));
			String line = null;
			while ((line = reader.readLine()) != null) {
				es.submit(new Scanner(line));
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("扫描文件不存在！");
		} catch (IOException e) {
			System.out.println("读取文件失败！");
		}
	}
	
}
