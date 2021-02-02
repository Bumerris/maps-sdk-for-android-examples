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
package com.tomtom.online.sdk.samples.cases.route.matrix;

import android.content.Context;

import androidx.annotation.NonNull;

import com.tomtom.online.sdk.common.rx.RxContext;
import com.tomtom.online.sdk.routing.OnlineRoutingApi;
import com.tomtom.online.sdk.routing.RoutingApi;
import com.tomtom.online.sdk.routing.matrix.MatrixRoutesCallback;
import com.tomtom.online.sdk.routing.matrix.MatrixRoutesSpecification;
import com.tomtom.online.sdk.samples.BuildConfig;

import java.util.concurrent.Executors;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MatrixRouteRequester implements RxContext {

    private final static int NUMBER_OF_NETWORKING_THREADS = 4;

    private RoutingApi routePlannerApi;

    MatrixRouteRequester(Context context) {
        routePlannerApi = OnlineRoutingApi.create(context, BuildConfig.ROUTING_API_KEY);
    }

    void performMatrixRouting(MatrixRoutesSpecification specification, MatrixRoutesCallback matrixRoutesCallback) {
        //tag::doc_execute_matrix_routing[]
        routePlannerApi.planRoutes(specification, matrixRoutesCallback);
        //end::doc_execute_matrix_routing[]
    }

    @NonNull
    @Override
    public Scheduler getWorkingScheduler() {
        return Schedulers.from(Executors.newFixedThreadPool(NUMBER_OF_NETWORKING_THREADS));
    }

    @NonNull
    @Override
    public Scheduler getResultScheduler() {
        return AndroidSchedulers.mainThread();
    }
}
