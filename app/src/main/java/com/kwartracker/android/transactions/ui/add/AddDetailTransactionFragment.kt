package com.kwartracker.android.transactions.ui.add

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.kwartracker.android.R
import com.kwartracker.android.databinding.FragmentAddDetailTransactionBinding
import com.kwartracker.android.widgets.ConfirmedDialog

class AddDetailTransactionFragment : Fragment() {
    lateinit var binding: FragmentAddDetailTransactionBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_add_detail_transaction,
            container, false
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val confirmedDialog = ConfirmedDialog(activity as Activity)
        binding.btnBack.setOnClickListener {
            findNavController().navigate(R.id.transaction_fragment)
        }
        binding.btnAdd.setOnClickListener {
            confirmedDialog.setIcon(R.drawable.ic_success_teal_75)
            confirmedDialog.setTitle(getString(R.string.lbl_success))
            confirmedDialog.setExitText(getString(R.string.lbl_cool))
            confirmedDialog.setMessage(getString(R.string.lbl_confirmed_success_message))
            confirmedDialog.show()
            findNavController().navigate(R.id.transaction_fragment)
        }
    }
}
