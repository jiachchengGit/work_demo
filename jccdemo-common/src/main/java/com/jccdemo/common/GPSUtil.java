package com.jccdemo.common;

/**
 * Created by chenjiacheng on 2016/11/28.
 */
public class GPSUtil {

    private static final  double EARTH_RADIUS = 6378137;//赤道半径(单位m)

    /**
     * 转化为弧度(rad)
     * */
    private static double rad(double d)
    {
        return d * Math.PI / 180.0;
    }

    /**
     * 基于余弦定理求两经纬度距离
     * @param lon1 第一点的精度
     * @param lat1 第一点的纬度
     * @param lon2 第二点的精度
     * @param lat2 第二点的纬度
     * @return 返回的距离，单位m
     * */
    public static double lantitudeLongitudeDist(double lon1, double lat1,double lon2, double lat2) {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);

        double radLon1 = rad(lon1);
        double radLon2 = rad(lon2);

        if (radLat1 < 0)
            radLat1 = Math.PI / 2 + Math.abs(radLat1);// south
        if (radLat1 > 0)
            radLat1 = Math.PI / 2 - Math.abs(radLat1);// north
        if (radLon1 < 0)
            radLon1 = Math.PI * 2 - Math.abs(radLon1);// west
        if (radLat2 < 0)
            radLat2 = Math.PI / 2 + Math.abs(radLat2);// south
        if (radLat2 > 0)
            radLat2 = Math.PI / 2 - Math.abs(radLat2);// north
        if (radLon2 < 0)
            radLon2 = Math.PI * 2 - Math.abs(radLon2);// west
        double x1 = EARTH_RADIUS * Math.cos(radLon1) * Math.sin(radLat1);
        double y1 = EARTH_RADIUS * Math.sin(radLon1) * Math.sin(radLat1);
        double z1 = EARTH_RADIUS * Math.cos(radLat1);

        double x2 = EARTH_RADIUS * Math.cos(radLon2) * Math.sin(radLat2);
        double y2 = EARTH_RADIUS * Math.sin(radLon2) * Math.sin(radLat2);
        double z2 = EARTH_RADIUS * Math.cos(radLat2);

        double d = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2)+ (z1 - z2) * (z1 - z2));
        //余弦定理求夹角
        double theta = Math.acos((EARTH_RADIUS * EARTH_RADIUS + EARTH_RADIUS * EARTH_RADIUS - d * d) / (2 * EARTH_RADIUS * EARTH_RADIUS));
        double dist = theta * EARTH_RADIUS;
        return dist;
    }

    /**
     * 该方法性能优于lantitudeLongitudeDist
     * @param lon1 经度
     * @param lat1 维度
     * @param lon2 经度
     * @param lat2 维度
     * @return 单位m
     */
    public static double getDistance(double lon1,double lat1,double lon2, double lat2)
    {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lon1) - rad(lon2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a/2),2)+Math.cos(radLat1)*Math.cos(radLat2)*Math.pow(Math.sin(b/2),2)));
        s = s * EARTH_RADIUS;
        //s = Math.round(s * 10000) / 10000;
        return s;
    }
    public static void main(String[] args){
//        long start = System.currentTimeMillis();
//        System.out.println("--->start1="+start);
//        int size = 100000;
//        for(int i=0;i<size;i++){
//            lantitudeLongitudeDist(104.075312,30.543067,104.074881,30.552895);
//        }
//        long aa = System.currentTimeMillis();
//        System.out.println("-----start2="+(aa -start));
//        for(int i=0;i<size;i++){
//            getDistance(104.075312,30.543067,104.074881,30.552895);
//        }
//        System.out.println("-----start2="+(System.currentTimeMillis()-aa));

        System.out.println("---->"+lantitudeLongitudeDist(104.075312,30.543067,104.074881,30.552895));
        System.out.println("---->"+getDistance(104.075312,30.543067,104.074881,30.552895));
        System.out.println("---->"+getDistance(104.075312,30.54,104.075312,30.51)); // 0.03
        System.out.println("---->"+getDistance(104.07,30.54,104.105,30.54)); //0.035

    }
}
