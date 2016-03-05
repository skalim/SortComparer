package saad.sortcomparer;

import android.animation.ObjectAnimator;
import android.os.AsyncTask;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
/**
 * Created by Saad on 23-Jan-16.
 */
public class Animator {
    Animation scaleIn;
    Animation scaleOut;
    Animation opacityOut;

    AnimationSet animSetOut;

    ProgressBar progressBar;

    ObjectAnimator animation;

    public Animator() {
        this.scaleIn = new ScaleAnimation(0, 1f, 0, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        this.scaleOut = new ScaleAnimation(1f, 0, 1f, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        this.opacityOut = new AlphaAnimation(100, 0);
        scaleIn.setDuration(250);
        scaleOut.setDuration(250);
        opacityOut.setDuration(250);

        animSetOut = new AnimationSet(true);
        animSetOut.setFillEnabled(true);
        animSetOut.addAnimation(scaleOut);
        animSetOut.addAnimation(opacityOut);
    }

    public void animateIn(ImageView iv){
        iv.startAnimation(scaleIn);
    }

    public void animateIn(View rl){
        int cx = rl.getWidth() / 2;
        int cy = rl.getHeight() / 2;
        float finalRadius = (float) Math.hypot(cx, cy);
        android.animation.Animator anim = ViewAnimationUtils.createCircularReveal(rl, cx, cy, 0, finalRadius);
        anim.start();
    }

    public void animateOut(ImageView iv){
        iv.startAnimation(animSetOut);
    }

    public void animateBar(ProgressBar pb, int max){
        if(android.os.Build.VERSION.SDK_INT >= 11){
            animation = ObjectAnimator.ofInt(pb, "progress", max);
            animation.setDuration(500); // 0.5 second
            animation.setInterpolator(new DecelerateInterpolator());
            animation.start();
        }else{
            progressBar = pb;
            new AnimateBarTask().execute( max );
        }
    }

    public class AnimateBarTask extends AsyncTask<Integer, ProgressBar, Void>{
        int i;
        @Override
        protected Void doInBackground(Integer... params) {
            for(i = 0; i < params[0]; i++){
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                publishProgress();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(ProgressBar... pb) {
            progressBar.setProgress( i );
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            progressBar.setProgress(i);
        }
    }
}
