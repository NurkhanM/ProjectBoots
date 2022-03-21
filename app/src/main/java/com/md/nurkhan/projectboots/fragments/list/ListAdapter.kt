package com.md.nurkhan.projectboots.fragments.list

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.md.nurkhan.projectboots.IClickListnear
import com.md.nurkhan.projectboots.R
import com.md.nurkhan.projectboots.model.User
import kotlinx.android.synthetic.main.button_sheet_layout.view.*
import kotlinx.android.synthetic.main.castom_row.*
import kotlinx.android.synthetic.main.castom_row.view.*
import kotlinx.android.synthetic.main.fragment_add.view.*
import kotlin.coroutines.coroutineContext


class ListAdapter(private val onClickListener: IClickListnear) : RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private lateinit var botto: BottomSheetBehavior<LinearLayout>

    private var userList = emptyList<User>()


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.castom_row, parent, false)
        )

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = userList[position]
        holder.itemView.txtList.text = currentItem.name
        holder.itemView.txtList2.text = currentItem.name2.toString()
        holder.itemView.txtList3.text = currentItem.name3
        holder.itemView.txtList4.text = currentItem.name4.toString()
        holder.itemView.imgAdd.load(currentItem.profilePhoto)

        holder.itemView.rowCostom.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
        holder.itemView.mMenu.setOnClickListener {
            onClickListener.clickListener()
        }

    }

        override fun getItemCount(): Int {
            return userList.size
        }

        @SuppressLint("NotifyDataSetChanged")
        fun setData(user: List<User>) {
            this.userList = user
            notifyDataSetChanged()
        }

}