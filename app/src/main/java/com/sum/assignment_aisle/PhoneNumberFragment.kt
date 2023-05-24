package com.sum.assignment_aisle

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.lifecycleScope
import com.sum.assignment_aisle.api.PhoneNumberAPI
import com.sum.assignment_aisle.databinding.FragmentPhoneNumberBinding
import com.sum.assignment_aisle.model.PhoneNumberModel
import com.sum.assignment_aisle.retrofit.RetrofitClient
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PhoneNumberFragment : Fragment() {

    private lateinit var binding: FragmentPhoneNumberBinding
    private lateinit var phnNum: StringBuilder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_phone_number, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.button.setOnClickListener {
            lifecycleScope.launch {
                val countryCode:String = binding.countryCode.text.toString()
                val num: String = binding.phoneNum.text.toString()

                 phnNum = StringBuilder().append(countryCode)
                 phnNum.append(num)

                Log.i("TAG", "onCreateView: $phnNum")

                if (phnNum.length == 13) {

                    val status = retrofitResult(phnNum.toString())
                    if(status == true.toString())
                    {
                        passData()
                    }
                    else {
                        Toast.makeText(
                            activity,
                            "Please enter correct number!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    Toast.makeText(
                        activity,
                        "Please enter country code and phone number!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
       }
    }

    private suspend fun retrofitResult(phnNum : String) : String {
        return RetrofitClient.getInstance().create(PhoneNumberAPI::class.java).getStatus(phnNum).status.toString()
//            .enqueue(object : Callback<PhoneNumberModel> {
//                override fun onResponse(
//                    call: Call<PhoneNumberModel>,
//                    response: Response<PhoneNumberModel>
//                ) {
//                    resultStatus = response.body().toString()
//                    Log.i("TAG", "onResponse: $resultStatus")
//                }
//
//                override fun onFailure(call: Call<PhoneNumberModel>, t: Throwable) {
//                    resultStatus = true.toString()
//                    Toast.makeText(
//                        activity,
//                        "API response fail!",
//                        Toast.LENGTH_SHORT
//                    ).show()
//                }
//            })
    }

    private fun passData(){
        val bundle = Bundle()
        bundle.putString("phnNumber",phnNum.toString())
        val transaction = getFragmentManager()?.beginTransaction()
        val fragmentTwo = OtpFragment()
        fragmentTwo.arguments = bundle
        transaction?.replace(R.id.frameLayout, fragmentTwo)?.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)?.commit()

    }

    companion object { }
}