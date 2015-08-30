package me.darklost.driversimulation.utils;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.util.Log;

import java.util.HashMap;


public class MidPlayer {
    private static String Tag = "MidPlayer";
    public MediaPlayer midPlayer;
    private static SoundPool soundPool;
    public Activity activity = null;
    public static int volumeLevel;
    public static int soundState;
    private static AudioManager am;
    private HashMap<Integer, Integer> soundPoolMap;
    private static int streamID;

    public MidPlayer(Activity activity) {
        this.activity = activity;
        am = (AudioManager) activity.getSystemService(Context.AUDIO_SERVICE);
        // maxVol = am.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        soundPool = new SoundPool(5, AudioManager.STREAM_RING, 100);
        soundPoolMap = new HashMap();
//        soundPoolMap.put(R.raw.start_qile, soundPool.load(WaWaSystem.getActivity(), R.raw.start_qile, 1));

    }


    public void playMusic(int soundID) {

        try {

            if(null==midPlayer)
            {
                midPlayer = MediaPlayer.create(activity, soundID);
            }
            if (midPlayer.isPlaying())
            {
                midPlayer.stop();
                midPlayer.release();
                midPlayer = null;
                midPlayer = MediaPlayer.create(activity, soundID);
            }
        } catch (Exception e) {
            e.printStackTrace();
            midPlayer = MediaPlayer.create(activity, soundID);
        }
        try {
            midPlayer.setLooping(false);
            midPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mp.start();
                }
            });
            // Prepare to async playing
            midPlayer.prepareAsync();
            midPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                public void onCompletion(MediaPlayer mp) {
                    MidPlayer.this.midPlayer.release();
                    MidPlayer.this.midPlayer = null;
                }
            });
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

     public void playSound(int soundID, int loop) {

        try {
           midPlayer = MediaPlayer.create(activity, soundID);
           midPlayer.setLooping(false);
           //midPlayer.prepare();
           midPlayer.start();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        midPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener(){
            public void onCompletion(MediaPlayer mp){
               mp.release();
            }
        });
    }
    public void playSound(int soundID) {




        Log.i(Tag, "soundPool=" + soundPool + " soundPoolMap=" + soundPoolMap +" soundID="+soundID+" soundPoolMap.get(soundID)="+soundPoolMap.get(soundID));
        streamID = soundPool.play(soundPoolMap.get(soundID), 1, 1, 1, 0, 1f);



    }


    public void playSound1(int soundID) {
        int soundVolume = 100;

        soundPool.play(soundID, soundVolume, soundVolume, 1, 0, 1f);
    }


    public void freeMidPlayer() {
        try {
            if (midPlayer != null) {
                midPlayer.stop();
                midPlayer.release();
                midPlayer = null;
            }
        }catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public static void setMusicVolume(int musicVolume) {
        float streamVolumeMax = am.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        Log.i(Tag, "max=" + streamVolumeMax + " soundVolume=" + musicVolume);
        int volume = (int) (musicVolume / 100 * streamVolumeMax);

        am.setStreamVolume(AudioManager.STREAM_MUSIC, volume, AudioManager.FLAG_PLAY_SOUND);
    }





    public void stopSound() {
        try {
            if (midPlayer != null) {
                midPlayer.stop();
                midPlayer.release();
            }
        }catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }


}
