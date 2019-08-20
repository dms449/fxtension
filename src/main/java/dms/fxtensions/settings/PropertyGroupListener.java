package dms.fxtensions.settings;

/**
 * A listener attached to {@link PropertyGroup}.
 *
 * The method will be called AFTER every property in the PropertyGroup has been updated.
 */
@FunctionalInterface
public interface PropertyGroupListener {
    public void onChanged();
}
