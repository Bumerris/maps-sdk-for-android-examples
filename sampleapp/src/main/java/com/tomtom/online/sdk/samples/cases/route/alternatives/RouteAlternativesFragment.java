/**
 * Copyright (c) 2015-2020 TomTom N.V. All rights reserved.
 *
 * This software is the proprietary copyright of TomTom N.V. and its subsidiaries and may be used
 * for internal evaluation purposes or commercial use strictly subject to separate licensee
 * agreement between you and TomTom. If you are the licensee, you are only permitted to use
 * this Software in accordance with the terms of your license agreement. If you are not the
 * licensee then you are not authorised to use this software in any manner and should
 * immediately return it to TomTom N.V.
 */
package com.tomtom.online.sdk.samples.cases.route.alternatives;

import com.tomtom.online.sdk.samples.R;
import com.tomtom.online.sdk.samples.cases.RoutePlannerFragment;
import com.tomtom.online.sdk.samples.utils.views.OptionsButtonsView;

public class RouteAlternativesFragment extends RoutePlannerFragment<RouteAlternativesPresenter> {

    @Override
    protected RouteAlternativesPresenter createPresenter() {
        return new RouteAlternativesPresenter(this);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    protected void onOptionsButtonsView(OptionsButtonsView view) {
        view.addOption(R.string.btn_text_one);
        view.addOption(R.string.btn_text_three);
        view.addOption(R.string.btn_text_five);
    }

    @Override
    public void onChange(final boolean[] oldValues, final boolean[] newValues) {
        if (newValues[0]) {
            presenter.startRouting(1);
        } else if (newValues[1]) {
            presenter.startRouting(3);
        } else if (newValues[2]) {
            presenter.startRouting(5);
        }
    }

}
