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

package individual.leobert.uilib.lrecyclerview.progressindicator.indicator;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.animation.LinearInterpolator;

import java.util.ArrayList;
import java.util.List;


public class BallTrianglePathIndicator extends BaseIndicatorController {

    float[] translateX=new float[3],translateY=new float[3];

    @Override
    public void draw(Canvas canvas, Paint paint) {
        paint.setStrokeWidth(3);
        paint.setStyle(Paint.Style.STROKE);
        for (int i = 0; i < 3; i++) {
            canvas.save();
            canvas.translate(translateX[i], translateY[i]);
            canvas.drawCircle(0, 0, getWidth() / 10, paint);
            canvas.restore();
        }
    }

    @Override
    public List<Animator> createAnimation() {
        List<Animator> animators=new ArrayList<>();
        float startX=getWidth()/5;
        float startY=getWidth()/5;
        for (int i = 0; i < 3; i++) {
            final int index=i;
            ValueAnimator translateXAnim=ValueAnimator.ofFloat(getWidth()/2,getWidth()-startX,startX,getWidth()/2);
            if (i==1){
                translateXAnim=ValueAnimator.ofFloat(getWidth()-startX,startX,getWidth()/2,getWidth()-startX);
            }else if (i==2){
                translateXAnim=ValueAnimator.ofFloat(startX,getWidth()/2,getWidth()-startX,startX);
            }
            ValueAnimator translateYAnim=ValueAnimator.ofFloat(startY,getHeight()-startY,getHeight()-startY,startY);
            if (i==1){
                translateYAnim=ValueAnimator.ofFloat(getHeight()-startY,getHeight()-startY,startY,getHeight()-startY);
            }else if (i==2){
                translateYAnim=ValueAnimator.ofFloat(getHeight()-startY,startY,getHeight()-startY,getHeight()-startY);
            }

            translateXAnim.setDuration(2000);
            translateXAnim.setInterpolator(new LinearInterpolator());
                translateXAnim.setRepeatCount(-1);
            translateXAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    translateX [index]= (float) animation.getAnimatedValue();
                    postInvalidate();
                }
            });
            translateXAnim.start();

            translateYAnim.setDuration(2000);
            translateYAnim.setInterpolator(new LinearInterpolator());
            translateYAnim.setRepeatCount(-1);
            translateYAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    translateY [index]= (float) animation.getAnimatedValue();
                    postInvalidate();
                }
            });
            translateYAnim.start();

            animators.add(translateXAnim);
            animators.add(translateYAnim);
        }
        return animators;
    }


}
