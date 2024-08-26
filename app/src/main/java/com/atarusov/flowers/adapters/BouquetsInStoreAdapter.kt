package com.atarusov.flowers.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.atarusov.flowers.R
import com.atarusov.flowers.databinding.BouquetStoreCardBinding
import com.atarusov.flowers.model.Bouquet
import com.atarusov.flowers.viewmodel.BouquetViewModel

class BouquetsInStoreAdapter(
    private val context: Context,
    private val bouquetViewModel: BouquetViewModel
) :
    RecyclerView.Adapter<BouquetsInStoreAdapter.BouquetsViewHolder>() {

    var bouquets: List<Bouquet> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BouquetsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = BouquetStoreCardBinding.inflate(inflater, parent, false)
        return BouquetsViewHolder(binding)
    }

    override fun getItemCount(): Int = bouquets.size

    override fun onBindViewHolder(holder: BouquetsViewHolder, position: Int) {
        val bouquet = bouquets[position]

        holder.binding.bouquetImg.setImageResource(bouquet.photo)
        holder.binding.bouquetDescriptionTv.text = bouquet.description
        holder.binding.bouquetPriceTv.text = context.getString(R.string.price_format, bouquet.price)

        if (bouquet.inCart) {
            holder.binding.bottomBarOrderBtn.visibility = View.GONE
            holder.binding.inCartButton.visibility = View.VISIBLE
        } else {
            holder.binding.bottomBarOrderBtn.visibility = View.VISIBLE
            holder.binding.inCartButton.visibility = View.GONE
        }

        holder.binding.bottomBarOrderBtn.setOnClickListener {
            it.visibility = View.GONE
            holder.binding.inCartButton.visibility = View.VISIBLE
            bouquetViewModel.putBouquetToCart(bouquet)
        }

        holder.binding.inCartButton.setOnClickListener {
            it.visibility = View.GONE
            holder.itemView.findViewById<Button>(R.id.bottom_bar_order_btn).visibility =
                View.VISIBLE
            bouquetViewModel.removeBouquetFromCart(bouquet)
        }
    }

    class BouquetsViewHolder(
        val binding: BouquetStoreCardBinding
    ) : RecyclerView.ViewHolder(binding.root)
}