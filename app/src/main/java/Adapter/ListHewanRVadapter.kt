package Adapter

import Database.GlobalVar
import Interface.CardListener
import Model.Ayam

import android.provider.Settings.Global

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.week2_0706012110005.R
import com.example.week2_0706012110005.databinding.HewanCardBinding
import model.Animal

// INFO: Kalau di android studio saya saat run pertama error, harus rebuild project baru di run biar bisa jalan:)

class ListAnimalRvAdapter(var listUser: ArrayList<Animal>, val cardListener: CardListener):
    RecyclerView.Adapter<ListAnimalRvAdapter.viewHolder>(){
    private var position = -1
    class viewHolder(itemView: View, val cardListener: CardListener): RecyclerView.ViewHolder(itemView) {

        //bind
        val binding = HewanCardBinding.bind(itemView)
        fun setData(data:Animal){
            binding.namaHewanTextViewHewanCard.text= data.nama
            binding.jenisHewanTextViewHewanCard.text=data.jenis
            binding.usiaHewanTextViewHewanCard.text=data.usia
            binding.deletebuttonHewanCard.setOnClickListener {
                cardListener.onCardClick(true, adapterPosition)
            }
            binding.editbuttonHewanCard.setOnClickListener {
                cardListener.onCardClick(false, adapterPosition)
            }
//            itemView.setOnClickListener{
//                cardListener1.onCardClick(adapterPosition)
//            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListAnimalRvAdapter.viewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.hewan_card, parent, false)
        return viewHolder(view, cardListener)
    }

    override fun onBindViewHolder(holder: ListAnimalRvAdapter.viewHolder, position: Int) {
        holder.setData(listUser[position])

        holder.binding.soundButtonHewanCard.setOnClickListener(){
            Toast.makeText(it.context, GlobalVar.listDataAnimal.get(position).makesound(), Toast.LENGTH_SHORT).show()
        }
        holder.binding.makanButtonHewanCard.setOnClickListener(){
            if (GlobalVar.listDataAnimal.get(position) is Ayam){
                Toast.makeText(it.context,GlobalVar.listDataAnimal.get(position).animalmakan(GlobalVar.listDataAnimal.get(position).animalmakan(biji = String())), Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(it.context,GlobalVar.listDataAnimal.get(position).animalmakan(rumput = 0), Toast.LENGTH_SHORT).show()
            }
        }

    }

    override fun getItemCount(): Int {
        return listUser.size
    }

//    fun insertFilter(newHewan:ArrayList<Animal>){
//        listUser= newHewan
//        notifyDataSetChanged()
//    }
}