package com.rankbook.hrms.automation.mobile.utils;

public final class GeoCoordinatesUtil {
    private static final double EARTH_RADIUS_METERS = 6371000.0;

    private GeoCoordinatesUtil() {}

    public static double calculateDistanceMeters(double lat1, double lon1, double lat2, double lon2) {
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(dLon / 2) * Math.sin(dLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return EARTH_RADIUS_METERS * c;
    }

    public static boolean isWithinRadius(double originLat, double originLon, double targetLat, double targetLon, double radiusMeters) {
        return calculateDistanceMeters(originLat, originLon, targetLat, targetLon) <= radiusMeters;
    }
}