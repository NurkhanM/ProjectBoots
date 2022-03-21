package com.md.nurkhan.projectboots.fragments.list

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Bundle
import android.view.*
import androidx.compose.ui.graphics.ImageBitmap
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.md.nurkhan.projectboots.IClickListnear
import com.md.nurkhan.projectboots.R
import com.md.nurkhan.projectboots.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.castom_row.*
import kotlinx.android.synthetic.main.fragment_list.view.*
import kotlinx.android.synthetic.main.my_toolbar.*

class ListFragment : Fragment() {


    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_list, container, false)

        activity?.toolbar_back?.visibility = View.GONE
        activity?.toolbar_image?.visibility = View.VISIBLE
        activity?.toolbar_img_delete?.visibility = View.GONE
        activity?.txttoolbar?.text = "Главная"
        val adapter = ListAdapter(object : IClickListnear{
            @SuppressLint("InflateParams")
            override fun clickListener() {
            val vieww: View = layoutInflater.inflate(R.layout.button_sheet_layout, null)
            val dialog = BottomSheetDialog(requireContext())
            dialog.setContentView(vieww)
            dialog.show()
            }

        })
        val recyclerView = view.recyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(requireContext(),2)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        mUserViewModel.readAllData.observe(viewLifecycleOwner, Observer { user ->
            adapter.setData(user)
        })


        view.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

        setHasOptionsMenu(true)

        return view
    }


// Ne udalya nujnyi kod


//    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        inflater.inflate(R.menu.delete_menu, menu)
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        if (item.itemId == R.id.menu_delete){
//            deleteAllUsers()
//        }
//        return super.onOptionsItemSelected(item)
//    }
//
//    private fun deleteAllUsers() {
//        val builder = AlertDialog.Builder(requireContext())
//        builder.setPositiveButton("YES"){_,_ ->
//            mUserViewModel.deleteAllUsers()
//            Toast.makeText(requireContext(), "Uspeshno udaleno",
//                Toast.LENGTH_SHORT).show()
//        }
//        builder.setNegativeButton("NO"){_, _ ->}
//        builder.setTitle("Delete ALL?")
//        builder.setMessage("Vy Tochno Hotite Udalit ALL?")
//        builder.create().show()
//    }





}