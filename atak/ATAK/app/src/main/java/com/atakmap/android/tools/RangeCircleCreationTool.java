
package com.atakmap.android.tools;

import android.graphics.Color;

import com.atakmap.android.drawing.mapItems.DrawingCircle;
import com.atakmap.android.drawing.tools.CircleCreationTool;
import com.atakmap.android.maps.MapGroup;
import com.atakmap.android.maps.MapView;
import com.atakmap.android.maps.Marker;
import com.atakmap.android.toolbars.RangeCircle;
import com.atakmap.android.util.Rings;
import com.atakmap.annotations.DeprecatedApi;
import com.atakmap.app.R;

public class RangeCircleCreationTool extends CircleCreationTool {

    protected static final String TOOL_IDENTIFIER = "com.atakmap.android.survey.tools.RangeCircleCreationTool";

    public static final String TAG = "RangeCircleCreationTool";

    private static final double STROKE_WEIGHT = 3d;

    public RangeCircleCreationTool(MapView mapView, MapGroup rabGroup) {
        super(mapView, rabGroup, TOOL_IDENTIFIER);
    }

    @Override
    protected DrawingCircle createCircle() {
        return new RangeCircle(_mapView);
    }

    @Override
    protected int getDefaultColor() {
        int color = Color.RED;
        try {
            color = Color.parseColor(_prefs.get("rab_color_pref", "red"));
        } catch (IllegalArgumentException ignore) {
        }
        return color;
    }

    @Override
    protected void addCircle(DrawingCircle circle) {
        circle.setTitle(getDefaultName(_context.getString(
                R.string.rb_circle_prefix)));
        circle.setColor(getDefaultColor());
        circle.setStrokeWeight(STROKE_WEIGHT);
        circle.setMetaString("entry", "user");
        _mapGroup.addItem(circle);
        circle.persist(_mapView.getMapEventDispatcher(), null, getClass());
    }

    /**
     * @deprecated Circles are no longer marker-centric
     * Create a new {@link RangeCircle} instead
     */
    @Deprecated
    @DeprecatedApi(since = "4.1", forRemoval = true, removeAt = "4.4")
    public static Rings getRingsFromMarker(MapView mapView, Marker marker) {
        return null;
    }
}
