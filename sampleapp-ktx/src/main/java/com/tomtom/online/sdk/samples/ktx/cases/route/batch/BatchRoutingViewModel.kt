/*
 * Copyright (c) 2015-2021 TomTom N.V. All rights reserved.
 *
 * This software is the proprietary copyright of TomTom N.V. and its subsidiaries and may be used
 * for internal evaluation purposes or commercial use strictly subject to separate licensee
 * agreement between you and TomTom. If you are the licensee, you are only permitted to use
 * this Software in accordance with the terms of your license agreement. If you are not the
 * licensee then you are not authorised to use this software in any manner and should
 * immediately return it to TomTom N.V.
 */

package com.tomtom.online.sdk.samples.ktx.cases.route.batch

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.tomtom.online.sdk.routing.RoutingException
import com.tomtom.online.sdk.routing.batch.BatchRoutesCallback
import com.tomtom.online.sdk.routing.batch.BatchRoutesPlan
import com.tomtom.online.sdk.routing.batch.BatchRoutesSpecification
import com.tomtom.online.sdk.samples.ktx.cases.route.RouteViewModel
import com.tomtom.online.sdk.samples.ktx.cases.route.RoutingRequester
import com.tomtom.online.sdk.samples.ktx.utils.arch.Resource
import com.tomtom.online.sdk.samples.ktx.utils.arch.ResourceLiveData
import com.tomtom.sdk.examples.R

class BatchRoutingViewModel(application: Application) : RouteViewModel(application) {

    val result = ResourceLiveData<BatchRoutesPlan>()
    var routesDescription = MutableLiveData(intArrayOf())

    private val routingRequester = RoutingRequester(application)

    fun startRoutingDependsOnTravelMode() {
        routesDescription.value = intArrayOf(R.string.batch_travel_mode_car_text, R.string.batch_travel_mode_truck_text, R.string.batch_travel_mode_pedestrian_text)

        val batchRoutesSpecification = BatchRoutingSpecificationFactory()
                .prepareTravelModeBatchRouteSpecification()

        planBatchRoutes(batchRoutesSpecification)
    }

    fun startRoutingDependsOnRouteType() {
        routesDescription.value = intArrayOf(R.string.batch_route_type_fastest, R.string.batch_route_type_shortest, R.string.batch_route_type_eco)
        val batchRoutesSpecification = BatchRoutingSpecificationFactory()
                .prepareRouteTypeBatchRouteSpecification()

        planBatchRoutes(batchRoutesSpecification)
    }

    fun startRoutingDependsOnAvoids() {
        routesDescription.value = intArrayOf(R.string.batch_avoid_motorways, R.string.batch_avoid_toll_roads, R.string.batch_avoid_ferries)

        val batchRoutesSpecification = BatchRoutingSpecificationFactory()
                .prepareAvoidsBatchRouteSpecification()

        planBatchRoutes(batchRoutesSpecification)
    }

    private fun planBatchRoutes(batchRoutesSpecification: BatchRoutesSpecification) {
        selectedRoute.value = null
        result.value = Resource.loading(null)
        routingRequester.planBatchRoutes(batchRoutesSpecification, batchRoutesCallback)
    }

    private val batchRoutesCallback = object : BatchRoutesCallback {
        override fun onSuccess(routePlan: BatchRoutesPlan) {
            result.value = Resource.success(routePlan)
        }

        override fun onError(error: RoutingException) {
            result.value = Resource.error(null, Error(error.message))
        }
    }
}
