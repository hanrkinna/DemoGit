package com.example.demo;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class RecordActivity extends Activity implements OnClickListener {

        private static final int RESULT_CAPTURE_IMAGE = 1;// 鐓х浉鐨剅equestCode
        private static final int REQUEST_CODE_TAKE_VIDEO = 2;// 鎽勫儚鐨勭収鐩哥殑requestCode
        private static final int RESULT_CAPTURE_RECORDER_SOUND = 3;// 褰曢煶鐨剅equestCode
       
        private String strImgPath = "";// 鐓х墖鏂囦欢缁濆璺緞
        private String strVideoPath = "";// 瑙嗛鏂囦欢鐨勭粷瀵硅矾寰�
        private String strRecorderPath = "";// 褰曢煶鏂囦欢鐨勭粷瀵硅矾寰�

        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
               
        }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
                super.onActivityResult(requestCode, resultCode, data);
                switch (requestCode) {
                case RESULT_CAPTURE_IMAGE://鎷嶇収
                        if (resultCode == RESULT_OK) {
                                Toast.makeText(this, strImgPath, Toast.LENGTH_SHORT).show();
                        }
                        break;
                case REQUEST_CODE_TAKE_VIDEO://鎷嶆憚瑙嗛
                        if (resultCode == RESULT_OK) {
                                Uri uriVideo = data.getData();
                                Cursor cursor=this.getContentResolver().query(uriVideo, null, null, null, null);
                                if (cursor.moveToNext()) {
                                        /** _data锛氭枃浠剁殑缁濆璺緞 锛宊display_name锛氭枃浠跺悕 */
                                        strVideoPath = cursor.getString(cursor.getColumnIndex("_data"));
                                        Toast.makeText(this, strVideoPath, Toast.LENGTH_SHORT).show();
                                }
                        }
                        break;
                case RESULT_CAPTURE_RECORDER_SOUND://褰曢煶
                        if (resultCode == RESULT_OK) {
                                Uri uriRecorder = data.getData();
                                Cursor cursor=this.getContentResolver().query(uriRecorder, null, null, null, null);
                                if (cursor.moveToNext()) {
                                        /** _data锛氭枃浠剁殑缁濆璺緞 锛宊display_name锛氭枃浠跺悕 */
                                        strRecorderPath = cursor.getString(cursor.getColumnIndex("_data"));
                                        Toast.makeText(this, strRecorderPath, Toast.LENGTH_SHORT).show();
                                }
                        } 
                        break;
                }
        }
       
       

        /**
         * 鐓х浉鍔熻兘
         */
        private void cameraMethod() {
                Intent imageCaptureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                strImgPath = Environment.getExternalStorageDirectory().toString() + "/CONSDCGMPIC/";//瀛樻斁鐓х墖鐨勬枃浠跺す
                String fileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".jpg";//鐓х墖鍛藉悕
                File out = new File(strImgPath);
                if (!out.exists()) {
                        out.mkdirs();
                }
                out = new File(strImgPath, fileName);
                strImgPath = strImgPath + fileName;//璇ョ収鐗囩殑缁濆璺緞
                Uri uri = Uri.fromFile(out);
                imageCaptureIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                imageCaptureIntent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
                startActivityForResult(imageCaptureIntent, RESULT_CAPTURE_IMAGE);

        }

        /**
         * 鎷嶆憚瑙嗛
         */
        private void videoMethod() {
                Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 0);
                startActivityForResult(intent, REQUEST_CODE_TAKE_VIDEO);
        }

        /**
         * 褰曢煶鍔熻兘
         */
        private void soundRecorderMethod() {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("audio/amr");
                startActivityForResult(intent, RESULT_CAPTURE_RECORDER_SOUND);
        }

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
		}


}