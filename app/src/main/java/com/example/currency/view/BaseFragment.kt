package com.example.currency.view

import Valute
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.currency.adapter.CurrencyAdapter
import com.example.currency.databinding.FragmentBaseBinding
import com.example.currency.viewModel.BaseViewModel

class BaseFragment : Fragment() {

    lateinit var binding: FragmentBaseBinding
    private lateinit var viewModel: BaseViewModel
    lateinit var hashMap:HashMap<String,Valute>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBaseBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(BaseViewModel::class.java)
        viewModel.printInformation()
        val recyclerView = binding.tvView
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 1)
        var a = 0

        val textWatcher = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                Log.d("test","ararara ${binding.etNumber.text.toString()}")
                if(binding.etNumber.text.isNullOrBlank()) {
                    recyclerView.adapter = CurrencyAdapter(hashMap, 0)
                }
                else {
                    a = binding.etNumber.text.toString().toInt()
                    recyclerView.adapter =
                        CurrencyAdapter(hashMap, a)
                }
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        }
        if(binding.etNumber!=null) {
            viewModel.currencyLiveData.observe(viewLifecycleOwner) {
                Log.d("test", "rcview $it")
                recyclerView.adapter = CurrencyAdapter(it, 0)
                hashMap = it


            }
        }
        binding.etNumber.addTextChangedListener(textWatcher)
    }
}

