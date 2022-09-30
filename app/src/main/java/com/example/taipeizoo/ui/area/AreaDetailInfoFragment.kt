package com.example.taipeizoo.ui.area

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taipeizoo.R
import com.example.taipeizoo.ui.model.AreaInfo
import com.example.taipeizoo.ui.model.PlantInfo
import com.example.taipeizoo.ui.plant.PlantAdapter
import com.example.taipeizoo.ui.plant.PlantPresenter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.area_detail_fragment.*

class AreaDetailInfoFragment : Fragment(), PlantPresenter.View {
    private lateinit var plantAdapter: PlantAdapter
    private var presenter = PlantPresenter()
    private var infoList: MutableList<PlantInfo>? = ArrayList()
    lateinit var currentAreaInfo : AreaInfo

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        getCurrentAreaInfo()
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity).supportActionBar?.title = currentAreaInfo.e_name

        val view = inflater.inflate(R.layout.area_detail_fragment, container, false)
        presenter.attachView(this)
        presenter.getListFromApi()

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        plant_recyclerview.layoutManager = LinearLayoutManager(context)
        plantAdapter = PlantAdapter(
            infoList as ArrayList<PlantInfo>,
            this
        )
        plant_recyclerview.adapter = plantAdapter;
    }

    override fun onItemClick(plantInfo: PlantInfo) {
        val bundle = bundleOf("plantInfo" to plantInfo)
        findNavController().navigate(R.id.action_area_detail_fragment_to_plant_fragment, bundle)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun updateViewAndData(userList: ArrayList<PlantInfo>) {
        val data: AreaInfo? = arguments?.getParcelable("areaInfo")
        area_detail_info.setText(data?.e_info)
        Picasso.get().load(data?.e_pic_url).into(area_detail_img)

        plantAdapter.plantInfoList = mappingLocation(data as AreaInfo, userList)
        plantAdapter.notifyDataSetChanged()
    }

    private fun mappingLocation(
        areaInfo: AreaInfo,
        plantInfo: ArrayList<PlantInfo>): ArrayList<PlantInfo> {
        val data: ArrayList<PlantInfo> = ArrayList()
        for (p in plantInfo) {
            if (p.F_Location.contains(areaInfo.e_name)) {
                data.add(p)
            }
        }
        return data
    }

    override fun showError() {

    }

    private fun getCurrentAreaInfo() {
        currentAreaInfo = arguments?.getParcelable("areaInfo")!!
    }
}