package individual.leobert.uilib.vlayout;

import java.util.ArrayList;
import java.util.List;

/**
 * <p><b>Package:</b> individual.leobert.uilib.vlayout </p>
 * <p><b>Project:</b> UiLib </p>
 * <p><b>Classname:</b> Data1 </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/5/22.
 */

public class Data1 {
    public static List<Data1> genTest(int size, String tag) {
        final ArrayList list = new ArrayList();
        for (int i = 0; i < size; i++) {
            Data1 data = new Data1();
            data.setUser("安倍" + i);
            data.setTitle("奥巴马的狗" + i);
            data.setIntro("item:" + i + " of " + tag);
            list.add(data);
        }
        return list;
    }

    private String user;
    private String title;

    private String intro;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

}
