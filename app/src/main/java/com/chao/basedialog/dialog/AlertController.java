package com.chao.basedialog.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.chao.basedialog.R;


/**
 * Created by yang2 on 2017/6/21.
 */

public class AlertController {
    private BaseDialog mBaseDialog;

    private Window mWindow;

    public AlertController(BaseDialog BaseDialog, Window window) {
       this. mBaseDialog = BaseDialog;
       this. mWindow = window;
    }

    public static class AlertParams{

        public Context mContext;

        public int mThemeResId = R.style.dialog;
        public boolean mCancelable;
        public DialogInterface.OnCancelListener mOnCancelListener;
        public DialogInterface.OnDismissListener mOnDismissListener;
        public DialogInterface.OnKeyListener mOnKeyListener;
        public SparseArray<CharSequence> mTextArray = new SparseArray<>();
        public SparseArray<View.OnClickListener> mListenerArray = new SparseArray<>();
        public View mLayoutView;
        private DialogViewHelper mViewHelper;
        public int mWidth = LinearLayout.LayoutParams.WRAP_CONTENT;;
        public int mAnimtion = 0;
        public int mGravity = Gravity.CENTER;
        public int mHeight = LinearLayout.LayoutParams.WRAP_CONTENT;

        public AlertParams(Context context, int themeResId) {
            this.mContext = context;
            this.mThemeResId = themeResId;

        }

        public void apply(AlertController mAlert) throws IllegalAccessException {

            if (mLayoutView !=null){
                mViewHelper = new DialogViewHelper(mContext,mLayoutView);
            }
            if (mViewHelper == null){
                throw new IllegalAccessException("please setContentView");
            }
            mAlert.mBaseDialog.setContentView(mViewHelper.getContentView());

            int textSize = mTextArray.size();
            int lisSize = mListenerArray.size();
            for (int i = 0 ; i < textSize; i++){
                mViewHelper.setText(mTextArray.keyAt(i),mTextArray.valueAt(i));
            }
            for (int i = 0 ; i < lisSize; i++){
                mViewHelper.setOnclickListener(mListenerArray.keyAt(i),mListenerArray.valueAt(i));
            }

            Window window = mAlert.mWindow;

            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.width = mWidth;
            attributes.height = mHeight;
            window.setAttributes(attributes);
            window.setGravity(mGravity);
        }
    }

}
