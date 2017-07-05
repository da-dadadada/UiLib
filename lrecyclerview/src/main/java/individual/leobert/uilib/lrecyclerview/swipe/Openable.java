/*
 * MIT License
 *
 * Copyright (c) 2017 leobert-lan
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package individual.leobert.uilib.lrecyclerview.swipe;


public interface Openable {

    /**
     * The menu is open?
     *
     * @return true, otherwise false.
     */
    boolean isMenuOpen();

    /**
     * The menu is open on the left?
     *
     * @return true, otherwise false.
     */
    boolean isLeftMenuOpen();

    /**
     * The menu is open on the right?
     *
     * @return true, otherwise false.
     */
    boolean isRightMenuOpen();

    /**
     * The menu is completely open?
     *
     * @return true, otherwise false.
     */
    boolean isCompleteOpen();

    /**
     * The menu is completely open on the left?
     *
     * @return true, otherwise false.
     */
    boolean isLeftCompleteOpen();

    /**
     * The menu is completely open on the right?
     *
     * @return true, otherwise false.
     */
    boolean isRightCompleteOpen();

    /**
     * The menu is open?
     *
     * @return true, otherwise false.
     */
    boolean isMenuOpenNotEqual();

    /**
     * The menu is open on the left?
     *
     * @return true, otherwise false.
     */
    boolean isLeftMenuOpenNotEqual();

    /**
     * The menu is open on the right?
     *
     * @return true, otherwise false.
     */
    boolean isRightMenuOpenNotEqual();

    /**
     * Open the current menu.
     */
    void smoothOpenMenu();

    /**
     * Open the menu on left.
     */
    void smoothOpenLeftMenu();

    /**
     * Open the menu on right.
     */
    void smoothOpenRightMenu();

    /**
     * Open the menu on left for the duration.
     *
     * @param duration duration time.
     */
    void smoothOpenLeftMenu(int duration);

    /**
     * Open the menu on right for the duration.
     *
     * @param duration duration time.
     */
    void smoothOpenRightMenu(int duration);

}
