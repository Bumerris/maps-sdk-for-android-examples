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
package com.tomtom.online.sdk.samples.cases.map.layers.layerstypes;

import com.google.common.base.Preconditions;
import com.tomtom.online.sdk.map.TomtomMap;
import com.tomtom.online.sdk.samples.R;
import com.tomtom.online.sdk.samples.activities.BaseFunctionalExamplePresenter;
import com.tomtom.online.sdk.samples.activities.FunctionalExampleModel;
import com.tomtom.online.sdk.samples.fragments.FunctionalExampleFragment;
import com.tomtom.online.sdk.samples.utils.Locations;

@SuppressWarnings("deprecation")
public class MapLayersTypesPresenter extends BaseFunctionalExamplePresenter {

    private MapLayersTypesView view;

    @Override
    public void bind(FunctionalExampleFragment view, TomtomMap map) {
        super.bind(view, map);
        if (!view.isMapRestored()) {
            centerOn(Locations.AMSTERDAM_LOCATION);
        }
        Preconditions.checkArgument(view instanceof MapLayersTypesView);
        this.view = (MapLayersTypesView) view;
        updateTilesStatus();

    }

    @Override
    public FunctionalExampleModel getModel() {
        return new MapLayersTypesFunctionalExample();
    }

    @Override
    public void cleanup() {
        super.cleanup();
        tomtomMap.getUiSettings().setMapTilesType(com.tomtom.online.sdk.map.model.MapTilesType.VECTOR);
        tomtomMap.getUiSettings().setMapLayersType(com.tomtom.online.sdk.map.model.MapLayersType.BASIC);
    }

    public void setMapTilesType(com.tomtom.online.sdk.map.model.MapTilesType type) {
        tomtomMap.getUiSettings().setMapTilesType(type);
        updateTilesStatus();
    }

    public void setMapLayersType(com.tomtom.online.sdk.map.model.MapLayersType type) {
        tomtomMap.getUiSettings().setMapLayersType(type);
        updateTilesStatus();
    }

    public com.tomtom.online.sdk.map.model.MapTilesType getMapTilesType() {
        return tomtomMap.getUiSettings().getMapTilesType();
    }

    public com.tomtom.online.sdk.map.model.MapLayersType getMapLayersType() {
        return tomtomMap.getUiSettings().getMapLayersType();
    }

    private void updateTilesStatus() {
        String tilesStatus = getContext().getString(R.string.map_layers_status,
                getMapTilesType(), getMapLayersType());
        view.showTilesStatus(tilesStatus);
    }
}
