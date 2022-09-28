package com.example.taipeizoo.ui.plant

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taipeizoo.R
import com.example.taipeizoo.ui.plant.PlantPresenter
import com.example.taipeizoo.ui.model.PlantInfo

class PlantFragment : Fragment(), PlantPresenter.View {
    private lateinit var plantAdapter: PlantAdapter
    private var presenter = PlantPresenter()
    private var plantInfoList: MutableList<PlantInfo>? = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.area_fragment, container, false)
        presenter.attachView(this)

        if (plantInfoList == null) {
            presenter.getListFromApi()
        }

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //setupRecyclerView()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        plantInfoList = null
        presenter.detachView()
    }

    override fun onItemClick(plantInfo: PlantInfo) {
        TODO("Not yet implemented")
    }

//    private fun setupRecyclerView() {
//        area_recyclerview.layoutManager = LinearLayoutManager(context)
//        plantAdapter = PlantAdapter(
//            plantInfoList as ArrayList<PlantInfo>,
//            AreaItemListener()
//        )
//        area_recyclerview.adapter = areaAdapter;
//    }

//    class PlantItemListener : PlantAdapter.Listener {
//        override fun onClickItem(plantInfo: PlantInfo) {
//        }
//    }

    @SuppressLint("NotifyDataSetChanged")
    override fun notifyDataSetChanged(userList: ArrayList<PlantInfo>) {
        plantAdapter.plantInfoList = userList
        plantAdapter.notifyDataSetChanged()
//        errorContainer.visibility = View.GONE
    }

    override fun showError() {

    }
}