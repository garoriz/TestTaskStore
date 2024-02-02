package com.garif.item_feature.presentation.adapter.characteristics

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.garif.item_feature.databinding.CharacteristicItemBinding
import com.garif.network.response.items.Info

class CharacteristicHolder(
    private val binding: CharacteristicItemBinding,
) : RecyclerView.ViewHolder(binding.root) {
    private var item: Info? = null

    fun bind(item: Info) {
        this.item = item
        with(binding) {
            tvCharacteristic.text = item.title
            tvCharacteristicValue.text = item.value
        }
    }

    companion object {

        fun create(
            parent: ViewGroup,
        ) = CharacteristicHolder(
            CharacteristicItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
}