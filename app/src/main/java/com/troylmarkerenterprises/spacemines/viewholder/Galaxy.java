/***********************************************************************************************************************************************
 *  Project Name: Space Mines                                                                                                                  *
 *  Class Name: com.troylmarkerenterprises.spacemines.viewholder.Galaxy                                                                        *
 *  File Name: Galaxy.java                                                                                                                     *
 *  File Creation Date: 7/15/2022                                                                                                              *
 *  File Creation Time: 18:5:38                                                                                                                *
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
package com.troylmarkerenterprises.spacemines.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.troylmarkerenterprises.spacemines.R;

@SuppressWarnings("unused")
public class Galaxy extends RecyclerView.ViewHolder {
    public final ImageView imgPlanetIcon;
    public final TextView txtPlanetId;
    public final TextView txtPlanetName;
    public final TextView txtPlanetSize;
    public ImageView imgSelect;

    public Galaxy(View itemView) {
        super(itemView);
        imgPlanetIcon = itemView.findViewById(R.id.imgPlanetIcon);
        txtPlanetId = itemView.findViewById(R.id.txtPlanetId);
        txtPlanetName = itemView.findViewById(R.id.txtPlanetName);
        txtPlanetSize = itemView.findViewById(R.id.txtPlanetSize);
    }
}