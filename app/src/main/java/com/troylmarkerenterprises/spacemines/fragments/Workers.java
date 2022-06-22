/*********************************************************************************************************************************************
 *  Project Name: Space Mines                                                                                                                *
 *  Class Name: com.troylmarkerenterprises.spacemines.fragments.Workers                                                                      *
 *  File Name: Workers.java                                                                                                                  *
 *  File Creation Date: 6/19/2022                                                                                                            *
 *  File Creation Time: 16:19:11                                                                                                             *
 *  File Creator: troylmarker                                                                                                                *
 *                                                                                                                                           *
 *  Copyright 2022  by Troy L Marker Enterprises                                                                                             *
 *                                                                                                                                           *
 *  Licensed under the Apache License, Version 2.0 (the "License"); You may not use this file except in compliance with the License.         *
 *  You may obtain a copy of the License at                                                                                                  *
 *                                                                                                                                           *
 *       http://www.apache.org/licenses/LICENSE-2.0                                                                                          *
 *                                                                                                                                           *
 *  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS,    *
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.                                                                 *
 *  See the License for the specific language governing permissions and limitations under the License.                                       *
 *********************************************************************************************************************************************/
package com.troylmarkerenterprises.spacemines.fragments;

import static com.troylmarkerenterprises.spacemines.constants.Pref.PREF_PLANETID;
import static com.troylmarkerenterprises.spacemines.constants.Pref.PREF_PLANETNAME;
import static com.troylmarkerenterprises.spacemines.constants.Pref.PREF_SHIPLEVEL;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.troylmarkerenterprises.spacemines.R;
import com.troylmarkerenterprises.spacemines.database.Database;
import com.troylmarkerenterprises.spacemines.model.WorkerModel;

public class Workers extends Fragment {

    String mPlanet;
    int mId;
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
        currentPlanet = view.findViewById (R.id.txtCurrentPlanet);
        inTransitWorkers = view.findViewById (R.id.txtIntTransit);
        recruitment = view.findViewById(R.id.txtRecruitment);
        maxRecruit = view.findViewById(R.id.txtMaxRec);
        dwc = view.findViewById(R.id.d_w_c);
        dsc = view.findViewById(R.id.d_s_c);
        mwc = view.findViewById(R.id.m_w_c);
        msc = view.findViewById(R.id.m_s_c);
        ewc = view.findViewById(R.id.e_w_c);
        esc = view.findViewById(R.id.e_s_c);
        return view;
    }

    @Override
    public void setMenuVisibility(final boolean visible) {
        super.setMenuVisibility(visible);
        if (!visible) {
            currentPlanet.setText(getString(R.string.current_workers, "-------"));
        }else{
            if(db.checkPref(PREF_PLANETNAME)) {
                displayCurrentCounts();
                displayTransitCounts();
                displayRecruitment();
            } else {
                currentPlanet.setText(getString(R.string.current_workers, "-------"));
            }
        }
    }

    private void displayCurrentCounts () {
        mPlanet = db.getPref(PREF_PLANETNAME);
        mId = Integer.parseInt(db.getPref(PREF_PLANETID));
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
        inTransitWorkers.setText(getString(R.string.in_transit_workers, mPlanet));
    }

    private void displayRecruitment () {
        recruitment.setText(getString(R.string.recruitment, mPlanet));
        int shipLevel = Integer.parseInt(db.checkPrefOrCreate(PREF_SHIPLEVEL,"1"));
        int maxRecruitCount = shipLevel * 110;
        maxRecruit.setText(getString(R.string.max_recruitable, String.valueOf(maxRecruitCount)));


    }
}
