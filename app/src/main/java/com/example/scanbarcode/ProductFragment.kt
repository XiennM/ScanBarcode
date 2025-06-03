package com.example.scanbarcode

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.scanbarcode.databinding.FragmentProductBinding
import com.bumptech.glide.Glide
import java.util.UUID

private const val ARG_PRODUCT_ID = "product_id"

class ProductFragment : Fragment() {

    private var _binding: FragmentProductBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ProductViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val barcode = arguments?.getString("barcode")
        if (!barcode.isNullOrEmpty()) {
            viewModel.saveProduct(barcode)
        }
        else {
            val productId: UUID = arguments?.getSerializable(ARG_PRODUCT_ID) as UUID
            viewModel.loadProduct(productId)
        }

        viewModel.product.observe(viewLifecycleOwner) { product ->
            if (product != null) {
                binding.productName.text = product.name
                binding.productBrand.text = product.brands
                binding.productIngredients.text = product.ingredients
                binding.productQuantity.text = product.quantity
                binding.productCategories.text = product.categoriesTags
                binding.productAllergens.text = product.allergensTags

                if(product.isVegan.isNullOrEmpty())
                    binding.productVegetarian.visibility = View.GONE
                else
                    binding.productVegetarian.text = product.isVegeterian

                if(product.isVegan.isNullOrEmpty())
                    binding.productVegan.visibility = View.GONE
                else
                    binding.productVegan.text = product.isVegan

                binding.productProtein.text = product.proteins
                binding.productFats.text = product.fats
                binding.productCarbohydrates.text = product.carbohydrates
                binding.productEnergy.text = product.energy

                Glide.with(this)
                    .load(product.imageUrl)
                    .placeholder(R.drawable.placeholder_image)
                    .error(R.drawable.ic_launcher_background)
                    .into(binding.productImage)
            } else {
                binding.productName.text = "Продукт не найден"
                binding.productImage.setImageResource(R.drawable.ic_launcher_background)
            }
        }

        viewModel.productItem.observe(viewLifecycleOwner) { productItem ->
            if (productItem != null) {
                val product = productItem
                binding.productName.text = product.name
                binding.productBrand.text = product.brands
                binding.productIngredients.text = product.ingredients
                binding.productQuantity.text = product.quantity
                binding.productCategories.text = product.categoriesTags
                binding.productAllergens.text = product.allergensTags

                if(product.isVegan.isNullOrEmpty())
                    binding.productVegetarian.visibility = View.GONE
                else
                    binding.productVegetarian.text = product.isVegeterian

                if(product.isVegan.isNullOrEmpty())
                    binding.productVegan.visibility = View.GONE
                else
                    binding.productVegan.text = product.isVegan

                binding.productProtein.text = product.proteins
                binding.productFats.text = product.fats
                binding.productCarbohydrates.text = product.carbohydrates
                binding.productEnergy.text = product.energy

                Glide.with(this)
                    .load(product.imageUrl)
                    .placeholder(R.drawable.placeholder_image)
                    .error(R.drawable.ic_launcher_background)
                    .into(binding.productImage)
            } else {
                binding.productName.text = "Продукт не найден"
                binding.productImage.setImageResource(R.drawable.ic_launcher_background)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance(productId: UUID): ProductFragment {
            val args = Bundle().apply {
                putSerializable(ARG_PRODUCT_ID, productId)
            }
            return ProductFragment().apply {
                arguments = args
            }
        }
    }
}