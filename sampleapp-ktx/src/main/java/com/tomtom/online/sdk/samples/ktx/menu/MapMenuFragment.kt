/*
 * Copyright (c) 2015-2020 TomTom N.V. All rights reserved.
 *
 * This software is the proprietary copyright of TomTom N.V. and its subsidiaries and may be used
 * for internal evaluation purposes or commercial use strictly subject to separate licensee
 * agreement between you and TomTom. If you are the licensee, you are only permitted to use
 * this Software in accordance with the terms of your license agreement. If you are not the
 * licensee then you are not authorised to use this software in any manner and should
 * immediately return it to TomTom N.V.
 */

package com.tomtom.online.sdk.samples.ktx.menu

import com.tomtom.online.sdk.samples.ktx.menu.adapters.SubMenuAdapter
import com.tomtom.online.sdk.samples.ktx.menu.data.MenuItem
import com.tomtom.sdk.examples.R

class MapMenuFragment : MenuFragment() {

    companion object {
        val MENU_ITEMS = listOf(
            MenuItem(
                title = R.string.menu_map_tiles_title,
                icon = R.drawable.ic_map_menu_icon,
                description = R.string.menu_map_tiles_description,
                banner = R.drawable.ic_map_menu_map_tiles,
                onClickNavigateTo = R.id.mapTilesFragment
            ),
            MenuItem(
                title = R.string.menu_map_vector_and_traffic_title,
                icon = R.drawable.ic_map_menu_icon,
                description = R.string.menu_map_vector_and_traffic_description,
                banner = R.drawable.ic_map_menu_vector_map_and_traffic,
                onClickNavigateTo = R.id.mapVectorAndTrafficFragment
            ),
            MenuItem(
                title = R.string.menu_map_raster_and_traffic_title,
                icon = R.drawable.ic_map_menu_icon,
                description = R.string.menu_map_raster_and_traffic_description,
                banner = R.drawable.ic_map_menu_raster_map_and_traffic,
                onClickNavigateTo = R.id.mapRasterAndTrafficFragment
            ),
            MenuItem(
                title = R.string.menu_map_language_title,
                icon = R.drawable.ic_map_menu_icon,
                description = R.string.menu_map_language_description,
                banner = R.drawable.ic_map_menu_map_language,
                onClickNavigateTo = R.id.mapLanguageFragment
            ),
            MenuItem(
                title = R.string.menu_map_geopolitical_view_title,
                icon = R.drawable.ic_map_menu_icon,
                description = R.string.menu_map_geopolitical_view_description,
                banner = R.drawable.ic_map_menu_geopolitical_view,
                onClickNavigateTo = R.id.mapGeopoliticalViewFragment
            ),
            MenuItem(
                title = R.string.menu_map_styles_title,
                icon = R.drawable.ic_map_menu_icon,
                description = R.string.menu_map_styles_description,
                banner = R.drawable.ic_map_custom_style,
                onClickNavigateTo = R.id.customMapStyleFragment
            ),
            MenuItem(
                title = R.string.menu_map_layers_visibility_title,
                icon = R.drawable.ic_map_menu_icon,
                description = R.string.menu_map_layers_visibility_description,
                banner = R.drawable.ic_map_layers_visibility,
                onClickNavigateTo = R.id.mapLayerVisibilityFragment
            ),
            MenuItem(
                title = R.string.menu_map_poi_layers_visibility_title,
                icon = R.drawable.ic_map_menu_icon,
                description = R.string.menu_map_poi_layers_visibility_description,
                banner = R.drawable.ic_map_menu_poi_layers_visibility,
                onClickNavigateTo =R.id.poiLayersVisibilityFragment
            ),
            MenuItem(
                title = R.string.menu_map_dynamic_sources_title,
                icon = R.drawable.ic_map_menu_icon,
                description = R.string.menu_map_dynamic_sources_description,
                banner = R.drawable.ic_map_dynamic_sources,
                onClickNavigateTo = R.id.mapDynamicSourcesFragment
            ),
            MenuItem(
                title = R.string.menu_map_dynamic_layer_ordering_title,
                icon = R.drawable.ic_map_menu_icon,
                description = R.string.menu_map_dynamic_layer_ordering_description,
                banner = R.drawable.ic_map_dynamic_layer_ordering,
                onClickNavigateTo = R.id.mapDynamicLayerOrderFragment
            ),
            MenuItem(
                title = R.string.menu_map_interactive_layers_title,
                icon = R.drawable.ic_map_menu_icon,
                description = R.string.menu_map_interactive_layers_description,
                banner = R.drawable.ic_map_menu_interactive_layers,
                onClickNavigateTo = R.id.mapInteractiveLayersFragment
            ),
            MenuItem(
                title = R.string.menu_map_layers_filtering_title,
                icon = R.drawable.ic_map_menu_icon,
                description = R.string.menu_map_layers_filtering_description,
                banner = R.drawable.ic_map_layers_filtering,
                onClickNavigateTo = R.id.mapLayerFilteringFragment
            ),
            MenuItem(
                title = R.string.menu_map_image_clustering_title,
                icon = R.drawable.ic_map_menu_icon,
                description = R.string.menu_map_image_clustering_description,
                banner = R.drawable.ic_map_image_clustering,
                onClickNavigateTo = R.id.mapImageClusteringFragment
            ),
            MenuItem(
                title = R.string.menu_map_static_image_title,
                icon = R.drawable.ic_map_menu_icon,
                description = R.string.menu_map_static_image_description,
                banner = R.drawable.ic_map_menu_static_image,
                onClickNavigateTo = R.id.mapStaticImageFragment
            ),
            MenuItem(
                title = R.string.menu_map_centering_title,
                icon = R.drawable.ic_map_menu_icon,
                description = R.string.menu_map_centering_description,
                banner = R.drawable.ic_map_menu_map_centering,
                onClickNavigateTo = R.id.mapCenteringFragment
            ),
            MenuItem(
                title = R.string.menu_map_initialization_title,
                icon = R.drawable.ic_map_menu_icon,
                description = R.string.menu_map_initialization_description,
                banner = R.drawable.ic_map_menu_map_initialization,
                onClickNavigateTo = R.id.mapInitializationFragment
            ),
            MenuItem(
                title = R.string.menu_map_perspective_title,
                icon = R.drawable.ic_map_menu_icon,
                description = R.string.menu_map_perspective_description,
                banner = R.drawable.ic_map_menu_map_perspective,
                onClickNavigateTo = R.id.mapPerspectiveFragment
            ),
            MenuItem(
                title = R.string.menu_map_snapshot_title,
                icon = R.drawable.ic_map_menu_icon,
                description = R.string.menu_map_snapshot_description,
                banner = R.drawable.ic_map_menu_map_snapshot,
                onClickNavigateTo = R.id.mapSnapshotFragment
            ),
            MenuItem(
                title = R.string.menu_map_events_title,
                icon = R.drawable.ic_map_menu_icon,
                description = R.string.menu_map_events_description,
                banner = R.drawable.ic_map_menu_map_events,
                onClickNavigateTo = R.id.mapEventsFragment
            ),
            MenuItem(
                title = R.string.menu_map_ui_extensions_title,
                icon = R.drawable.ic_map_menu_icon,
                description = R.string.menu_map_ui_extension_description,
                banner = R.drawable.ic_map_menu_map_ui_extensions,
                onClickNavigateTo = R.id.mapUiExtensionsFragment
            ),
            MenuItem(
                title = R.string.menu_map_markers_title,
                icon = R.drawable.ic_map_menu_icon,
                description = R.string.menu_map_markers_description,
                banner = R.drawable.ic_map_menu_markers,
                onClickNavigateTo = R.id.markersFragment
            ),
            MenuItem(
                title = R.string.menu_map_advanced_markers_title,
                icon = R.drawable.ic_map_menu_icon,
                description = R.string.menu_map_advanced_markers_description,
                banner = R.drawable.ic_map_menu_advanced_markers,
                onClickNavigateTo = R.id.advancedMarkersFragment
            ),
            MenuItem(
                title = R.string.menu_map_custom_balloon_title,
                icon = R.drawable.ic_map_menu_icon,
                description = R.string.menu_map_custom_balloon_description,
                banner = R.drawable.ic_map_menu_custom_balloon,
                onClickNavigateTo = R.id.customBalloonFragment
            ),
            MenuItem(
                title = R.string.menu_map_custom_banner_view_title,
                icon = R.drawable.ic_map_menu_icon,
                description = R.string.menu_map_custom_banner_view_description,
                banner = R.drawable.ic_map_menu_custom_map_banner,
                onClickNavigateTo = R.id.customMapBannerFragment
            ),
            MenuItem(
                title = R.string.menu_map_custom_shapes_title,
                icon = R.drawable.ic_map_menu_icon,
                description = R.string.menu_map_custom_shapes_description,
                banner = R.drawable.ic_map_menu_custom_shapes,
                onClickNavigateTo = R.id.customShapesFragment
            ),
            MenuItem(
                title = R.string.menu_map_marker_clustering_title,
                icon = R.drawable.ic_map_menu_icon,
                description = R.string.menu_map_marker_clustering_description,
                banner = R.drawable.ic_map_menu_marker_clustering,
                onClickNavigateTo = R.id.markerClusteringFragment
            ),
            MenuItem(
                title = R.string.menu_map_multiple_maps_title,
                icon = R.drawable.ic_map_menu_icon,
                description = R.string.menu_map_multiple_maps_description,
                banner = R.drawable.ic_map_menu_multiple_maps,
                onClickNavigateTo = R.id.multipleMapsFragment
            ),
            MenuItem(
                title = R.string.menu_map_building_heights_title,
                icon = R.drawable.ic_map_menu_icon,
                description = R.string.menu_map_building_heights_description,
                banner = R.drawable.ic_map_menu_building_height,
                onClickNavigateTo = R.id.mapBuildingHeightsFragment
            ),
            MenuItem(
                title = R.string.menu_map_custom_route_title,
                icon = R.drawable.ic_map_menu_icon,
                description = R.string.menu_map_custom_route_description,
                banner = R.drawable.ic_map_menu_custom_route,
                onClickNavigateTo = R.id.customRouteFragment
            )
        )
    }

    override fun createMenuAdapter() = SubMenuAdapter(MENU_ITEMS)

}
