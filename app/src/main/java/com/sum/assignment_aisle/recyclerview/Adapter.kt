package com.sum.assignment_aisle.recyclerview

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.sum.assignment_aisle.R
import com.sum.assignment_aisle.databinding.RecyclerviewItemBinding
import com.sum.assignment_aisle.model.notesmodel.Invites
import com.sum.assignment_aisle.model.notesmodel.Likes
import com.sum.assignment_aisle.model.notesmodel.Profile

class MyAdapter(var invites: Invites, var likes: Likes): RecyclerView.Adapter<MyAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: RecyclerviewItemBinding = DataBindingUtil.inflate(layoutInflater, R.layout.recyclerview_item, parent, false)
        Log.i("TAG", "onCreateViewHolder: In adapter class")
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val invite = invites.profiles[position]
        val like = likes.profiles[position]
        holder.bind(invites, likes)
        Log.i("TAG", "onBindViewHolder: entered")
    }


    override fun getItemCount(): Int {
        return 0
    }

    class MyViewHolder( var itemBinding:RecyclerviewItemBinding ) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(invite: Invites, like: Likes) {
            val profile = invite.profiles[0]
            itemBinding.name.text = like.profiles[0].first_name
            Log.i("TAG3", "bind: "+like.profiles[0].first_name + " "+profile.general_information.age.toString())
            itemBinding.age.text = profile.general_information.age.toString()
            val url = profile.photos[0].photo
            Picasso.get().load(url).into(itemBinding.image)
        }
    }

}