package individual.leobert.uilib.vlayoutext.core;

/**
 * <p><b>Package:</b> individual.leobert.uilib.vlayoutext </p>
 * <p><b>Project:</b> UiLib </p>
 * <p><b>Classname:</b> IDataProvider </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/6/29.
 */

public interface IDataProvider<ID> {
    ID getItemDataByPosition(int position);
    int getItemDataCount();
}
