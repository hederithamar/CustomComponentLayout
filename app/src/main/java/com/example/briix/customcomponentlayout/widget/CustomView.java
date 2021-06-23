package com.example.briix.customcomponentlayout.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.Guideline;
import android.support.design.widget.TextInputLayout;
import android.text.InputFilter;
import android.text.InputType;
import android.text.method.DigitsKeyListener;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.briix.customcomponentlayout.R;
import com.example.briix.customcomponentlayout.databinding.CustomViewBinding;
import com.example.briix.customcomponentlayout.models.Information;

import java.util.ArrayList;

public class CustomView extends ConstraintLayout {

    CustomViewBinding mBinding;

    public CustomView(Context context){
        super(context);
        init();
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
        brandComponents(attrs);
    }

    private void init() {
        String infService = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(infService);
        mBinding = CustomViewBinding.inflate(layoutInflater, this, true);
    }

    private void brandComponents(AttributeSet attrs){
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.ComponentCustomInput);
        String txt_required = a.getString(R.styleable.ComponentCustomInput_required_text);
        String txt_hint = a.getString(R.styleable.ComponentCustomInput_hint_text);
        String input_type = a.getString(R.styleable.ComponentCustomInput_input_type);
        String ime_options = a.getString(R.styleable.ComponentCustomInput_ime_options);
        int max_length = a.getInt(R.styleable.ComponentCustomInput_max_length, 50);
        Drawable drawable = a.getDrawable(R.styleable.ComponentCustomInput_icon);
        String icon_space = a.getString(R.styleable.ComponentCustomInput_icon_space);
        String digits = null;

        Guideline guideLine = findViewById(R.id.gl_1v);
        ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) guideLine.getLayoutParams();
        if (icon_space == null || icon_space.equals("false")) {
            mBinding.imageIcon.setVisibility(View.GONE);
            params.guidePercent = 0.05f;
            guideLine.setLayoutParams(params);
        } else {
            if (drawable != null)
                mBinding.imageIcon.setImageDrawable(drawable);
            else
                mBinding.imageIcon.setVisibility(View.INVISIBLE);
        }

        mBinding.textInput.setHint(txt_hint);
        mBinding.textRequired.setText(txt_required);

        if (input_type != null) {
            switch (input_type) {
                case "text":
                    mBinding.editCustom.setInputType(InputType.TYPE_CLASS_TEXT);
                    break;
                case "password":
                    mBinding.editCustom.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    break;
                case "number":
                    mBinding.editCustom.setInputType(InputType.TYPE_CLASS_NUMBER);
                    break;
                default:
                    mBinding.editCustom.setInputType(InputType.TYPE_CLASS_TEXT);
                    break;
            }
        }

        setImeOptions(ime_options);

        ArrayList<InputFilter> inputFilters = new ArrayList<>();
        inputFilters.add(new InputFilter.LengthFilter(max_length));

        if (digits != null)
            inputFilters.add(DigitsKeyListener.getInstance(digits));

        InputFilter[] inputArray = inputFilters.toArray(new InputFilter[0]);

        mBinding.editCustom.setFilters(inputArray);

        a.recycle();
    }

    private void setImeOptions(String ime_options) {
        if (ime_options != null) {
            switch (ime_options) {
                case "actionDone":
                    mBinding.editCustom.setImeOptions(EditorInfo.IME_ACTION_DONE);
                    break;
                case "actionGo":
                    mBinding.editCustom.setImeOptions(EditorInfo.IME_ACTION_GO);
                    break;
                default:
                    mBinding.editCustom.setImeOptions(EditorInfo.IME_ACTION_NEXT);
                    break;
            }
        }
    }

    public String getText(){
        return mBinding.editCustom.getText().toString();
    }

    public void setText(String text){
        mBinding.editCustom.setText(text);
    }

    public void setModel(Information information){
        mBinding.setModel(information);
    }

    public EditText getEditText(){
        return mBinding.editCustom;
     }

    public TextView getTextView(){
        return mBinding.textRequired;
    }

    public TextInputLayout getTextInputLayout(){
        return mBinding.textInput;
     }

    public ImageView getImageView(){
        return mBinding.imageIcon;
    }

}
