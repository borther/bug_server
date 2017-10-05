package com.sec.io;

import com.sec.util.ContentUtil;
import com.sec.vuln.Exp;

/**
 * 扫描类
 * @author Administrator
 *
 */
public class Scanner implements Runnable{
	private String target_ip;
	
	public Scanner(String target_ip) {
		this.target_ip = target_ip;
	}
	
	@Override
	public void run() {
		if ("all".equals(ContentUtil.bug_type) | "redis".equals(ContentUtil.bug_type)) {
			Exp.redisAccess(target_ip);
		}
		if ("all".equals(ContentUtil.bug_type) | "mongodb".equals(ContentUtil.bug_type)) {
			Exp.mongodbAccess(target_ip);
		}
	}
}
