package com.chao.basedialog.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * Created by yang2 on 2017/6/21.
 */

public class BaseDialog extends Dialog {


    private static BaseDialog BaseDialog;
    private AlertController mAlert;
    public BaseDialog(@NonNull Context context) {
        super(context);
    }

    public BaseDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
        mAlert = new AlertController(this,getWindow());
    }

    public static class Builder{


        private AlertController.AlertParams P;

        public Builder(Context context){
           this(context,0);

        }

        public Builder(Context context, int themeResId){
            P = new AlertController.AlertParams(context,themeResId);
        }

        public Builder setText(int ViewId,CharSequence text){
            P.mTextArray.put(ViewId,text);
            return this;
        }

        public Builder setOnclickListener(int viewId, View.OnClickListener listener){
            P.mListenerArray.put(viewId,listener);
            return this;
        }

        public Builder setContentView(int layoutId){
            P.mLayoutView = LayoutInflater.from(P.mContext).inflate(layoutId, null);
            return this;
        }
        public Builder setContentView(View layoutView){
            P.mLayoutView = layoutView;
            return this;
        }
        /**
         * Sets the callback that will be called if the dialog is canceled.
         *
         * <p>Even in a cancelable dialog, the dialog may be dismissed for reasons other than
         * being canceled or one of the supplied choices being selected.
         * If you are interested in listening for all cases where the dialog is dismissed
         * and not just when it is canceled, see
         * {@link #setOnDismissListener(android.content.DialogInterface.OnDismissListener) setOnDismissListener}.</p>
         * @see #setCancelable(boolean)
         * @see #setOnDismissListener(android.content.DialogInterface.OnDismissListener)
         *
         * @return This Builder object to allow for chaining of calls to set methods
         */
        public Builder setOnCancelListener(OnCancelListener onCancelListener) {
            P.mOnCancelListener = onCancelListener;
            return this;
        }

        /**
         * Sets the callback that will be called when the dialog is dismissed for any reason.
         *
         * @return This Builder object to allow for chaining of calls to set methods
         */
        public Builder setOnDismissListener(OnDismissListener onDismissListener) {
            P.mOnDismissListener = onDismissListener;
            return this;
        }

        /**
         * Sets the callback that will be called if a key is dispatched to the dialog.
         *
         * @return This Builder object to allow for chaining of calls to set methods
         */
        public Builder setOnKeyListener(OnKeyListener onKeyListener) {
            P.mOnKeyListener = onKeyListener;
            return this;
        }
        public Builder fullWidth(){
            P.mWidth = ViewGroup.LayoutParams.MATCH_PARENT;
            return this;
        }

        public Builder fromBottom(){
            P.mGravity = Gravity.BOTTOM;
            return this;
        }

        public Builder defauleAnimtion(){
            return this;
        }

        public Builder setAnimtion(int style){
            P.mAnimtion = style;
            return this;
        }

        public Builder setWidthAndHeight(int width, int height){
            P.mWidth = width;
            P.mHeight = height;
            return this;
        }

        public Builder setWidth(int width){
            P.mWidth = width;
            return this;
        }

        public Builder setCancelable(boolean cancelable){
            P.mCancelable  =  cancelable;
            return this;
        }

        private BaseDialog create() throws IllegalAccessException {
            final BaseDialog dialog = new BaseDialog(P.mContext,P.mThemeResId);
            P.apply(dialog.mAlert);
            dialog.setCancelable(P.mCancelable);
            if (P.mCancelable) {
                dialog.setCanceledOnTouchOutside(true);
            }
            dialog.setOnCancelListener(P.mOnCancelListener);
            dialog.setOnDismissListener(P.mOnDismissListener);
            if (P.mOnKeyListener != null) {
                dialog.setOnKeyListener(P.mOnKeyListener);
            }
            return dialog;

        }
        public BaseDialog show()  {
             BaseDialog dialog = null;
            try {
                dialog = create();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            dialog.show();
            return dialog;
        }


    }




}
