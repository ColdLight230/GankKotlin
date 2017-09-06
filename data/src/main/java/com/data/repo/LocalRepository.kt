package com.data.repo

import android.content.Context
import android.provider.MediaStore
import com.data.bean.openeyes.Video
import io.reactivex.Observable

/**
 * 描    述：
 * 作    者：xul@13322.com
 * 时    间：2017/9/5
 */
object LocalRepository {

    private val MEDIA_COL = arrayOf(MediaStore.Video.Media.DATA,
            MediaStore.Video.Media._ID,
            MediaStore.Video.Media.TITLE,
            MediaStore.Video.Media.MIME_TYPE,
            MediaStore.Video.Media.SIZE,
            MediaStore.Video.Media.DESCRIPTION,
            MediaStore.Video.Media.DATE_TAKEN,
            MediaStore.Video.Media.DURATION,
            MediaStore.Video.Media.MINI_THUMB_MAGIC)


    fun getLocalVideos(context: Context): Observable<List<Video>> {
        return Observable.just(context.applicationContext.contentResolver)
                .flatMap({ contentResolver ->
                    val cursor = contentResolver.query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, MEDIA_COL, null, null,
                            MediaStore.Images.Media.DATE_MODIFIED + " desc")
                    //                                MediaStore.Images.Media.DATE_MODIFIED + " desc" + " LIMIT " + page * IMediaTask.PAGE_LIMIT + " , " + IMediaTask.PAGE_LIMIT)
                    val localVideos = mutableListOf<Video>()
                    cursor.use { cursor ->
                        if (cursor != null && cursor.moveToFirst()) {
                            do {
                                val data = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.DATA))
//                                val id = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media._ID))
                                val title = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.TITLE))
                                val type = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.MIME_TYPE))
                                val size = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.SIZE))
                                val desc = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.DESCRIPTION))
                                val date = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.DATE_TAKEN))
                                val duration = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.DURATION))
                                val imageUrl = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.MINI_THUMB_MAGIC))
                                val video = Video(title, duration.toLong(), type, size.toLong(), data, imageUrl, desc)
                                localVideos.add(video)
                            } while (!cursor.isLast && cursor.moveToNext())
                        }
                    }
                    Observable.just(localVideos)
                })
    }
}