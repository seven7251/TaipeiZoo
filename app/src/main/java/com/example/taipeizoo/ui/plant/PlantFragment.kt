package com.example.taipeizoo.ui.plant

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.taipeizoo.R
import com.example.taipeizoo.ui.model.PlantInfo
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.plant_fragment.*

class PlantFragment : Fragment() {
    private var presenter = PlantPresenter()
    private var plantInfoList: MutableList<PlantInfo>? = ArrayList()
    lateinit var currentPlantInfo: PlantInfo

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        getCurrentPlantInfo()
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity).supportActionBar?.title = currentPlantInfo.F_Name_En

        val view = inflater.inflate(R.layout.plant_fragment, container, false)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        updateViewAndData();
    }

    override fun onDestroyView() {
        super.onDestroyView()
        plantInfoList = null
        presenter.detachView()
    }

    fun updateViewAndData() {
        plant_name_ch.setText(currentPlantInfo.F_Name_Ch)
        plant_name_en.setText(currentPlantInfo.F_Name_En)
        plant_also_know.text = currentPlantInfo.F_AlsoKnown
        plant_brief.setText(currentPlantInfo.F_Brief)
        plant_feature.setText(currentPlantInfo.F_Feature)
        plant_function.setText(currentPlantInfo.F_FunctionApplication)

        Picasso.get().load(currentPlantInfo.F_Pic01_URL).into(plant_img)
    }

    private fun getCurrentPlantInfo() {
        currentPlantInfo = arguments?.getParcelable("plantInfo")!!
    }
}