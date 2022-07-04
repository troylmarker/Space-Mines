/************************************************************************************************************************************************
 *  Project Name: Space Mines                                                                                                                   *
 *  Class Name: com.troylmarkerenterprises.spacemines.fragments.Workers                                                                         *
 *  File Name: Workers.java                                                                                                                     *
 *  File Creation Date: 6/19/2022                                                                                                               *
 *  File Creation Time: 16:19:11                                                                                                                *
 *  File Creator: troylmarker                                                                                                                   *
 *                                                                                                                                              *
 *  Copyright 2022  by Troy L Marker Enterprises                                                                                                *
 *                                                                                                                                              *
 *  Licensed under the Apache License, Version 2.0 (the "License"); You may not use this file except in compliance with the License.            *
 *  You may obtain a copy of the License at                                                                                                     *
 *                                                                                                                                              *
 *       http://www.apache.org/licenses/LICENSE-2.0                                                                                             *
 *                                                                                                                                              *
 *  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS,       *
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.                                                                    *
 *  See the License for the specific language governing permissions and limitations under the License.                                          *
 ************************************************************************************************************************************************/
package com.troylmarkerenterprises.spacemines.fragments;

import static com.troylmarkerenterprises.spacemines.constants.Pref.PREFERENCE_PLANET_ID;
import static com.troylmarkerenterprises.spacemines.constants.Pref.PREFERENCE_PLANET_NAME;
import static com.troylmarkerenterprises.spacemines.constants.Pref.PREFERENCE_SHIP_LEVEL;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.troylmarkerenterprises.spacemines.R;
import com.troylmarkerenterprises.spacemines.database.Database;
import com.troylmarkerenterprises.spacemines.model.ITWorkersModel;
import com.troylmarkerenterprises.spacemines.model.WorkerModel;

public class Workers extends Fragment implements SeekBar.OnSeekBarChangeListener, View.OnClickListener {

    String mPlanet;
    int mId;
    SeekBar sbMiner;
    SeekBar sbMaint;
    SeekBar sbEnter;
    Button btnRecruit;
    TextView currentPlanet;
    TextView inTransitWorkers;
    TextView recruitment;
    TextView maxRecruit;
    TextView txtCurrentMinerWorker;
    TextView txtCurrentMinerSupervisor;
    TextView txtCurrentMaintenanceWorker;
    TextView txtCurrentMaintenanceSupervisor;
    TextView txtCurrentEntertainerWorker;
    TextView txtCurrentEntertainerSupervisor;
    TextView txtRecruitMinerWorker;
    TextView txtRecruitMinerSupervisor;
    TextView txtRecruitMaintenanceWorker;
    TextView txtRecruitMaintenanceSupervisor;
    TextView txtRecruitEntertainerWorker;
    TextView txtRecruitEntertainerSupervisor;
    int shipLevel;
    int maxRecruitCount;
    WorkerModel planetWorkers;
    Database db;


    public static Workers newInstance() {
        return new Workers ();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_workers, container, false);
        db = new Database(requireContext().getApplicationContext());
        shipLevel = Integer.parseInt(db.checkPrefOrCreate(PREFERENCE_SHIP_LEVEL,"1"));
        maxRecruitCount = shipLevel * 110;
        currentPlanet = view.findViewById (R.id.txtCurrentPlanet);
        inTransitWorkers = view.findViewById (R.id.txtIntTransit);
        recruitment = view.findViewById(R.id.txtRecruitment);
        maxRecruit = view.findViewById(R.id.txtMaxRec);
        sbMiner = view.findViewById(R.id.sbMiner);
        sbMaint = view.findViewById(R.id.sbMaint);
        sbEnter = view.findViewById(R.id.sbEnter);
        btnRecruit = view.findViewById(R.id.btnRecruit);
        sbMiner.setOnSeekBarChangeListener(this);
        sbMaint.setOnSeekBarChangeListener(this);
        sbEnter.setOnSeekBarChangeListener(this);
        btnRecruit.setOnClickListener(this);
        txtRecruitMinerWorker = view.findViewById(R.id.txtRecruitMinerWorker);
        txtRecruitMinerSupervisor = view.findViewById(R.id.txtRecruitMinerSupervisor);
        txtRecruitMaintenanceWorker = view.findViewById(R.id.txtRecruitMaintenanceWorker);
        txtRecruitMaintenanceSupervisor = view.findViewById(R.id.txtRecruitMaintenanceSupervisor);
        txtRecruitEntertainerWorker = view.findViewById(R.id.txtRecruitEntertainerWorker);
        txtRecruitEntertainerSupervisor = view.findViewById(R.id.txtRecruitEntertainerSupervisor);
        txtCurrentMinerWorker = view.findViewById(R.id.txtCurrentMinerWorker);
        txtCurrentMinerSupervisor = view.findViewById(R.id.txtCurrentMinerSupervisor);
        txtCurrentMaintenanceWorker = view.findViewById(R.id.txtCurrentMaintenanceWorker);
        txtCurrentMaintenanceSupervisor = view.findViewById(R.id.txtCurrentMaintenanceSupervisor);
        txtCurrentEntertainerWorker = view.findViewById(R.id.txtCurrentEntertainerWorker);
        txtCurrentEntertainerSupervisor = view.findViewById(R.id.txtCurrentEntertainerSupervisor);
        sbMiner.setMin(0);
        sbMiner.setMax(maxRecruitCount-(shipLevel * 10));
        sbMaint.setMin(0);
        sbMaint.setMax(maxRecruitCount-(shipLevel * 10));

        return view;
    }

    @Override
    public void setMenuVisibility(final boolean visible) {
        super.setMenuVisibility(visible);
        if (!visible) {
            currentPlanet.setText(getString(R.string.current_workers, "-------"));
        }else{
            if(db.checkPref(PREFERENCE_PLANET_NAME)) {
                displayCurrentCounts();
                displayTransitCounts();
                displayRecruitment();
            } else {
                currentPlanet.setText(getString(R.string.current_workers, "-------"));
                inTransitWorkers.setText(getString(R.string.in_transit_workers, "-------"));
                recruitment.setText(getString(R.string.recruitment, "-------"));
                maxRecruit.setText(getString(R.string.max_recruitable, "-------"));
            }
        }
    }

    private void displayCurrentCounts () {
        mPlanet = db.getPref(PREFERENCE_PLANET_NAME);
        mId = Integer.parseInt(db.getPref(PREFERENCE_PLANET_ID));
        planetWorkers = db.getPlanetWorker(mId);
        currentPlanet.setText(getString(R.string.current_workers, mPlanet));
        txtCurrentMinerWorker.setText(String.valueOf(planetWorkers.getMinerw()));
        txtCurrentMinerSupervisor.setText(String.valueOf(planetWorkers.getMiners()));
        txtCurrentMaintenanceWorker.setText(String.valueOf(planetWorkers.getMaintw()));
        txtCurrentMaintenanceSupervisor.setText(String.valueOf(planetWorkers.getMaints()));
        txtCurrentEntertainerWorker.setText(String.valueOf(planetWorkers.getEnterw()));
        txtCurrentEntertainerSupervisor.setText(String.valueOf(planetWorkers.getEnters()));
    }

    private void displayTransitCounts () {
        inTransitWorkers.setText(getString(R.string.in_transit_workers, "--------"));
    }

    private void displayRecruitment () {
        recruitment.setText(getString(R.string.recruitment, mPlanet));
        maxRecruit.setText(getString(R.string.max_recruitable, String.valueOf(maxRecruitCount)));
        ITWorkersModel itworkers = db.getITWorkers(mId);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        if(seekBar == sbMiner) {
            txtRecruitMinerWorker.setText(String.valueOf(progress));
            txtRecruitMinerSupervisor.setText(String.valueOf((int) Math.ceil(progress/10.0)));
            sbMaint.setMax(100-sbMiner.getProgress());
        }
        if(seekBar == sbMaint) {
            txtRecruitMaintenanceWorker.setText(String.valueOf(progress));
            txtRecruitMaintenanceSupervisor.setText(String.valueOf((int) Math.ceil(progress/10.0)));
            sbEnter.setMax(100-sbMiner.getProgress()-sbMaint.getProgress());
        }
        if(seekBar == sbEnter) {
            txtRecruitEntertainerWorker.setText(String.valueOf(progress));
            txtRecruitEntertainerSupervisor.setText(String.valueOf((int) Math.ceil(progress/10.0)));
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
    }

    @Override
    public void onClick(View v) {
        if(v == btnRecruit) {
            if(sbMiner.getProgress() == 0 && sbMaint.getProgress() == 0 && sbEnter.getProgress() == 0) {
                Toast.makeText(getContext(), "No Workers Selected", Toast.LENGTH_LONG).show();
            } else {
                db.setTransitWorkers(mId,
                                     Integer.parseInt(txtRecruitMinerWorker.getText().toString()),
                                     Integer.parseInt(txtRecruitMinerSupervisor.getText().toString()),
                                     Integer.parseInt(txtRecruitMaintenanceWorker.getText().toString()),
                                     Integer.parseInt(txtRecruitMaintenanceSupervisor.getText().toString()),
                                     Integer.parseInt(txtRecruitEntertainerWorker.getText().toString()),
                                     Integer.parseInt(txtRecruitEntertainerSupervisor.getText().toString()));
                sbMiner.setProgress(0);
                sbMaint.setProgress(0);
                sbEnter.setProgress(0);
            }
        }
    }
}