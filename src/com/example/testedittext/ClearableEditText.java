package com.example.testedittext;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

public class ClearableEditText extends EditText {

	Drawable mDeleteImage = null;

	// public ClearableEditText(Context context) {
	// super(context);
	// }

	public ClearableEditText(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public ClearableEditText(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	private void init(Context context, AttributeSet attrs) {
		TypedArray a = context.obtainStyledAttributes(attrs,
				R.styleable.ClearableEditText);

		mDeleteImage = a
				.getDrawable(R.styleable.ClearableEditText_deleteButtonRes);

		mDeleteImage.setBounds(0, 0, mDeleteImage.getIntrinsicWidth(),
				mDeleteImage.getIntrinsicHeight());
		a.recycle();

		handleDeleteBtn();

		setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				if (getCompoundDrawables()[2] != null) {

					boolean tappedX = event.getX() > (getWidth()
							- getPaddingRight() - mDeleteImage
							.getIntrinsicWidth());
					if (tappedX) {
						if (event.getAction() == MotionEvent.ACTION_UP) {
							setText("");
						}
						return true;
					}
				}
				return false;
			}
		});

		addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				// TODO Auto-generated method stub
				handleDeleteBtn();
			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable arg0) {
				// TODO Auto-generated method stub
				// handleDeleteBtn();
			}
		});

	}

	private void handleDeleteBtn() {
		Drawable d = getText().toString().equals("") ? null : mDeleteImage;
		setCompoundDrawables(getCompoundDrawables()[0],
				getCompoundDrawables()[1], d, getCompoundDrawables()[3]);
	}
}
