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

package com.tomtom.online.sdk.samples.ktx.cases.map.multiplemaps

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.tomtom.online.sdk.samples.ktx.MapAction
import com.tomtom.online.sdk.samples.ktx.utils.arch.SingleLiveEvent

class MultipleMapsViewModel(application: Application) : AndroidViewModel(application) {

    private var miniMapAction: SingleLiveEvent<MapAction> = SingleLiveEvent()

    fun applyOnMiniMap(action: MapAction) {
        miniMapAction.value = action
    }

    fun miniMapAction(): LiveData<MapAction> {
        return miniMapAction
    }

}
