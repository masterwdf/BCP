package com.example.bcp.features.money

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.request.RequestOptions
import com.example.bcp.R
import com.example.bcp.databinding.ContentMoneyBinding
import com.example.domain.entity.Currency
import com.example.domain.entity.Money
import kotlinx.android.synthetic.main.content_money.view.*

class MoneyAdapter(listener: MoneyAdapterListener) :
    RecyclerView.Adapter<MoneyAdapter.ViewHolder>() {

    private var listener = listener
    private val mList = ArrayList<Money>()
    var baseSymbol = ""

    override fun getItemCount(): Int {
        return mList.size
    }

    fun update(list: List<Money?>?) {
        mList.clear()

        list?.filterNotNull()?.let {
            mList.addAll(it)
        }

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        return ViewHolder(
            ContentMoneyBinding.inflate(
                LayoutInflater.from(viewGroup.context),
                viewGroup,
                false
            )
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            val item = mList[position]

            itemView.txtName.text = item.country
            itemView.txtDescription.text =
                "1 $baseSymbol = ${item.symbol?.let {
                    getRate(
                        item.currencies
                    )
                }} ${item.symbol}"

            item.image.let {
                Glide.with(itemView.context).load(it).apply(
                    RequestOptions.fitCenterTransform()
                        .placeholder(R.mipmap.ic_launcher)
                        .error(R.mipmap.ic_launcher)
                        .priority(Priority.HIGH)
                ).into(itemView.ivwMoney)
            }

            itemView.setOnClickListener {
                listener.selectItem(item)
            }
        }
    }

    fun getRate(currencies: List<Currency?>?): String {
        if (currencies != null) {
            for (item in currencies) {
                if (baseSymbol == item?.flag) {
                    return item.rate.toString()
                }
            }
        }

        return ""
    }

    class ViewHolder(itemView: ContentMoneyBinding) : RecyclerView.ViewHolder(itemView.root)
}

interface MoneyAdapterListener {

    fun selectItem(item: Money)

}