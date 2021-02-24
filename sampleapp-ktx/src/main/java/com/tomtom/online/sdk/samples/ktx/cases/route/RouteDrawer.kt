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

package com.tomtom.online.sdk.samples.ktx.cases.route

import android.content.Context
import android.graphics.Color
import com.tomtom.online.sdk.map.*
import com.tomtom.online.sdk.map.model.LineCapType
import com.tomtom.online.sdk.map.route.RouteLayerStyle
import com.tomtom.online.sdk.map.route.traffic.TrafficData
import com.tomtom.online.sdk.routing.ev.route.EvFullRoute
import com.tomtom.online.sdk.routing.ev.route.Leg
import com.tomtom.online.sdk.routing.route.information.FullRoute
import com.tomtom.sdk.examples.R

open class RouteDrawer(private val context: Context, private val tomtomMap: TomtomMap) {

    fun drawDefault(routes: List<FullRoute>) {
        //Add all routes
        var routeIdx = 0
        routes.forEach { route ->
            //tag::doc_display_route[]
            val routeBuilder = RouteBuilder(route.getCoordinates())
                .style(RouteStyle.DEFAULT_ROUTE_STYLE)
                .startIcon(createIcon(R.drawable.ic_map_route_departure))
                .endIcon(createIcon(R.drawable.ic_map_route_destination))
                .tag(routeIdx.toString())
            tomtomMap.addRoute(routeBuilder)
            //end::doc_display_route[]
            routeIdx++
        }
    }

    fun drawCustom(
        routes: List<FullRoute>,
        style: RouteStyle = createCustomStyle(),
        startIconResId: Int = R.drawable.ic_map_route_departure,
        endIconResId: Int = R.drawable.ic_map_fav
    ) {

        routes.forEach { route ->
            val routeBuilder = RouteBuilder(route.getCoordinates())
                .style(style)
                .startIcon(createIcon(startIconResId))
                .endIcon(createIcon(endIconResId))
            tomtomMap.addRoute(routeBuilder)
        }
    }

    fun drawDotted(
        routes: List<FullRoute>,
        style: RouteStyle = createDottedStyle(),
        startIconResId: Int = R.drawable.ic_map_route_departure,
        endIconResId: Int = R.drawable.ic_map_route_destination
    ) {

        routes.forEach { route ->
            val routeBuilder = RouteBuilder(route.getCoordinates())
                .style(style)
                .startIcon(createIcon(startIconResId))
                .endIcon(createIcon(endIconResId))
            tomtomMap.addRoute(routeBuilder)
        }
    }

    fun drawEv(routes: List<EvFullRoute>) {
        var routeIdx = 0
        val routeLegs = routes.flatMap { it.legs }
        routeLegs.forEachIndexed { index, leg ->
            val routeBuilder = RouteBuilder(leg.points)
                .style(RouteStyle.DEFAULT_ROUTE_STYLE)
                .tag(routeIdx.toString())
            setupEvRouteIcons(index, routeBuilder, routeLegs)
            tomtomMap.addRoute(routeBuilder)
            routeIdx++
        }
    }

    private fun setupEvRouteIcons(index: Int, routeBuilder: RouteBuilder, routeLegs: List<Leg>) {
        when (index) {
            0 -> routeBuilder.startIcon(createIcon(R.drawable.ic_map_route_departure))
            routeLegs.size - 1 -> routeBuilder.endIcon(createIcon(R.drawable.ic_map_route_destination))
        }
    }

    fun drawRouteWithTraffic(routes: List<FullRoute>) {
        routes.forEachIndexed { routeIdx, route ->
            val routeBuilder = RouteBuilder(route.getCoordinates())
                .style(RouteStyle.DEFAULT_ROUTE_STYLE)
                .startIcon(createIcon(R.drawable.ic_map_route_departure))
                .endIcon(createIcon(R.drawable.ic_map_route_destination))
                .tag(routeIdx.toString())
            tomtomMap.addRoute(routeBuilder)
            showTrafficOnRoute(route)
        }
    }

    private fun showTrafficOnRoute(route: FullRoute) {
        //tag::doc_route_traffic_style_data_mapping[]
        val trafficStyle = mutableMapOf<RouteLayerStyle, List<TrafficData>>()
        route.sections.forEach {
            //DELAY_MAGNITUDE_UNKNOWN = 5
            val density = if (it.magnitudeOfDelay >= 0) it.magnitudeOfDelay else DELAY_MAGNITUDE_UNKNOWN
            val style = routeTrafficStyles[density]
            val trafficData = trafficStyle[style]?.toMutableList() ?: mutableListOf()
            trafficData.add(TrafficData(it.startPointIndex, it.endPointIndex))
            trafficStyle[style] = trafficData
        }
        //end::doc_route_traffic_style_data_mapping[]
        val routeId = tomtomMap.routes.first().id
        //tag::doc_route_traffic_show[]
        tomtomMap.showTrafficOnRoute(routeId, trafficStyle)
        //end::doc_route_traffic_show[]
    }

    //tag::doc_create_route_traffic_style[]
    private val routeTrafficStyles: List<RouteLayerStyle> =
        listOf(
            DENSITY_LEVEL_0_COLOR, //DENSITY_LEVEL_0_COLOR = 0xFFCC9900
            DENSITY_LEVEL_1_COLOR, //DENSITY_LEVEL_1_COLOR = 0xFFFFFF00
            DENSITY_LEVEL_2_COLOR, //DENSITY_LEVEL_2_COLOR = 0xFFFF9900
            DENSITY_LEVEL_3_COLOR, //DENSITY_LEVEL_3_COLOR = 0xFFFF3300
            DENSITY_LEVEL_4_COLOR, //DENSITY_LEVEL_4_COLOR = 0xFF993300
            DENSITY_LEVEL_5_COLOR //DENSITY_LEVEL_5_COLOR = 0xFFFFFFFF
        ).map {
            RouteLayerStyle.Builder()
                .color(it.toInt())
                .build()
        }
    //end::doc_create_route_traffic_style[]

    fun setAllAsInactive() {
        //To simplify, mark all as inactive and then selected as active
        //In more advanced flow, one can control style of each route separately
        tomtomMap.routeSettings.routes.forEach { route ->
            tomtomMap.routeSettings.updateRouteStyle(
                route.id,
                RouteStyle.DEFAULT_INACTIVE_ROUTE_STYLE
            )
        }
    }

    fun setActiveByIdx(idx: Int) {
        //Find route by id and mark selected as active
        val routeId = tomtomMap.routeSettings.routes[idx].id
        tomtomMap.routeSettings.updateRouteStyle(routeId, RouteStyle.DEFAULT_ROUTE_STYLE)
        tomtomMap.routeSettings.bringRouteToFront(routeId)
    }

    fun updateRouteStyle(idx: Int, routeStyle: RouteStyle) {
        val routeId = tomtomMap.routeSettings.routes[idx].id
        tomtomMap.routeSettings.updateRouteStyle(routeId, routeStyle)
    }

    private fun createIcon(iconResId: Int): Icon = Icon.Factory.fromResources(context, iconResId)

    private fun createCustomStyle(): RouteStyle {
        return (
            //tag::doc_create_custom_route_style[]
            RouteStyleBuilder.create()
                .withWidth(ROUTE_WIDTH)
                .withFillColor(Color.BLACK)
                .withOutlineColor(Color.RED)
                .build()
            //end::doc_create_custom_route_style[]
            )
    }

    private fun createDottedStyle(): RouteStyle {
        return RouteStyleBuilder.create()
            .withWidth(DOTTED_ROUTE_WIDTH)
            .withFillColor(COLOR_LIGHT_BLUE)
            .withOutlineColor(COLOR_LIGHT_BLACK)
            .withLineCapType(LineCapType.ROUND)
            .withDashList(DASH_LIST)
            .build()
    }

    companion object {
        const val ROUTE_WIDTH = 2.0
        private const val DOTTED_ROUTE_WIDTH = 0.3
        private const val DASH_LENGTH = 0.01
        private const val DASH_GAP = 2.0
        private const val DELAY_MAGNITUDE_UNKNOWN = 5

        private const val DENSITY_LEVEL_0_COLOR = 0xFFCC9900
        private const val DENSITY_LEVEL_1_COLOR = 0xFFFFFF00
        private const val DENSITY_LEVEL_2_COLOR = 0xFFFF9900
        private const val DENSITY_LEVEL_3_COLOR = 0xFFFF3300
        private const val DENSITY_LEVEL_4_COLOR = 0xFF993300
        private const val DENSITY_LEVEL_5_COLOR = 0xFFFFFFFF

        private val ROUTE_DASH = DashDescriptor(DASH_LENGTH, DASH_GAP)
        private val DASH_LIST: List<DashDescriptor> = listOf(ROUTE_DASH)
        private val COLOR_LIGHT_BLUE = Color.rgb(26, 181, 196)
        private val COLOR_LIGHT_BLACK = Color.rgb(48, 48, 48)
    }

}
