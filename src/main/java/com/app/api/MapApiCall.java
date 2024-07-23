package com.app.api;

public class MapApiCall {

    private static String MAP_URL = "https://www.google.com/maps/dir/";
    public static String openGoogleMap(String origin, String destination) {
            return MAP_URL.concat(origin +"/"+destination);
    }


//    public static void main(String[] args) {
//        String API_KEY = loadProps().getProperty("map.api.key");
//        GeoApiContext apiContext = new GeoApiContext.Builder()
//                .apiKey(API_KEY)
//                .build();
//
//        try {
//            String[] startPoint = {"Ghana, Koforidua, Eastern Region"};
//            String[] destination = {"Ghana, Madina, Accra"};
//
//            DistanceMatrix result = DistanceMatrixApi.getDistanceMatrix(apiContext, startPoint, destination).await();
//
//            if (result.rows[0].elements[0].status == DistanceMatrixElementStatus.OK) {
//                System.out.println("Distance: " + result.rows[0].elements[0].distance.humanReadable);
//                System.out.println("Duration: " + result.rows[0].elements[0].duration.humanReadable);
//            } else {
//                System.out.println("Error: " + result.rows[0].elements[0].status);
//            }
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        apiContext.shutdown();
//    }
}
