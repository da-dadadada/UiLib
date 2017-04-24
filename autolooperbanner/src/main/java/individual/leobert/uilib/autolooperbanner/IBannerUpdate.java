package individual.leobert.uilib.autolooperbanner;

import android.widget.ImageView;


/**
 * <p><b>Package</b> individual.leobert.uilib.autolooperbanner
 * <p><b>Project</b> uilib
 * <p><b>Classname</b> IBannerUpdate
 * <p><b>Description</b>:图片更新行为接口，使用者实现。避免图片库直接依赖
 * <p>
 * Created by leobert on 2017/4/24.
 */
public interface IBannerUpdate {
    void UpdateImage(ImgRes<?> res, ImageView imageView);
}
