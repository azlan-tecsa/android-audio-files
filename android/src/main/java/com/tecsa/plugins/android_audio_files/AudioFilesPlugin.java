package com.tecsa.plugins.android_audio_files;

import android.Manifest;
import android.database.Cursor;
import android.provider.MediaStore;
import android.util.Log;

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

    @PluginMethod(returnType = PluginMethod.RETURN_PROMISE)
    public void listAudioFiles(PluginCall call) {
        if(getPermissionState("storage") == PermissionState.GRANTED) {
            JSObject audioList = new JSObject();
            String[] strings = {MediaStore.Audio.Media._ID, MediaStore.Audio.Media.DISPLAY_NAME, MediaStore.Audio.Media.RELATIVE_PATH};
            Cursor externalCursor = getActivity().getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, strings, null, null, null);

            if(externalCursor != null) {
                if(externalCursor.moveToFirst()) {
                    do {
                        int idIndex = externalCursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID);
                        int nameIndex = externalCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME);
                        int pathIndex = externalCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.RELATIVE_PATH);
                        JSObject fileDetails = new JSObject()
                                .put("name", externalCursor.getString(nameIndex))
                                .put("path", externalCursor.getString(pathIndex));
                        audioList.put(externalCursor.getString(idIndex), fileDetails);
                    } while(externalCursor.moveToNext());
                }
            }
            externalCursor.close();

            JSObject ret = new JSObject();
            ret.put("files", audioList);
            call.resolve(ret);
        } else {
            requestPermissionForAlias("storage", call, "storagePermissionCallback");
        }
    }

    @PermissionCallback
    private void storagePermissionCallback(PluginCall call) {}
}
