package com.project.organicoutlet.ui.recyclerview.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.organicoutlet.databinding.ProductItemBinding
import com.project.organicoutlet.model.Product

class ProductsListAdapter(products: List<Product>) : RecyclerView.Adapter<ProductsListAdapter.ViewHolder>() {

    private val productsMutable = products.toMutableList()
    class ViewHolder(private val binding: ProductItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Product) {
            binding.product = item
            binding.executePendingBindings()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ProductItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = productsMutable[position]
        holder.bind(product)
    }

    override fun getItemCount(): Int {
        return productsMutable.size
    }

    fun update(allProducts: List<Product>) {
        productsMutable.clear()
        productsMutable.addAll(allProducts)
        notifyDataSetChanged()
    }

}
