package org.openqa.selenium;

import java.util.Set;

public interface ContextAware {
    WebDriver context(String name);
    Set<String> getContextHandles();
    String getContext();
}
