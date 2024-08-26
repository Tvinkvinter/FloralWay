package com.atarusov.flowers.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.atarusov.flowers.R
import com.atarusov.flowers.databinding.OrderCardBinding
import com.atarusov.flowers.model.OrderWithBouquets
import com.atarusov.flowers.viewmodel.BouquetViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class OrdersAdapter(private val bouquetViewModel: BouquetViewModel, private val context: Context) :
    RecyclerView.Adapter<OrdersAdapter.OrdersViewHolder>() {

    var ordersWithBouquets: List<OrderWithBouquets> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrdersViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = OrderCardBinding.inflate(inflater, parent, false)
        return OrdersViewHolder(binding)
    }


    override fun getItemCount(): Int = ordersWithBouquets.size

    override fun onBindViewHolder(holder: OrdersViewHolder, position: Int) {
        holder.binding.orderNumberTv.text =
            context.getString(R.string.order_number, ordersWithBouquets[position].order.orderId)
        val formatter = SimpleDateFormat("hh:mm:ss dd.MM.yyyy", Locale.getDefault())
        val dateString = formatter.format(Date(ordersWithBouquets[position].order.date))
        holder.binding.dateTv.text = dateString
        holder.binding.orderSumTv.text =
            context.getString(R.string.price_format, ordersWithBouquets[position].order.sum)

        val adapter = BouquetsInOrderAdapter()
        adapter.bouquets = ordersWithBouquets[position].bouquets
        holder.binding.bouquetsInOrderRw.adapter = adapter

        holder.binding.cancelOrderTv.setOnClickListener {
            bouquetViewModel.cancelOrder(ordersWithBouquets[position].order)
        }
    }

    class OrdersViewHolder(
        val binding: OrderCardBinding
    ) : RecyclerView.ViewHolder(binding.root)
}