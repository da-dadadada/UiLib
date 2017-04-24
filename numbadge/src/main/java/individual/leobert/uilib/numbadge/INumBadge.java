package individual.leobert.uilib.numbadge;

/**
 * <p><b>Package</b> individual.leobert.uilib.numbadge
 * <p><b>Project</b> NumBadge
 * <p><b>Classname</b> INumBudge
 * <p><b>Description</b>: API of NumBadge
 * <p>Created by leobert on 2016/12/12.
 */

public interface INumBadge {
    void clear();

    void updateWithPointMode();

    void updateWithActualMode(int num);

    void updateWithFriendlyMode(int num, int max);

    void setBackgroundShape(Shape shape);

    /**
     * 背景形状
     */
    enum Shape {
        rectAngle, oval
    }
}
