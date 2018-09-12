/**   
 * Copyright © 2017 eSunny Info. Tech Ltd. All rights reserved.
 * 
 * @Package: com.itcast.aaa 
 * @author: zyf  
 * @date: 2017年12月26日 下午2:17:46 
 */
package com.izhuixin.rsps.common;

 /** 
 * @ClassName: FinalPositionUtils 
 * @Description: 将WGS-84经纬度转换成BD-09经纬度，并计算两个坐标点之间的距离
 * @author: zyf
 * @date: 2017年12月26日 下午2:17:46  
 */
public class FinalPositionUtils {
	private static final double EARTH_RADIUS = 6371.004;
	static double pi = 3.1415926535897932384626;
	static double a = 6378245.0;
	static double ee = 0.00669342162296594323;
	static double x_pi = 3.14159265358979324 * 3000.0 / 180.0;

	/**
	 * 将一个点坐标转换为百度坐标
	 * 
	 * @param
	 */
	public static double[] transBMapPosition(double wgLat, double wgLon) {
		if (wgLat == 0 || wgLon == 0)
			return null;
		double[] MarsGPS = transform(wgLat, wgLon);
		double x = MarsGPS[1], y = MarsGPS[0];
		double z = Math.sqrt(x * x + y * y) + 0.00002 * Math.sin(y * x_pi);
		double theta = Math.atan2(y, x) + 0.000003 * Math.cos(x * x_pi);
		MarsGPS[0] = z * Math.sin(theta) + 0.0060068;
		MarsGPS[1] = z * Math.cos(theta) + 0.0065054;
		return MarsGPS;
	}
     
	/**
	 * World Geodetic System ==> Mars Geodetic System,将大地坐标系转换成GCJ-02火星坐标系
	 * @param wgLat
	 * @param wgLon
	 * @return
	 */
	private static double[] transform(double wgLat, double wgLon) {
		double[] ret = new double[2];
		ret[0] = wgLat;
		ret[1] = wgLon;
		double mgLat, mgLon;
		if (outOfChina(wgLat, wgLon)) {
			mgLat = wgLat;
			mgLon = wgLon;
			ret[0] = mgLat;
			ret[1] = mgLon;
			return ret;
		}
		double dLat = transformLat(wgLon - 105.0, wgLat - 35.0);
		double dLon = transformLon(wgLon - 105.0, wgLat - 35.0);
		double radLat = wgLat / 180.0 * pi;
		double magic = Math.sin(radLat);
		magic = 1 - ee * magic * magic;
		double sqrtMagic = Math.sqrt(magic);
		dLat = (dLat * 180.0) / ((a * (1 - ee)) / (magic * sqrtMagic) * pi);
		dLon = (dLon * 180.0) / (a / sqrtMagic * Math.cos(radLat) * pi);
		mgLat = wgLat + dLat;
		mgLon = wgLon + dLon;

		ret[0] = mgLat;
		ret[1] = mgLon;
		return ret;
	}
	/**
	 * 判断是否在中国范围内
	 * @param lat
	 * @param lon
	 * @return
	 */
	private static boolean outOfChina(double lat, double lon) {
		if (lon < 72.004 || lon > 137.8347)
			return true;
		if (lat < 0.8293 || lat > 55.8271)
			return true;
		return false;
	}
     
	/**
	 * 转换纬度latitude
	 * @param x
	 * @param y
	 * @return
	 */
	private static double transformLat(double x, double y) {
		double ret = -100.0 + 2.0 * x + 3.0 * y + 0.2 * y * y + 0.1 * x * y
				+ 0.2 * Math.sqrt(Math.abs(x));
		ret += (20.0 * Math.sin(6.0 * x * pi) + 20.0 * Math.sin(2.0 * x * pi)) * 2.0 / 3.0;
		ret += (20.0 * Math.sin(y * pi) + 40.0 * Math.sin(y / 3.0 * pi)) * 2.0 / 3.0;
		ret += (160.0 * Math.sin(y / 12.0 * pi) + 320 * Math.sin(y * pi / 30.0)) * 2.0 / 3.0;
		return ret;
	}
    /**
     * 转换经度longitude
     * @param x
     * @param y
     * @return
     */
	private static double transformLon(double x, double y) {
		double ret = 300.0 + x + 2.0 * y + 0.1 * x * x + 0.1 * x * y + 0.1
				* Math.sqrt(Math.abs(x));
		ret += (20.0 * Math.sin(6.0 * x * pi) + 20.0 * Math.sin(2.0 * x * pi)) * 2.0 / 3.0;
		ret += (20.0 * Math.sin(x * pi) + 40.0 * Math.sin(x / 3.0 * pi)) * 2.0 / 3.0;
		ret += (150.0 * Math.sin(x / 12.0 * pi) + 300.0 * Math.sin(x / 30.0
				* pi)) * 2.0 / 3.0;
		return ret;
	}

	private static double rad(double d) {
		return d * Math.PI / 180.0;
	}

    /**
     * 获取两点之间的距离
     * @param lat1
     * @param lng1
     * @param lat2
     * @param lng2
     * @return
     */
	public static double GetDistance(double lat1, double lng1, double lat2,
			double lng2) {
		double radLat1 = rad(lat1);
		double radLat2 = rad(lat2);
		double a = radLat1 - radLat2;
		double b = rad(lng1) - rad(lng2);
		double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
				+ Math.cos(radLat1) * Math.cos(radLat2)
				* Math.pow(Math.sin(b / 2), 2)));
		s = s * EARTH_RADIUS * 1000;
		s = Math.round(s * 10000) / 10000;
//		 System.out.println("两点间的距离是：" + s + "米" );
		return s;
	}

	/**
	 * 测试类
	 * @param args
	 */
	public static void main(String[] args) {
		
//		System.out.println("两点之间的距离是："
//				+ GetDistance(23.5539530, 114.8903920, 23.5554550, 114.8868890)
//				+ "米");
		double[] rs = transBMapPosition(30.25961, 120.13026);
		System.out.println("百度坐标:" + rs[1] + "," + rs[0]);
	}
}
