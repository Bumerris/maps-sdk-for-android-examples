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
package com.tomtom.online.sdk.samples.cases.route.supportingpoints;

import androidx.annotation.VisibleForTesting;

import com.google.common.collect.ImmutableList;
import com.tomtom.online.sdk.common.location.LatLng;
import com.tomtom.online.sdk.map.RouteSettings;
import com.tomtom.online.sdk.map.TomtomMap;
import com.tomtom.online.sdk.map.TomtomMapCallback;
import com.tomtom.online.sdk.routing.route.RouteCalculationDescriptor;
import com.tomtom.online.sdk.routing.route.RouteDescriptor;
import com.tomtom.online.sdk.routing.route.RouteSpecification;
import com.tomtom.online.sdk.routing.route.information.FullRoute;
import com.tomtom.online.sdk.samples.activities.FunctionalExampleModel;
import com.tomtom.online.sdk.samples.cases.RoutePlannerPresenter;
import com.tomtom.online.sdk.samples.cases.RoutingUiListener;
import com.tomtom.online.sdk.samples.fragments.FunctionalExampleFragment;
import com.tomtom.online.sdk.samples.utils.RouteUtils;

import java.util.List;

public class RouteSupportingPointsPresenter extends RoutePlannerPresenter {

    private final LatLng EXAMPLE_DESTINATION = new LatLng(40.209408, -8.423741);
    private final LatLng EXAMPLE_ORIGIN = new LatLng(40.10995732392718, -8.501433134078981);

    private final List<LatLng> SUPPORTING_POINTS = ImmutableList.of(
            new LatLng(40.10995732392718, -8.501433134078981),
            new LatLng(40.11115121590874, -8.500000834465029),
            new LatLng(40.11089684892725, -8.497683405876161),
            new LatLng(40.11192251642396, -8.498423695564272),
            new LatLng(40.209408, -8.423741)
    );

    public RouteSupportingPointsPresenter(RoutingUiListener viewModel) {
        super(viewModel);
    }

    @Override
    public void bind(FunctionalExampleFragment view, TomtomMap map) {
        super.bind(view, map);
        tomtomMap.addOnRouteClickListener(onRouteClickListener);
    }

    @Override
    public void cleanup() {
        super.cleanup();
        tomtomMap.removeOnRouteClickListener(onRouteClickListener);
    }

    @Override
    public void centerOnDefaultLocation() {
        centerOn(EXAMPLE_ORIGIN);
    }

    @Override
    public FunctionalExampleModel getModel() {
        return new RouteSupportingPointsFunctionalExample();
    }

    public void startRouting(int minDeviationDistance) {
        tomtomMap.clearRoute();
        viewModel.showRoutingInProgressDialog();
        showRoute(getRouteSpecification(minDeviationDistance));
    }

    @VisibleForTesting
    protected RouteSpecification getRouteSpecification(int minDeviationDistance) {
        //tag::doc_route_supporting_points[]
        RouteDescriptor routeDescriptor = new RouteDescriptor.Builder()
                .considerTraffic(false)
                .build();

        RouteCalculationDescriptor routeCalculationDescriptor = new RouteCalculationDescriptor.Builder()
                .routeDescription(routeDescriptor)
                .maxAlternatives(1)
                .minDeviationTime(0)
                .supportingPoints(SUPPORTING_POINTS)
                .minDeviationDistance(minDeviationDistance)
                .build();

        RouteSpecification routeSpecification = new RouteSpecification.Builder(EXAMPLE_ORIGIN, EXAMPLE_DESTINATION)
                .routeCalculationDescriptor(routeCalculationDescriptor)
                .build();
        //end::doc_route_supporting_points[]
        return routeSpecification;
    }

    private TomtomMapCallback.OnRouteClickListener onRouteClickListener = route -> {
        long routeId = route.getId();
        RouteSettings routeSettings = tomtomMap.getRouteSettings();

        RouteUtils.setRoutesInactive(routeSettings);
        RouteUtils.setRouteActive(routeId, routeSettings);

        FullRoute fullRoute = routesMap.get(routeId);
        displayInfoAboutRoute(fullRoute);
    };

}
