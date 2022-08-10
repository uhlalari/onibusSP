package com.example.onibussp.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.onibussp.databinding.LinhasItemBinding
import com.example.onibussp.model.Linhas

class MainAdapter(private val linhas: List<Linhas>) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    class ViewHolder(val binding: LinhasItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = LinhasItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            binding.tvCl.text = linhas[position].cl.toString()
            binding.tvTp.text = linhas[position].tp
            binding.tvTs.text = linhas[position].ts
        }
    }

    override fun getItemCount(): Int {
        return linhas.size
    }


}