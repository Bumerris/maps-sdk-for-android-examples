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
package com.tomtom.online.sdk.samples.cases.factory;

import com.tomtom.online.sdk.samples.R;
import com.tomtom.online.sdk.samples.cases.runtimestyle.sources.DynamicSourcesFragment;
import com.tomtom.online.sdk.samples.cases.runtimestyle.visibility.LayersVisibilityFragment;
import com.tomtom.online.sdk.samples.cases.runtimestyle.zorder.ZOrderFragment;
import com.tomtom.online.sdk.samples.fragments.FunctionalExampleFragment;

public class RuntimeStyleExampleFactory implements ExampleFactory {

    @Override
    public FunctionalExampleFragment create(int itemId) {
        switch (itemId) {
            case R.id.dynamic_map_sources:
                return new DynamicSourcesFragment();
            case R.id.map_layers_visibility:
                return new LayersVisibilityFragment();
            case R.id.zorder_map_layers:
                return new ZOrderFragment();
        }
        return null;
    }

}
