/******************************************************************************
 *   Project Name: Space Mines                                                *
 *   Class Name: com.troylmarkerenterprises.spacemines.activities.Main        *
 *   File Name: Main.java                                                     *
 *   File Creation Date: 6/11/2022                                            *
 *   File Creator: troylmarker                                                *
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
package com.troylmarkerenterprises.spacemines.activities;

import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import com.troylmarkerenterprises.spacemines.database.Database;
import com.troylmarkerenterprises.spacemines.R;

public class Main extends FragmentActivity {


    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);
        Database db = new Database (this.getApplicationContext());
        ViewPager2 viewPager = findViewById (R.id.pager);
        viewPager.setAdapter (new com.troylmarkerenterprises.spacemines.adapters.Main(this));
        db.createDatabase ();

        TabLayout tabLayout = findViewById (R.id.sliding_tabs);
        new TabLayoutMediator (tabLayout, viewPager, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText ("Planets");
                    break;
                case 1:
                    tab.setText ("Workers");
                    break;
                case 2:
                    tab.setText ("Mines");
                    break;
                case 3:
                    tab.setText ("Shipping");
                    break;
                case 4:
                    tab.setText ("Housing");
                    break;
                case 5:
                    tab.setText ("Entertainment");
                    break;
            }
        }).attach ();
    }
}