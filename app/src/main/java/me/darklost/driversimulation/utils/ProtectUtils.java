package me.darklost.driversimulation.utils;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by dengke on 15/9/1.
 */
public class ProtectUtils {

    public static final String getOblyDevicesID(Context mContext) {

        String m_szImei="";
        try {
            TelephonyManager TelephonyMgr =null;
            TelephonyMgr = (TelephonyManager) mContext.getSystemService(Activity.TELEPHONY_SERVICE);
            m_szImei = TelephonyMgr.getDeviceId();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        String m_szDevIDShort=null;
        try {
             m_szDevIDShort = "35" + //we make this look like a valid IMEI

                    Build.BOARD.length() % 10 +
                    Build.BRAND.length() % 10 +
                    Build.CPU_ABI.length() % 10 +
                    Build.DEVICE.length() % 10 +
                    Build.DISPLAY.length() % 10 +
                    Build.HOST.length() % 10 +
                    Build.ID.length() % 10 +
                    Build.MANUFACTURER.length() % 10 +
                    Build.MODEL.length() % 10 +
                    Build.PRODUCT.length() % 10 +
                    Build.TAGS.length() % 10 +
                    Build.TYPE.length() % 10 +
                    Build.USER.length() % 10; //13 digits
        }catch(Exception ex){
            ex.printStackTrace();
        }

        String m_szAndroidID="";
        try {
            m_szAndroidID = "";
            Settings.Secure.getString(mContext.getContentResolver(), Settings.Secure.ANDROID_ID);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        String m_szWLANMAC="";
        try {
        WifiManager wm = (WifiManager) mContext.getSystemService(Context.WIFI_SERVICE);
         m_szWLANMAC = wm.getConnectionInfo().getMacAddress();
    }catch(Exception ex){
        ex.printStackTrace();
    }
        String m_szBTMAC=null;
        try {
        BluetoothAdapter m_BluetoothAdapter = null; // Local Bluetooth adapter
        m_BluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        m_szBTMAC = m_BluetoothAdapter.getAddress();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        String m_szLongID = m_szImei + m_szDevIDShort
                + m_szAndroidID + m_szWLANMAC + m_szBTMAC;
// compute md5
        MessageDigest m = null;
        try {
            m = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        m.update(m_szLongID.getBytes(), 0, m_szLongID.length());
// get md5 bytes
        byte p_md5Data[] = m.digest();
// create a hex string
        String m_szUniqueID = new String();
        for (int i = 0; i < p_md5Data.length; i++) {
            int b = (0xFF & p_md5Data[i]);
// if it is a single digit, make sure it have 0 in front (proper padding)
            if (b <= 0xF)
                m_szUniqueID += "0";
// add number to string
            m_szUniqueID += Integer.toHexString(b);
        }   // hex string to uppercase
        m_szUniqueID = m_szUniqueID.toUpperCase();


        return m_szUniqueID;
    }

}
