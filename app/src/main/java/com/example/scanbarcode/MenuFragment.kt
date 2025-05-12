package com.example.scanbarcode

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import com.example.scanbarcode.databinding.FragmentMenuBinding

class MenuFragment : Fragment() {
    private lateinit var handInputButton: Button
    private lateinit var cameraImageButton: ImageButton

    var onCameraClickListener: (() -> Unit)? = null
    var onHandInputClickListener: (() -> Unit)? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMenuBinding.inflate(inflater, container, false)

        handInputButton = binding.handInputButton
        cameraImageButton = binding.cameraImageButton

        handInputButton.setOnClickListener {
            onHandInputClickListener?.invoke()
        }

        cameraImageButton.setOnClickListener {
            onCameraClickListener?.invoke()
        }

        return binding.root
    }
}