package newLight.map;

import java.util.Arrays;

public class MapUtil {

	/**
	 * 根据点的坐标 距离和角度,计算另外一点的坐标
	 * 
	 * @param point 点坐标，数组类型 Double[0] = longitude, Double[1]=latitude
	 * @param distance
	 * @param angle
	 * @return
	 */
	public static Double[] getPointByDistanceAndAngle(Double[] point, Integer distance, Double angle) {
		int ea = 6378137;	//赤道半径
    	int eb = 6356725;	//极半径
    	
    	double dx = distance  * Math.sin(angle * Math.PI / 180);
        double dy = distance  * Math.cos(angle * Math.PI / 180);
        //ec的作用就是修正因为纬度不断变化的球半径长度
        double ec = eb + (ea - eb) * (90 - point[1]) / 90;
        //Ed是GLAT所在纬度的纬度圈的半径
        double ed = ec * Math.cos(point[1] * Math.PI / 180);
        double lng = (dx / ed + point[0] * Math.PI / 180) * 180 / Math.PI;
        double lat = (dy / ec + point[1] * Math.PI / 180) * 180 / Math.PI;
		return new Double[]{lng, lat};
	}
	
	public static void main(String[] args) {
		Double[] point = {116.3842398351800000,30.7645882916990000}; 
		Double[] point2 = getPointByDistanceAndAngle(point, 400, 90.0);
		System.out.println(Arrays.toString(point2));
	}
}
