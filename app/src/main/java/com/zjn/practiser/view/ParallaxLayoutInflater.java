package com.zjn.practiser.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import com.zjn.practiser.R;
import com.zjn.practiser.ui.ParallaxFragment;

/**
 * Created by Administrator on 2018/2/18.
 */

public class ParallaxLayoutInflater extends LayoutInflater {


    public final Context newContext;
    public final ParallaxFragment parallaxFragment;

    public ParallaxLayoutInflater(LayoutInflater original, Context newContext, ParallaxFragment parallaxFragment) {
        super(original, newContext);
        this.newContext = newContext;
        this.parallaxFragment = parallaxFragment;
        ParallaxFactory parallaxFactory = new ParallaxFactory(this);
        setFactory(parallaxFactory);
    }

    @Override
    public LayoutInflater cloneInContext(Context newContext) {
        return new ParallaxLayoutInflater(this, newContext,parallaxFragment);
    }

    /**
     * 自定义工厂类，视图创建的工厂类
     */
    class ParallaxFactory implements Factory {

        public final LayoutInflater original;

        public ParallaxFactory(LayoutInflater original) {
            this.original = original;
        }

        @Override
        public View onCreateView(String name, Context context, AttributeSet attrs) {
            //实例化里面的view
            View view = null;
            if (view == null) {
                view = createView(name, context, attrs);
            }
            if (view != null) {
                //获取自定义属性,并将自定义标签的值绑定到view上面
                getCustomAttrs(context, attrs, view);
                parallaxFragment.getViews().add(view);
            }
            return view;
        }

        @SuppressLint("ResourceType")
        private void getCustomAttrs(Context context, AttributeSet attrs, View view) {
            // 所有自定义的属性
            int[] attrIds = {
                    R.attr.a_in,
                    R.attr.a_out,
                    R.attr.x_in,
                    R.attr.x_out,
                    R.attr.y_in,
                    R.attr.y_out,

            };
            TypedArray typedArray = context.obtainStyledAttributes(attrs, attrIds);
            if (typedArray != null && typedArray.length() > 0) {
                ParallaxViewTag parallaxViewTag = new ParallaxViewTag();
                parallaxViewTag.alphaIn = typedArray.getFloat(0, 0f); //alpha_in
                parallaxViewTag.alphaOut = typedArray.getFloat(1, 0f);//alpha_out
                parallaxViewTag.xIn = typedArray.getFloat(2, 0f);//xIn
                parallaxViewTag.xOut = typedArray.getFloat(3, 0f);//xOut
                parallaxViewTag.yIn = typedArray.getFloat(4, 0f);//yIn
                parallaxViewTag.yOut = typedArray.getFloat(5, 0f);//yOut
                view.setTag(R.id.parallax_view_tag, parallaxViewTag);
            }
            typedArray.recycle();
        }

        private View createView(String name, String prefixs, Context context, AttributeSet attrs) {
            //通过系统的inflater类创建视图
            try {
                return original.createView(name, null, attrs);

            } catch (ClassNotFoundException e) {
                return null;
            }
        }

        private final String[] prefixs = {"android.widget.", "android.view."};

        private View createView(String name, Context context, AttributeSet attrs) {
            //通过系统的inflater类创建视图
            if (name.contains(".")) {//自定义控件，已经是全类名了
                return this.createView(name, "", context, attrs);

            } else {
                for (String prefix : prefixs) {
                    View view = createView(name, "", context, attrs);
                    if (view != null) {
                        return view;
                    }
                }
            }
            return null;
        }
    }
}
