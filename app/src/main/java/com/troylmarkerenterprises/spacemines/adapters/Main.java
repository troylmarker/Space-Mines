/***********************************************************************************************************************************************
 *  Project Name: Space Mines                                                                                                                  *
 *  Class Name: com.troylmarkerenterprises.spacemines.adapters.Main                                                                            *
 *  File Name: Main.java                                                                                                                       *
 *  File Creation Date: 7/15/2022                                                                                                              *
 *  File Creation Time: 16:15:10                                                                                                               *
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
package com.troylmarkerenterprises.spacemines.adapters;

import static com.troylmarkerenterprises.spacemines.constants.Pref.PREFERENCE_SHIP_PASSENGER;
import static com.troylmarkerenterprises.spacemines.constants.Pref.PREFERENCE_SHIP_SPEED;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.troylmarkerenterprises.spacemines.database.Prefs;
import com.troylmarkerenterprises.spacemines.fragments.Entertainment;
import com.troylmarkerenterprises.spacemines.fragments.Housing;
import com.troylmarkerenterprises.spacemines.fragments.Mines;
import com.troylmarkerenterprises.spacemines.fragments.Planets;
import com.troylmarkerenterprises.spacemines.fragments.Shipping;
import com.troylmarkerenterprises.spacemines.fragments.Workers;

public class Main extends FragmentStateAdapter {
    final int PAGE_COUNT  = 6;
    int shipPassenger;
    int shipSpeed;
    Prefs prefs;

    public Main(FragmentActivity fa) {
        super(fa);
        prefs = new Prefs(fa.getApplicationContext());
        shipPassenger = Integer.parseInt(prefs.getPrefOrCreate(PREFERENCE_SHIP_PASSENGER,"1"));
        shipSpeed = Integer.parseInt(prefs.getPrefOrCreate(PREFERENCE_SHIP_SPEED, "1"));
    }

    @Override
    public int getItemCount() {
        return PAGE_COUNT;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment returnFragment;
        switch (position) {
            case 0:
                returnFragment = Planets.newInstance();
                break;
            case 1:
                returnFragment = Workers.newInstance();
                break;
            case 2:
                returnFragment = Mines.newInstance();
                break;
            case 3:
                returnFragment = Shipping.newInstance();
                break;
            case 4:
                returnFragment = Housing.newInstance();
                break;
            case 5:
                returnFragment = Entertainment.newInstance();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + position);
        }
        return returnFragment;
    }
}