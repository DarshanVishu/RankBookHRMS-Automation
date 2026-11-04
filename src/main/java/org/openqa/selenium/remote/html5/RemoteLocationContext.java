package org.openqa.selenium.remote.html5;

import org.openqa.selenium.html5.Location;
import org.openqa.selenium.html5.LocationContext;
import org.openqa.selenium.remote.ExecuteMethod;

public class RemoteLocationContext implements LocationContext {
    public RemoteLocationContext(ExecuteMethod executeMethod) {
    }

    @Override
    public Location location() {
        return null;
    }

    @Override
    public void setLocation(Location location) {
    }
}
