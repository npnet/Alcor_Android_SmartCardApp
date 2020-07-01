/*
 * Copyright (C) 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.camera.v2.module;

import android.app.Activity;

/**
 * The controller at app level.
 */
public interface ModuleController {
    /** Preview is fully visible. */
    public static final int VISIBILITY_VISIBLE = 0;
    /** Preview is covered by e.g. the transparent mode drawer. */
    public static final int VISIBILITY_COVERED = 1;
    /** Preview is fully hidden, e.g. by the filmstrip. */
    public static final int VISIBILITY_HIDDEN = 2;

    /**
     * Returns a unique string which identifies this module.
     * This string is used by the SettingsManager to scope settings
     * specific to each module.
     */
    public String getModuleStringIdentifier();

    /********************** Life cycle management **********************/

    /**
     * Initializes the module.
     *
     * @param activity The camera activity.
     * @param isSecureCamera Whether the app is in secure camera mode.
     * @param isCaptureIntent Whether the app is in capture intent mode.
     */
    public void init(Activity activity, boolean isSecureCamera, boolean isCaptureIntent);

    /**
     * Resumes the module. Always call this method whenever it's being put in
     * the foreground.
     */
    public void resume();

    /**
     * Pauses the module. Always call this method whenever it's being put in the
     * background.
     */
    public void pause();

    /**
     * Destroys the module. Always call this method to release the resources used
     * by this module.
     */
    public void destroy();

    /********************** UI / Camera preview **********************/

    /**
     * Called when the preview becomes visible/invisible.
     *
     * @param visible Whether the preview is visible, one of
     *            {@link #VISIBILITY_VISIBLE}, {@link #VISIBILITY_COVERED},
     *            {@link #VISIBILITY_HIDDEN}
     */
    public void onPreviewVisibilityChanged(int visibility);

    /**
     * Called when the framework layout orientation changed.
     *
     * @param isLandscape Whether the new orientation is landscape or portrait.
     */
    public void onLayoutOrientationChanged(boolean isLandscape);

    /**
     * Called when the UI orientation is changed.
     *
     * @param orientation The new orientation, valid values are 0, 90, 180 and
     *                    270.
     */
    public void onOrientationChanged(int orientation);

    /**
     * Called when back key is pressed.
     *
     * @return Whether the back key event is processed.
     */
    public abstract boolean onBackPressed();

    /********************** App-level resources **********************/

    /**
     * Called when user click switch camera icon.
     * Note: this function is called before {@link #onCameraPicked(String)}.
     *
     * @param newCameraId the new camera id
     */
    public void onBeforeCameraPicked(String newCameraId);

    /**
     * Called when user click switch camera icon.
     * @param newCameraId the new camera id
     */
    public void onCameraPicked(String newCameraId);

    public boolean isUsingBottomBar();
}