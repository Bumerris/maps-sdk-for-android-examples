/**
 * Copyright (c) 2015-2021 TomTom N.V. All rights reserved.
 *
 * This software is the proprietary copyright of TomTom N.V. and its subsidiaries and may be used
 * for internal evaluation purposes or commercial use strictly subject to separate licensee
 * agreement between you and TomTom. If you are the licensee, you are only permitted to use
 * this Software in accordance with the terms of your license agreement. If you are not the
 * licensee then you are not authorised to use this software in any manner and should
 * immediately return it to TomTom N.V.
 */
package com.tomtom.online.sdk.samples.cases.route.waypoints;

import androidx.annotation.VisibleForTesting;

import com.tomtom.online.sdk.common.location.LatLng;
import com.tomtom.online.sdk.map.CameraPosition;
import com.tomtom.online.sdk.map.MapConstants;
import com.tomtom.online.sdk.map.MarkerBuilder;
import com.tomtom.online.sdk.routing.route.RouteCalculationDescriptor;
import com.tomtom.online.sdk.routing.route.RouteDescriptor;
import com.tomtom.online.sdk.routing.route.RoutePlan;
import com.tomtom.online.sdk.routing.route.RouteSpecification;
import com.tomtom.online.sdk.samples.activities.FunctionalExampleModel;
import com.tomtom.online.sdk.samples.cases.RoutePlannerPresenter;
import com.tomtom.online.sdk.samples.cases.RoutingUiListener;
import com.tomtom.online.sdk.samples.routes.AmsterdamToBerlinRouteConfig;
import com.tomtom.online.sdk.samples.utils.Locations;

import java.util.ArrayList;
import java.util.List;

/**
 * An extended version of the {@link RoutePlannerPresenter} which allows to plan a route with Waypoints.
 */
public class RouteWaypointsPresenter extends RoutePlannerPresenter {

    private static final int DEFAULT_ZOOM_LEVEL = 4;

    private static final LatLng BRUSSELS = new LatLng(50.854751, 4.305694);
    private static final LatLng HAMBURG = new LatLng(53.560096, 9.788492);
    private static final LatLng ZURICH = new LatLng(47.385150, 8.476178);
    private List<LatLng> wayPoints;


    public RouteWaypointsPresenter(RoutingUiListener viewModel) {
        super(viewModel);
        wayPoints = new ArrayList<>();
    }

    @Override
    public void cleanup() {
        clearMap();
        super.cleanup();
    }

    @Override
    public FunctionalExampleModel getModel() {
        return new RouteWaypointsFunctionalExample();
    }

    private void addDefaultWaypoints() {
        addWaypoint(HAMBURG);
        addWaypoint(ZURICH);
        addWaypoint(BRUSSELS);
    }

    public void initialOrder() {
        clearMap();
        addDefaultWaypoints();
        displayRoute(getRouteSpecification());
    }

    public void noWaypoints() {
        clearMap();
        displayRoute(getRouteSpecification());
    }

    private void clearMap() {
        wayPoints.clear();
        tomtomMap.clear();
    }

    @Override
    public void centerOnDefaultLocation() {
        LatLng location = Locations.AMSTERDAM_BERLIN_CENTER_LOCATION;
        tomtomMap.centerOn(CameraPosition.builder()
                .focusPosition(location)
                .bearing(MapConstants.ORIENTATION_NORTH)
                .zoom(DEFAULT_ZOOM_LEVEL)
                .build());
    }

    protected void displayRoute(RouteSpecification routeSpecification) {
        viewModel.showRoutingInProgressDialog();
        tomtomMap.clearRoute();
        showRoute(routeSpecification);
    }

    @Override
    protected void displayRoutes(RoutePlan routePlan) {
        for (LatLng waypoint : wayPoints) {
            tomtomMap.addMarker(new MarkerBuilder(waypoint));
        }
        super.displayRoutes(routePlan);
    }

    @VisibleForTesting
    protected RouteSpecification getRouteSpecification() {
        AmsterdamToBerlinRouteConfig routeConfig = new AmsterdamToBerlinRouteConfig();
        //tag::doc_route_waypoints[]
        RouteDescriptor routeDescriptor = new RouteDescriptor.Builder()
                .considerTraffic(false)
                .build();

        RouteCalculationDescriptor routeCalculationDescriptor = new RouteCalculationDescriptor.Builder()
                .routeDescription(routeDescriptor)
                .waypoints(wayPoints)
                .build();

        RouteSpecification routeSpecification = new RouteSpecification.Builder(routeConfig.getOrigin(), routeConfig.getDestination())
                .routeCalculationDescriptor(routeCalculationDescriptor)
                .build();
        //end::doc_route_waypoints[]
        return routeSpecification;
    }

    private void addWaypoint(LatLng latLng) {
        wayPoints.add(latLng);
    }

    void setWayPoints(List<LatLng> points) {
        wayPoints = points;
    }

    List<LatLng> getWayPoints() {
        return wayPoints;
    }

}
