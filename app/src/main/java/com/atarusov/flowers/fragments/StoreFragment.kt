package com.atarusov.flowers.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.atarusov.flowers.adapters.BouquetsInStoreAdapter
import com.atarusov.flowers.R
import com.atarusov.flowers.databinding.FragmentStoreBinding
import com.atarusov.flowers.model.Bouquet
import com.atarusov.flowers.viewmodel.BouquetViewModel


class StoreFragment : Fragment() {

    private lateinit var binding: FragmentStoreBinding

    private lateinit var viewModel: BouquetViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStoreBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[BouquetViewModel::class.java]

        val adapter = BouquetsInStoreAdapter(requireContext(), viewModel)
        binding.bouquetsCardsRw.adapter = adapter

        viewModel.readAllData.observe(viewLifecycleOwner, Observer { bouquets ->
            adapter.bouquets = bouquets
            if (bouquets.isEmpty()) load_bouquets()
        })

        return binding.root
    }

    fun load_bouquets() {
        val bouquets = listOf(
            Bouquet(
                0,
                description = resources.getStringArray(R.array.bouquet_descriptions)[0],
                photo = R.drawable.bouquet_0,
                price = 790
            ),
            Bouquet(
                0,
                description = resources.getStringArray(R.array.bouquet_descriptions)[1],
                photo = R.drawable.bouquet_1,
                price = 1376
            ),
            Bouquet(
                0,
                description = resources.getStringArray(R.array.bouquet_descriptions)[2],
                photo = R.drawable.bouquet_2,
                price = 2046
            ),
            Bouquet(
                0,
                description = resources.getStringArray(R.array.bouquet_descriptions)[3],
                photo = R.drawable.bouquet_3,
                price = 1650
            ),
            Bouquet(
                0,
                description = resources.getStringArray(R.array.bouquet_descriptions)[4],
                photo = R.drawable.bouquet_4,
                price = 2990
            ),
            Bouquet(
                0,
                description = resources.getStringArray(R.array.bouquet_descriptions)[5],
                photo = R.drawable.bouquet_5,
                price = 3284
            ),
            Bouquet(
                0,
                description = resources.getStringArray(R.array.bouquet_descriptions)[6],
                photo = R.drawable.bouquet_6,
                price = 2064
            ),
            Bouquet(
                0,
                description = resources.getStringArray(R.array.bouquet_descriptions)[7],
                photo = R.drawable.bouquet_7,
                price = 1500
            )
        )
        for (bouquet in bouquets) {
            viewModel.addBouquet(bouquet)
        }
    }
}