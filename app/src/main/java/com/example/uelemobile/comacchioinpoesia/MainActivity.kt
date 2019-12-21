package com.example.uelemobile.comacchioinpoesia

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.uelemobile.comacchioinpoesia.data.dataPoesie
import com.google.firebase.FirebaseApp
import java.io.IOException
import com.google.firebase.database.*
import java.util.ArrayList


class MainActivity : AppCompatActivity() {

    lateinit var  ref: DatabaseReference
    internal lateinit var testoinfo: TextView
    internal var visible = "invisible"
    lateinit var dati : MutableList<dataPoesie>

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        testoinfo = findViewById(R.id.testoInfo) as TextView

        var k: Intent = Intent(this@MainActivity, poesie_liste::class.java)


        var stringaCaricata = LoadData("info.txt");
        testoinfo.text = stringaCaricata

        val buttonMail = findViewById(R.id.buttonMail) as Button







        //Fine pubblicità


        //Inserimento dati
        ref = FirebaseDatabase.getInstance().getReference("pbTitolo")




        ref.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot) {

                if(p0.exists()){

                    for(h in p0.children){

                        val dat = h.getValue(dataPoesie::class.java)
                        dati.add(dat!!)

                        val pbT = variable()
                        pbT.pbTitolo = arrayOf(dat.titoloIt)
                        pbT.pbTitoloD = arrayOf(dat.titoloD)
                        pbT.pbTitoloLinkVideo = arrayOf(dat.linkVideo)







                    }






                }

            }


        })
        //fina inserimento dati





//Pulante email


        buttonMail.setOnClickListener(object : View.OnClickListener {

            override fun onClick(v: View?) {

                val intentMail = Intent(Intent.ACTION_SEND)

                intentMail.type = "text/html"

                intentMail.putExtra(Intent.EXTRA_EMAIL,arrayOf<String>("emanuele7804@gmail.com"))

                intentMail.putExtra(Intent.EXTRA_SUBJECT, "Informazioni")

                startActivity(intentMail)
            }
        })



        val buttonInfo = findViewById(R.id.buttonInfo) as ImageButton


        buttonInfo.setOnClickListener(object : View.OnClickListener {

            override fun onClick(v: View?) {

                if (visible === "invisible") {

                    testoinfo.visibility = View.VISIBLE

                    //rendo invisibile il testo
                    val fadeInfo = ObjectAnimator.ofFloat(testoinfo, View.ALPHA, 0.0f, 1.0f)
                    fadeInfo.setDuration(1000)
                    fadeInfo.start()


                    val rotInfo = ObjectAnimator.ofFloat(buttonInfo, View.ROTATION, 360.0f, 1.0f)
                    rotInfo.setDuration(1000)
                    rotInfo.start()

                    buttonMail.visibility = View.VISIBLE
                    visible = "visible"
                    buttonMail.alpha = 1.0f

                    val moveBmail = ObjectAnimator.ofFloat(buttonMail, View.TRANSLATION_Y, 360.0f, 1.0f)
                    moveBmail.setDuration(1000)
                    moveBmail.start()

                    Handler().postDelayed({

                        buttonInfo.setImageResource(R.mipmap.close_info)

                    }, 500)



                } else if (visible === "visible") {

                    //rendo visibile il testo
                    val fadeInfo2 = ObjectAnimator.ofFloat(testoinfo, View.ALPHA, 1.0f, 0.0f)
                    fadeInfo2.setDuration(1000)
                    fadeInfo2.start()

                    val rotInfo = ObjectAnimator.ofFloat(buttonInfo, View.ROTATION, -360.0f, 1.0f)
                    rotInfo.setDuration(1000)
                    rotInfo.start()

                    Handler().postDelayed({

                        testoinfo.visibility = View.VISIBLE

                    }, 1000)


                    val moveBmail = ObjectAnimator.ofFloat(buttonMail, View.ALPHA, 1.0f, 0.0f)
                    moveBmail.setDuration(1000)
                    moveBmail.start()


                    visible = "invisible"

                    Handler().postDelayed({

                        buttonInfo.setImageResource(R.mipmap.info)


                    }, 500)

                    Handler().postDelayed({


                        buttonMail.visibility = View.INVISIBLE
                        buttonMail.alpha = 0.0f

                    }, 1000)


                }

            }
        })


        val buttonLibro1 = findViewById(R.id.libro1) as ImageButton



        buttonLibro1.setOnClickListener(object : View.OnClickListener {

            override fun onClick(v: View?) {

                k.putExtra(".titoloAutore", "Pietro Boccaccini");
                k.putExtra(".titoloLibro", "Ech'el sunet");
                k.putExtra(".numeroLibro", "0");

                startActivity(k)

            }
        })






        val buttonLibro2 = findViewById(R.id.libro2) as ImageButton

        buttonLibro2.setOnClickListener(object : View.OnClickListener {



            override fun onClick(v: View?) {

                k.putExtra(".titoloAutore", "Pietro Boccaccini");
                k.putExtra(".titoloLibro", "Poesie varie");
                k.putExtra(".numeroLibro", "1");

                startActivity(k);

            }
        })






        val buttonLibro3 = findViewById(R.id.libro3) as ImageButton



        buttonLibro3.setOnClickListener(object : View.OnClickListener {

            override fun onClick(v: View?) {

                k.putExtra(".titoloAutore", "Franco Luciani");
                k.putExtra(".titoloLibro", "Parà, fichet, fusnà");
                k.putExtra(".numeroLibro", "4");

                startActivity(k);

            }
        })

        val buttonLibro4 = findViewById(R.id.libro4) as ImageButton




        buttonLibro4.setOnClickListener(object : View.OnClickListener {

            override fun onClick(v: View?) {

                k.putExtra(".titoloAutore", "Franco Luciani");
                k.putExtra(".titoloLibro", "Poesie varie");
                k.putExtra(".numeroLibro", "5");

                startActivity(k);



            }
        })

        val buttonLibro5 = findViewById(R.id.libro5) as ImageButton


        buttonLibro5.setOnClickListener(object : View.OnClickListener {

            override fun onClick(v: View?) {



                k.putExtra(".titoloAutore", "Valter Bellotti");
                k.putExtra(".titoloLibro", "Un puech ad puàsiè");
                k.putExtra(".numeroLibro", "2");

                startActivity(k);

            }
        })

        val buttonLibro6 = findViewById(R.id.libro6) as ImageButton


        buttonLibro6.setOnClickListener(object : View.OnClickListener {

            override fun onClick(v: View?) {



                k.putExtra(".titoloAutore", "Valter Bellotti");
                k.putExtra(".titoloLibro", "Poesie varie");
                k.putExtra(".numeroLibro", "3");

                startActivity(k);
            }
        })



    }


    fun LoadData(inFile: String): String {
        var tContents = ""

        try {
            val stream = assets.open(inFile)

            val size = stream.available()
            val buffer = ByteArray(size)
            stream.read(buffer)
            stream.close()
            tContents = String(buffer)

        } catch (e: IOException) {
            // Handle exceptions here
        }

        return tContents

    }

}
