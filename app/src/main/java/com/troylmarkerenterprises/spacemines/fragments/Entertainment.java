/*********************************************************************************************************************************************
 *  Project Name: Space Mines                                                                                                                *
 *  Class Name: com.troylmarkerenterprises.spacemines.fragments.Entertainment                                                                *
 *  File Name: Entertainment.java                                                                                                            *
 *  File Creation Date: 6/19/2022                                                                                                            *
 *  File Creation Time: 15:42:13                                                                                                             *
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

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.troylmarkerenterprises.spacemines.R;

public class Entertainment extends Fragment {

    public static Entertainment newInstance() {
        return new Entertainment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_entertainment, container, false);
        TextView textView = (TextView) view;
        textView.setText(R.string.entertainment_fragment);
        return view;
    }
}