package com.example.taipeizoo.ui.area

import android.util.Log
import com.example.taipeizoo.ApiService
import com.example.taipeizoo.ui.BasePresenter
import com.example.taipeizoo.ui.model.AreaInfo
import com.example.taipeizoo.ui.model.AreaResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AreaPresenter : BasePresenter<AreaPresenter.View>() {
    private val TAG = AreaPresenter::class.java.simpleName

    private val apiServiceInterface = ApiService.create()

    fun getListFromApi() {
        val call = apiServiceInterface.getAreaInfo()
        call.enqueue(object : Callback<AreaResponse> {
            override fun onFailure(call: Call<AreaResponse>, t: Throwable) {
                Log.d(TAG, "onFailure: " + t.message)
                view?.showError()
            }

            override fun onResponse(
                call: Call<AreaResponse>,
                response: Response<AreaResponse>
            ) {
                response.body()?.result?.results?.let { view?.notifyDataSetChanged(it) }
                Log.i(TAG, "onResponse: success")
            }
        })
    }

    interface View {
        fun notifyDataSetChanged(userList: ArrayList<AreaInfo>)
        fun showError()
    }
}