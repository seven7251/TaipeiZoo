package com.example.taipeizoo.ui.area

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taipeizoo.R
import com.example.taipeizoo.ui.model.AreaInfo
import kotlinx.android.synthetic.main.area_fragment.*

class AreaFragment : Fragment(), AreaPresenter.View {
    private lateinit var areaAdapter: AreaAdapter
    private var presenter = AreaPresenter()
    private var areaInfoList: MutableList<AreaInfo>? = ArrayList()

    private lateinit var progressBar:ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.area_fragment, container, false)
        progressBar = view.findViewById(R.id.progressBar)
        presenter.attachView(this)
        presenter.getListFromApi()

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupRecyclerView()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        areaInfoList = null
        presenter.detachView()
    }

    private fun setupRecyclerView() {
        area_recyclerview.layoutManager = LinearLayoutManager(context)
        areaAdapter = AreaAdapter(
            areaInfoList as ArrayList<AreaInfo>,
            this
        )
        area_recyclerview.adapter = areaAdapter;
    }

    override fun onItemClick(areaInfo: AreaInfo) {
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.container, AreaDetailInfoFragment())
            ?.commitNow()
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun notifyDataSetChanged(userList: ArrayList<AreaInfo>) {
        areaAdapter.areaInfoList = userList
        areaAdapter.notifyDataSetChanged()
        progressBar.visibility = View.GONE
    }

    override fun showError() {

    }
}