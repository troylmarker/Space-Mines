/*********************************************************************************************************************************************
 *  Project Name: Space Mines                                                                                                                *
 *  Class Name: com.troylmarkerenterprises.spacemines.fragments.Planets                                                                      *
 *  File Name: Planets.java                                                                                                                  *
 *  File Creation Date: 6/19/2022                                                                                                            *
 *  File Creation Time: 15:53:48                                                                                                             *
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

import static com.troylmarkerenterprises.spacemines.constants.Db.PLANETS_TABLE;
import static com.troylmarkerenterprises.spacemines.constants.Db.PRICING_TABLE;
import static com.troylmarkerenterprises.spacemines.constants.Db.WORKER_TABLE;
import static com.troylmarkerenterprises.spacemines.constants.Pref.PREF_PLANETID;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.troylmarkerenterprises.spacemines.R;
import com.troylmarkerenterprises.spacemines.adapters.GalaxyRVA;
import com.troylmarkerenterprises.spacemines.database.Database;
import com.troylmarkerenterprises.spacemines.helpers.General;
import com.troylmarkerenterprises.spacemines.interfaces.Planet;
import com.troylmarkerenterprises.spacemines.interfaces.Galaxy;
import com.troylmarkerenterprises.spacemines.model.PlanetModel;
import com.troylmarkerenterprises.spacemines.model.PricingModel;
import com.troylmarkerenterprises.spacemines.model.WorkerModel;

import java.util.ArrayList;

@SuppressWarnings("unused")
public class Planets extends Fragment {

    private ArrayList<PlanetModel> mGalaxy;
    private ArrayList<PricingModel> mPricing;
    private ArrayList<WorkerModel> mWorker;

    GalaxyRVA gAdapter;
    RecyclerView planets;
    Galaxy galaxyInterface;
    Planet planet;
    int Index;

    public static Planets newInstance() {
        return new Planets();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        getParentFragmentManager ().setFragmentResultListener ("requestKey", this, (requestKey, result) -> {
            Bundle results = result;
        });
        loadDatabase();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_planets, container, false);
        planets = view.findViewById(R.id.rvGalaxy);
        if (savedInstanceState != null) {
            Index = savedInstanceState.getInt(PREF_PLANETID);
            Planets.this.displayPlanet (view, mGalaxy.get (Index), mPricing.get (Index), mWorker.get(Index));
        }

        planet = index -> {
            Index = index;
            Planets.this.displayPlanet (view, mGalaxy.get (Index), mPricing.get (Index) , mWorker.get(Index));
        };
        gAdapter = new GalaxyRVA(mGalaxy, requireActivity(), planet, galaxyInterface);
        planets.setAdapter(gAdapter);
        planets.setLayoutManager(new LinearLayoutManager(requireActivity()));
        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt(PREF_PLANETID, Index);
    }

    private void loadDatabase() {
        Database db = new Database(requireActivity().getApplicationContext());
        if (db.doesTableExist(PLANETS_TABLE)) {
            mGalaxy = db.loadGalaxy();
        } else {
            mGalaxy = null;
        }
        if (db.doesTableExist (PRICING_TABLE)) {
            mPricing = db.loadPricing ();
        } else {
            mPricing = null;
        }
        if (db.doesTableExist(WORKER_TABLE)) {
            mWorker = db.loadWorkers();
        } else {
            mWorker = null;
        }
    }

    public void displayPlanet(@NonNull View view, @NonNull PlanetModel planet, @NonNull PricingModel pricing, WorkerModel workers) {
        General gh = new General();
        TextView planetId = view.findViewById(R.id.planetId);
        TextView planetName = view.findViewById(R.id.planetName);
        TextView planetSize = view.findViewById(R.id.planetSize);
        TextView cuMass = view.findViewById(R.id.cuMass);
        TextView cuValue = view.findViewById(R.id.cuValue);
        TextView agMass = view.findViewById(R.id.agMass);
        TextView agValue = view.findViewById(R.id.agValue);
        TextView auMass = view.findViewById(R.id.auMass);
        TextView auValue = view.findViewById(R.id.auValue);
        TextView ptMass = view.findViewById(R.id.ptMass);
        TextView ptValue = view.findViewById(R.id.ptValue);
        TextView pdMass = view.findViewById(R.id.pdMass);
        TextView pdValue = view.findViewById(R.id.pdValue);
        TextView cuMMass = view.findViewById(R.id.cuMMass);
        TextView cuMValue = view.findViewById(R.id.cuMValue);
        TextView agMMass = view.findViewById(R.id.agMMass);
        TextView agMValue = view.findViewById(R.id.agMValue);
        TextView auMMass = view.findViewById(R.id.auMMass);
        TextView auMValue = view.findViewById(R.id.auMValue);
        TextView ptMMass = view.findViewById(R.id.ptMMass);
        TextView ptMValue = view.findViewById(R.id.ptMValue);
        TextView pdMMass = view.findViewById(R.id.pdMMass);
        TextView pdMValue = view.findViewById(R.id.pdMValue);
        TextView cuRMass = view.findViewById(R.id.cuRMass);
        TextView cuRValue = view.findViewById(R.id.cuRValue);
        TextView agRMass = view.findViewById(R.id.agRMass);
        TextView agRValue = view.findViewById(R.id.agRValue);
        TextView auRMass = view.findViewById(R.id.auRMass);
        TextView auRValue = view.findViewById(R.id.auRValue);
        TextView ptRMass = view.findViewById(R.id.ptRMass);
        TextView ptRValue = view.findViewById(R.id.ptRValue);
        TextView pdRMass = view.findViewById(R.id.pdRMass);
        TextView pdRValue = view.findViewById(R.id.pdRValue);
        TextView minerWC = view.findViewById(R.id.d_w_c);
        TextView minerSC = view.findViewById(R.id.d_s_c);
        TextView maintWC = view.findViewById(R.id.m_w_c);
        TextView maintSC = view.findViewById(R.id.m_s_c);
        TextView enterWC = view.findViewById(R.id.e_w_c);
        TextView enterSC = view.findViewById(R.id.e_s_c);
        planetId.setText(String.format("%s", planet.getId()));
        planetName.setText(String.format("%s", planet.getName()));
        planetSize.setText(String.format("%s miles", planet.getSize()));
        minerWC.setText(String.valueOf(workers.getMiners()));
        minerSC.setText(String.valueOf(workers.getMiners_supervisor()));
        maintWC.setText(String.valueOf(workers.getMaintenance()));
        maintSC.setText(String.valueOf(workers.getMaintenance_supervisor()));
        enterWC.setText(String.valueOf(workers.getEntertain()));
        enterSC.setText(String.valueOf(workers.getEntertain_supervisor()));
        cuMass.setText(gh.getNamedNumber(planet.getT_CU()));
        cuValue.setText(String.format("$%s", gh.getNamedNumber(planet.getT_CU() * (pricing.getCu () * 36000d))));
        agMass.setText(gh.getNamedNumber(planet.getT_AG()));
        agValue.setText(String.format("$%s", gh.getNamedNumber(planet.getT_AG() * (pricing.getAg () * 36000d))));
        auMass.setText(gh.getNamedNumber(planet.getT_AU()));
        auValue.setText(String.format("$%s", gh.getNamedNumber(planet.getT_AU() * (pricing.getAu () * 36000d))));
        ptMass.setText(gh.getNamedNumber(planet.getT_PT()));
        ptValue.setText(String.format("$%s", gh.getNamedNumber(planet.getT_PT() * (pricing.getPt () * 36000d))));
        pdMass.setText(gh.getNamedNumber(planet.getT_PD()));
        pdValue.setText(String.format("$%s", gh.getNamedNumber(planet.getT_PD() * (pricing.getPd () * 36000d))));
        cuMMass.setText(gh.getNamedNumber(planet.getM_CU()));
        cuMValue.setText(String.format("$%s", gh.getNamedNumber(planet.getM_CU() * (pricing.getCu () * 36000d))));
        agMMass.setText(gh.getNamedNumber(planet.getM_AG()));
        agMValue.setText(String.format("$%s", gh.getNamedNumber(planet.getM_AG() * (pricing.getAg () * 36000d))));
        auMMass.setText(gh.getNamedNumber(planet.getM_AU()));
        auMValue.setText(String.format("$%s", gh.getNamedNumber(planet.getM_AU() * (pricing.getAu () * 36000d))));
        ptMMass.setText(gh.getNamedNumber(planet.getM_PT()));
        ptMValue.setText(String.format("$%s", gh.getNamedNumber(planet.getM_PT() * (pricing.getPt () * 36000d))));
        pdMMass.setText(gh.getNamedNumber(planet.getM_PD()));
        pdMValue.setText(String.format("$%s", gh.getNamedNumber(planet.getM_PD() * (pricing.getPd () * 36000d))));
        double r_cu = planet.getT_CU () - planet.getM_CU ();
        cuRMass.setText(gh.getNamedNumber(r_cu));
        cuRValue.setText(String.format("$%s", gh.getNamedNumber(r_cu * (pricing.getCu () * 36000d))));
        double r_ag = planet.getT_AG () - planet.getM_AG ();
        agRMass.setText(gh.getNamedNumber(r_ag));
        agRValue.setText(String.format("$%s", gh.getNamedNumber(r_ag * (pricing.getAg () * 36000d))));
        double r_au = planet.getT_AU () - planet.getM_AU ();
        auRMass.setText(gh.getNamedNumber(r_au));
        auRValue.setText(String.format("$%s", gh.getNamedNumber(r_au * (pricing.getAu () * 36000d))));
        double r_pt = planet.getT_PT() - planet.getM_PT ();
        ptRMass.setText(gh.getNamedNumber(r_pt));
        ptRValue.setText(String.format("$%s", gh.getNamedNumber(r_pt * (pricing.getPt () * 36000d))));
        double r_pd = planet.getT_PD() - planet.getM_PD ();
        pdRMass.setText(gh.getNamedNumber(r_pd));
        pdRValue.setText(String.format("$%s", gh.getNamedNumber(r_pd * (pricing.getPd () * 36000d))));
    }
}