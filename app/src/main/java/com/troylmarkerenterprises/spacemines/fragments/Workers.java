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

import android.content.Context;
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
    TextView dwc;
    TextView dsc;
    TextView mwc;
    TextView msc;
    TextView ewc;
    TextView esc;
    TextView txtMinerW;
    TextView txtMinerS;
    TextView txtMaintW;
    TextView txtMaintS;
    TextView txtEnterW;
    TextView txtEnterS;
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
        txtMinerW = view.findViewById(R.id.txtMinerW);
        txtMinerS = view.findViewById(R.id.txtMinerS);
        txtMaintW = view.findViewById(R.id.txtMaintW);
        txtMaintS = view.findViewById(R.id.txtMaintS);
        txtEnterW = view.findViewById(R.id.txtEnterW);
        txtEnterS = view.findViewById(R.id.txtEnterS);
        dwc = view.findViewById(R.id.d_w_c);
        dsc = view.findViewById(R.id.d_s_c);
        mwc = view.findViewById(R.id.m_w_c);
        msc = view.findViewById(R.id.m_s_c);
        ewc = view.findViewById(R.id.e_w_c);
        esc = view.findViewById(R.id.e_s_c);
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
        dwc.setText(String.valueOf(planetWorkers.getMiners()));
        dsc.setText(String.valueOf(planetWorkers.getMiners_supervisor()));
        mwc.setText(String.valueOf(planetWorkers.getMaintenance()));
        msc.setText(String.valueOf(planetWorkers.getMaintenance_supervisor()));
        ewc.setText(String.valueOf(planetWorkers.getEntertain()));
        esc.setText(String.valueOf(planetWorkers.getEntertain_supervisor()));
    }

    private void displayTransitCounts () {
        inTransitWorkers.setText(getString(R.string.in_transit_workers, "--------"));
    }

    private void displayRecruitment () {
        recruitment.setText(getString(R.string.recruitment, mPlanet));
        maxRecruit.setText(getString(R.string.max_recruitable, String.valueOf(maxRecruitCount)));
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        if(seekBar == sbMiner) {
            txtMinerW.setText(String.valueOf(progress));
            txtMinerS.setText(String.valueOf((int) Math.ceil(progress/10.0)));
            sbMaint.setMax(100-sbMiner.getProgress());
        }
        if(seekBar == sbMaint) {
            txtMaintW.setText(String.valueOf(progress));
            txtMaintS.setText(String.valueOf((int) Math.ceil(progress/10.0)));
            sbEnter.setMax(100-sbMiner.getProgress()-sbMaint.getProgress());
        }
        if(seekBar == sbEnter) {
            txtEnterW.setText(String.valueOf(progress));
            txtEnterS.setText(String.valueOf((int) Math.ceil(progress/10.0)));
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
                                     Integer.parseInt(txtMinerW.getText().toString()), Integer.parseInt(txtMinerS.getText().toString()),
                                     Integer.parseInt(txtMaintW.getText().toString()), Integer.parseInt(txtMaintS.getText().toString()),
                                     Integer.parseInt(txtEnterW.getText().toString()), Integer.parseInt(txtEnterS.getText().toString()));
                sbMiner.setProgress(0);
                sbMaint.setProgress(0);
                sbEnter.setProgress(0);
            }
        }
    }
}