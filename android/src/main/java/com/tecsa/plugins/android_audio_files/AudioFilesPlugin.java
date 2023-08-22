package com.tecsa.plugins.android_audio_files;

import android.Manifest;
import android.database.Cursor;
import android.provider.MediaStore;

import com.getcapacitor.JSObject;
import com.getcapacitor.PermissionState;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;
import com.getcapacitor.annotation.Permission;
import com.getcapacitor.annotation.PermissionCallback;

import java.util.ArrayList;

@CapacitorPlugin(
    name = "AudioFiles",
    permissions = {
        @Permission(
            alias = "storage",
            strings = {
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            }
        )
    }
)
public class AudioFilesPlugin extends Plugin {

    @PluginMethod
    public void listAudioFiles(PluginCall call) {
        if(getPermissionState("storage") == PermissionState.GRANTED) {
            ArrayList<String> audioList = new ArrayList<>();
            String[] strings = {MediaStore.Audio.Media._ID, MediaStore.Audio.Media.DISPLAY_NAME};
            Cursor cursor = getActivity().getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, strings, null, null, null);

            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {
                        int audioIndex = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME);
                        audioList.add(cursor.getString(audioIndex));
                    } while (cursor.moveToNext());
                }
            }
            cursor.close();

            JSObject ret = new JSObject();
            ret.put("files", audioList.toArray());
            call.resolve(ret);
        }else {
            requestPermissionForAlias("storage", call, "storagePermissionCallback");
        }
    }

    @PermissionCallback
    private void storagePermissionCallback(PluginCall call) {}
}
