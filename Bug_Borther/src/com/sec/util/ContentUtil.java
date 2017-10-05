package com.sec.util;

import java.io.File;
import java.util.Random;

/**
 * 工具类
 * @author Administrator
 *
 */
public class ContentUtil {
	public static String randNum = null; // 随机数值用于结果文件名
	public static File target_file = null; // 待扫描文件
	public static int threads_num = 25; // 线程数量
	public static String bug_type = "all"; // 选择扫描漏洞类型
	public static String[] args;
	static {
		randNum = String.valueOf(new Random().nextInt(10000));
	}
	
	/**
	 * 对传递参数判断赋值
	 * @return
	 */
	public static boolean param() {
		if (args.length > 0) {
			for (int i = 0; i < args.length; i++) {
				if (args[i].indexOf("--file=") > -1) {
					target_file = new File(args[i].substring(args[i].indexOf("=")+1, args[i].length()));
					System.out.println("target_file" + target_file.getAbsolutePath());
				} else if (args[i].indexOf("--type=") > -1) {
					bug_type = args[i].substring(args[i].indexOf("=")+1, args[i].length());
				}  else if (args[i].indexOf("--threads=") > -1) {
					threads_num = Integer.parseInt(args[i].substring(args[i].indexOf("=")+1, args[i].length()));
				}
			} 
			if(target_file != null) {
				return true;
			}
		} else {
			System.out.println("--file=目标文件");
			System.out.println("--threads=线程数量   默认线程数量25");
			System.out.println("--type=类型   可选参数:redis(只扫描redis) mongodb(只扫描mongdb) all(全部扫描) 默认为:all");
			System.out.println("案例:");
			System.out.println("\t java -jar bug_server.jar --file=target.txt");
			System.out.println("\t java -jar bug_server.jar --file=target.txt --threads=10");
			System.out.println("\t java -jar bug_server.jar --file=target.txt --threads=10 --type=redis");
		}
		return false;
	}
}
