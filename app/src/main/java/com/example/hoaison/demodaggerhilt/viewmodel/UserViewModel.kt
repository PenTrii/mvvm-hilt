package com.example.hoaison.demodaggerhilt.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hoaison.demodaggerhilt.data.OperationCallback
import com.example.hoaison.demodaggerhilt.model.User
import com.example.hoaison.demodaggerhilt.repository.UserRepository
import com.example.hoaison.demodaggerhilt.utils.DataResource
import com.example.hoaison.demodaggerhilt.utils.MyCoroutine
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val repository: UserRepository) : ViewModel() {

    private val _userListResponse = MutableLiveData<DataResource<List<User>>>()
    val userListResponse: LiveData<DataResource<List<User>>>
        get() = _userListResponse

    fun getUserList(token: String, page: Int?, limit: Int?) {
        MyCoroutine.main {
            _userListResponse.postValue(DataResource.loading())
            repository.getUserList(object : OperationCallback<User> {
                override fun onSuccess(data: List<User>?) {
                    _userListResponse.postValue(DataResource.success(data!!))
                }

                override fun onError(error: String?) {
                    _userListResponse.postValue(DataResource.error(null, error!!))
                }
            }, token, page, limit)
        }
    }
}