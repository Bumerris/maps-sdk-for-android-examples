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
package com.tomtom.online.sdk.samples.cases.map.traffic.incident;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tomtom.online.sdk.samples.R;
import com.tomtom.online.sdk.samples.activities.ActionBarModel;
import com.tomtom.online.sdk.samples.cases.map.traffic.incident.helpers.FunctionalExampleFragmentAdapter;
import com.tomtom.online.sdk.samples.cases.map.traffic.incident.list.TrafficIncidentsListAdapter;
import com.tomtom.online.sdk.samples.cases.map.traffic.incident.model.TrafficIncidentItem;

import java.util.List;

public class TrafficIncidentListFragment extends FunctionalExampleFragmentAdapter {

    private TrafficIncidentListPresenter presenter;
    private TrafficIncidentsListAdapter listAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new TrafficIncidentListPresenter(this);
        getLifecycle().addObserver(presenter);
    }

    @Override
    public void onResume() {
        super.onResume();
        ActionBarModel actionBar = (ActionBarModel) getActivity();
        actionBar.setActionBarTitle(presenter.getModel().getPlayableTitle());
        actionBar.setActionBarSubtitle(presenter.getModel().getPlayableSubtitle());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_traffic_incident_list, container, false);
        setupIncidentsList(view);
        return view;
    }

    private void setupIncidentsList(View view) {
        RecyclerView trafficIncidentsList = view.findViewById(R.id.trafficIncidentsList);
        listAdapter = new TrafficIncidentsListAdapter();
        trafficIncidentsList.setLayoutManager(new LinearLayoutManager(getContext()));
        trafficIncidentsList.setAdapter(listAdapter);
    }

    void updateTrafficIncidentsList(List<TrafficIncidentItem> list) {
        listAdapter.updateIncidentsList(list);
    }
}
