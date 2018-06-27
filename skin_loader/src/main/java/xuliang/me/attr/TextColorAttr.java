package xuliang.me.attr;

import android.view.View;
import android.widget.TextView;

import xuliang.me.attr.base.SkinAttr;
import xuliang.me.utils.SkinResourcesUtils;


/**
 * Created by _SOLID
 * Date:2016/4/13
 * Time:22:53
 */
public class TextColorAttr extends SkinAttr {

    @Override
    protected void applySkin(View view) {
        if (view instanceof TextView) {
            TextView tv = (TextView) view;
            if (isColor()) {
                tv.setTextColor(SkinResourcesUtils.getColorStateList(attrValueRefId));
            }
        }
    }

    @Override
    protected void applyNightMode(View view) {
        if (view instanceof TextView) {
            TextView tv = (TextView) view;
            if (isColor()) {
                tv.setTextColor(SkinResourcesUtils.getNightColorStateList(attrValueRefId));
            }
        }
    }
}
