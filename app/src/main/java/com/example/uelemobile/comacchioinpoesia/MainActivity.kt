package com.example.uelemobile.comacchioinpoesia

import android.animation.ObjectAnimator
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.os.Handler
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.InterstitialAd
import java.io.IOException


class MainActivity : AppCompatActivity() {

    internal lateinit var testoinfo: TextView
    internal var visible = "invisible"
    internal lateinit var mAdView: AdView

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)

        testoinfo = findViewById(R.id.testoInfo) as TextView

        var k: Intent = Intent(this@MainActivity, poesie_liste::class.java)


        var stringaCaricata = LoadData("info.txt");
                testoinfo.text = stringaCaricata

        val buttonMail = findViewById(R.id.buttonMail) as Button






//Admob pubblicità
        val mInterstitialAd: InterstitialAd


        mInterstitialAd =  InterstitialAd(this)
        mInterstitialAd.setAdUnitId("ca-app-pub-1084610005673175/1647626035")




        val buttonMod = findViewById(R.id.buttonMob) as Button


        buttonMod.setOnClickListener(object : View.OnClickListener {

            override fun onClick(v: View?) {

                if (mInterstitialAd.isLoaded()) {

                    mInterstitialAd.show();

                } else {

                    mInterstitialAd.show();

                }

            }
        })





        mAdView = findViewById(R.id.adView) as AdView
        val adRequestBuilder = AdRequest.Builder()
        adRequestBuilder.addTestDevice("519A88B7B0BB0A505A0F36FEDAFF3260")



        // mAdView.loadAd(adRequestBuilder.build())
        //   mInterstitialAd.loadAd(adRequestBuilder.build())

        mAdView.loadAd(AdRequest.Builder().build())
        mInterstitialAd.loadAd(AdRequest.Builder().build())

        buttonMod.visibility = View.VISIBLE
        mAdView.visibility = View.VISIBLE



        mInterstitialAd.adListener = object : AdListener() {

            override fun onAdClosed() {


                mInterstitialAd.loadAd(AdRequest.Builder().build())
                //  mInterstitialAd.loadAd(adRequestBuilder.build())

            }

        }


        Handler().postDelayed({

            if (mInterstitialAd.isLoaded()) {
                mInterstitialAd.show();
            } else {
                //Begin Game, continue with app
            }


        }, 15000)


        //Fine pubblicità





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


                k.putExtra(".titoloAutore", "Valter Bellotti");
                    k.putExtra(".titoloLibro", "Un puech ad puàsiè");
                        k.putExtra(".numeroLibro", "2");

                startActivity(k);
            }
        })

        val buttonLibro4 = findViewById(R.id.libro4) as ImageButton




        buttonLibro4.setOnClickListener(object : View.OnClickListener {

            override fun onClick(v: View?) {



                k.putExtra(".titoloAutore", "Valter Bellotti");
                    k.putExtra(".titoloLibro", "Poesie varie");
                        k.putExtra(".numeroLibro", "3");

                startActivity(k);

            }
        })

        val buttonLibro5 = findViewById(R.id.libro5) as ImageButton


        buttonLibro5.setOnClickListener(object : View.OnClickListener {

            override fun onClick(v: View?) {

                k.putExtra(".titoloAutore", "Franco Luciani");
                    k.putExtra(".titoloLibro", "Parà, fichet, fusnà");
                         k.putExtra(".numeroLibro", "4");

                startActivity(k);

            }
        })

        val buttonLibro6 = findViewById(R.id.libro6) as ImageButton


        buttonLibro6.setOnClickListener(object : View.OnClickListener {

            override fun onClick(v: View?) {

                k.putExtra(".titoloAutore", "Franco Luciani");
                    k.putExtra(".titoloLibro", "Poesie varie");
                         k.putExtra(".numeroLibro", "5");

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
