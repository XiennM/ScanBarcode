package com.example.scanbarcode

import android.content.Context
import android.os.Bundle
import android.text.format.DateFormat
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import java.util.UUID

class ProductListFragment: Fragment() {

    interface Callbacks {
        fun onProductSelected(productId: UUID)
    }

    private var callbacks: Callbacks? = null
    private lateinit var productRecyclerView: RecyclerView
    private var adapter: ProductAdapter? = ProductAdapter(emptyList())

    private val productListViewModel: ProductListViewModel by viewModels()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callbacks = context as Callbacks
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_product_list, container, false)

        productRecyclerView = view.findViewById<RecyclerView>(R.id.product_recycler_view)
        productRecyclerView.layoutManager = LinearLayoutManager(context)
        productRecyclerView.adapter = adapter

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        productListViewModel.productListLiveData.observe(
            viewLifecycleOwner,
            Observer {
                    products ->
                products?.let {
                    updateUI(products)
                }
            }
        )
    }

    override fun onDetach() {
        super.onDetach()
        callbacks = null
    }

    private fun updateUI(products: List<Product>){
        adapter = ProductAdapter(products)
        productRecyclerView.adapter = adapter
    }

    private inner class ProductHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {

        private lateinit var product: Product

        private val titleTextView: TextView = itemView.findViewById(R.id.product_title)
        private val dateTextView: TextView = itemView.findViewById(R.id.scan_date)
        private val productImageView: ImageView = itemView.findViewById(R.id.productImage)

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(product: Product){

            this.product = product
            titleTextView.text = this.product.name
            dateTextView.text = DateFormat.format("EEEE, dd MMM, yyyy, hh:mm:ss ", this.product.date)
            Glide.with(this@ProductListFragment)
                .load(product.imageUrl)
                .placeholder(R.drawable.placeholder_image)
                .error(R.drawable.ic_launcher_background)
                .into(productImageView)
        }

        override fun onClick(v: View?) {

            callbacks?.onProductSelected(product.id)
        }
    }

    private inner class ProductAdapter(var products: List<Product>) : RecyclerView.Adapter<ProductHolder>(){

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): ProductHolder {
            val view = layoutInflater.inflate(R.layout.list_item_product, parent, false)

            return ProductHolder(view)
        }

        override fun onBindViewHolder(
            holder: ProductHolder,
            position: Int
        ) {
            val product = products[position]
            holder.bind(product)
        }

        override fun getItemCount() = products.size
    }

    companion object{
        fun newInstance(): ProductListFragment {
            return ProductListFragment()
        }
    }
}