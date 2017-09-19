package com.chao.basedialog.dialog;

import android.content.Context;
import android.util.SparseArray;
import android.view.View;
import android.widget.TextView;

import java.lang.ref.WeakReference;

/**
 * Created by yang2 on 2017/6/21.
 */

public class DialogViewHelper {

    private Context mContext;
    private View mLayoutView;
    private SparseArray<WeakReference<View>> viewArray = new SparseArray<>();
    public DialogViewHelper(Context mContext, View mLayoutView) {
        this.mContext = mContext;
        this.mLayoutView = mLayoutView;

    }

    public View getContentView() {
        return mLayoutView;
    }

    public void setText(int viewId, CharSequence charSequence) {
        TextView tv = getView(viewId);
        if (charSequence!=null){
            tv.setText(charSequence);
        }
    }

    private <T extends View> T getView(int viewId){
        WeakReference<View> viewWeakReference = viewArray.get(viewId);
        if (viewWeakReference != null){
            return (T) viewWeakReference.get().findViewById(viewId);
        }else {
            viewArray.put(viewId,new WeakReference<View>(mLayoutView.findViewById(viewId)));
            return (T) mLayoutView.findViewById(viewId);
        }
    }

    public void setOnclickListener(int viewId, View.OnClickListener listener) {
        View view = getView(viewId);
        view.setOnClickListener(listener);
    }

}
