package com.md.nurkhan.projectboots.fragments.add

import android.app.Activity.RESULT_OK
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.text.TextUtils
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.md.nurkhan.projectboots.R
import com.md.nurkhan.projectboots.model.User
import com.md.nurkhan.projectboots.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*
import kotlinx.android.synthetic.main.my_toolbar.*
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream

class AddFragment : Fragment() {


    lateinit var uri: Uri

    private lateinit var mUserViewModel: UserViewModel

    companion object {

        const val IMAGE_REQUEST_CODE = 100

    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_add, container, false)

        activity?.toolbar_back?.visibility = View.VISIBLE
        activity?.toolbar_image?.visibility = View.GONE
        activity?.txttoolbar?.text = "Добавление"
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        view.imgAdd.setOnClickListener {
            pickImageGallery()
        }
        view.add_btn.setOnClickListener {
            insertDataToDataBase()
        }

        return view
    }

    private fun pickImageGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == IMAGE_REQUEST_CODE && resultCode == RESULT_OK) {
            imgAdd.setImageURI(data?.data)
            uri = Uri.parse(data?.data.toString())
            Log.e(TAG, "onActivityResult: " + data )
            Log.e(TAG, "onActivityResult: 2 " + data?.data )
        }

    }

    private fun insertDataToDataBase() {
        val name = edit.text.toString()
        val name2 = edit2.text
        val name3 = edit3.text.toString()
        val name4 = edit4.text



        if(inputCheck(name, name2, name3, name4)) {
            lifecycleScope.launch {
                val user = User(0, name, Integer.parseInt(name2.toString()), name3, Integer.parseInt(name4.toString()), getBitmap())
                mUserViewModel.addUser(user)
                Toast.makeText(requireContext(), "Uspeshno dobavleno", Toast.LENGTH_LONG).show()
                findNavController().navigate(R.id.action_addFragment_to_listFragment)
            }

        } else{
            Toast.makeText(requireContext(), "Pojalusta zapolnite", Toast.LENGTH_LONG).show()
        }
    }

        private fun inputCheck(name: String, name2: Editable, name3: String, name4: Editable): Boolean{
        return !(TextUtils.isEmpty(name) && name2.isEmpty() && TextUtils.isEmpty(name3) && name4.isEmpty())
    }


    private suspend fun getBitmap(): Bitmap {
        val loading = ImageLoader(requireContext())
        val request = ImageRequest.Builder(requireContext())
            .data(uri)
            .build()
        Log.e(TAG, uri.toString())

        val result  = (loading.execute(request) as SuccessResult).drawable
        return (result as BitmapDrawable).bitmap
    }






}