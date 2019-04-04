package com.example.uelemobile.comacchioinpoesia.adapter

import android.app.Activity
import android.content.Context
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.uelemobile.comacchioinpoesia.R
import com.example.uelemobile.comacchioinpoesia.data.UserDto

class UserListAdapter(private var activity: Activity, private var items: ArrayList<UserDto>): BaseAdapter() {

    private class ViewHolder(row: View?) {
        var item: TextView? = null
        var titolo: TextView? = null
        var sottotitolo: TextView? = null

        init {

            this.titolo = row?.findViewById(R.id.titolo)
            this.sottotitolo = row?.findViewById(R.id.sottotitolo)
            this.item = row?.findViewById(R.id.item)

        }

    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View?
        val viewHolder: ViewHolder
        if (convertView == null) {
            val inflater = activity?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.poesie_list_row, null)
            viewHolder = ViewHolder(view)
            view?.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        var userDto = items[position]
        viewHolder.titolo?.text = userDto.titolo
        viewHolder.sottotitolo?.text = userDto.sottotitolo
        viewHolder.item?.text = userDto.item



        return view as View
    }

    override fun getItem(i: Int): UserDto {
        return items[i]
    }

    override fun getItemId(i: Int): Long {
        return i.toLong()
    }

    override fun getCount(): Int {
        return items.size
    }

}

