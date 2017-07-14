package skripsi.rizky.absensi.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;

import skripsi.rizky.absensi.model.Mahasiswa;

/**
 * Created by ASUS on 12/07/2017.
 */

public class PrefUtilMahasiswa {

    public static final String MAHASISWA_SESSION = "mahasiswa_session";

    public static SharedPreferences getSharedPreferenceMahasiswa(Context context){

        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static void putMahasiswa(Context context, String key, Mahasiswa mahasiswa){

        Gson gson = new Gson();
        String json = gson.toJson(mahasiswa);
        putString(context, key, json);
    }

    public static Mahasiswa getMahasiswa(Context context, String key){

        Gson gson = new Gson();
        String json = getString(context, key);
        Mahasiswa mahasiswa = gson.fromJson(json, Mahasiswa.class);
        return mahasiswa;
    }

    public static void putString(Context context, String key, String value){

        getSharedPreferenceMahasiswa(context).edit().putString(key, value).apply();
    }

    public static String getString(Context context, String key){

        return getSharedPreferenceMahasiswa(context).getString(key, null);
    }

    public static void clear(Context context){

        getSharedPreferenceMahasiswa(context).edit().clear().apply();
    }
}
