package com.project.organicoutlet.ui.recyclerview.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.project.organicoutlet.R
import com.project.organicoutlet.databinding.ProductItemBinding
import com.project.organicoutlet.model.Product
import org.w3c.dom.Text

class ProductsListAdapter(private val products: List<Product>) : RecyclerView.Adapter<ProductsListAdapter.ViewHolder>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(item: Product) {
            val name: TextView = itemView.findViewById(R.id.txt_product_name)
            name.text = item.name
            val description: TextView = itemView.findViewById(R.id.txt_description)
            description.text = item.description
            val price: TextView = itemView.findViewById(R.id.txt_price)
            price.text = item.price.toPlainString()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ProductItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = products[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return products.size
    }

}
