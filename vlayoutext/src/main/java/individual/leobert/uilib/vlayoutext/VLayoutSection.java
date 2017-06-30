package individual.leobert.uilib.vlayoutext;

import individual.leobert.uilib.vlayoutext.core.SectionAdapter;

/**
 * <p><b>Package:</b> individual.leobert.uilib.vlext </p>
 * <p><b>Project:</b> UiLib </p>
 * <p><b>Classname:</b> VLayoutSection </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/5/22.
 */

public abstract class VLayoutSection<SD> {

    public abstract SD getSectionData();

    public abstract SectionAdapter getAdapter();

}
