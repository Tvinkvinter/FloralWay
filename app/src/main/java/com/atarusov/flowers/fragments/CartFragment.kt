package com.atarusov.flowers.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.atarusov.flowers.adapters.BouquetsInCartAdapter
import com.atarusov.flowers.R
import com.atarusov.flowers.databinding.FragmentCartBinding
import com.atarusov.flowers.viewmodel.BouquetViewModel
import java.util.Calendar


class CartFragment : Fragment() {

    private lateinit var binding: FragmentCartBinding

    private lateinit var viewModel: BouquetViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCartBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[BouquetViewModel::class.java]

        val adapter = BouquetsInCartAdapter(viewModel)
        binding.bouquetsInCartCardsRw.adapter = adapter

        viewModel.bouquetsInCart.observe(viewLifecycleOwner, Observer { bouquets ->
            adapter.bouquets = bouquets
            binding.bottomBarSumTv.text =
                getString(R.string.bottom_bar_sum, bouquets.sumOf { it.price })
            if (bouquets.isEmpty()) {
                binding.emptyCartMessageTv.visibility = View.VISIBLE
                binding.bottomBar.visibility = View.GONE
                binding.bottomBarSumTv.visibility = View.GONE
                binding.bottomBarOrderBtn.visibility = View.GONE
            } else {
                binding.emptyCartMessageTv.visibility = View.GONE
                binding.bottomBar.visibility = View.VISIBLE
                binding.bottomBarSumTv.visibility = View.VISIBLE
                binding.bottomBarOrderBtn.visibility = View.VISIBLE
            }
        })

        viewModel.ordersWithBouquets.observe(viewLifecycleOwner, Observer { })

        binding.bottomBarOrderBtn.setOnClickListener {
            viewModel.makeOrder(Calendar.getInstance().timeInMillis)
        }

        return binding.root
    }
}