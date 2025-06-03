package com.example.scanbarcode

import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.scanbarcode.databinding.FragmentMenuBinding

class MenuFragment : Fragment() {
    private lateinit var handInputButton: Button
    private lateinit var cameraImageButton: ImageButton

    var onCameraClickListener: (() -> Unit)? = null
    var onHandInputClickListener: ((String) -> Unit)? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMenuBinding.inflate(inflater, container, false)

        handInputButton = binding.handInputButton
        cameraImageButton = binding.cameraImageButton

        handInputButton.setOnClickListener {
            showManualInputDialog()
        }

        cameraImageButton.setOnClickListener {
            onCameraClickListener?.invoke()
        }

        return binding.root
    }

    private fun showManualInputDialog() {
        val editText = EditText(requireContext())
        editText.inputType = InputType.TYPE_CLASS_NUMBER

        AlertDialog.Builder(requireContext())
            .setTitle("Введите штрихкод")
            .setView(editText)
            .setPositiveButton("OK") { _, _ ->
                val inputBarcode = editText.text.toString().trim()
                if (inputBarcode.isNotEmpty()) {
                    onHandInputClickListener?.invoke(inputBarcode)
                } else {
                    Toast.makeText(context, "Поле не может быть пустым", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("Отмена", null)
            .show()
    }
}