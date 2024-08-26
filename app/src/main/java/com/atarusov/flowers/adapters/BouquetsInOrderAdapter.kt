package com.atarusov.flowers.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.atarusov.flowers.R
import com.atarusov.flowers.databinding.BouquetInOrderCardBinding
import com.atarusov.flowers.model.Bouquet

class BouquetsInOrderAdapter() :
    RecyclerView.Adapter<BouquetsInOrderAdapter.BouquetsInOrderViewHolder>() {

    var bouquets: List<Bouquet> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BouquetsInOrderViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = BouquetInOrderCardBinding.inflate(inflater, parent, false)
        return BouquetsInOrderViewHolder(binding)
    }

    override fun getItemCount(): Int = bouquets.size

    override fun onBindViewHolder(holder: BouquetsInOrderViewHolder, position: Int) {
        val bouquet = bouquets[position]

        holder.itemView.findViewById<TextView>(R.id.bouquet_price_tv).text =
            bouquet.price.toString().plus(" ₽")

        holder.binding.bouquetImg.setImageResource(bouquet.photo)
    }

    class BouquetsInOrderViewHolder(
        val binding: BouquetInOrderCardBinding
    ) : RecyclerView.ViewHolder(binding.root)
}