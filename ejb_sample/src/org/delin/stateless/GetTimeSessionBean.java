package org.delin.stateless;

import javax.ejb.Stateless;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Stateless
public class GetTimeSessionBean implements GetTimeSessionBeanRemote {
    @Override
    public String getTime() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now());
    }
}
