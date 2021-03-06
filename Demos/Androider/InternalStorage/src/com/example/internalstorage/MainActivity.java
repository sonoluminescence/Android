package com.example.internalstorage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	EditText edFileName, edContent;
	Button btnSave;
	ListView listSavedFiles;

	String[] SavedFiles;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		edFileName = (EditText) findViewById(R.id.filename);
		edContent = (EditText) findViewById(R.id.content);
		btnSave = (Button) findViewById(R.id.save);
		listSavedFiles = (ListView) findViewById(R.id.list);

		ShowSavedFiles();

		btnSave.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String fileName = edFileName.getText().toString();
				String content = edContent.getText().toString();

				FileOutputStream fos;
				try {
					fos = openFileOutput(fileName, Context.MODE_PRIVATE);
					fos.write(content.getBytes());
					fos.close();

					Toast.makeText(MainActivity.this, fileName + " saved",
							Toast.LENGTH_LONG).show();

				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				ShowSavedFiles();

			}
		});

		listSavedFiles.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				String clickedFile = (String) parent
						.getItemAtPosition(position);
				OpenFileDialog(clickedFile);
			}

		});

	}

	void ShowSavedFiles() {
		SavedFiles = getApplicationContext().fileList();
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, SavedFiles);

		listSavedFiles.setAdapter(adapter);

	}

	void OpenFileDialog(final String file) {

		// Read file in Internal Storage
		FileInputStream fis;
		String content = "";
		try {
			fis = openFileInput(file);
			byte[] input = new byte[fis.available()];
			while (fis.read(input) != -1) {
			}
			content += new String(input);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Create a custom Dialog
		AlertDialog.Builder fileDialog = new AlertDialog.Builder(
				MainActivity.this);
		fileDialog.setTitle(file);

		TextView textContent = new TextView(MainActivity.this);
		textContent.setText(content);
		LayoutParams textViewLayoutParams = new LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		textContent.setLayoutParams(textViewLayoutParams);

		fileDialog.setView(textContent);

		fileDialog.setPositiveButton("OK", null);
		OnClickListener DeleteListener = new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				deleteFile(file);
				Toast.makeText(MainActivity.this,
						file + " deleted", Toast.LENGTH_LONG).show();
				ShowSavedFiles();
			}

		};
		fileDialog.setNeutralButton("DELETE", DeleteListener);
		fileDialog.show();
	}
}
