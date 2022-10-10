package com.example.cineapp.ui.profile

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.cineapp.BuildConfig
import com.example.cineapp.services.ResourceResponse
import com.example.cineapp.services.Status
import com.example.cineapp.services.instance.ApiInstance
import com.example.cineapp.services.objects.account.Account
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileViewModel(application: Application) : AndroidViewModel(application) {

    /*    private val _text = MutableLiveData<String>().apply {
        value = "This is profile Fragment"
    }
    val text: LiveData<String> = _text*/

    /**
     * Method for create request for get information of the account.
     * @param apiToken is the api key.
     * @return LiveData value.
     */
    fun requestGetProfile(): LiveData<ResourceResponse<Account>> {
        // Create variable mutable of response to request.
        val profileResponse: MutableLiveData<ResourceResponse<Account>> = MutableLiveData()
        ApiInstance.getInstance(getApplication<Application>().applicationContext)
            .getInfoAccount(BuildConfig.API_KEY)
            .enqueue(object : Callback<Account> {
                override fun onResponse(call: Call<Account>, response: Response<Account>) {
                    // Validate if request has been successful.
                    if (response.isSuccessful) {
                        profileResponse.value =
                            ResourceResponse(
                                Status.SUCCESS,
                                response.body(),
                                response.message()
                            )
                    } else {
                        profileResponse.value =
                            ResourceResponse(
                                Status.ERROR,
                                null,
                                "Error al obtener la información del usuario"
                            )
                    }
                }

                override fun onFailure(call: Call<Account>, t: Throwable) {
                    t.printStackTrace()
                    profileResponse.value =
                        ResourceResponse(
                            Status.ERROR,
                            null,
                            t.message ?: "Error al obtener la información del usuario"
                        )
                }
            })

        return profileResponse
    }

}