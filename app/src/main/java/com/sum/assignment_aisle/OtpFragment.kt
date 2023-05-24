package com.sum.assignment_aisle

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.lifecycleScope
import com.sum.assignment_aisle.api.OtpApi
import com.sum.assignment_aisle.api.PhoneNumberAPI
import com.sum.assignment_aisle.databinding.FragmentOtpBinding
import com.sum.assignment_aisle.retrofit.RetrofitClient
import kotlinx.coroutines.launch

class OtpFragment : Fragment() {

    private lateinit var binding: FragmentOtpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_otp, container, false)
        binding.number.text = arguments?.getString("phnNumber")
        Log.i("TAG", "onCreateView: arg = "+ arguments?.getString("phnNumber"))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val phoneNum = binding.number.text.toString()

        binding.button.setOnClickListener{
            lifecycleScope.launch {
                if (binding.enterOtp.text != null) {
                    val token = retrofitResponse(phoneNum, binding.enterOtp.text.toString())
                    if (token != null) {
                        passDataAndLaunchNextFrag(token)
                    }
                    else{
                        Toast.makeText(activity,"(token is null) Enter correct OTP",Toast.LENGTH_SHORT).show()
                    }
                }
                else{
                    Toast.makeText(activity,"Please enter the OTP",Toast.LENGTH_SHORT).show()
                }
            }
        }

    }

    private suspend fun retrofitResponse(phnNum : String, otp: String) : String {
      return RetrofitClient.getInstance().create(OtpApi::class.java).getResponse(phnNum,otp).token.toString()
    }

    private fun passDataAndLaunchNextFrag(token: String){
        val bundle = Bundle()
        bundle.putString("token",token)
        val transaction = getFragmentManager()?.beginTransaction()
        val fragmentTwo = NotesFragment()
        fragmentTwo.arguments = bundle
        transaction?.replace(R.id.frameLayout, fragmentTwo)?.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)?.commit()

    }

}