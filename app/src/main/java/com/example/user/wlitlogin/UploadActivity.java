package com.example.user.wlitlogin;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;


public class UploadActivity extends Activity {
	// LogCat tag
	private static final String TAG = MainActivity.class.getSimpleName();

	private ProgressBar progressBar;
	public File filePath = null;
	private TextView txtPercentage;
	private ImageView imgPreview;
	private VideoView vidPreview;
	private Button btnUpload;
	long totalSize = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_upload);
		txtPercentage = (TextView) findViewById(R.id.txtPercentage);
		btnUpload = (Button) findViewById(R.id.btnUpload);
		progressBar = (ProgressBar) findViewById(R.id.progressBar);
		imgPreview = (ImageView) findViewById(R.id.imgPreview);
		vidPreview = (VideoView) findViewById(R.id.videoPreview);
		imgPreview.setVisibility(View.VISIBLE);

		// Changing action bar background color
//		getActionBar().setBackgroundDrawable(
//				new ColorDrawable(Color.parseColor(getResources().getString(
//						R.color.action_bar))));

		// Receiving the data from previous activity
		Intent i = getIntent();

		// image or video path that is captured in previous activity
		final File filePath = (File)i.getExtras().get("filePath");

		System.out.println(filePath+"k ayo hola");
		// boolean flag to identify the media type, image or video
		boolean isImage = i.getBooleanExtra("isImage", true);

		if (filePath != null) {
			// Displaying the image or video on the screen
			previewMedia(isImage, filePath);
		} else {
			Toast.makeText(getApplicationContext(),
					"Sorry, file path is missing!", Toast.LENGTH_LONG).show();
		}

		btnUpload.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// uploading the file to server
				new UploadFileToServer(filePath).execute();
			}
		});

	}

	/**
	 * Displaying captured image/video on the screen
	 * */
	private void previewMedia(boolean isImage, File filePath) {
		// Checking whether captured media is image or video
		if (isImage) {
			imgPreview.setVisibility(View.VISIBLE);
			// bimatp factory

			BitmapFactory.Options options = new BitmapFactory.Options();

			// down sizing image as it throws OutOfMemory Exception for larger
			// images
			options.inSampleSize = 8;
			System.out.println(this.filePath +"k ahudai cha");

//			File path = android.os.Environment.getExternalStorageDirectory();
			final Bitmap bitmap = BitmapFactory.decodeFile(filePath.getAbsolutePath(), options);

			imgPreview.setImageBitmap(bitmap);
		}
	}

	/**
	 * Uploading the file to server
	 * */
	private class UploadFileToServer extends AsyncTask<Void, Integer, String> {
		File filePaths;
		public UploadFileToServer(File filePath) {

			filePaths = filePath;
		}

		@Override
		protected void onPreExecute() {
			// setting progress bar to zero
			progressBar.setProgress(0);
			super.onPreExecute();
		}

		@Override
		protected void onProgressUpdate(Integer... progress) {
			// Making progress bar visible
			progressBar.setVisibility(View.VISIBLE);

			// updating progress bar value
			progressBar.setProgress(progress[0]);

			// updating percentage value
			txtPercentage.setText(String.valueOf(progress[0]) + "%");
		}

		@Override
		protected String doInBackground(Void... params) {
			return uploadFile();
		}

		@SuppressWarnings("deprecation")
		private String uploadFile() {
			String responseString = null;

			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost(Config.FILE_UPLOAD_URL);

			try {
				AndroidMultiPartEntity entity = new AndroidMultiPartEntity(
						new AndroidMultiPartEntity.ProgressListener() {

							@Override
							public void transferred(long num) {
								publishProgress((int) ((num / (float) totalSize) * 100));
							}
						});

//				File sourceFile = new File(String.valueOf(filePath));

				// Adding file data to http body
				entity.addPart("image", new FileBody(filePaths));

				// Extra parameters if you want to pass to server
				entity.addPart("website",
						new StringBody("www.kapas.wlit.org.np"));
				entity.addPart("email", new StringBody("abc@gmail.com"));

				totalSize = entity.getContentLength();
				httppost.setEntity(entity);

				// Making server call
				HttpResponse response = httpclient.execute(httppost);
				HttpEntity r_entity = response.getEntity();

				int statusCode = response.getStatusLine().getStatusCode();
				if (statusCode == 200) {
					// Server response
					responseString = EntityUtils.toString(r_entity);
				} else {
					responseString = "Error occurred! Http Status Code: "
							+ statusCode;
				}

			} catch (ClientProtocolException e) {
				responseString = e.toString();
			} catch (IOException e) {
				responseString = e.toString();
			}

			return responseString;

		}

		@Override
		protected void onPostExecute(String result) {
			Log.e(TAG, "Response from server: " + result);

			// showing the server response in an alert dialog
			showAlert(result);

			super.onPostExecute(result);
		}

	}

	/**
	 * Method to show alert dialog
	 * */
	private void showAlert(String message) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(message).setTitle("Response from Servers")
				.setCancelable(false)
				.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						// do nothing
					}
				});
		AlertDialog alert = builder.create();
		alert.show();
	}

}