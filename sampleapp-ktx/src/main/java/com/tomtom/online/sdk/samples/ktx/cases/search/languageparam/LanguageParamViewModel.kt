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

package com.tomtom.online.sdk.samples.ktx.cases.search.languageparam

import android.app.Application
import com.tomtom.online.sdk.common.location.LatLngBias
import com.tomtom.online.sdk.samples.ktx.cases.search.SearchViewModel
import com.tomtom.online.sdk.samples.ktx.utils.arch.LanguageSelector
import com.tomtom.online.sdk.search.fuzzy.FuzzySearchSpecification
import com.tomtom.online.sdk.search.fuzzy.FuzzyLocationDescriptor
import com.tomtom.online.sdk.search.fuzzy.FuzzySearchEngineDescriptor

class LanguageParamViewModel(application: Application) : SearchViewModel(application) {

    private var languageSelector: LanguageSelector = LanguageSelector.EN

    override fun search(term: String) {
        val locationDescriptor = FuzzyLocationDescriptor.Builder()
        addPosition()?.let { position -> locationDescriptor.positionBias(LatLngBias(position)) }
        //tag::doc_create_simple_specification_with_lang[]
        val searchEngineDescriptor = FuzzySearchEngineDescriptor.Builder()
            .language(languageSelector.code)
            .build()

        val fuzzySearchSpecification = FuzzySearchSpecification.Builder(term)
            .searchEngineDescriptor(searchEngineDescriptor)
            .locationDescriptor(locationDescriptor.build())
            .build()
        //end::doc_create_simple_specification_with_lang[]
        search(fuzzySearchSpecification)
    }

    fun enableEnglish() {
        languageSelector = LanguageSelector.EN
    }

    fun enableGerman() {
        languageSelector = LanguageSelector.DE
    }

    fun enableFrench() {
        languageSelector = LanguageSelector.FR
    }

    fun enableSpanish() {
        languageSelector = LanguageSelector.ES
    }
}
