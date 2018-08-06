package me.nakker.numberpicker;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.widget.NumberPicker;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author nakker1218
 */
public class AutoScrollNumberPicker extends NumberPicker {
    private static final int MAX_VALUE = 9;

    private final Handler handler = new Handler();

    private Timer timer = new Timer();
    private int number = 1;
    private boolean isProgress = false;

    public AutoScrollNumberPicker(Context context) {
        super(context);
        init();
    }

    public AutoScrollNumberPicker(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AutoScrollNumberPicker(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setMaxValue(MAX_VALUE);
    }

    public void start() {
        if (isProgress) return;

        TimerTask task = new TimerTask() {

            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        setValue(number);
                        if (number > MAX_VALUE) {
                            number = 0;
                        } else {
                            number++;
                        }
                    }
                });
            }
        };

        timer = new Timer();
        timer.scheduleAtFixedRate(task, 0, 100);
        isProgress = true;
    }

    public int stop() {
        timer.cancel();
        isProgress = false;
        return number;
    }
}
