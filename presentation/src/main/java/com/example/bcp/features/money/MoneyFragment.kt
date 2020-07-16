package com.example.bcp.features.money

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bcp.base.BaseFragment
import com.example.bcp.databinding.FragmentMoneyBinding
import com.example.bcp.features.money.di.MoneyComponent
import com.example.domain.entity.Money
import javax.inject.Inject

class MoneyFragment : BaseFragment(), MoneyView, MoneyAdapterListener {

    private var _binding: FragmentMoneyBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var presenter: MoneyPresenter

    lateinit var moneyAdapter: MoneyAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMoneyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onInjectView(): Boolean {
        getComponent(MoneyComponent::class.java).inject(this)
        return true
    }

    override fun onViewInjected(savedInstanceState: Bundle?) {
        super.onViewInjected(savedInstanceState)
        this.presenter.attachView(this)
        this.presenter.getMoney()
        setupAdapter()
    }

    override fun context(): Context {
        return activity as Context
    }

    fun setupAdapter() {
        moneyAdapter = MoneyAdapter(this)
        binding.recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.adapter = moneyAdapter
    }

    override fun showMoney(list: List<Money?>?) {
        moneyAdapter.baseSymbol = list?.get(0)?.symbol.toString()
        moneyAdapter.update(list)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun selectItem(item: Money) {
        val intent = Intent()
        intent.putExtra("params", item)
        activity?.setResult(Activity.RESULT_OK, intent)
        activity?.finish()
    }
}
