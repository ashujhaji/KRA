package money.fi.assignment

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import money.fi.assignment.databinding.ActivityMainBinding
import money.fi.assignment.viewModel.MainViewModel
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Pattern

class MainActivity : AppCompatActivity(), View.OnClickListener, TextWatcher {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var mViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        mViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        setContentView(binding.root)


        //set listener
        binding.noPanText.setOnClickListener(this)
        binding.submitBtn.setOnClickListener(this)
        binding.pan.addTextChangedListener(this)
        binding.date.addTextChangedListener(this)
        binding.month.addTextChangedListener(this)
        binding.year.addTextChangedListener(this)
        binding.date.setOnClickListener(this)


        //Live data observer
        mViewModel.submitButtonStatus.observe(this, {
            binding.submitBtn.isEnabled = it
        })

        mViewModel.panStatus.observe(this, {
            val status = it && mViewModel.dateStatus.value != null && mViewModel.dateStatus.value!!
            mViewModel.submitButtonStatus.postValue(status)
        })

        mViewModel.dateStatus.observe(this, {
            val status = it && mViewModel.panStatus.value != null && mViewModel.panStatus.value!!
            mViewModel.submitButtonStatus.postValue(status)
        })

    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.no_pan_text -> {
                finishAffinity()
            }
            R.id.submitBtn -> {
                if (mViewModel.submitButtonStatus.value == null) {
                    return
                }
                if (mViewModel.submitButtonStatus.value == false) {
                    //values are incorrect

                    return
                }
                Toast.makeText(this, "Details submitted successfully", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

    }

    override fun afterTextChanged(s: Editable?) {
        when (s!!.hashCode()) {
            binding.pan.text.hashCode() -> {
                if (!binding.pan.text.isNullOrEmpty()) {
                    val sPattern: Pattern = Pattern.compile("([A-Z]{5}[0-9]{4}[A-Z]{1})")
                    val isValidPan = sPattern.matcher(binding.pan.text.toString())
                        .matches() && binding.pan.text.toString().length == 10
                    mViewModel.panStatus.postValue(isValidPan)
                } else {
                    mViewModel.panStatus.postValue(false)
                }
            }
            binding.date.text.hashCode() -> {
                if (!binding.date.text.isNullOrEmpty()) {
                    if (binding.date.text?.length == 2) {
                        binding.date.clearFocus()
                        binding.month.requestFocus()

                    }
                }
            }
            binding.month.text.hashCode() -> {
                if (!binding.month.text.isNullOrEmpty()) {
                    if (binding.month.text?.length == 2) {
                        binding.month.clearFocus()
                        binding.year.requestFocus()
                    }
                } else {
                    binding.month.clearFocus()
                    binding.date.requestFocus()
                }
            }
            binding.year.text.hashCode() -> {
                if (!binding.year.text.isNullOrEmpty()) {
                    if (binding.year.text?.length == 4) {
                        this.currentFocus?.let { view ->
                            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
                            imm?.hideSoftInputFromWindow(view.windowToken, 0)
                        }
                    }
                    val sPattern: Pattern = Pattern.compile("([0-9]{2}[0-9]{2}[0-9]{4})")
                    val date = "${binding.date.text.toString()}${binding.month.text.toString()}${binding.year.text.toString()}"
                    val isValidDob = sPattern.matcher(date).matches() && isValidDate(date)
                    mViewModel.dateStatus.postValue(isValidDob)
                } else {
                    binding.year.clearFocus()
                    binding.month.requestFocus()
                }
            }
        }
    }

    private fun isValidDate(date: String): Boolean {
        val formatter: DateFormat = SimpleDateFormat("ddMMyyyy")
        val dateStr: Date = formatter.parse(date) as Date
        Log.d("prevTag","prev ${dateStr.time} curr ${System.currentTimeMillis()/1000}")
        return dateStr.time < System.currentTimeMillis()
    }
}