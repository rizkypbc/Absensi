package skripsi.rizky.absensi.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;

import skripsi.rizky.absensi.model.Dosen;

/**
 * Created by ASUS on 15/07/2017.
 */

public class PrefUtilDosen {

    public static final String DOSEN_SESSION = "dosen_session";

    public static SharedPreferences getSharedPreferenceDosen(Context context){

        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static void putDosen(Context context, String key, Dosen dosen){

        Gson gson = new Gson();
        String json = gson.toJson(dosen);
        putString(context, key, json);
    }

    public static Dosen getDosen(Context context, String key){

        Gson gson = new Gson();
        String json = getString(context, key);
        Dosen dosen = gson.fromJson(json, Dosen.class);
        return dosen;
    }

    public static void putString(Context context, String key, String value){

        getSharedPreferenceDosen(context).edit().putString(key, value).apply();
    }

    public static String getString(Context context, String key){

        return getSharedPreferenceDosen(context).getString(key, null);
    }

    public static void clear(Context context){

        getSharedPreferenceDosen(context).edit().clear().apply();
    }
}
