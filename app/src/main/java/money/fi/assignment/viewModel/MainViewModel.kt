package money.fi.assignment.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Created by Ashutosh Jha on 25,March,2022
 */
class MainViewModel  : ViewModel(){
    val submitButtonStatus = MutableLiveData<Boolean>()
    val panStatus = MutableLiveData<Boolean>()
    val dateStatus = MutableLiveData<Boolean>()
}