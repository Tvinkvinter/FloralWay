package com.atarusov.flowers.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.atarusov.flowers.adapters.OrdersAdapter
import com.atarusov.flowers.databinding.FragmentOrdersBinding
import com.atarusov.flowers.viewmodel.BouquetViewModel

class OrdersFragment : Fragment() {

    private lateinit var binding: FragmentOrdersBinding

    private lateinit var viewModel: BouquetViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOrdersBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this)[BouquetViewModel::class.java]

        val adapter = OrdersAdapter(viewModel, requireContext())
        binding.ordersCardsRw.adapter = adapter

        viewModel.ordersWithBouquets.observe(viewLifecycleOwner, Observer { ordersWithBouquets ->
            adapter.ordersWithBouquets = ordersWithBouquets
            if (ordersWithBouquets.isEmpty())
                binding.noOrdersMessageTv.visibility = View.VISIBLE
            else binding.noOrdersMessageTv.visibility = View.GONE
        })

        return binding.root
    }
}