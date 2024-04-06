package com.project.organicoutlet.ui.recyclerview.adapter

import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.project.organicoutlet.R
import com.project.organicoutlet.databinding.ProductItemBinding
import com.project.organicoutlet.extensions.loadImage
import com.project.organicoutlet.database.Product

class ProductsListAdapter(
    products: List<Product> = emptyList(),
    val onItemClickListener: (product: Product) -> Unit,
) :
    RecyclerView.Adapter<ProductsListAdapter.ViewHolder>() {

    private val productsMutable = products.toMutableList()

    inner class ViewHolder(private val binding: ProductItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private lateinit var product: Product


        fun bind(item: Product) {
            product = item
            binding.product = item


            binding.root.setOnClickListener {
                onItemClickListener(item)
            }

            if (item.image == null) {
                binding.imgProduct.visibility = View.GONE
            } else {
                binding.imgProduct.visibility = View.VISIBLE
                binding.imgProduct.loadImage(item.image)
            }

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
