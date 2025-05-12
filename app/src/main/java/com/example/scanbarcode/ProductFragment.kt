package com.example.scanbarcode

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.scanbarcode.databinding.FragmentProductBinding
import com.bumptech.glide.Glide

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
            viewModel.loadProduct(barcode)
        } else {

        }

        viewModel.product.observe(viewLifecycleOwner) { product ->
            if (product != null) {
                binding.productName.text = product.name ?: "Без названия"
                binding.productBrand.text = product.brands ?: "Без бренда"
                binding.productIngredients.text = product.ingredients ?: "Нет состава"

                Glide.with(this)
                    .load(product.imageUrl)
                    .placeholder(R.drawable.placeholder_image)
                    .error(R.drawable.ic_launcher_background)
                    .into(binding.productImage)
            } else {
                binding.productName.text = "Продукт не найден"
                binding.productBrand.text = ""
                binding.productIngredients.text = ""
                binding.productImage.setImageResource(R.drawable.ic_launcher_background)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}