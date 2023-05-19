package com.example.jokenpo;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ImageView jogador1, jogador2;
    ImageButton pedra, papel, tesoura;

    Animation some, aparece;

    int jogada1 = 0;
    int jogada2 = 0;

    MediaPlayer mediaPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.alex_play);

        jogador1 = findViewById(R.id.jogador1);
        jogador2 = findViewById(R.id.jogador2);
        pedra = findViewById(R.id.pedra);
        papel = findViewById(R.id.papel);
        tesoura = findViewById(R.id.tesoura);

        some = new AlphaAnimation(1,0);
        aparece = new AlphaAnimation(0,1);

        some.setDuration(1000);
        aparece.setDuration(100);

        some.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                jogador2.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                jogador2.setVisibility(View.INVISIBLE);
                jogador2.startAnimation(aparece);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        aparece.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                sorteiaInimigo();
                jogador2.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                verificaJogada();
                jogador2.setVisibility(View.VISIBLE);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });



    }
    public void tocou(View view){
        tocaSom();
        jogador1.setScaleX(-1);
        switch (view.getId()){
            case(R.id.pedra):jogador1.setImageResource(R.drawable.pedra);
            jogada1 = 1;
            break;
            case(R.id.papel):jogador1.setImageResource(R.drawable.papel);
            jogada1 = 2;
            break;
            case(R.id.tesoura):jogador1.setImageResource(R.drawable.tesoura);
            jogada1 = 3;
            break;
        }
        jogador2.startAnimation(some);
        jogador2.setImageResource(R.drawable.interrogacao);

    }
    public void sorteiaInimigo(){
        Random r = new Random();
        int numRandom = r.nextInt(3);
        switch (numRandom){
            case 0: jogador2.setImageResource(R.drawable.pedra);
            jogada2 = 1;
            break;

            case 1: jogador2.setImageResource(R.drawable.papel);
            jogada2 = 2;
            break;
            case 2: jogador2.setImageResource(R.drawable.tesoura);
            jogada2 = 3;
            break;

        }

    }
    public void verificaJogada(){
        if (jogada1 == jogada2){
            Toast.makeText(this, "EMPATE", Toast.LENGTH_LONG).show();
        }
        if ((jogada1==1&&jogada2==3)||(jogada1==2&&jogada2==1)||(jogada1==3&&jogada2==2)){
            Toast.makeText(this, "GANHEI", Toast.LENGTH_LONG).show();
        }
        if ((jogada2==1&&jogada1==3)||(jogada2==2&&jogada1==1)||(jogada2==3&&jogada1==2)) {
            Toast.makeText(this, "PERDI", Toast.LENGTH_LONG).show();
        }


    }
    public void tocaSom(){
        if(mediaPlayer != null){
            mediaPlayer.start();
        }
    }
}