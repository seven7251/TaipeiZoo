package com.example.taipeizoo.ui.plant

import android.util.Log
import com.example.taipeizoo.ApiService
import com.example.taipeizoo.ui.BasePresenter
import com.example.taipeizoo.ui.model.PlantInfo
import com.example.taipeizoo.ui.model.PlantResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PlantPresenter : BasePresenter<PlantPresenter.View>() {
    private val TAG = PlantPresenter::class.java.simpleName

    private val apiServiceInterface = ApiService.create()

    fun getListFromApi() {
        val call = apiServiceInterface.getPlantResponse()
        call.enqueue(object : Callback<PlantResponse> {
            override fun onFailure(call: Call<PlantResponse>, t: Throwable) {
                Log.d(TAG, "onFailure: " + t.message)
                view?.showError()
            }

            override fun onResponse(
                call: Call<PlantResponse>,
                response: Response<PlantResponse>
            ) {
                response.body()?.result?.results?.let {
                    view?.updateViewAndData(it)
                }
                Log.i(TAG, "onResponse: success")
            }
        })
    }

    interface View {
        fun onItemClick(plantInfo: PlantInfo)
        fun updateViewAndData(userList: ArrayList<PlantInfo>)
        fun showError()
    }
}