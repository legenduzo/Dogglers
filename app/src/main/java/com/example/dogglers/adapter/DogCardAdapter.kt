/*
* Copyright (C) 2021 The Android Open Source Project.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package com.example.dogglers.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dogglers.R
import com.example.dogglers.const.Layout
import com.example.dogglers.data.DataSource

/**
 * Adapter to inflate the appropriate list item layout and populate the view with information
 * from the appropriate data source
 */
class DogCardAdapter(
    private val context: Context?,
    private val layout: Int
) : RecyclerView.Adapter<DogCardAdapter.DogCardViewHolder>() {

//    Dataset for Recyclerview Adapter initialized to dogs list in Datasource class

    private val dataset = DataSource.dogs

    /**
     * Initialize view elements. Each element is acquired from the Dog Model
     */
    class DogCardViewHolder(view: View?) : RecyclerView.ViewHolder(view!!) {
        val dogImage: ImageView? = view?.findViewById(R.id.dog_image)
        val dogName: TextView? = view?.findViewById(R.id.dog_name)
        val dogAge: TextView? = view?.findViewById(R.id.dog_age)
        val dogHobby: TextView? = view?.findViewById(R.id.dog_hobbies)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogCardViewHolder {

//        To determine what Layout to display, we use an if statement to determine the value
//        of adapterLayout

        val adapterLayout = if (layout == Layout.GRID) {
            LayoutInflater.from(parent.context)
                .inflate(R.layout.grid_list_item, parent, false)
        } else {
            LayoutInflater.from(parent.context)
                .inflate(R.layout.vertical_horizontal_list_item, parent, false)
        }

//        The right Layout is passed into the ViewHolder

        return DogCardViewHolder(adapterLayout)
    }

    /**
    * The getItemCount() determines the size of our dataset
    */
    override fun getItemCount(): Int = dataset.size


    /**
     * onBindViewHolder binds the data to the ViewHolder at the right position, hence, it is
     * important we derive the  correct position for each item of the dataset to be used within this
     * function
     */
    override fun onBindViewHolder(holder: DogCardViewHolder, position: Int) {
        val dog = dataset[position]

//      This variable shortens the context call for the TextViews
        val resources = context?.resources

        holder.dogImage?.setImageResource(dog.imageResourceId)
        holder.dogName?.text = dog.name
        holder.dogAge?.text = resources?.getString(R.string.dog_age, dog.age)
        holder.dogHobby?.text = resources?.getString(R.string.dog_hobbies, dog.hobbies)

    }
}
