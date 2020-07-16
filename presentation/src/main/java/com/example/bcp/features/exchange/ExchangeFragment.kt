package com.example.bcp.features.exchange

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bcp.base.BaseFragment
import com.example.bcp.databinding.FragmentExchangeBinding
import com.example.bcp.features.exchange.di.ExchangeComponent
import com.example.bcp.features.money.MoneyActivity
import com.example.domain.entity.Currency
import com.example.domain.entity.Money
import javax.inject.Inject

class ExchangeFragment : BaseFragment(), ExchangeView {

    private var _binding: FragmentExchangeBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var presenter: ExchangePresenter

    var moneyOrigin: Money? = null
    var moneyDestiny: Money? = null

    private var type = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentExchangeBinding.inflate(inflater, container, false)

        binding.btnOrigin.setOnLongClickListener {
            goToMoney(1)
            true
        }

        binding.btnDestiny.setOnLongClickListener {
            goToMoney(2)
            true
        }

        binding.btnChange.setOnClickListener {
            if (type == 1) {
                type = 2
                binding.btnOrigin.text = moneyDestiny?.symbol
                binding.btnDestiny.text = moneyOrigin?.symbol

                val moneyOriginTemp = moneyDestiny
                val moneyDestinyTemp = moneyOrigin

                moneyOrigin = moneyOriginTemp
                moneyDestiny = moneyDestinyTemp
            } else {
                type = 1
                binding.btnOrigin.text = moneyDestiny?.symbol
                binding.btnDestiny.text = moneyOrigin?.symbol

                val moneyOriginTemp = moneyDestiny
                val moneyDestinyTemp = moneyOrigin

                moneyOrigin = moneyOriginTemp
                moneyDestiny = moneyDestinyTemp
            }

            val origin = binding.txtOrigin.text
            val destiny = binding.txtDestiny.text

            binding.txtOrigin.text = destiny
            binding.txtOrigin.setSelection(destiny.length)
            binding.txtDestiny.text = origin

            updateTotal()
        }

        binding.btnOperation.setOnClickListener {
            calculateMoney()
        }

        return binding.root
    }

    override fun onInjectView(): Boolean {
        getComponent(ExchangeComponent::class.java).inject(this)
        return true
    }

    override fun onViewInjected(savedInstanceState: Bundle?) {
        super.onViewInjected(savedInstanceState)
        this.presenter.attachView(this)
        this.presenter.getCurrentMoney()
    }

    fun goToMoney(type: Int) {
        val intent = Intent(activity, MoneyActivity::class.java)
        startActivityForResult(intent, type)
    }

    fun calculateMoney() {
        hideKeyboard()

        val input = binding.txtOrigin.text.toString()

        if (input.isNotEmpty()) {
            binding.txtDestiny.setText((input.toFloat() * getRate(moneyOrigin?.currencies)?.sell!!.toFloat()).toString())
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == 1) {
                moneyOrigin = data?.extras?.getParcelable("params")!!
            } else if (requestCode == 2) {
                moneyDestiny = data?.extras?.getParcelable("params")!!
            }

            updateTotal()
            calculateMoney()
        }
    }

    @SuppressLint("SetTextI18n")
    override fun showMoney(list: List<Money?>?) {
        if (list != null) {
            moneyOrigin = list[0]!!
            moneyDestiny = list[1]!!

            updateTotal()
        }
    }

    fun updateTotal() {
        binding.btnOrigin.text = moneyOrigin?.symbol
        binding.btnDestiny.text = moneyDestiny?.symbol

        binding.txtDescription.text =
            "Compra: ${getRate(moneyOrigin?.currencies)?.buy} | Venta: ${getRate(moneyOrigin?.currencies)?.sell}"
    }

    fun getRate(currencies: List<Currency?>?): Currency? {
        if (currencies != null) {
            for (item in currencies) {
                if (item?.flag == moneyDestiny?.symbol) {
                    return item
                }
            }
        }

        return null
    }

    override fun context(): Context {
        return activity as Context
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
