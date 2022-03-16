package com.example.buzzebeesassignment.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.example.buzzebeesassignment.R
import com.example.buzzebeesassignment.model.MainViewModel
import kotlinx.android.synthetic.main.fragment_description.*

class DescriptionFragment: Fragment() {

    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_description, container, false)
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

        textName.text = viewModel.selectedCampaign.value!!.name
        textPrice.text = viewModel.selectedCampaign.value!!.price
        textDescription.text = viewModel.selectedCampaign.value!!.description

        imageBack.setOnClickListener {
            activity!!.supportFragmentManager.beginTransaction().remove(this).commit()
        }
    }
}