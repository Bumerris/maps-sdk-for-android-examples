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
package com.tomtom.online.sdk.samples.cases.map.layers.tilestypes;

import com.tomtom.online.sdk.map.TomtomMap;
import com.tomtom.online.sdk.map.model.MapTilesType;
import com.tomtom.online.sdk.samples.activities.BaseFunctionalExamplePresenter;
import com.tomtom.online.sdk.samples.activities.FunctionalExampleModel;
import com.tomtom.online.sdk.samples.fragments.FunctionalExampleFragment;
import com.tomtom.online.sdk.samples.utils.Locations;

public class MapTilesTypesPresenter extends BaseFunctionalExamplePresenter {

    @Override
    public void bind(FunctionalExampleFragment view, TomtomMap map) {
        super.bind(view, map);
        if (!view.isMapRestored()) {
            centerOn(Locations.AMSTERDAM_LOCATION);
        }
    }

    @Override
    public FunctionalExampleModel getModel() {
        return new MapTilesTypesFunctionalExample();
    }

    @SuppressWarnings("deprecation")
    @Override
    public void cleanup() {
        tomtomMap.getUiSettings().setMapTilesType(MapTilesType.VECTOR);
    }

    @SuppressWarnings("deprecation")
    public void showOnlyVectorTiles() {
        //tag::doc_set_vector_tiles[]
        tomtomMap.getUiSettings().setMapTilesType(MapTilesType.VECTOR);
        //end::doc_set_vector_tiles[]
    }

    @SuppressWarnings("deprecation")
    public void showOnlyRasterTiles() {
        //tag::doc_set_raster_tiles[]
        tomtomMap.getUiSettings().setMapTilesType(MapTilesType.RASTER);
        //end::doc_set_raster_tiles[]
    }

    @SuppressWarnings({"unused", "deprecation"})
    public void showNoTiles() {
        //tag::doc_set_none_tiles[]
        tomtomMap.getUiSettings().setMapTilesType(MapTilesType.NONE);
        //end::doc_set_none_tiles[]
    }

    @SuppressWarnings("unused")
    private void loadRasterStyle() {
        //tag::doc_load_raster_tiles[]
        tomtomMap.getUiSettings().setStyleUrl("asset://styles/mapssdk-raster_style.json");
        //end::doc_load_raster_tiles[]
    }

    @SuppressWarnings("unused")
    private void loadVectorStyle() {
        //tag::doc_load_vector_tiles[]
        tomtomMap.getUiSettings().loadDefaultStyle();
        //end::doc_load_vector_tiles[]
    }
}
