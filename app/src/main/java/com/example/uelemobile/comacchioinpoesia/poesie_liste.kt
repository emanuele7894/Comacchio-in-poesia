package com.example.uelemobile.comacchioinpoesia


import android.content.Intent
import android.os.Bundle
import android.widget.*
import android.widget.Toast
import android.widget.AdapterView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.uelemobile.comacchioinpoesia.adapter.UserListAdapter
import com.example.uelemobile.comacchioinpoesia.data.UserDto


class poesie_liste : AppCompatActivity() {

    internal lateinit var listView: ListView
    internal lateinit var titolo: TextView
    internal lateinit var sottotitolo: TextView

    var titoloPoesia: Array<String>? = null
    var sottotitoloPoesia: Array<String>? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.poesie_liste)

        listView = findViewById(R.id.listView) as ListView

        titolo = findViewById(R.id.titolo) as TextView
        sottotitolo = findViewById(R.id.sottotitolo) as TextView



        intent = intent


        var autoreLibro = intent.getStringExtra(".titoloAutore");
        var nomeLibro = intent.getStringExtra(".titoloLibro");
        var numeroLibro = intent.getStringExtra(".numeroLibro");


        titolo.text = " " + autoreLibro
        sottotitolo.text = nomeLibro


        if(numeroLibro == "0"){

            val Titol1 = variable()
            titoloPoesia = Titol1.pbTitoloD
            sottotitoloPoesia = Titol1.pbTitolo
            // listView.setBackgroundResource(R.mipmap.pietroboccaccini);

        }else if(numeroLibro == "1"){

            val Titol1 = variable()
            titoloPoesia = Titol1.pbVTitoloD
            sottotitoloPoesia = Titol1.pbVTitolo
            //  listView.setBackgroundResource(R.mipmap.pietroboccaccini);

        }else if(numeroLibro == "2"){

            val Titol1 = variable()
            titoloPoesia = Titol1.vbTitoloD
            sottotitoloPoesia = Titol1.vbTitolo
            //  listView.setBackgroundResource(R.mipmap.valterbellotti);


        }else if(numeroLibro == "3"){

            val Titol1 = variable()
            titoloPoesia = Titol1.vbVTitoloD
            sottotitoloPoesia = Titol1.vbVTitolo
            //  listView.setBackgroundResource(R.mipmap.valterbellotti);

        }else if(numeroLibro == "4"){

            val Titol1 = variable()
            titoloPoesia = Titol1.flTitoloD
            sottotitoloPoesia = Titol1.flTitolo
            //  listView.setBackgroundResource(R.mipmap.francoluciani);

        }else if(numeroLibro == "5"){

            val Titol1 = variable()
            titoloPoesia = Titol1.flVTitoloD
            sottotitoloPoesia = Titol1.flVTitolo
            // listView.setBackgroundResource(R.mipmap.francoluciani);


        }


        var adapter = UserListAdapter (this,generateData())

        listView?.adapter = adapter

        adapter?.notifyDataSetChanged()


        var k2: Intent = Intent(this@poesie_liste, videoPoesia::class.java)

        // ListView Item Click Listener
        listView.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, index, l ->
            val clickItemObj = index

            k2.putExtra(".titoloAutoreElibro", "$autoreLibro / $nomeLibro")
            k2.putExtra(".titoloIta", sottotitoloPoesia!![clickItemObj])
            k2.putExtra(".titoloDial", titoloPoesia!![clickItemObj])
            k2.putExtra(".titoloAutore2", "$autoreLibro")
            k2.putExtra(".numeroLibro", numeroLibro);
            k2.putExtra(".numeropoesia", "$clickItemObj");

            if (titoloPoesia!![0] == ""){

                k2.putExtra(".soloIta", "true")
                Toast.makeText(this, sottotitoloPoesia!![clickItemObj]  , Toast.LENGTH_SHORT).show()
                startActivity(k2);

            }else {

                k2.putExtra(".soloIta", "false")
                Toast.makeText(this, titoloPoesia!![clickItemObj]  , Toast.LENGTH_SHORT).show()
                startActivity(k2);

            }






        }

    }



    fun generateData(): ArrayList<UserDto> {
        var result = ArrayList<UserDto>()

        var number: Int = titoloPoesia!!.count() + 1


        if (titoloPoesia!![0] == "") {

            for (i in titoloPoesia!!.indices) {




                var index = i + 1


                var user: UserDto = UserDto(index.toString(), sottotitoloPoesia!![i], titoloPoesia!![i]);
                result.add(user)
            }

        }else {
            for (i in titoloPoesia!!.indices) {




                var index = i + 1


                var user: UserDto = UserDto(index.toString(), titoloPoesia!![i], sottotitoloPoesia!![i]);
                result.add(user)
            }

        }


        return result
    }



}