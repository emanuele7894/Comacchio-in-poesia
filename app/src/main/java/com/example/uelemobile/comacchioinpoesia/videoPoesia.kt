package com.example.uelemobile.comacchioinpoesia


import android.os.Bundle
import android.animation.ObjectAnimator
import android.content.ActivityNotFoundException
import android.view.View
import android.widget.*
import android.os.Build
import android.support.annotation.RequiresApi
import java.io.*
import android.text.method.ScrollingMovementMethod
import android.util.TypedValue
import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.net.Uri
import android.support.constraint.ConstraintSet
import android.transition.AutoTransition
import android.transition.TransitionManager
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerFragment
import kotlinx.android.synthetic.main.activity_video_poesia.*




class videoPoesia : YouTubeBaseActivity(), YouTubePlayer.OnInitializedListener{

    companion object {
        var stringaPathVideo = ""
        val apiKey = "AIzaSyDzXkniPBFLiJxzQMzj223WgXnl5p5ppgk"
    }



    internal lateinit var titolo2: TextView
    internal lateinit var sottotitolo2: TextView
    internal lateinit var testoPoesia: TextView
    internal lateinit var barra: ImageView
    internal lateinit var rotateImg: ImageView
    internal lateinit var conteggioVideo: TextView

    var videoPlay = YouTubePlayerFragment()
    internal lateinit var videoPlayB: YouTubePlayer
    var titoloPoesia: Array<String>? = null
    var sottotitoloPoesia: Array<String>? = null
    var linkVideo: Array<String>? = null




    var stringaPoePatchD = ""
    var stringaPoePatchI = ""
    var stringaCaricata1 = ""
    var stringaCaricata2 = ""
    var stringTrue = true
    var muve = false

    var stringaAutore: String = ""
    var stringaAutoreElibro: String = ""
    var stringaTitoloIta: String = ""
    var stringaTitoloDial: String = ""
    var numeroLibro = 0
    var numeroVideo = 0
    var totaleVideo = 0


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_poesia)


        titolo2 = findViewById<TextView>(R.id.titolo2)
        sottotitolo2 = findViewById<TextView>(R.id.sottotitolo2)
        testoPoesia = findViewById<TextView>(R.id.testoPoesia)
        testoPoesia.movementMethod = ScrollingMovementMethod()
        barra = findViewById<ImageView>(R.id.barra)
        rotateImg = findViewById<ImageView>(R.id.rotate)
        conteggioVideo = findViewById<TextView>(R.id.contV)
        videoPlay = fragmentManager.findFragmentById(R.id.youtubeplayerfragment) as YouTubePlayerFragment;


        //Dichiaro i pulsanti
        val muveUp = findViewById<ImageButton>(R.id.move)
        val ingrandisci = findViewById<Button>(R.id.ingrandisci)
        val diminuisci = findViewById<Button>(R.id.diminuisci)
        val traduzione = findViewById<Button>(R.id.traduz)
        val nextB = findViewById<ImageButton>(R.id.nextButton)
        val backB = findViewById<ImageButton>(R.id.backButton)
        val youTub = findViewById<ImageButton>(R.id.youButton)



        //dichiaro le animazioni di rotazione pulsanti


        val rotMove = ObjectAnimator.ofFloat(muveUp, View.ROTATION, 180.0f, 0.0f)
        rotMove.duration = 500

        val rotMove2 = ObjectAnimator.ofFloat(muveUp, View.ROTATION, -180.0f, 0.0f)
        rotMove2.duration = 500





        intent = intent


        //creo la stringa esatta per caricare i file video e testi poesia
        //
        // PRIMO AVVIO


        stringaAutore = intent.getStringExtra(".titoloAutore2")
        stringaAutoreElibro = intent.getStringExtra(".titoloAutoreElibro")
        stringaTitoloIta = intent.getStringExtra(".titoloIta")
        stringaTitoloDial  = intent.getStringExtra(".titoloDial")
        numeroLibro = intent.getStringExtra(".numeroLibro").toInt()
        numeroVideo = intent.getStringExtra(".numeropoesia").toInt()



        //creo la stringa video
        fun stringaVideo(string: String): String{

            var stringaVideoA: String = string.toLowerCase()
            var stringaVideoB: String = stringaVideoA.replace(' ','_')
            var stringaVideoC: String = stringaVideoB.replace(',','_')
            var stringaVideo: String = stringaVideoC.replace('\'','_')

            return stringaVideo

        }





        val Titol1 = variable()
        //carico array

        if(numeroLibro == 0){

            titoloPoesia = Titol1.pbTitoloD
            sottotitoloPoesia = Titol1.pbTitolo
            linkVideo = Titol1.pbTitoloLinkVideo

        }else if(numeroLibro == 1){

            titoloPoesia = Titol1.pbVTitoloD
            sottotitoloPoesia = Titol1.pbVTitolo
            linkVideo = Titol1.pbVTitoloLinkVideo

        }else if(numeroLibro == 2){

            titoloPoesia = Titol1.vbTitoloD
            sottotitoloPoesia = Titol1.vbTitolo
            linkVideo = Titol1.vbTitoloLinkVideo

        }else if(numeroLibro == 3){

            titoloPoesia = Titol1.vbVTitoloD
            sottotitoloPoesia = Titol1.vbVTitolo
            linkVideo = Titol1.vbVTitoloLinkVideo

        }else if(numeroLibro == 4){

            titoloPoesia = Titol1.flTitoloD
            sottotitoloPoesia = Titol1.flTitolo
            linkVideo = Titol1.flTitoloLinkVideo

        }else if(numeroLibro == 5){

            titoloPoesia = Titol1.flVTitoloD
            sottotitoloPoesia = Titol1.flVTitolo
            linkVideo = Titol1.flVTitoloLinkVideo


        }


        totaleVideo = sottotitoloPoesia!!.count() - 1




        fun controlloPNB(){


            var numVideo =  numeroVideo + 1
            var totalV = sottotitoloPoesia!!.count()

            conteggioVideo.text = "$numVideo / $totalV"



        }


        controlloPNB()

        var stringaVideoLoad = stringaVideo(stringaTitoloIta)




        var stringaAutore1 = stringaAutore.toLowerCase()
        var stringaAutore2 = stringaAutore1.replace(' ','_')
        var stringaAutore3 = stringaAutore2.replace('\'','_')
        var stringaAutore4 = stringaAutore3.replace(',','_')



        stringaPoePatchD = "$stringaVideoLoad" + "d.txt"
        stringaPoePatchI = "$stringaVideoLoad" + "i.txt"

        stringaCaricata1 = LoadData("$stringaAutore4" + "/" + "$stringaPoePatchD")
        stringaCaricata2 = LoadData("$stringaAutore4" + "/" + "$stringaPoePatchI")


        testoPoesia.text = "$stringaCaricata1"
        testoPoesia.width = 1500

        if (stringaTitoloDial == ""){

            stringTrue = true

        }else {

            stringTrue = false

        }


        if (stringTrue == true) {

            titolo2.text = " " + stringaTitoloIta

        }else if (stringTrue == false){

            titolo2.text = " " + stringaTitoloDial

        }

        //impostzioni video player
        //stabilisco i dati per il videoplayer
        //val m = MediaController(this)
        //videoPlay .setMediaController(m)



        stringaPathVideo = linkVideo!![numeroVideo]


        videoPlay.initialize(apiKey,this)






        sottotitolo2.text = stringaAutoreElibro.toString()

        fun controllo(){

            if (stringTrue == true) {

                titolo2.text = " " + stringaTitoloIta

            }else if (stringTrue == false){

                titolo2.text = " " + stringaTitoloDial

            }

        }

        controllo()






        fun controlloDialetto(){

            if (stringaTitoloDial == ""){

                traduzione.visibility = View.INVISIBLE

            }
        }





        //Controllo se il video è presente altrimenti nascondo la maschera del video
        var con =  ConstraintSet()
        con.clone(mainL)


        fun videoNo(){



            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)

            muveUp.visibility = View.INVISIBLE
                youTub.visibility = View.INVISIBLE
                    rotateImg.visibility = View.INVISIBLE

            val transition = AutoTransition()

            transition.duration = 500

            TransitionManager.beginDelayedTransition(mainL, transition)


            var con2 =  ConstraintSet()
            con2.clone(mainL)
            con2.clear(R.id.barra, ConstraintSet.BOTTOM)
            con2.applyTo(mainL)


            barra.background = resources.getDrawable(R.mipmap.fondo2)




        }

        //sposta la barra al top dopo aver cliccato il pulsante alza

        fun videoNo2(){


            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)

            rotateImg.visibility = View.INVISIBLE

            val transition = AutoTransition()

            transition.duration = 500

            TransitionManager.beginDelayedTransition(mainL, transition)


            var con2 =  ConstraintSet()
            con2.clone(mainL)
            con2.clear(R.id.barra, ConstraintSet.BOTTOM)
            con2.applyTo(mainL)

            barra.background = resources.getDrawable(R.mipmap.fondo2)




        }






        //sposta la barra al centro dopo aver cliccato il pulsante alza
        fun videoYes2(){

            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR)

            muveUp.visibility = View.VISIBLE
                youTub.visibility = View.VISIBLE
                    rotateImg.visibility = View.VISIBLE

            val transition = AutoTransition()

            transition.duration = 500

            TransitionManager.beginDelayedTransition(mainL, transition)

            con.applyTo(mainL)


            barra.background = resources.getDrawable(R.mipmap.fondo3)

            controlloDialetto()
        }


        //Controllo all'avvio se il video è presente posiziona la barra al centro
        fun videoYes(){

            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR)

            muveUp.visibility = View.VISIBLE
                youTub.visibility = View.VISIBLE
                    rotateImg.visibility = View.VISIBLE

            val transition = AutoTransition()
            transition.duration = 500

            TransitionManager.beginDelayedTransition(mainL, transition)
            con.applyTo(mainL)


            barra.background = resources.getDrawable(R.mipmap.fondo3)
            muveUp.setImageResource(R.mipmap.up)
            muve = false


        }








        if (  linkVideo!![numeroVideo] == "" ) {  // the resouce exists...ù


            videoNo()
            muve = true

            muveUp.setImageResource(R.mipmap.down)
            rotMove.start()

        } else {  // checkExistence == 0  // the resouce does NOT exist!!

            videoYes()
            muve = false



        }



        //Pulsante indgrandisci font

        ingrandisci.setOnClickListener(object : View.OnClickListener {

            override fun onClick(v: View?) {



                if (testoPoesia.textSize <= 70f) {

                    testoPoesia.width = testoPoesia.width + 140
                    val size = testoPoesia.textSize * 1.1f
                    testoPoesia.setTextSize(TypedValue.COMPLEX_UNIT_PX, size)

                }

            }
        })


        //Pulsante diminuisci font

        diminuisci.setOnClickListener(object : View.OnClickListener {

            override fun onClick(v: View?) {

                if (testoPoesia.textSize >= 40f) {

                    val size = testoPoesia.textSize / 1.1f
                    testoPoesia.setTextSize(TypedValue.COMPLEX_UNIT_PX, size)
                    testoPoesia.width = testoPoesia.width - 140

                }

            }
        })

        fun trad(){

            if (traduzione.text == "ITALIANO") {

                titolo2.text = " " + stringaTitoloIta
                testoPoesia.text = "$stringaCaricata2"
                traduzione.text = "DIALETTO"

            }else  if (traduzione.text == "DIALETTO") {

                titolo2.text = " " + stringaTitoloDial
                testoPoesia.text = "$stringaCaricata1"
                traduzione.text = "ITALIANO"

            }
        }

        //Pulsante dialetto o italiano
        traduzione.setOnClickListener(object : View.OnClickListener {

            override fun onClick(v: View?) {

                trad()

            }
        })




        if (stringaTitoloDial == ""){

            traduzione.visibility = View.INVISIBLE

        }





        youTub.setOnClickListener(object : View.OnClickListener {

            override fun onClick(v: View?) {


                openYoutubeLink(stringaPathVideo)


            }

        })







        muveUp.setOnClickListener(object : View.OnClickListener {


            override fun onClick(v: View?) {


                videoPlayB.pause()




                if (muve == true){

                    muveUp.setImageResource(R.mipmap.up)
                    rotMove2.start()
                    muve = false

                    videoYes2()







                }else {

                    muveUp.setImageResource(R.mipmap.down)
                    rotMove.start()
                    muve = true


                    videoNo2()

                }





            }
        })



        //prossimo video o precedente
        fun videoAvaInd(num: Int){

            stringaTitoloIta = sottotitoloPoesia!![num]
            stringaTitoloDial = titoloPoesia!![num]

            stringaVideoLoad = stringaVideo(sottotitoloPoesia!![num])

            stringaPoePatchD = "$stringaVideoLoad" + "d.txt"
            stringaPoePatchI = "$stringaVideoLoad" + "i.txt"

            stringaCaricata1 = LoadData("$stringaAutore4" + "/" + "$stringaPoePatchD")
            stringaCaricata2 = LoadData("$stringaAutore4" + "/" + "$stringaPoePatchI")


            // inserisco testo poesia
            testoPoesia.text = "$stringaCaricata1"
            testoPoesia.width = 1000

            stringaPathVideo = linkVideo!![num]
            videoPlayB.loadVideo(stringaPathVideo)

            sottotitolo2.text = stringaAutoreElibro.toString()

            if (  linkVideo!![numeroVideo] == "" ) {  // the resouce exists...ù


                videoNo()
                muve = true

                muveUp.setImageResource(R.mipmap.down)
                rotMove.start()

            } else {  // checkExistence == 0  // the resouce does NOT exist!!

                videoYes()
                muve = false



            }


            controllo()
            controlloDialetto()

            traduzione.text = "ITALIANO"


        }

        nextB.setOnClickListener(object : View.OnClickListener {

            override fun onClick(v: View?) {

                var numVideo =  numeroVideo + 1
                var totalV = sottotitoloPoesia!!.count()

                conteggioVideo.text = "$numVideo / $totalV"


                if (numeroVideo == totaleVideo){

                    //disattiva nextButton

                }else {

                    numeroVideo += 1
                    videoAvaInd(numeroVideo)

                    controlloPNB()


                }



            }


        })



        backB.setOnClickListener(object : View.OnClickListener {

            override fun onClick(v: View?) {

                var numVideo =  numeroVideo + 1
                var totalV = sottotitoloPoesia!!.count()

                conteggioVideo.text = "$numVideo / $totalV"


                if (numeroVideo == 0){

                    //disattiva backButton

                }else {

                    numeroVideo -= 1
                    videoAvaInd(numeroVideo)

                    controlloPNB()


                }



            }


        })


    }





    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)
        if (newConfig != null) {


            if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                videoPlayB.setFullscreen(true)
            } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
                videoPlayB.setFullscreen(false)
            }
        }
    }




    override fun onInitializationSuccess(provider: YouTubePlayer.Provider?, player: YouTubePlayer?, wasRestored: Boolean) {


        //Log.d(TAG, "onInitializationSuccess: provider: ${provider?.javaClass}, player: ${player?.javaClass}")
        //Toast.makeText(this, "Initialized YoutubePlayer successfully", Toast.LENGTH_LONG).show()


        if (!wasRestored) {


            player?.loadVideo(stringaPathVideo)
            player?.setPlayerStateChangeListener(playerStateListener)
            player?.setPlaybackEventListener(playerPlaybackListener)
            player?.setPlayerStyle(YouTubePlayer.PlayerStyle.MINIMAL)

            videoPlayB = player!!


        } else {


        }



    }

    override fun onInitializationFailure(provider: YouTubePlayer.Provider?, result: YouTubeInitializationResult?) {

    }
    private val playerPlaybackListener = object : YouTubePlayer.PlaybackEventListener {
        override fun onPlaying() {

        }

        override fun onPaused() {

        }

        override fun onSeekTo(p0: Int) {}

        override fun onBuffering(p0: Boolean) {}

        override fun onStopped() {
        }
    }
    private val playerStateListener = object: YouTubePlayer.PlayerStateChangeListener {
        override fun onAdStarted() {

        }

        override fun onLoading() {


        }


        override fun onVideoStarted() {

        }

        override fun onLoaded(p0: String?) {}

        override fun onVideoEnded() {

        }

        override fun onError(p0: YouTubePlayer.ErrorReason?) {}



    }



    fun openYoutubeLink(youtubeID: String) {
        val intentApp = Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + youtubeID))
        val intentBrowser = Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=" + youtubeID))
        try {
            this.startActivity(intentApp)
        } catch (ex: ActivityNotFoundException) {
            this.startActivity(intentBrowser)
        }

    }






    override fun onResume() {
        super.onResume()



    }



    override fun onBackPressed() {

        super.onBackPressed()


        val pref = getSharedPreferences("Session Data", Context.MODE_PRIVATE)

        val edit = pref.edit()

        edit.putInt("Position", 0)

        edit.commit()

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
