package com.example.rentalio.adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.rentalio.R
import com.example.rentalio.model.KategoriModel

class KategoriAdapter(val parent: Fragment) :
    RecyclerView.Adapter<KategoriAdapter.FlashViewHolder>() {

    private var kategoriList: List<KategoriModel>? = null

    fun setProductList(kategoriList: List<KategoriModel>?) {
        this.kategoriList = kategoriList
        notifyDataSetChanged()
    }

    class FlashViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivProduk: ImageView = itemView.findViewById(R.id.iv_kategori)
        val namaProduk: TextView = itemView.findViewById(R.id.tv_kategori)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlashViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.kategori_item, parent, false)
        return FlashViewHolder(itemView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: FlashViewHolder, position: Int) {

        kategoriList.let { list ->

            val product = list!![position]
            holder.namaProduk.text = product.name

            holder.ivProduk.setImageDrawable(
                ContextCompat.getDrawable(
                    parent.requireContext(), // Context
                    product.image// Drawable
                )
            )
            holder.itemView.setOnClickListener {
                onItemClickListener?.let { it(product) }
            }

        }
    }

    override fun getItemCount(): Int {
        return if (kategoriList == null) {
            0
        } else {
            kategoriList!!.size
        }
    }
    private var onItemClickListener: ((KategoriModel) -> Unit)? = null

    fun setOnItemClickListener(listener: (KategoriModel) -> Unit) {
        onItemClickListener = listener
    }
}