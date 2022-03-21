package com.md.nurkhan.projectboots.fragments.update

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.core.view.drawToBitmap
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.md.nurkhan.projectboots.R
import com.md.nurkhan.projectboots.fragments.add.AddFragment
import com.md.nurkhan.projectboots.model.User
import com.md.nurkhan.projectboots.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.castom_row.*
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*
import kotlinx.android.synthetic.main.my_toolbar.*

class UpdateFragment : Fragment() {



    private val args by navArgs<UpdateFragmentArgs>()

    private lateinit var mUserViewModel: UserViewModel


    companion object {
        const val IMAGE_REQUEST_CODE = 101
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_update, container, false)

        activity?.toolbar_back?.visibility = View.VISIBLE
        activity?.toolbar_image?.visibility = View.GONE
        activity?.toolbar_img_delete?.visibility = View.VISIBLE
        activity?.txttoolbar?.text = "Редатирование"
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        view.edit_first.setText(args.currentUser.name)
        view.edit2_first.setText(args.currentUser.name2.toString())
        view.edit3_first.setText(args.currentUser.name3)
        view.edit4_first.setText(args.currentUser.name4.toString())

        view.imgAddFirst.setOnClickListener {
            pickImageGallery()
        }

        view.update_btn.setOnClickListener {
            updateItem()
        }

        setHasOptionsMenu(true)

        return view
    }

    private fun updateItem(){
        val name = edit_first.text.toString()
        val name2 = Integer.parseInt(edit2_first.text.toString())
        val name3 = edit3_first.text.toString()
        val name4 = Integer.parseInt(edit4_first.text.toString())

        if(inputCheck(name, edit2_first.text, name3, edit4_first.text)){
            // Create User Object
            val updatedUser = User(args.currentUser.id, name, name2, name3, name4, imgAdd.drawToBitmap())
            // Update Current User
            mUserViewModel.updateUser(updatedUser)
            Toast.makeText(requireContext(), "Updated Successfully!", Toast.LENGTH_SHORT).show()
            // Navigate Back
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }else{
            Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_SHORT).show()
        }
    }



    private fun inputCheck(name: String, name2: Editable, name3: String, name4: Editable): Boolean{
        return !(TextUtils.isEmpty(name) && name2.isEmpty() && TextUtils.isEmpty(name3) && name4.isEmpty())
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete){
            deleteUser()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteUser() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("YES"){_,_ ->
            mUserViewModel.deleteUser(args.currentUser)
            Toast.makeText(requireContext(), "Uspeshno: ${args.currentUser.name}",
                Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builder.setNegativeButton("NO"){_, _ ->}
        builder.setTitle("Delete ${args.currentUser.name}?")
        builder.setMessage("Vy Tochno Hotite Udalit ${args.currentUser.name}?")
        builder.create().show()
    }

    private fun pickImageGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, AddFragment.IMAGE_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == AddFragment.IMAGE_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            imgAddFirst.setImageURI(data?.data)
        }

    }


}