package com.sum.assignment_aisle

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.sum.assignment_aisle.api.NotesApi
import com.sum.assignment_aisle.databinding.FragmentNotesBinding
import com.sum.assignment_aisle.model.notesmodel.Invites
import com.sum.assignment_aisle.model.notesmodel.Likes
import com.sum.assignment_aisle.recyclerview.MyAdapter
import com.sum.assignment_aisle.retrofit.RetrofitClient
import kotlinx.coroutines.launch

class NotesFragment : Fragment() {

    lateinit var binding: FragmentNotesBinding
    private lateinit var adapter: MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_notes, container, false)
        return binding.root
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.bottomNavigationView.menu.getItem(1).isChecked = true


        lifecycleScope.launch{

            val token = arguments?.getString("token")
            lateinit var resultInvites : Invites
            lateinit var resultLikes: Likes

            if (token != null) {
              resultInvites =  RetrofitClient.getInstance().create(NotesApi::class.java).getProfileList(token).invites
              resultLikes =   RetrofitClient.getInstance().create(NotesApi::class.java).getProfileList(token).likes

                val layout = LinearLayoutManager(context)
                binding.recyclerView.layoutManager = layout
                adapter = MyAdapter(resultInvites,resultLikes)
                binding.recyclerView.adapter = adapter

                Log.i("TAG", "invites: " +resultInvites)
                Log.i("TAG", "likes: " +resultLikes)
                Log.i("TAG", "value: "+resultLikes.profiles[0].first_name + " "+resultInvites.profiles[0].general_information.age.toString()+
                " "+ resultInvites.profiles[0].photos[0].photo)

            }
        }

    }


 }