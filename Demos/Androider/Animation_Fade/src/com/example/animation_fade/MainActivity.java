package com.example.animation_fade;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button buttonFadeIn = (Button) findViewById(R.id.fadein);
		Button buttonFadeOut = (Button) findViewById(R.id.fadeout);
		final ImageView image = (ImageView) findViewById(R.id.image);

		final Animation animationFadeIn = AnimationUtils.loadAnimation(this,
				R.anim.fadein);
		buttonFadeIn.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				image.startAnimation(animationFadeIn);
			}
		});

		final Animation animationFadeOut = AnimationUtils.loadAnimation(this,
				R.anim.fadeout);
		buttonFadeOut.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				image.startAnimation(animationFadeOut);
			}
		});
	}

}