# BaseDialog
### 本框架是仿照AlterDialog的建造者设计模式的写法。比原生的dialog使用起来更灵性，通俗易懂。
#### 这里会比原生的AlterDialog多一个DialogViewHelper作用是设置View的一些属性和状态，下面附上代码
```
package com.example.chao.library;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import java.lang.ref.WeakReference;

/**
 * Created by yang2 on 2017/8/11.
 */

class DialogViewHelper {

    private View mContentView = null;

    private SparseArray<WeakReference<View>> mViews;

    public DialogViewHelper(Context context,int mViewLayoutResId){
        this();
        this.mContentView = LayoutInflater.from(context).inflate(mViewLayoutResId,null);
    }

    public DialogViewHelper(){
        mViews = new SparseArray<>();
    }

    public void setContentView(View contentView){
        this.mContentView = contentView;
    }
    
    public void setText(int viewId,CharSequence charSequence){
        TextView textView = getView(viewId);
        assert textView!=null;
        textView.setText(charSequence);
    }

    public  <T extends  View>T getView(int viewId) {
        WeakReference<View> viewWeakReference = mViews.get(viewId);

        View view  = null;
        if (viewWeakReference!=null){
            view = viewWeakReference.get();
        }else {
           view =  mContentView.findViewById(viewId);
            mViews.put(viewId,new WeakReference<View>(view
            dz));
        }
        return (T) view;
    }

    public void setOnClickListener(int viewId, View.OnClickListener listener){
        View view = getView(viewId);
        if (view!=null){
            view.setOnClickListener(listener);
        }
    }

    public View getContentView() {
        return mContentView;
    }
}

```

# 使用说明 


```
baseDialog = new BaseDialog.Builder(this, R.layout.dialog_item)
                .setText(R.id.tv_title,"标题")
                .setText(R.id.tv_confirm,"确认")
                .setText(R.id.tv_cancle,"取消")
                .setOnclickListener(R.id.tv_confirm, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        baseDialog.dismiss();
                    }
                })
                .setOnclickListener(R.id.tv_confirm, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        baseDialog.dismiss();
                    }
                })
                //点击外部是否销毁
                .setCancelable(false)
                //全屏
                .fullWidth()
                .show();
```

    



