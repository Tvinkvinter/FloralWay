package com.atarusov.flowers.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.atarusov.flowers.R
import com.atarusov.flowers.databinding.BouquetCartCardBinding
import com.atarusov.flowers.model.Bouquet
import com.atarusov.flowers.viewmodel.BouquetViewModel

class BouquetsInCartAdapter(private val bouquetViewModel: BouquetViewModel) :
    RecyclerView.Adapter<BouquetsInCartAdapter.BouquetsInCartViewHolder>() {

    var bouquets: List<Bouquet> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BouquetsInCartViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = BouquetCartCardBinding.inflate(inflater, parent, false)
        return BouquetsInCartViewHolder(binding)
    }

    override fun getItemCount(): Int = bouquets.size

    override fun onBindViewHolder(holder: BouquetsInCartViewHolder, position: Int) {
        val bouquet = bouquets[position]

        holder.itemView.findViewById<ImageView>(R.id.bouquet_img).setImageResource(bouquet.photo)
        holder.itemView.findViewById<TextView>(R.id.bouquet_description_tv).text =
            bouquet.description
        holder.itemView.findViewById<TextView>(R.id.bouquet_price_tv).text =
            bouquet.price.toString().plus(" ₽")

        holder.binding.removeBtn.setOnClickListener {
            bouquetViewModel.removeBouquetFromCart(bouquet)
        }
    }

    class BouquetsInCartViewHolder(
        val binding: BouquetCartCardBinding
    ) : RecyclerView.ViewHolder(binding.root)
}