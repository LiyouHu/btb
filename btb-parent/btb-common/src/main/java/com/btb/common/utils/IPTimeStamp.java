package com.btb.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class IPTimeStamp {
	SimpleDateFormat sdf ;
	private String ip ;
	public IPTimeStamp(){		//如果不适用IP地址的话，使用此构造方法
	}
	public IPTimeStamp(String ip){
		this.ip = ip ;
	}
	public String getIPTimeStampRand(){
		StringBuffer buf = new StringBuffer();
		if(ip != null){
			if(ip.contains(".")) {	//Ipv4地址
				String s[] = this.ip.split("\\.") ;
				for(int i=0; i<s.length;i++){
					buf.append(this.addZero(s[i],3)) ;
				}			
			}else if(ip.contains(":")){	//ipv6地址
				String s[] = this.ip.split(":") ;
				for(int i=0; i<s.length;i++){
					buf.append(this.addZero(s[i],4)) ;
				}	
			}
		}
		this.sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS") ;
		buf.append(this.sdf.format(new Date()));
		Random rand = new Random();
		for(int i=0;i<3;i++){
			buf.append(rand.nextInt(10)) ;
		}
		return buf.toString() ;
	}
	private String addZero(String str,int len){
		StringBuffer buf = new StringBuffer() ;
		buf.append(str);
		while(buf.length()<len){
			buf.insert(0, 0);
		}
		return buf.toString() ;
	}
	public static void main(String args[]){
		System.out.println(new IPTimeStamp("192.168.0.1").getIPTimeStampRand());
		System.out.println(new IPTimeStamp().getIPTimeStampRand());
	}
}
