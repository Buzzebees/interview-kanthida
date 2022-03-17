package com.example.buzzebeesassignment.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.buzzebeesassignment.R
import com.example.buzzebeesassignment.adapter.CampaignListAdapter
import com.example.buzzebeesassignment.data.CampaignDao
import com.example.buzzebeesassignment.databinding.FragmentMainBinding
import com.example.buzzebeesassignment.model.MainViewModel
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment() {

    private val viewModel: MainViewModel by activityViewModels()

    private lateinit var campaignAdapter: CampaignListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentMainBinding = DataBindingUtil.inflate(inflater
            , R.layout.fragment_main, container, false)
        binding.viewmodel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setView()
        setEvent()
    }

    private fun setView() {
        rvCampaign.layoutManager = GridLayoutManager(activity, 2)
        campaignAdapter = CampaignListAdapter(object : CampaignListAdapter.CallBack{
            override fun onClickCampaign(campaign: CampaignDao) {
                viewModel.selectedCampaign.value = campaign
            }
        })
        rvCampaign.adapter = campaignAdapter
    }

    private fun setEvent(){
        viewModel.campaignItems.observe(viewLifecycleOwner, Observer { campaigns ->
            (rvCampaign.adapter as? CampaignListAdapter)?.submitList(campaigns)
        })
        viewModel.showError.observe(this, Observer {
            textError.text = it
            llLoadingError.visibility = View.VISIBLE
            rvCampaign.visibility = View.GONE
        })
        viewModel.isLoading.observe(this, Observer {
            llLoadingError.visibility = View.GONE
            progressBar.visibility = if (it) View.VISIBLE else View.GONE
            rvCampaign.visibility = if (it) View.GONE else View.VISIBLE
        })
    }
}