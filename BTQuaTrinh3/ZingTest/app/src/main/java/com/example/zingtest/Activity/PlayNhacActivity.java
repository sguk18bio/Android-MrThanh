package com.example.zingtest.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zingtest.Adapter.PlayListNhacAdapterPager;
import com.example.zingtest.Fragment.Fragment_Dia_Nhac;
import com.example.zingtest.Fragment.Fragment_Play_Nhac;
import com.example.zingtest.Model.BaiHat;
import com.example.zingtest.R;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;

public class PlayNhacActivity extends AppCompatActivity {
    BaiHat baihat;
    public static ArrayList<BaiHat> listbaihat = new ArrayList<>();
    public static PlayListNhacAdapterPager playListNhacAdapterPager;

    Toolbar toolbar;
    ViewPager viewPager;
    TextView tvstarttimesong, tvendtimesong;
    SeekBar sktime;
    ImageView ivshuffle, ivpreview, ivplay, ivnext, ivrepeat;
    MediaPlayer mediaPlayer = new MediaPlayer();

    Fragment_Dia_Nhac fragment_dia_nhac;
    Fragment_Play_Nhac fragment_play_nhac;

    int position = 0;
    boolean repeat = false, checkrandom = false, next = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_nhac);
        //kiểm tra dữ liệu mạng

        StrictMode.ThreadPolicy threadPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(threadPolicy);
        AnhXa();
        GetDataFromIntent();
        EventClick();

    }

    private void EventClick() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (playListNhacAdapterPager.getItem(1) != null) {
                    if (listbaihat.size() > 0) {
                        fragment_dia_nhac.PlayNhac(listbaihat.get(0).getHinhBaiHat());
                        handler.removeCallbacks(this);
                    } else {
                        handler.postDelayed(this, 300);
                    }
                }
            }
        }, 500);

        ivplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    ivplay.setImageResource(R.drawable.iconplay);
                    if (fragment_dia_nhac.objectAnimator != null) {
                        fragment_dia_nhac.objectAnimator.pause();
                    } else {
                        fragment_dia_nhac.objectAnimator.start();
                    }
                } else {
                    mediaPlayer.start();
                    ivplay.setImageResource(R.drawable.iconpause);
                    if(fragment_dia_nhac.objectAnimator != null){
                        fragment_dia_nhac.objectAnimator.resume();
                    }
                }
            }
        });

    }

    private void GetDataFromIntent() {
        Intent intent = getIntent();
        listbaihat.clear();
        if (intent != null) {
            if (intent.hasExtra("cakhuc")) {
                baihat = intent.getParcelableExtra("cakhuc");
                listbaihat.add(baihat);
            }
            if (intent.hasExtra("danhsachbaihat")) {
                ArrayList<BaiHat> arraylistbaihat = intent.getParcelableArrayListExtra("danhsachbaihat");
                listbaihat = arraylistbaihat;
            }
        }
    }

    private void AnhXa() {
        toolbar = findViewById(R.id.toolbarplaynhac);
        viewPager = findViewById(R.id.viewpagerplaynhac);
        tvstarttimesong = findViewById(R.id.textviewtimesong);
        tvendtimesong = findViewById(R.id.textviewtotaltimesong);
        ivshuffle = findViewById(R.id.imageviewshuffleplaynhac);
        ivpreview = findViewById(R.id.imagepreviewplayplaynhac);
        ivplay = findViewById(R.id.imageviewplayplaynhac);
        ivnext = findViewById(R.id.imageviewnextplaynhac);
        ivrepeat = findViewById(R.id.imageviewrepeatplaynhac);
        sktime = findViewById(R.id.seekbarplaynhac);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                mediaPlayer.stop();
                listbaihat.clear();
            }
        });
        toolbar.setTitleTextColor(Color.WHITE);

        playListNhacAdapterPager = new PlayListNhacAdapterPager(getSupportFragmentManager());
        playListNhacAdapterPager.addFragment(new Fragment_Play_Nhac());
        playListNhacAdapterPager.addFragment(new Fragment_Dia_Nhac());
        viewPager.setAdapter(playListNhacAdapterPager);

        fragment_dia_nhac = (Fragment_Dia_Nhac) playListNhacAdapterPager.getItem(1);
        if (listbaihat.size() > 0) {
            getSupportActionBar().setTitle(listbaihat.get(0).getTenBaiHat());
            new PlayMp3().execute(listbaihat.get(0).getLinkBaiHat());
            ivplay.setImageResource(R.drawable.iconpause);
        }

        ivrepeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (repeat == false) {
                    if (checkrandom == true) {
                        checkrandom = false;
                        ivrepeat.setImageResource(R.drawable.iconsyned);
                        ivshuffle.setImageResource(R.drawable.iconsuffle);
                    }
                    ivrepeat.setImageResource(R.drawable.iconsyned);
                    repeat = true;
                } else {
                    ivrepeat.setImageResource(R.drawable.iconrepeat);
                    repeat = false;
                }
            }
        });

        ivshuffle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkrandom == false) {
                    if (repeat == true) {
                        repeat = false;
                        ivshuffle.setImageResource(R.drawable.iconshuffled);
                        ivrepeat.setImageResource(R.drawable.iconrepeat);
                    }
                    ivshuffle.setImageResource(R.drawable.iconshuffled);
                    checkrandom = true;
                } else {
                    ivshuffle.setImageResource(R.drawable.iconsuffle);
                    checkrandom = false;
                }
            }
        });

        sktime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(seekBar.getProgress());
            }
        });

        ivnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listbaihat.size() > 0) {
                    if (mediaPlayer.isPlaying() || mediaPlayer != null) {
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }
                    if (position < (listbaihat.size())) {
                        ivplay.setImageResource(R.drawable.iconpause);
                        position++;
                        if (repeat == true) {
                            if (position == 0) {
                                position = listbaihat.size();
                            }
                            position -= 1;
                        }
                        if (checkrandom == true) {
                            Random random = new Random();
                            int index = random.nextInt(listbaihat.size());
                            if (index == position) {
                                position = index - 1;
                            }
                            position = index;
                        }
                        if (position > (listbaihat.size() - 1)) {
                            position = 0;
                        }
                        new PlayMp3().execute(listbaihat.get(position).getLinkBaiHat());
                        fragment_dia_nhac.PlayNhac(listbaihat.get(position).getHinhBaiHat());
                        getSupportActionBar().setTitle(listbaihat.get(position).getTenBaiHat());
//                        UpdateTime();
                    }
                    ivpreview.setClickable(false);
                    ivnext.setClickable(false);
                    Handler handler1 = new Handler();
                    handler1.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            ivpreview.setClickable(true);
                            ivnext.setClickable(true);
                        }
                    }, 5000);
                }
            }
        });

        ivpreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listbaihat.size() > 0) {
                    if (mediaPlayer.isPlaying() || mediaPlayer != null) {
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }
                    if (position < (listbaihat.size())) {
                        ivplay.setImageResource(R.drawable.iconpause);
                        position--;
                        if (position < 0) {
                            position = listbaihat.size() - 1;
                        }
                        if (repeat == true) {
                            if (position == 0) {
                                position = listbaihat.size();
                            }
                            position += 1;
                        }
                        if (checkrandom == true) {
                            Random random = new Random();
                            int index = random.nextInt(listbaihat.size());
                            if (index == position) {
                                position = index - 1;
                            }
                            position = index;
                        }
                        new PlayMp3().execute(listbaihat.get(position).getLinkBaiHat());
                        fragment_dia_nhac.PlayNhac(listbaihat.get(position).getHinhBaiHat());
                        getSupportActionBar().setTitle(listbaihat.get(position).getTenBaiHat());
                        UpdateTime();
                    }
                    ivpreview.setClickable(false);
                    ivnext.setClickable(false);
                    Handler handler1 = new Handler();
                    handler1.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            ivpreview.setClickable(true);
                            ivnext.setClickable(true);
                        }
                    }, 5000);
                }
            }
        });
    }

    class PlayMp3 extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            return strings[0];
        }

        @Override
        protected void onPostExecute(String baihat) {
            super.onPostExecute(baihat);
            try {
                // nghe nhạc dưới hình thức online
                mediaPlayer = new MediaPlayer();
                mediaPlayer.setAudioAttributes(
                        new AudioAttributes
                                .Builder()
                                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                                .build());
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mediaPlayer.stop();
                        mediaPlayer.reset();
                    }
                });
                mediaPlayer.setDataSource(baihat);
                //media muốn phát được phải gọi qua hàm prepare
                mediaPlayer.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
            mediaPlayer.start();
            TimeSong();
            UpdateTime();
        }
    }

    private void TimeSong() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        tvendtimesong.setText(simpleDateFormat.format(mediaPlayer.getDuration()));
        sktime.setMax(mediaPlayer.getDuration());
    }

    private void UpdateTime() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mediaPlayer != null) {
                    sktime.setProgress(mediaPlayer.getCurrentPosition());
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
                    tvstarttimesong.setText(simpleDateFormat.format(mediaPlayer.getCurrentPosition()));
                    handler.postDelayed(this, 300);
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            next = true;
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        }, 300);
        Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (next == true) {
                    {
                        if (listbaihat.size() > 0 || listbaihat != null) {
                            mediaPlayer.stop();
                            mediaPlayer.release();
                            mediaPlayer = null;
                        }
                        if (position < listbaihat.size()) {
                            ivplay.setImageResource(R.drawable.iconpause);
                            position++;
                            if (repeat == true) {
                                if (position == 0) {
                                    position = listbaihat.size();
                                }
                                position -= 1;
                            }
                            if (checkrandom == true) {
                                Random random = new Random();
                                int index = random.nextInt(listbaihat.size());
                                if (index == position) {
                                    position = index - 1;
                                }
                                position = index;
                            }
                            if (position > (listbaihat.size() - 1)) {
                                position = 0;
                            }
                            new PlayMp3().execute(listbaihat.get(position).getLinkBaiHat());
                            fragment_dia_nhac.PlayNhac(listbaihat.get(position).getHinhBaiHat());
                            getSupportActionBar().setTitle(listbaihat.get(position).getTenBaiHat());
                        }
                        ivpreview.setEnabled(false);
                        ivnext.setEnabled(false);
                        Handler handler1 = new Handler();
                        handler1.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                ivpreview.setEnabled(true);
                                ivnext.setEnabled(true);
                            }
                        }, 5000);
                        next = false;
                        handler.removeCallbacks(this);
                    }
                } else {
                    handler1.postDelayed(this, 1000);
                }
            }
        }, 1000);
    }
}
