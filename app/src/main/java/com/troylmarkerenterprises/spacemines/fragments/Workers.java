/***********************************************************************************************************************************************
 *  Project Name: Space Mines                                                                                                                  *
 *  Class Name: com.troylmarkerenterprises.spacemines.fragments.Workers                                                                        *
 *  File Name: Workers.java                                                                                                                    *
 *  File Creation Date: 7/24/2022                                                                                                              *
 *  File Creation Time: 11:0:14                                                                                                                *
 *  File Creator: troylmarker                                                                                                                  *
 *                                                                                                                                             *
 *  Copyright 2022  by Troy L Marker Enterprises                                                                                               *
 *                                                                                                                                             *
 *  Licensed under the Apache License, Version 2.0 (the "License"); You may not use this file except in compliance with the License.           *
 *  You may obtain a copy of the License at                                                                                                    *
 *                                                                                                                                             *
 *       http://www.apache.org/licenses/LICENSE-2.0                                                                                            *
 *                                                                                                                                             *
 *  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS,      *
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.                                                                   *
 *  See the License for the specific language governing permissions and limitations under the License.                                         *
 ***********************************************************************************************************************************************/
package com.troylmarkerenterprises.spacemines.fragments;

import static com.troylmarkerenterprises.spacemines.constants.Pref.PREFERENCE_PLANET_ID;
import static com.troylmarkerenterprises.spacemines.constants.Pref.PREFERENCE_PLANET_NAME;
import static com.troylmarkerenterprises.spacemines.constants.Pref.PREFERENCE_SHIP_PASSENGER;
import static com.troylmarkerenterprises.spacemines.constants.Pref.PREFERENCE_SHIP_SPEED;
import static com.troylmarkerenterprises.spacemines.constants.Pref.PREFERENCE_TRANSIT_TIMER;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.troylmarkerenterprises.spacemines.R;
import com.troylmarkerenterprises.spacemines.database.Delete;
import com.troylmarkerenterprises.spacemines.database.Prefs;
import com.troylmarkerenterprises.spacemines.database.Read;
import com.troylmarkerenterprises.spacemines.database.Update;
import com.troylmarkerenterprises.spacemines.database.Utilities;
import com.troylmarkerenterprises.spacemines.model.ITWorkersModel;
import com.troylmarkerenterprises.spacemines.model.TimeModel;
import com.troylmarkerenterprises.spacemines.model.WorkerModel;


public class Workers extends Fragment implements SeekBar.OnSeekBarChangeListener, View.OnClickListener {

    String mPlanet;
    int mId;
    SeekBar sbMiner;
    SeekBar sbMaint;
    SeekBar sbEnter;
    Button btnRecruit;
    Button btnRecall;
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
    TextView txtTransitMinerWorker;
    TextView txtTransitMinerSupervisor;
    TextView txtTransitMaintenanceWorker;
    TextView txtTransitMaintenanceSupervisor;
    TextView txtTransitEntertainerWorker;
    TextView txtTransitEntertainerSupervisor;
    TextView txtMinerTransitTime;
    TextView txtMaintenanceTransitTime;
    TextView txtEntertainerTransitTime;

    int shipPassenger;
    int shipSpeed;
    int maxRecruitCount;
    String trans;

    WorkerModel planetWorkers;
    Prefs prefs;
    Read read;
    Update update;
    Utilities utilities;
    Delete delete;

    private CountDownTimer mTransitTimer;
    private boolean mTimerRunning;
    private long mTimeLeftInSecs;
    private long secs;
    private long mElapsed;

    public static Workers newInstance() {
        return new Workers();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_workers, container, false);
        prefs = new Prefs(requireContext().getApplicationContext());
        read = new Read(requireContext().getApplicationContext());
        update = new Update(requireContext().getApplicationContext());
        utilities = new Utilities(requireContext().getApplicationContext());
        delete = new Delete(requireContext().getApplicationContext());
        shipPassenger = Integer.parseInt(prefs.getPrefOrCreate(PREFERENCE_SHIP_PASSENGER, "1"));
        shipSpeed = Integer.parseInt(prefs.getPrefOrCreate(PREFERENCE_SHIP_SPEED, "1"));
        maxRecruitCount = shipPassenger * 110;
        currentPlanet = view.findViewById(R.id.txtCurrentPlanet);
        inTransitWorkers = view.findViewById(R.id.txtIntTransit);
        recruitment = view.findViewById(R.id.txtRecruitment);
        maxRecruit = view.findViewById(R.id.txtMaxRec);
        sbMiner = view.findViewById(R.id.sbMiner);
        sbMaint = view.findViewById(R.id.sbMaint);
        sbEnter = view.findViewById(R.id.sbEnter);
        btnRecruit = view.findViewById(R.id.btnRecruit);
        btnRecall = view.findViewById(R.id.btnRecall);
        sbMiner.setOnSeekBarChangeListener(this);
        sbMaint.setOnSeekBarChangeListener(this);
        sbEnter.setOnSeekBarChangeListener(this);
        btnRecruit.setOnClickListener(this);
        btnRecall.setOnClickListener(this);
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
        txtTransitMinerWorker = view.findViewById(R.id.txtTransitMinerWorker);
        txtTransitMinerSupervisor = view.findViewById(R.id.txtTransitMinerSupervisor);
        txtTransitMaintenanceWorker = view.findViewById(R.id.txtTransitMaintenanceWorker);
        txtTransitMaintenanceSupervisor = view.findViewById(R.id.txtTransitMaintenanceSupervisor);
        txtTransitEntertainerWorker = view.findViewById(R.id.txtTransitEntertainerWorker);
        txtTransitEntertainerSupervisor = view.findViewById(R.id.txtTransitEntertainerSupervisor);
        txtMinerTransitTime = view.findViewById(R.id.txtMinerTransitTime);
        txtMaintenanceTransitTime = view.findViewById(R.id.txtMaintenanceTransitTime);
        txtEntertainerTransitTime = view.findViewById(R.id.txtEntertainerTransitTime);
        sbMiner.setMin(0);
        sbMiner.setMax(maxRecruitCount - (shipPassenger * 10));
        sbMaint.setMin(0);
        sbMaint.setMax(maxRecruitCount - (shipPassenger * 10));
        txtMinerTransitTime.setText(getString(R.string.blank_transit_time, "00:00:00"));
        txtMaintenanceTransitTime.setText(getString(R.string.blank_transit_time, "00:00:00"));
        txtEntertainerTransitTime.setText(getString(R.string.blank_transit_time, "00:00:00"));
        return view;
    }

    @Override
    public void setMenuVisibility(final boolean visible) {
        super.setMenuVisibility(visible);
        if (!visible) {
            currentPlanet.setText(getString(R.string.current_workers, "-------"));
            mTransitTimer.cancel();
            prefs.setPref(PREFERENCE_TRANSIT_TIMER, String.valueOf(secs));
        }else{
            if(prefs.checkPref(PREFERENCE_PLANET_NAME)) {
                displayCurrentCounts();
                displayTransitCounts();
                displayRecruitment();
                secs = Integer.parseInt(prefs.getPrefOrCreate(PREFERENCE_TRANSIT_TIMER, "0"));
                if(secs != 0) {

                }
            } else {
                currentPlanet.setText(getString(R.string.current_workers, "-------"));
                inTransitWorkers.setText(getString(R.string.in_transit_workers, "-------"));
                recruitment.setText(getString(R.string.recruitment, "-------"));
                maxRecruit.setText(getString(R.string.max_recruitable, "-------"));
            }
        }
    }

    private void displayCurrentCounts () {
        mPlanet = prefs.getPref(PREFERENCE_PLANET_NAME);
        mId = Integer.parseInt(prefs.getPref(PREFERENCE_PLANET_ID));
        planetWorkers = read.readPlanetWorker(mId);
        currentPlanet.setText(getString(R.string.current_workers, mPlanet));
        txtCurrentMinerWorker.setText(String.valueOf(planetWorkers.getMinerw()));
        txtCurrentMinerSupervisor.setText(String.valueOf(planetWorkers.getMiners()));
        txtCurrentMaintenanceWorker.setText(String.valueOf(planetWorkers.getMaintw()));
        txtCurrentMaintenanceSupervisor.setText(String.valueOf(planetWorkers.getMaints()));
        txtCurrentEntertainerWorker.setText(String.valueOf(planetWorkers.getEnterw()));
        txtCurrentEntertainerSupervisor.setText(String.valueOf(planetWorkers.getEnters()));
    }

    private void displayTransitCounts () {
        inTransitWorkers.setText(getString(R.string.in_transit_workers, mPlanet));
        ITWorkersModel itworkers = read.readITWorkers(mId);
        mTimeLeftInSecs = utilities.getTransitTime(mId, mId);

        //prefs.setPref(PREFERENCE_TRANSIT_TIMER, String.valueOf(transit));
        trans = utilities.getTime();
        if (itworkers.getMinerw() > 0) {
            txtMinerTransitTime.setText(getString(R.string.blank_transit_time, trans));
        }
        if (itworkers.getMaintw() > 0) {
            txtMaintenanceTransitTime.setText(getString(R.string.blank_transit_time, trans));
        }
        if (itworkers.getEnterw() > 0) {
            txtEntertainerTransitTime.setText(getString(R.string.blank_transit_time, trans));
        }
        txtTransitMinerWorker.setText(String.valueOf(itworkers.getMinerw()));
        txtTransitMinerSupervisor.setText(String.valueOf(itworkers.getMiners()));
        txtTransitMaintenanceWorker.setText(String.valueOf(itworkers.getMaintw()));
        txtTransitMaintenanceSupervisor.setText(String.valueOf(itworkers.getMaints()));
        txtTransitEntertainerWorker.setText(String.valueOf(itworkers.getEnterw()));
        txtTransitEntertainerSupervisor.setText(String.valueOf(itworkers.getEnters()));
        mTransitTimer = setupTimer();
        mTransitTimer.start();
    }

    private void displayRecruitment () {
        recruitment.setText(getString(R.string.recruitment, mPlanet));
        maxRecruit.setText(getString(R.string.max_recruitable, String.valueOf(maxRecruitCount)));
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
                update.updateTransitWorkers(mId,
                        Integer.parseInt(txtRecruitMinerWorker.getText().toString()),
                        Integer.parseInt(txtRecruitMinerSupervisor.getText().toString()),
                        Integer.parseInt(txtRecruitMaintenanceWorker.getText().toString()),
                        Integer.parseInt(txtRecruitMaintenanceSupervisor.getText().toString()),
                        Integer.parseInt(txtRecruitEntertainerWorker.getText().toString()),
                        Integer.parseInt(txtRecruitEntertainerSupervisor.getText().toString()));
                sbMiner.setProgress(0);
                sbMaint.setProgress(0);
                sbEnter.setProgress(0);
                displayTransitCounts();
                mTimeLeftInSecs = utilities.getTransitTime(mId,mId);
                btnRecruit.setVisibility(View.INVISIBLE);
                btnRecall.setVisibility(View.VISIBLE);
            }
        }
        if(v == btnRecall) {
            update.recallWorkers(mId);
            displayTransitCounts();
            txtMinerTransitTime.setText(getString(R.string.blank_transit_time, "00:00:00"));
            txtMaintenanceTransitTime.setText(getString(R.string.blank_transit_time, "00:00:00"));
            txtEntertainerTransitTime.setText(getString(R.string.blank_transit_time, "00:00:00"));
            btnRecruit.setVisibility(View.VISIBLE);
            btnRecall.setVisibility(View.INVISIBLE);
        }
    }

    private void showRemainingTime(int intSecs) {
        if(!txtTransitMinerWorker.getText().toString().equals("0"))
            txtMinerTransitTime.setText(requireContext().getString(R.string.blank_transit_time, new TimeModel(intSecs).toString()));
        if(!txtTransitMaintenanceWorker.getText().toString().equals("0"))
            txtMaintenanceTransitTime.setText(getString(R.string.blank_transit_time, new TimeModel(intSecs).toString()));
        if(!txtTransitEntertainerWorker.getText().toString().equals("0"))
            txtEntertainerTransitTime.setText(getString(R.string.blank_transit_time, new TimeModel(intSecs).toString()));
    }

    private CountDownTimer setupTimer() {
        //long timer = secs * 1000;
        long timer = mTimeLeftInSecs * 1000;
        secs = (int) mTimeLeftInSecs;
        CountDownTimer returnValue;
        returnValue = new CountDownTimer(timer, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                secs--;
                showRemainingTime((int) secs);
            }
            @Override
            public void onFinish() {

            }
        };
        return returnValue;
    }
}
