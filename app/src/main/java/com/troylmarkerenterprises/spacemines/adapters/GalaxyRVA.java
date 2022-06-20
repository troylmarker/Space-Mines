/*********************************************************************************************************************************************
 *  Project Name: Space Mines                                                                                                                *
 *  Class Name: com.troylmarkerenterprises.spacemines.adapters.GalaxyRVA                                                                     *
 *  File Name: GalaxyRVA.java                                                                                                                *
 *  File Creation Date: 6/19/2022                                                                                                            *
 *  File Creation Time: 14:26:8                                                                                                              *
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
package com.troylmarkerenterprises.spacemines.adapters;

import static com.troylmarkerenterprises.spacemines.constants.Pref.PREF_PLANETNAME;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.troylmarkerenterprises.spacemines.R;
import com.troylmarkerenterprises.spacemines.database.Database;
import com.troylmarkerenterprises.spacemines.interfaces.Galaxy;
import com.troylmarkerenterprises.spacemines.interfaces.Planet;
import com.troylmarkerenterprises.spacemines.model.PlanetModel;

import java.util.List;

public class GalaxyRVA extends RecyclerView.Adapter<com.troylmarkerenterprises.spacemines.viewholder.Galaxy> {
    List<PlanetModel> galaxy;

    Database db;
    Context context;
    Planet listener;
    Galaxy selectedPlanet;

    public GalaxyRVA (List<PlanetModel> galaxy, Context context, Planet listener, Galaxy mCallback) {
        this.galaxy = galaxy;
        this.context = context;
        this.listener = listener;
        this.selectedPlanet = mCallback;
    }

    @NonNull
    @Override
    public com.troylmarkerenterprises.spacemines.viewholder.Galaxy onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View planetView = inflater.inflate(R.layout.list_planet, parent,false);
        return new com.troylmarkerenterprises.spacemines.viewholder.Galaxy(planetView);
    }

    @Override
    public void onBindViewHolder(@NonNull final com.troylmarkerenterprises.spacemines.viewholder.Galaxy viewHolder, final int position) {
        final int index = viewHolder.getAdapterPosition();
        int icon = galaxy.get(position).getIcon();
        int id = galaxy.get(position).getId();
        String name = galaxy.get(position).getName();
        int size = galaxy.get(position).getSize();
        db = new Database(context.getApplicationContext());

        switch(icon) {
            case 1:
                viewHolder.imgPlanetIcon.setImageResource(R.drawable.planet_01);
                break;
            case 2:
                viewHolder.imgPlanetIcon.setImageResource(R.drawable.planet_02);
                break;
            case 3:
                viewHolder.imgPlanetIcon.setImageResource(R.drawable.planet_03);
                break;
            case 4:
                viewHolder.imgPlanetIcon.setImageResource(R.drawable.planet_04);
                break;
            case 5:
                viewHolder.imgPlanetIcon.setImageResource(R.drawable.planet_05);
                break;
            case 6:
                viewHolder.imgPlanetIcon.setImageResource(R.drawable.planet_06);
                break;
            case 7:
                viewHolder.imgPlanetIcon.setImageResource(R.drawable.planet_07);
                break;
            case 8:
                viewHolder.imgPlanetIcon.setImageResource(R.drawable.planet_08);
                break;
            case 9:
                viewHolder.imgPlanetIcon.setImageResource(R.drawable.planet_09);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + icon);
        }
        viewHolder.txtPlanetId.setText(String.format("Plant ID: %s", id));
        viewHolder.txtPlanetName.setText(String.format("Planet Name: %s",name));
        viewHolder.txtPlanetSize.setText(String.format("Planet Diameter: %s miles", size));
        viewHolder.imgPlanetIcon.setOnClickListener(v -> {
            db.setPref(PREF_PLANETNAME, name);
            listener.onPlanetChange(index);
        });
    }
    
    @Override
    public int getItemCount() {
        return galaxy.size();
    }
}