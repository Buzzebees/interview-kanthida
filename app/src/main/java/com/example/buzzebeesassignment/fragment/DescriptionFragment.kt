package com.example.buzzebeesassignment.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.example.buzzebeesassignment.R
import com.example.buzzebeesassignment.databinding.FragmentDescriptionBinding
import com.example.buzzebeesassignment.model.MainViewModel
import kotlinx.android.synthetic.main.fragment_description.*

class DescriptionFragment: Fragment() {

    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentDescriptionBinding = DataBindingUtil.inflate(inflater
            , R.layout.fragment_description, container, false)
        binding.viewmodel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setView()
    }

    private fun setView() {
        Glide.with(activity!!)
            .load(viewModel.selectedCampaign.value!!.imageUrl)
            .centerCrop()
            .into(imageCampaign)

        imageBack.setOnClickListener {
            activity!!.supportFragmentManager.beginTransaction().remove(this).commit()
        }
    }
}