package com.example.taipeizoo.ui.area

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taipeizoo.R
import com.example.taipeizoo.ui.model.PlantInfo
import com.example.taipeizoo.ui.plant.PlantAdapter
import com.example.taipeizoo.ui.plant.PlantPresenter
import kotlinx.android.synthetic.main.area_detail_fragment.*

class AreaDetailInfoFragment : Fragment(), PlantPresenter.View {
    private lateinit var plantAdapter: PlantAdapter
    private var presenter = PlantPresenter()
    private var infoList: MutableList<PlantInfo>? = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.area_detail_fragment, container, false)
        presenter.attachView(this)
        presenter.getListFromApi()

        (activity as AppCompatActivity).supportActionBar?.title = "12313123"
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
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.container, AreaDetailInfoFragment())
            ?.commitNow()
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun notifyDataSetChanged(userList: ArrayList<PlantInfo>) {
        plantAdapter.plantInfoList = userList
        plantAdapter.notifyDataSetChanged()
    }

    override fun showError() {

    }
}