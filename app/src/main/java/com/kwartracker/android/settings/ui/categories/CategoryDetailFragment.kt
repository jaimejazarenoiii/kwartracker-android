package com.kwartracker.android.settings.ui.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.kwartracker.android.R
import com.kwartracker.android.databinding.FragmentCategoryDetailBinding

class CategoryDetailFragment : Fragment(), View.OnClickListener {

    lateinit var binding: FragmentCategoryDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_category_detail, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnBack.setOnClickListener(this)
        binding.ibEditCategory.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.btnBack -> {
                findNavController().popBackStack()
            }
            binding.ibEditCategory -> {
                Toast.makeText(activity, "Edit Category Clicked", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
