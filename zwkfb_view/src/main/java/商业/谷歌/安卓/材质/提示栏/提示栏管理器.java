package 商业.谷歌.安卓.材质.提示栏;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.snackbar.Snackbar;

import java.lang.ref.WeakReference;

/** Manages {@link Snackbar}s. */
class 提示栏管理器 {

    static final int MSG_TIMEOUT = 0;

    private static final int SHORT_DURATION_MS = 1500;
    private static final int LONG_DURATION_MS = 2750;

    private static 提示栏管理器 snackbarManager;

    static 提示栏管理器 getInstance() {
        if (snackbarManager == null) {
            snackbarManager = new 提示栏管理器();
        }
        return snackbarManager;
    }

    @NonNull
    private final Object lock;
    @NonNull private final Handler handler;

    @Nullable
    private 提示栏管理器.SnackbarRecord currentSnackbar;
    @Nullable private 提示栏管理器.SnackbarRecord nextSnackbar;

    private 提示栏管理器() {
        lock = new Object();
        handler =
                new Handler(
                        Looper.getMainLooper(),
                        new Handler.Callback() {
                            @Override
                            public boolean handleMessage(@NonNull Message message) {
                                switch (message.what) {
                                    case MSG_TIMEOUT:
                                        handleTimeout((提示栏管理器.SnackbarRecord) message.obj);
                                        return true;
                                    default:
                                        return false;
                                }
                            }
                        });
    }

    interface Callback {
        void show();

        void dismiss(int event);
    }

    public void show(int duration, 提示栏管理器.Callback callback) {
        synchronized (lock) {
            if (isCurrentSnackbarLocked(callback)) {
                // Means that the callback is already in the queue. We'll just update the duration
                currentSnackbar.duration = duration;

                // If this is the Snackbar currently being shown, call re-schedule it's
                // timeout
                handler.removeCallbacksAndMessages(currentSnackbar);
                scheduleTimeoutLocked(currentSnackbar);
                return;
            } else if (isNextSnackbarLocked(callback)) {
                // We'll just update the duration
                nextSnackbar.duration = duration;
            } else {
                // Else, we need to create a new record and queue it
                nextSnackbar = new 提示栏管理器.SnackbarRecord(duration, callback);
            }

            if (currentSnackbar != null
                    && cancelSnackbarLocked(currentSnackbar, Snackbar.Callback.DISMISS_EVENT_CONSECUTIVE)) {
                // If we currently have a Snackbar, try and cancel it and wait in line
                return;
            } else {
                // Clear out the current snackbar
                currentSnackbar = null;
                // Otherwise, just show it now
                showNextSnackbarLocked();
            }
        }
    }

    public void dismiss(提示栏管理器.Callback callback, int event) {
        synchronized (lock) {
            if (isCurrentSnackbarLocked(callback)) {
                cancelSnackbarLocked(currentSnackbar, event);
            } else if (isNextSnackbarLocked(callback)) {
                cancelSnackbarLocked(nextSnackbar, event);
            }
        }
    }

    /**
     * Should be called when a Snackbar is no longer displayed. This is after any exit animation has
     * finished.
     */
    public void onDismissed(提示栏管理器.Callback callback) {
        synchronized (lock) {
            if (isCurrentSnackbarLocked(callback)) {
                // If the callback is from a Snackbar currently show, remove it and show a new one
                currentSnackbar = null;
                if (nextSnackbar != null) {
                    showNextSnackbarLocked();
                }
            }
        }
    }

    /**
     * Should be called when a Snackbar is being shown. This is after any entrance animation has
     * finished.
     */
    public void onShown(提示栏管理器.Callback callback) {
        synchronized (lock) {
            if (isCurrentSnackbarLocked(callback)) {
                scheduleTimeoutLocked(currentSnackbar);
            }
        }
    }

    public void pauseTimeout(提示栏管理器.Callback callback) {
        synchronized (lock) {
            if (isCurrentSnackbarLocked(callback) && !currentSnackbar.paused) {
                currentSnackbar.paused = true;
                handler.removeCallbacksAndMessages(currentSnackbar);
            }
        }
    }

    public void restoreTimeoutIfPaused(提示栏管理器.Callback callback) {
        synchronized (lock) {
            if (isCurrentSnackbarLocked(callback) && currentSnackbar.paused) {
                currentSnackbar.paused = false;
                scheduleTimeoutLocked(currentSnackbar);
            }
        }
    }

    public boolean isCurrent(提示栏管理器.Callback callback) {
        synchronized (lock) {
            return isCurrentSnackbarLocked(callback);
        }
    }

    public boolean isCurrentOrNext(提示栏管理器.Callback callback) {
        synchronized (lock) {
            return isCurrentSnackbarLocked(callback) || isNextSnackbarLocked(callback);
        }
    }

    private static class SnackbarRecord {
        @NonNull final WeakReference<提示栏管理器.Callback> callback;
        int duration;
        boolean paused;

        SnackbarRecord(int duration, 提示栏管理器.Callback callback) {
            this.callback = new WeakReference<>(callback);
            this.duration = duration;
        }

        boolean isSnackbar(@Nullable 提示栏管理器.Callback callback) {
            return callback != null && this.callback.get() == callback;
        }
    }

    private void showNextSnackbarLocked() {
        if (nextSnackbar != null) {
            currentSnackbar = nextSnackbar;
            nextSnackbar = null;

            final 提示栏管理器.Callback callback = currentSnackbar.callback.get();
            if (callback != null) {
                callback.show();
            } else {
                // The callback doesn't exist any more, clear out the Snackbar
                currentSnackbar = null;
            }
        }
    }

    private boolean cancelSnackbarLocked(@NonNull 提示栏管理器.SnackbarRecord record, int event) {
        final 提示栏管理器.Callback callback = record.callback.get();
        if (callback != null) {
            // Make sure we remove any timeouts for the SnackbarRecord
            handler.removeCallbacksAndMessages(record);
            callback.dismiss(event);
            return true;
        }
        return false;
    }

    private boolean isCurrentSnackbarLocked(提示栏管理器.Callback callback) {
        return currentSnackbar != null && currentSnackbar.isSnackbar(callback);
    }

    private boolean isNextSnackbarLocked(提示栏管理器.Callback callback) {
        return nextSnackbar != null && nextSnackbar.isSnackbar(callback);
    }

    private void scheduleTimeoutLocked(@NonNull 提示栏管理器.SnackbarRecord r) {
        if (r.duration == Snackbar.LENGTH_INDEFINITE) {
            // If we're set to indefinite, we don't want to set a timeout
            return;
        }

        int durationMs = LONG_DURATION_MS;
        if (r.duration > 0) {
            durationMs = r.duration;
        } else if (r.duration == Snackbar.LENGTH_SHORT) {
            durationMs = SHORT_DURATION_MS;
        }
        handler.removeCallbacksAndMessages(r);
        handler.sendMessageDelayed(Message.obtain(handler, MSG_TIMEOUT, r), durationMs);
    }

    void handleTimeout(@NonNull 提示栏管理器.SnackbarRecord record) {
        synchronized (lock) {
            if (currentSnackbar == record || nextSnackbar == record) {
                cancelSnackbarLocked(record, Snackbar.Callback.DISMISS_EVENT_TIMEOUT);
            }
        }
    }
}
