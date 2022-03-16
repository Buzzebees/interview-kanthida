package com.example.buzzebeesassignment.model

import android.util.Log
import androidx.lifecycle.*
import com.example.buzzebeesassignment.Contextor
import com.example.buzzebeesassignment.R
import com.example.buzzebeesassignment.data.CampaignDao
import com.example.buzzebeesassignment.https.HttpManager
import okhttp3.ResponseBody
import org.json.JSONArray
import retrofit2.Call
import retrofit2.Response

class MainViewModel : ViewModel() {

    private val _campaignItems = MutableLiveData<List<CampaignDao>>().apply { value = emptyList() }
    val campaignItems: LiveData<List<CampaignDao>> = _campaignItems

    val showError = MutableLiveData<String>()
    val selectedCampaign = MutableLiveData<CampaignDao>()

    init {
        getAllCampaign()
    }

    private fun getAllCampaign() {
        HttpManager.service.getAllCampaign().enqueue(object: retrofit2.Callback<ResponseBody>{
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if(response.isSuccessful){

                    try {
                        val result = response.body()?.string()!!
                        val campaigns = JSONArray(result)

                        val items = ArrayList<CampaignDao>()
                        for(i in 0 until campaigns.length()){
                            val campaignDao = CampaignDao(BaseJsonObject(campaigns.getJSONObject(i).toString()))
                            items.add(campaignDao)
                        }
                        _campaignItems.postValue(items)
                        Log.e("testApi", _campaignItems.toString())

                    } catch (e: Exception) {
                    }

                } else {
                    val result = response.errorBody()?.string()!!
                    onShowError(result)
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                onShowError(Contextor.getContext().getString(R.string.text_no_internet))
            }
        })
    }

    fun onShowError(string: String) {
        showError.value = string
    }
}