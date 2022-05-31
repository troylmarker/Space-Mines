/******************************************************************************
 *   Project Name: Space Mines                                                *
 *   Class Name: com.troylmarkerenterprises.spacemines.fragments.Planets      *
 *   File Name: Planets.java                                                  *
 *   File Type: Interface file                                                *
 *   File Creation Date: 5/22/2022                                            *
 *   File Creator: tlmarker                                                   *
 *                                                                            *
 *   Copyright 2022  by Troy L Marker Enterprises                             *
 *                                                                            *
 *   Licensed under the Apache License, Version 2.0 (the "License");          *
 *   you may not use this file except in compliance with the License.         *
 *   You may obtain a copy of the License at                                  *
 *                                                                            *
 *       http://www.apache.org/licenses/LICENSE-2.0                           *
 *                                                                            *
 *   Unless required by applicable law or agreed to in writing, software      *
 *   distributed under the License is distributed on an "AS IS" BASIS,        *
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. *
 *   See the License for the specific language governing permissions and      *
 *   limitations under the License.                                           *
 ******************************************************************************/
package com.troylmarkerenterprises.spacemines.fragments;

import static com.troylmarkerenterprises.spacemines.constants.Db.PLANETS_TABLE;
import static com.troylmarkerenterprises.spacemines.constants.Db.PRICING_TABLE;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.troylmarkerenterprises.spacemines.R;
import com.troylmarkerenterprises.spacemines.adapters.GalaxyRVA;
import com.troylmarkerenterprises.spacemines.database.Database;
import com.troylmarkerenterprises.spacemines.helpers.General;
import com.troylmarkerenterprises.spacemines.interfaces.PlanetInterface;
import com.troylmarkerenterprises.spacemines.interfaces.GalaxyInterface;
import com.troylmarkerenterprises.spacemines.model.PlanetModel;
import com.troylmarkerenterprises.spacemines.model.PricingModel;

import java.util.ArrayList;

public class Planets extends Fragment {

    private ArrayList<PlanetModel> mGalaxy;
    private ArrayList<PricingModel> mPricing;

    GalaxyRVA gAdapter;
    RecyclerView planets;
    PlanetInterface planetInterface;
    GalaxyInterface galaxyInterface;

    public static Planets newInstance() {
        return new Planets();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getParentFragmentManager ().setFragmentResultListener ("requestKey", this, new FragmentResultListener () {
            @Override
            public void onFragmentResult (@NonNull String requestKey, @NonNull Bundle result) {
                Bundle results = result;

            }
        });
        loadDatabase();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_planets, container, false);
        planets = view.findViewById(R.id.rvGalaxy);
        planetInterface = new PlanetInterface () {
            @Override
            public void onPlanetChange(int index) {
                Planets.this.displayPlanet (view, mGalaxy.get (index), mPricing.get (index));
            }
        };
        gAdapter = new GalaxyRVA(mGalaxy, requireActivity(), planetInterface, galaxyInterface);
        planets.setAdapter(gAdapter);
        planets.setLayoutManager(new LinearLayoutManager(requireActivity()));
        return view;
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
    }

    public void displayPlanet(@NonNull View view, @NonNull PlanetModel planet, @NonNull PricingModel pricing) {
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
        planetId.setText(String.format("Planet Id: %s", planet.getId()));
        planetName.setText(String.format("Planet Name: %s", planet.getName()));
        planetSize.setText(String.format("Planet Diameter: %s miles", planet.getSize()));
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