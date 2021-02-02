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
package com.tomtom.online.sdk.samples.cases.route.departureandarrivaltime;

import androidx.annotation.VisibleForTesting;

import com.tomtom.online.sdk.routing.route.RouteSpecification;
import com.tomtom.online.sdk.samples.activities.FunctionalExampleModel;
import com.tomtom.online.sdk.samples.cases.RoutePlannerPresenter;
import com.tomtom.online.sdk.samples.cases.RoutingUiListener;
import com.tomtom.online.sdk.samples.cases.route.RouteSpecificationFactory;
import com.tomtom.online.sdk.samples.routes.AmsterdamToRotterdamRouteConfig;

import org.joda.time.DateTime;

import java.util.Date;

public class DepartureAndArrivalTimePresenter extends RoutePlannerPresenter {

    public DepartureAndArrivalTimePresenter(RoutingUiListener viewModel) {
        super(viewModel);
    }

    @Override
    public FunctionalExampleModel getModel() {
        return new DepartureAndArrivalTimeFunctionalExample();
    }

    public void clearRoute() {
        tomtomMap.clearRoute();
    }

    public void displayArrivalAtRoute(DateTime arrivalDateTime) {
        viewModel.showRoutingInProgressDialog();
        showRoute(getArrivalRouteSpecification(arrivalDateTime.toDate()));
    }

    @VisibleForTesting
    protected RouteSpecification getArrivalRouteSpecification(Date arrivalTime) {
        return RouteSpecificationFactory.createArrivalRouteSpecification(arrivalTime, new AmsterdamToRotterdamRouteConfig());
    }

    public void displayDepartureAtRoute(DateTime departureDate) {
        viewModel.showRoutingInProgressDialog();
        showRoute(getDepartureRouteSpecification(departureDate.toDate()));
    }

    protected RouteSpecification getDepartureRouteSpecification(Date departureTime) {
        return RouteSpecificationFactory.createDepartureRouteSpecification(departureTime, new AmsterdamToRotterdamRouteConfig());
    }

}
