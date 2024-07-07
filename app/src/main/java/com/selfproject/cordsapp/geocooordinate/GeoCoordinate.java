package com.selfproject.cordsapp.geocooordinate;

public class GeoCoordinate {
    private static GeoCoordinate sharedConverter;

    public GeoCoordinate( )
    {

    }

    public static GeoCoordinate getInstance( ) {

        if ( GeoCoordinate.sharedConverter == null ) {

            GeoCoordinate.sharedConverter = new GeoCoordinate( );
        }

        return GeoCoordinate.sharedConverter;
    }

//    public double[] utm2LatLon(String UTM)
//    {
//        UTM2LatLon c = new UTM2LatLon();
//        return c.convertUTMToLatLong(UTM);
//    }

    public String latLon2UTM(double latitude, double longitude)
    {
        LatLonToUtm c = new LatLonToUtm();
        return c.convertLatLonToUTM(latitude, longitude);

    }

//    public String latLon2MGRS(double latitude, double longitude)
//    {
//        LatLon2MGRS c = new LatLon2MGRS();
//        return c.convertLatLonToMGRS(latitude, longitude);
//    }
//
//    public double[] mgrs2LatLon(String MGRS)
//    {
//        MGRS2LatLon c = new MGRS2LatLon();
//        return c.convertMGRSToLatLong(MGRS);
//    }

    public double degreeToRadian(double degree)
    {
        return degree * Math.PI / 180;
    }

    public double radianToDegree(double radian)
    {
        return radian * 180 / Math.PI;
    }
}
