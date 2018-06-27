package xuliang.me.attr;

import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.ImageView;

import xuliang.me.attr.base.SkinAttr;
import xuliang.me.utils.SkinResourcesUtils;

/**
 * Created by _SOLID
 * Date:2017/2/15
 * Time:17:39
 * Desc:
 */

public class ImageViewSrcAttr extends SkinAttr {
    @Override
    protected void applySkin(View view) {
        if (view instanceof ImageView) {
            ImageView iv = (ImageView) view;
            if (isDrawable()) {
                iv.setImageDrawable(SkinResourcesUtils.getDrawable(attrValueRefId));
            } else if (isColor()) {
                iv.setImageDrawable(new ColorDrawable(SkinResourcesUtils.getColor(attrValueRefId)));
            }
        }
    }
}
