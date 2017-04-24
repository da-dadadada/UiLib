package individual.leobert.uilib.autolooperbanner;

/**
 * <p><b>Package</b> individual.leobert.uilib.autolooperbanner
 * <p><b>Project</b> uilib
 * <p><b>Classname</b> ImgRes
 * <p><b>Description</b>: 资源泛型类，可以是本地资源，也可以是url资源
 * <p>
 * Created by leobert on 2017/4/24.
 */
public class ImgRes<T> {
    private T res;

    private int defaultUrlRes;

    private boolean defaultExist = false;

    /*package*/  ImgRes(T res) {
        this.res = res;
    }

    public T getRes() {
        return res;
    }

    /*package*/  void setRes(T res) {
        this.res = res;
    }

    public int getDefaultUrlRes() {
        return defaultUrlRes;
    }

    /*package*/ void setDefaultUrlRes(int defaultUrlRes) {
        this.defaultUrlRes = defaultUrlRes;
        this.defaultExist = true;
    }

    public boolean isDefaultExist() {
        return defaultExist;
    }

}
